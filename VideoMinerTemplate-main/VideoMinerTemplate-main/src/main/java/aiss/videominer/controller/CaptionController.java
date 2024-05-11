package aiss.videominer.controller;

import aiss.videominer.model.Caption;
import aiss.videominer.model.Channel;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CaptionRepository;
import aiss.videominer.repository.VideoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/videominer/captions")
public class CaptionController {
    @Autowired
    CaptionRepository repository;

    @Autowired
    VideoRepository videoRepository;

    @GetMapping
    public List<Caption> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Caption findOne(@PathVariable String id) {
        Optional<Caption> canal = repository.findById(id);
        return canal.get();
    }


    public void create(String videoId, Caption captionRequest){
        Optional<Video> video = videoRepository.findById(videoId);
        video.get().getCaptions().add(captionRequest);
        repository.save(captionRequest);
    }
}

