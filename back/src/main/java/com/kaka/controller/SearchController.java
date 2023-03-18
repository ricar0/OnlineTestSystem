package com.kaka.controller;

import com.kaka.entity.Search;
import com.kaka.service.SearchService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    SearchService searchService;

    @RequestMapping(value="/searchMyExamByKey", method = RequestMethod.POST)
    ResponseResult searchMyExamByKey(@RequestBody Search search) {
        return new ResponseResult(200, "查询成功!", searchService.searchMyExamByKey(search));
    }

}
