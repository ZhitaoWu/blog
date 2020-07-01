package com.wzt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Create By coder_tao on 2020/4/2 0002
 */
@Controller
public class AboutShowController {

    @GetMapping("/about")
    protected String about() {

        return "about";
    }
}
