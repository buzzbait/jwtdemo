package com.buzz.jwtdemo.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class FilterExceptionController {

	@GetMapping(value = "/entrypoint")
    public HashMap<String,Object> entrypointException() {
        HashMap<String,Object> result = new HashMap<String,Object>();
        
        result.put("status_code", -1);
        result.put("error_msg","허용되지 않은 URL 입니다");        
        return result;
    }
}
