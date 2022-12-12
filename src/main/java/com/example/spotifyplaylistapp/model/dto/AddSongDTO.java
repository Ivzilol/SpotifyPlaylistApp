package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.StyleName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class AddSongDTO {

    private Long id;

    @Size(min = 3, max = 20, message = "Perform length must be between 3 and 20 characters" +
            "(inclusive 3 and 20)")
    @NotNull
    private String perform;

    @Size(min = 2, max = 20, message = "Title length must be between 2 and 20 characters" +
            "(inclusive 2 and 20)")
    @NotNull
    private String title;

    @PastOrPresent(message = "The Date cannot be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @Positive(message = "Duration must be a positive")
    @NotNull
    private Long duration;

    @NotNull(message = "You must select Style")
    @NotNull
    private StyleName style;

    public AddSongDTO() {
    }

    public Long getId() {
        return id;
    }

    public AddSongDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPerform() {
        return perform;
    }

    public AddSongDTO setPerform(String perform) {
        this.perform = perform;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AddSongDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AddSongDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public AddSongDTO setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public StyleName getStyle() {
        return style;
    }

    public AddSongDTO setStyle(StyleName style) {
        this.style = style;
        return this;
    }
}
