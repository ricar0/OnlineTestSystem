package com.kaka.service;

import com.kaka.entity.LoginUser;
import com.kaka.entity.User;
import com.kaka.utils.JwtUtil;
import com.kaka.utils.RedisCache;
import com.kaka.utils.ResponseResult;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.kaka.utils.JwtUtil.parseJWT;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出提示
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("登录失败");
        }
        //如果认证通过，使用userid生成jwt
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        // 把用户信息写入redis中
        redisCache.setCacheObject("login:"+userid, loginUser);
        return new ResponseResult(200, "登录成功", map);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(200, "注销成功!");
    }

    @Override
    public ResponseResult getLoginInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        //解析token
        String userid;
        try {
            Claims claims = parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("tolen非法");
        }
        // 从redis中获取用户信息
        String redisKey = "login:" + userid;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }
        return new ResponseResult(200, "验证成功！", loginUser);
    }
}
