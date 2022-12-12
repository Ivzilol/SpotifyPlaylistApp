package com.example.spotifyplaylistapp.init;

import com.example.spotifyplaylistapp.service.StylesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StylesInit  implements CommandLineRunner {

    private final StylesService stylesService;

    @Autowired
    public StylesInit(StylesService stylesService) {
        this.stylesService = stylesService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.stylesService.initStyles();
    }
}
