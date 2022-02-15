package com.ssafy.mogakgong.controller;

import com.ssafy.mogakgong.domain.Category;
import com.ssafy.mogakgong.service.CategoryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryServiceImpl;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @GetMapping
    @ApiOperation(value = "전체 카테고리 정보 갖고 오기", notes = "전체 카테고리 정보를 갖고 온다.", response = Map.class)
    public ResponseEntity<Map<String, Object>> getCategories(){

        Map<String, Object> resultMap = new HashMap<>();
        List<Category> categories = categoryServiceImpl.getCategoryList();

        resultMap.put("info", categories);
        resultMap.put("message", SUCCESS);

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }
}
