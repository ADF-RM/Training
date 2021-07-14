package com.naveen.training.SpringDemo.Shows;

import com.naveen.training.SpringDemo.Shows.Genres.Genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Series implements Show{
    @Autowired
    @Qualifier("fantasy")
    Genre genre;

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
    public void play(){
        System.out.println("Its Series Time...");
        genre.showGenre();
    }
}
