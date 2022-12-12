package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.SongsByGenreDTO;
import com.example.spotifyplaylistapp.service.HomeService;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final LoggedUser loggedUser;

    private final UserService userService;

    private final SongService songService;

    private final HomeService homeService;

    public HomeController(LoggedUser loggedUser, UserService userService, SongService songService, HomeService homeService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.songService = songService;
        this.homeService = homeService;
    }

    @GetMapping
    String index(){
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }
        model.addAttribute("songs", this.homeService.getSongs());
        model.addAttribute("playlist", this.songService.getPlaylist(loggedUser.getId()));
        return "home";
    }

    @GetMapping("/home/add-song-to-playlist/{id}")
    String addSongToPlaylist(@PathVariable("id") Long id) {
        if (!loggedUser.isLogged()) {
            return "redirect:/home";
        }
        this.homeService.addSong(id, this.loggedUser.getId());
        return "redirect:/home";
    }

    @ModelAttribute
    public SongsByGenreDTO songs() {
        return new SongsByGenreDTO();
    }

}
