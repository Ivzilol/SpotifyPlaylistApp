package com.example.spotifyplaylistapp.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Style extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private StyleName styleName;

    @Column
    private String description;

    @OneToMany(mappedBy = "style", fetch = FetchType.LAZY)
    private Set<Song> songs;

    public Style() {
    }

    public StyleName getStyleName() {
        return styleName;
    }

    public void setStyleName(StyleName style) {
        this.styleName = style;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
