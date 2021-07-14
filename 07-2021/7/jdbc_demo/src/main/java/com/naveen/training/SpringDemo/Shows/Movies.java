package com.naveen.training.SpringDemo.Shows;

import com.naveen.training.SpringDemo.Shows.Genres.Genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Movies implements Show{

    @Autowired
    Genre genre;

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void play(){
        System.out.println("Its Movie Time..");
        genre.showGenre();
    }
}
