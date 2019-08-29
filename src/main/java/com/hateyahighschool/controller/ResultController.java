package com.hateyahighschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by A.A.MAMUN on 8/20/2019.
 */
@Controller
public class ResultController {

    @RequestMapping("/result")
    private String initResult()
    {
        return "result";
    }

}
