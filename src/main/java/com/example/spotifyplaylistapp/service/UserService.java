package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.UserDTO;
import com.example.spotifyplaylistapp.model.dto.UserRegistrationDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final LoggedUser loggedUser;

    private final HttpSession httpSession;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       LoggedUser loggedUser,
                       HttpSession httpSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
        this.httpSession = httpSession;
    }

    public boolean checkUsernameAndPassword(String username, String password) {
        User user = this.getUserByUsername(username);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(password, user.getPassword());
    }

    private User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }

    public void login(String username) {
        User user = this.getUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    public UserDTO findUserByUsername(String username) {
        User user = this.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        return mapUserDTO(user);
    }

    private UserDTO mapUserDTO(User user) {
        return new UserDTO()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail());
    }

    public UserDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }
        return this.mapUserDTO(user);
    }
    
    public void register(UserRegistrationDTO userRegistrationDTO) {
        this.userRepository.save(this.mapUser(userRegistrationDTO));
        this.login(userRegistrationDTO.getUsername());
    }

    private User mapUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        return user;
    }

    public void logout() {
        this.httpSession.invalidate();
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public void addSongToUser(Long userId, Song song) {
        User user = this.getUserById(userId);
    }

    private User getUserById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow();
    }
}
