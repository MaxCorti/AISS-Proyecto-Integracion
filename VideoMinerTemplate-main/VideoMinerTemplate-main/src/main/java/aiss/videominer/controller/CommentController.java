package aiss.videominer.controller;

import aiss.videominer.model.Caption;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CaptionRepository;
import aiss.videominer.repository.CommentRepository;
import aiss.videominer.repository.VideoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/comments")
public class CommentController {
    @Autowired
    CommentRepository repository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    UserController userController;

    @GetMapping
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Comment findOne(@PathVariable String id) {
        Optional<Comment> canal = repository.findById(id);
        return canal.get();
    }

    /*public void create(String videoId, Comment commentRequest){
        Optional<Video> video = videoRepository.findById(videoId);
        video.get().getComments().add(commentRequest);
        Comment comment = repository.save(new Comment(commentRequest.getText(), commentRequest.getCreatedOn()));
        userController.create(comment.getId(), commentRequest.getAuthor());
    }*/
}


