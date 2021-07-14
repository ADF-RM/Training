package com.naveen.training.SpringDemo.Shows.Genres;

import org.springframework.stereotype.Component;

@Component
public class Fantasy implements Genre{
    public void showGenre(){
        System.out.println("Lets get Fantasized...");
    }
}
