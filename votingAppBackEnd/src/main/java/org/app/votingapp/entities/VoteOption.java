package org.app.votingapp.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Embeddable
public class VoteOption {

    private String optionText;
    private Long voteCount=0L;
}
