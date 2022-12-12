package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleName;
import com.example.spotifyplaylistapp.repository.StylesRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StylesService {

    private final StylesRepository stylesRepository;

    public StylesService(StylesRepository stylesRepository) {
        this.stylesRepository = stylesRepository;
    }


    public void initStyles() {
        if (this.stylesRepository.count() != 0) {
            return;
        }
        Arrays.stream(StyleName.values())
                .forEach(s -> {
                    Style style = new Style();
                    style.setStyleName(s);
                    style.setDescription("...");
                    stylesRepository.save(style);
                });
    }

    public Style findStyle(StyleName style) {
        return this.stylesRepository.findByStyleName(style).orElseThrow(null);
    }
}
