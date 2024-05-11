package aiss.videominer.controller;

import aiss.videominer.model.*;
import aiss.videominer.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/channels")
public class ChannelController {
    @Autowired
    ChannelRepository repository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CaptionRepository captionRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    VideoController videoController;

    @GetMapping
    public List<Channel> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Channel findOne(@PathVariable String id) {
        Optional<Channel> canal = repository.findById(id);
        return canal.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Channel create(@Valid @RequestBody Channel channel) {
        List<Video> videos = parseVideos(channel);
        Channel canal = new Channel(channel.getName(), channel.getDescription(), channel.getCreatedTime());
        canal.setVideos(videos);
        return repository.save(canal);
    }

    private List<Video> parseVideos(Channel channel){
        List<Video> res = new ArrayList<>();
        for (Video v : channel.getVideos()){
            List<Comment> comments = parseComment(v);
            parseCaption(v);
            Video video = new Video(v.getName(), v.getDescription(), v.getReleaseTime());
            video.setCaptions(v.getCaptions());
            video.setComments(comments);
            videoRepository.save(video);
            res.add(video);
        }
        return res;
    }

    private List<Comment> parseComment(Video v){
        List<Comment> res = new ArrayList<>();
        for (Comment c : v.getComments()){
            User user = c.getAuthor();
            userRepository.save(user);

            Comment comment = new Comment(c.getText(), c.getCreatedOn());
            comment.setAuthor(user);
            commentRepository.save(comment);
            res.add(comment);
        }
        return res;
    }

    private void parseCaption(Video v){
        for (Caption c : v.getCaptions()){
            captionRepository.save(c);
        }
    }

}
