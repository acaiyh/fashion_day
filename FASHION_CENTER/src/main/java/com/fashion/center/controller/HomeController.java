package com.fashion.center.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomeController {

    @GetMapping("rList")
    @PreAuthorize("hasAnyAuthority('P1')")
    public String home(){
        return "今日时尚：时尚之都，时尚之装，时尚之车，时尚之事，时尚之状";
    }

}
