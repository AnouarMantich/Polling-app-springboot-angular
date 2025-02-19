package org.app.votingapp.repositories;

import org.app.votingapp.entities.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll,Long> {
}
