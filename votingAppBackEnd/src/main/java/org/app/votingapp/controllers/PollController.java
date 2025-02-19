package org.app.votingapp.controllers;

import lombok.RequiredArgsConstructor;
import org.app.votingapp.dtos.Vote;
import org.app.votingapp.entities.Poll;
import org.app.votingapp.services.PollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/polls")
@CrossOrigin(origins = "http://localhost:4200/")
public class PollController {

    private final PollService service;

    @PostMapping
    public Poll createPoll(
           @RequestBody Poll poll
    ) {
        return service.createPoll(poll);
    }

    @GetMapping
    public List<Poll> getAllPolls() {
        return service.getAllPolls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPollById(
            @PathVariable(name = "id") long pollId
    ){
        return ResponseEntity.ok(service.getPollById(pollId));
    }

    @PostMapping("/vote")
    public ResponseEntity<Poll> vote(
          @RequestBody  Vote vote
    ){
        return ResponseEntity.ok(service.vote(vote));
    }



}
