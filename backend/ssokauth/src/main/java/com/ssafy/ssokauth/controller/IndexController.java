package com.ssafy.ssokauth.controller;

import com.ssafy.ssokauth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            System.out.println("로그인");
            System.out.println(user.getName() + " " + user.getEmail());
            model.addAttribute("userName", user.getName());
        }else{
            System.out.println("로그아웃");
        }
        return "index";
    }
}