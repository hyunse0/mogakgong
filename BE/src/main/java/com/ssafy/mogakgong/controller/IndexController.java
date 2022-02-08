package com.ssafy.mogakgong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping({"/"})
    public @ResponseBody String index() {
        return "인덱스 페이지 입니다.";
    }

    @GetMapping({"/community/check"})
    public @ResponseBody String community() { return "임시 커뮤니티"; }

}
