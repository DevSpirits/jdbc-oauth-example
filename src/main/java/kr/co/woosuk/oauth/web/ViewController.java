package kr.co.woosuk.oauth.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String mainPage(Model model) {
		
		return "login/login";
	}
	
}
