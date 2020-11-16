package com.goodbookclub.bookclub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {
	

	@RequestMapping(value="/access_denied", method = RequestMethod.GET, produces = "text/html")
    public String notAuth(){
        return "access_denied";
    }
	@RequestMapping(value="/login", method = RequestMethod.GET, produces = "text/html")
	public String loginForm(){
        return "login";
    }
	
}
