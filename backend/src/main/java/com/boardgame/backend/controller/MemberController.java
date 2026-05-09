package com.boardgame.backend.controller;

import com.boardgame.backend.functions.MemberPageFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberPageFunction memberPageFunction;

    @GetMapping("/{username}")
    public Map<String, Object> getMemberPage(@PathVariable String username) {

        return memberPageFunction.getMemberPageData(username);

    }

}