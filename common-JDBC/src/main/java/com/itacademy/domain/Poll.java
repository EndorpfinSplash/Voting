package com.itacademy.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "voting.poll")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "poll_id")
    private Long pollId;

    @Column(name = "poll_question")
    private String pollQuestion;

    public Poll() {
    }


    public Poll(Long pollId, String pollQuestion) {
        this.pollId = pollId;
        this.pollQuestion = pollQuestion;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public String getPollQuestion() {
        return pollQuestion;
    }

    public void setPollQuestion(String pollQuestion) {
        this.pollQuestion = pollQuestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poll poll = (Poll) o;
        return Objects.equals(pollId, poll.pollId) &&
                Objects.equals(pollQuestion, poll.pollQuestion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pollId, pollQuestion);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
