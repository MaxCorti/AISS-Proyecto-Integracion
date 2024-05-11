package aiss.videominer.controller;

import aiss.videominer.model.Caption;
import aiss.videominer.model.Channel;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.ChannelRepository;
import aiss.videominer.repository.VideoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/videos")
public class VideoController {
    @Autowired
    VideoRepository repository;

    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    CommentController commentController;
    @Autowired
    CaptionController captionController;

    @GetMapping
    public List<Video> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Video findOne(@PathVariable String id) {
        Optional<Video> video = repository.findById(id);
        return video.get();
    }

    public void create(String channelId, Video videoRequest){
        Optional<Channel> canal = channelRepository.findById(channelId);
        canal.get().getVideos().add(videoRequest);
        Video video = repository.save(new Video(videoRequest.getName(), videoRequest.getDescription(), videoRequest.getReleaseTime()));
        parseCaption(video);
        parseComment(video);
    }

    private void parseComment(Video video){
        for (Comment c : video.getComments()){
            commentController.create(video.getId(), c);
        }
    }

    private void parseCaption(Video video){
        for (Caption c : video.getCaptions()){
            captionController.create(video.getId(), c);
        }
    }
}
