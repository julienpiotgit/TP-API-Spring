package com.myaudiolibrary.apirest.model;

import javax.persistence.*;

@Entity
@Table(name="album")
public class Album {

    @ManyToOne
    @JoinColumn( name = "artistId")
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Album(String title) {
        this.title = title;
    }

    public Album() {
    }

}
