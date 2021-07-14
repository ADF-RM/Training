package com.naveen.training.SpringDemo.Shows.Genres;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Thriller implements Genre{
    public void showGenre(){
        System.out.println("Lets get Thrilled...");
    }
}
