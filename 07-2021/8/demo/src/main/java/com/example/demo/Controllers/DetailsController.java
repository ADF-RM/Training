package com.example.demo.Controllers;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.Models.Details;
import com.example.demo.RepoInterfaces.DetailsRepo;

import org.apache.coyote.http11.Http11AprProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DetailsController {

    @Autowired
    DetailsRepo repo;

    @RequestMapping("/")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }

    @RequestMapping("/details")
    public ModelAndView addDetails(Details details){
        ModelAndView mv = new ModelAndView("display");
        System.out.println("details : " + details.getRollNo());
        repo.save(details);
        mv.addObject("params",repo.findAll());
        return mv;
    }

    @RequestMapping("/showDetails")
    public ModelAndView showDetails(){
        ModelAndView mv = new ModelAndView("display");
        mv.addObject("params", repo.findAll());
        return mv;
    }
}
