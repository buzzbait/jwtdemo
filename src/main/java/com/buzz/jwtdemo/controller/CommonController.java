package com.buzz.jwtdemo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buzz.jwtdemo.service.CommonService;

@RestController
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	private CommonService _commonService;
	
	@GetMapping({"/v1/codelist", "/v1.5/codelist"})
    public ResponseEntity<HashMap<String,Object>> userInfo(@RequestParam(name="codekey") String codeKey) {
    	
		HashMap<String,Object> result =  _commonService.getCodeList(codeKey);		
    	return new ResponseEntity<HashMap<String,Object> >(result,HttpStatus.OK);        
    }
}
