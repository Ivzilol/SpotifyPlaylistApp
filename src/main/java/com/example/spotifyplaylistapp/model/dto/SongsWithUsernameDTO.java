package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.StyleName;

public class SongsWithUsernameDTO {

    private Long id;

    private String performer;

    private String title;

    private Long duration;


    private StyleName styleName;

    public SongsWithUsernameDTO() {
    }

    public Long getId() {
        return id;
    }

    public SongsWithUsernameDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public SongsWithUsernameDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SongsWithUsernameDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public SongsWithUsernameDTO setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public StyleName getStyleName() {
        return styleName;
    }

    public SongsWithUsernameDTO setStyleName(StyleName styleName) {
        this.styleName = styleName;
        return this;
    }
}
