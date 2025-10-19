package com.felfel.springweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping("/")
    public ModelAndView log( ModelAndView mv){
        System.out.println("Log page requested");
        mv.setViewName("log");
        return mv;
    }
    @RequestMapping("/home")
    public String home(@ModelAttribute User user){
        System.out.println("Home page requested");
        System.out.println(user);

        if (!(user.getUser().equals("admin") && user.getPass().equals("admin"))){
            return "log";
        }
        return "home";
    }
}
