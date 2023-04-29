package com.sample.springapplicationpositionsearch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.net.MalformedURLException;
import java.net.URL;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String location;
    private URL url;

    public Position(String title, String location) {
        this.title = title;
        this.location = location;
    }

    public void setUrl(String stringUrl) {
        try {
            this.url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}

