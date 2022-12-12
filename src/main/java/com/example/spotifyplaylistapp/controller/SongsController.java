package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.AddSongDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequestMapping("/songs")
public interface SongsController {

    @GetMapping("add-song")
    String addSong();

    @PostMapping("/add-song")
    String addSong(@Valid AddSongDTO addSongDTO, BindingResult result, RedirectAttributes redirectAttributes);
}
