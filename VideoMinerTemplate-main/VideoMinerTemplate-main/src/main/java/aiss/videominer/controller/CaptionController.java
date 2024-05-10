package aiss.videominer.controller;

import aiss.videominer.model.Caption;
import aiss.videominer.model.Channel;
import aiss.videominer.repository.CaptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/videominer/captions")
public class CaptionController {
    @Autowired
    CaptionRepository repository;

    @GetMapping
    public List<Caption> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Caption findOne(@PathVariable String id) {
        Optional<Caption> canal = repository.findById(id);
        return canal.get();
    }
}

