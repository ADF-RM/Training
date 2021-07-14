package com.example.demo.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.Models.Details;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping("home")
    public ModelAndView home(Details details){
        ModelAndView mv = new ModelAndView();
        mv.addObject("params", details);
        mv.setViewName("home");
        return mv;
        // String name = request.getParameter("name");
        // System.out.println("Hello" + name);
        // HttpSession session = request.getSession();
        // session.setAttribute("name", name);
        // return "home";
    }
}
