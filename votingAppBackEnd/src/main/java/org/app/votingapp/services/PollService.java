package org.app.votingapp.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.app.votingapp.dtos.Vote;
import org.app.votingapp.entities.Poll;
import org.app.votingapp.entities.VoteOption;
import org.app.votingapp.repositories.PollRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PollService {
    private final PollRepository pollRepository;

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Poll getPollById(Long id) {
        return pollRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Poll not found")
        );
    }

    public Poll vote(Vote vote) {
        Poll poll = pollRepository.findById(vote.getPollId()).orElseThrow(
                () -> new EntityNotFoundException("Poll not found")
        );

        List<VoteOption> options = poll.getOptions();
        if(vote.getIndexOption() <0 || options.size() < vote.getIndexOption()) {
            throw new IllegalArgumentException("Invalid option index");
        }

        VoteOption voteOption = options.get(vote.getIndexOption());
        voteOption.setVoteCount(voteOption.getVoteCount()+1);
        poll.setOptions(options);
        return pollRepository.save(poll);


    }


}
