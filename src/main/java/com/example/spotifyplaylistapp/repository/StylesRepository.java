package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StylesRepository extends JpaRepository<Style, Long> {


    Optional<Style> findByStyleName(StyleName style);

    Style findStyleByStyleName(StyleName styleName);
}
