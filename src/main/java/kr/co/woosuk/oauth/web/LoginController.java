package kr.co.woosuk.oauth.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping(value="/oauth_login", method=RequestMethod.GET)
	public String loginPage(Model model) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("index");
		
		return "/login/index";
	}
}
