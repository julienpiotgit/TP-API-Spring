package com.myaudiolibrary.apirest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="artist")
/*@Inheritance(strategy = InheritanceType.JOINED)/*/
public class Artist {

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER) /*recupere tout le temps les albums*/
    @JsonIgnoreProperties("artist")
    private Set<Album> albums = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist(String name) {
        this.name = name;
    }

    public Artist(){}


}
