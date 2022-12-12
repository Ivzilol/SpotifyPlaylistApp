package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.SongDTO;
import com.example.spotifyplaylistapp.model.dto.SongsByGenreDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleName;
import com.example.spotifyplaylistapp.repository.StylesRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class HomeService {

    private final UserService userService;

    private final StylesRepository stylesRepository;

    private final SongService songService;

    public HomeService(UserService userService, StylesRepository stylesRepository, SongService songService) {
        this.userService = userService;
        this.stylesRepository = stylesRepository;
        this.songService = songService;
    }


    public void addSong(Long songId, Long userID) {
        Song song = this.songService.findSongById(songId);
        this.userService.addSongToUser(userID, song);

    }

    public SongsByGenreDTO getSongs() {
        SongsByGenreDTO songs = new SongsByGenreDTO();
        songs.setPopSongs(this.getSongsByGenre(this.stylesRepository.findStyleByStyleName(StyleName.POP)));
        songs.setPopSongs(this.getSongsByGenre(this.stylesRepository.findStyleByStyleName(StyleName.ROCK)));
        songs.setPopSongs(this.getSongsByGenre(this.stylesRepository.findStyleByStyleName(StyleName.JAZZ)));
        return songs;
    }

    private Set<SongDTO> getSongsByGenre(Style style) {
        return this.songService.findSongByStyle(style);
    }
}
