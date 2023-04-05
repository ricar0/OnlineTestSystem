package com.kaka.controller;

import com.kaka.entity.Problem;
import com.kaka.entity.WrongBook;
import com.kaka.entity.WrongProblem;
import com.kaka.entity.WrongProblemFilter;
import com.kaka.service.ProblemService;
import com.kaka.service.WrongBookService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/wrongbook")
public class WrongBookController {

    @Autowired
    private WrongBookService wrongBookService;

    @Autowired
    private ProblemService problemService;

    @RequestMapping(value="/addProblemToWrongBook", method = RequestMethod.POST)
    ResponseResult addProblemToWrongBook(@RequestBody WrongBook wrongBook) {
        WrongBook tmp = wrongBookService.findWrongProblem(wrongBook);
        if (!Objects.isNull(tmp)) {
            return new ResponseResult(400, "已近存在");
        } else {
            wrongBookService.addProblemToWrongBook(wrongBook);
            return new ResponseResult(200, "添加成功!");
        }
    }

    @RequestMapping(value="getWrongProblemByUserId", method = RequestMethod.POST)
    ResponseResult getWrongProblemByUserId(@RequestBody WrongProblemFilter wrongProblemFilter) {
        List<WrongBook> wrongBookList = wrongBookService.getWrongBookByUserId(wrongProblemFilter.getUser_id());
        List<WrongProblem> wrongProblemList = new ArrayList<>();
        for (int i = wrongProblemFilter.getStart(); i < Math.min(wrongBookList.size(),wrongProblemFilter.getStart() + wrongProblemFilter.getPageSize()); i++) {
            Problem problem = problemService.getProblemById(wrongBookList.get(i).getProblem_id());
            WrongProblem wrongProblem = new WrongProblem();
            wrongProblem.setProblem(problem);
            wrongProblem.setLast_submit(wrongBookList.get(i).getLast_submit());
            wrongProblemList.add(wrongProblem);
        }
        return new ResponseResult(200, "获取成功!", wrongProblemList);
    }

    @RequestMapping(value="deleteWrongProblem", method = RequestMethod.POST)
    ResponseResult deleteWrongProblem(@RequestBody WrongBook wrongBook) {
        wrongBookService.deleteWrongProblem(wrongBook);
        return new ResponseResult(200, "删除成功!");
    }

    @RequestMapping(value="getWrongProblemNumber", method = RequestMethod.POST)
    ResponseResult getWrongProblemNumber(@RequestBody WrongProblemFilter wrongProblemFilter) {
        return new ResponseResult(200, "获取成功!", wrongBookService.getWrongProblemNumber(wrongProblemFilter.getUser_id()));
    }
}
