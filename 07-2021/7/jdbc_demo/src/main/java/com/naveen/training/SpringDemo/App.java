package com.naveen.training.SpringDemo;

import com.naveen.training.SpringDemo.Shows.Show;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = 
        new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("\n---------------------\n");

        System.out.println();

        Show show = context.getBean(Show.class);
        show.play();

        System.out.println();

        System.out.println("\n---------------------\n");

        ((AbstractApplicationContext) context).close();
    }
}
