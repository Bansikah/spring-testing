package com.bansikah.springtesting.post;


import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PostController.class);
    private final PostRepository repository;

    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    List<Post> findAll(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    Optional<Post> findById(@PathVariable Integer id){
        return repository.findById(id);
    }

    @PostMapping("")
   @ResponseStatus(HttpStatus.CREATED)
    Post save(@RequestBody @Valid Post post){
    return repository.save(post);
    }

    @PutMapping("/{id}")
    Post update(@PathVariable Integer id, @RequestBody Post post){
        Optional<Post> existing = repository.findById(id);
        if(existing.isPresent()) {
            Post updatedPost = new Post(existing.get().id(),
                    existing.get().userId(),
                    existing.get().title(),
                    existing.get().body(),
                    existing.get().version());
            return repository.save(updatedPost);
        }else {
            throw new PostNotFoundException();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        repository.deleteById(id);
    }
}
