package aiss.videominer.controller;

import aiss.videominer.model.User;
import aiss.videominer.model.Video;
import aiss.videominer.repository.UserRepository;
import aiss.videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/users")
public class UserController {
    @Autowired
    UserRepository repository;
    @GetMapping
    public List<User> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable String id) {
        Optional<User> video = repository.findById(id);
        return video.get();
    }
}
