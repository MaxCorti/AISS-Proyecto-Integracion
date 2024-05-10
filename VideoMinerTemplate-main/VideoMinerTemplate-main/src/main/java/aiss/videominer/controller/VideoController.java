package aiss.videominer.controller;

import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/videos")
public class VideoController {
    @Autowired
    VideoRepository repository;
    @GetMapping
    public List<Video> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Video findOne(@PathVariable String id) {
        Optional<Video> video = repository.findById(id);
        return video.get();
    }
}
