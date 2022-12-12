package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.dto.SongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {



    Set<Song> findAllByUserId(Long id);

    Set<Song> findByStyle(Style style);

    Set<Song> findSongByUserIdNot(Long id);

    Optional<Song> findByPerformer(String performer);
}
