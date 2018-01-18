package com.shinn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "index";
	}
//	@RequestMapping(value = "/about", method = RequestMethod.GET)
//    public String about(ModelMap model) {
//        return "content/about";
//    }
//	@RequestMapping(value = "/faq", method = RequestMethod.GET)
//    public String faq(ModelMap model) {
//        return "content/faq";
//    }
//	@RequestMapping(value = "/blog", method = RequestMethod.GET)
//    public String blog(ModelMap model) {
//        return "content/blog";
//    }

}
