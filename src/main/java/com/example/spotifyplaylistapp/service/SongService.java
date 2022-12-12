package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.AddSongDTO;
import com.example.spotifyplaylistapp.model.dto.SongDTO;
import com.example.spotifyplaylistapp.model.dto.SongsWithUsernameDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SongService {

    private final SongRepository songRepository;

    private final StylesService stylesService;

    private final UserService userService;

    public SongService(SongRepository songRepository, StylesService stylesService, UserService userService) {
        this.songRepository = songRepository;
        this.stylesService = stylesService;
        this.userService = userService;
    }

    public Set<Song> getSongsFromCurrentUser(Long id) {
        return this.songRepository.findAllByUserId(id);
    }

    public void addSong(AddSongDTO addSongDTO) {
        this.songRepository.save(this.mapSong(addSongDTO));
    }

    private Song mapSong(AddSongDTO addSongDTO) {
        Song song = new Song();
        Style style = this.stylesService.findStyle(addSongDTO.getStyle());
        song.setDuration(addSongDTO.getDuration());
        song.setTitle(addSongDTO.getTitle());
        song.setStyle(style);
        song.setPerformer(addSongDTO.getPerform());
        song.setReleaseDate(addSongDTO.getReleaseDate());
        return song;
    }


    public Set<SongsWithUsernameDTO> getSongsFromOtherUsers(Long id) {
        Set<Song> songByUserIdNot = songRepository.findSongByUserIdNot(id);
        return mapToSongWithUsernameDTO(songByUserIdNot);
    }

    private Set<SongsWithUsernameDTO> mapToSongWithUsernameDTO(Set<Song> songByUserIdNot) {
        return songByUserIdNot.stream()
                .map(song -> {
                    SongsWithUsernameDTO currentDTO = new SongsWithUsernameDTO();
                    currentDTO.setId(song.getId())
                            .setDuration(song.getDuration())
                            .setPerformer(song.getPerformer())
                            .setTitle(song.getTitle())
                            .setStyleName(song.getStyle().getStyleName());
                    return currentDTO;
                })
                .collect(Collectors.toSet());
    }

    public Song findSongById(Long id) {
        return this.songRepository.findById(id).orElseThrow();
    }

    public SongDTO findSongByPerformer(String performer) {
        Song song = this.songRepository.findByPerformer(performer).orElse(null);
        if (song == null) {
            return null;
        }

        return this.mapSongDTO(song);
    }

    public Set<SongDTO> findSongByStyle(Style style) {
        return this.songRepository.findByStyle(style)
                .stream()
                .map(this::mapSongDTO)
                .collect(Collectors.toSet());
    }

    private SongDTO mapSongDTO(Song song) {
        SongDTO songDTO = new SongDTO();
        songDTO.setId(song.getId());
        songDTO.setDuration(song.getDuration());
        songDTO.setPerformer(song.getPerformer());
        songDTO.setTitle(song.getTitle());
        return songDTO;
    }

    public Set<SongDTO> getPlaylist(Long id) {
        return this.songRepository.findAllByUserId(id)
                .stream()
                .map(this::mapSongDTO)
                .collect(Collectors.toSet());
    }
}
