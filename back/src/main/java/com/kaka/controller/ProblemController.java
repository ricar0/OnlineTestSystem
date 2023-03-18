package com.kaka.controller;

import com.kaka.entity.Problem;
import com.kaka.entity.ProblemFilter;
import com.kaka.mapper.ProblemMapper;
import com.kaka.service.ProblemService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;
    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    ResponseResult getAll() {
        return new ResponseResult(200, "获取成功!", problemService.getAll());
    }

    @RequestMapping(value="/getProblemByFilter", method = RequestMethod.POST)
    ResponseResult getProblemByFilter(@RequestBody ProblemFilter problemFilter) {
<<<<<<< HEAD

        return new ResponseResult(200, "获取成功!", problemService.getProblemByFilter(problemFilter));
=======
        List<String> source = problemFilter.getSource();
        List<String> difficulty = problemFilter.getDifficulty();
        List<Problem> ans = new ArrayList<>();
        if ((int)difficulty.size() != 0 && (int)source.size() != 0) {
            for (int i = 0; i < source.size(); i++) {
                for (int j = 0; j < difficulty.size(); j++) {
                    List<Problem> tmp = problemService.getProblemByFilter1(source.get(i), difficulty.get(j));
                    ans.addAll(tmp);
                }
            }
        } else if ((int)difficulty.size() != 0) {
            for (int i = 0; i < difficulty.size(); i++) {
                List<Problem> tmp = problemService.getProblemByFilter2(difficulty.get(i));
                ans.addAll(tmp);
            }
        } else if ((int)source.size() != 0) {
            for (int i = 0; i < source.size(); i++) {
                List<Problem> tmp = problemService.getProblemByFilter3(source.get(i));
                ans.addAll(tmp);
            }
        } else {
            ans = problemService.getAll();
        }
        return new ResponseResult(200, "获取成功!", ans);
>>>>>>> d05ed3986ccc16b970daa509d36c355f343d6d85
    }
}
