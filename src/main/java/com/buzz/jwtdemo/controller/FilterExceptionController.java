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
	
	@GetMapping(value = "/accessdenied")
    public HashMap<String,Object> accessDenied() {
        HashMap<String,Object> result = new HashMap<String,Object>();
        
        result.put("status_code", -1);
        result.put("error_msg","보유권한으로는 접근이 불가능 합니다");        
        return result;
    }
}
