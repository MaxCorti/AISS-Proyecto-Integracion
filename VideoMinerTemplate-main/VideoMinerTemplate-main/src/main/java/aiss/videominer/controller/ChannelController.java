package aiss.videominer.controller;

import aiss.videominer.model.Channel;
import aiss.videominer.repository.ChannelRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/channels")
public class ChannelController {
    @Autowired
    ChannelRepository repository;

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
        Channel canal = repository.save(new Channel(channel.getName(), channel.getDescription(), channel.getCreatedTime()));
        return canal;
    }

}
