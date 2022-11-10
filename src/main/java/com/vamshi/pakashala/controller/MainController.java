package com.vamshi.pakashala.controller;
 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vamshi.pakashala.dataconnection.GetDataUser;
import com.vamshi.pakashala.entity.Login;

@Controller
@SessionAttributes("name")
public class MainController {
	@RequestMapping(value="/home", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model,HttpServletRequest request){
		HttpSession session = request.getSession(false);
		
		if(session==null){ 
			model.addAttribute("message", "failed");
			
			System.out.println("inside failed");
			return "index";
		}
		else {
			session = request.getSession(true);
			model.addAttribute("message", "user");
			System.out.println("inside user");
		}
		session.invalidate();
		
		System.out.println("inside home");
        return "index";
    }
	@RequestMapping(value="/about", method = RequestMethod.GET)
    public String showAboutPage(ModelMap model){
        return "AboutUs";
    }
	@RequestMapping(value="/plans", method = RequestMethod.GET)
    public String showPlansPage(ModelMap model){
        return "plans";
    }
	@RequestMapping(value="/loginreg", method = RequestMethod.GET)
    public String loginRegister(ModelMap model){
        return "login";
    }
	@RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginsubmit(@ModelAttribute("user")Login uld,ModelMap model){
		
		String uname=uld.getUname();
		String psswd=uld.getPassword();
		Login l=new Login();
		GetDataUser g=new GetDataUser();
		l.setUname(g.getLoginData(uname,psswd).getUname()) ;
		if(l.getUname().equals("failed")) {
			model.addAttribute("message", "failed");
			return "login";
		}
		else {
			model.addAttribute("message", l.getUname());
			return "index";
		} 
    }
}
