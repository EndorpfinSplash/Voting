package com.itacademy.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "poll")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "poll_id")
    private Long pollId;

    @Column(name = "poll_question")
    private String pollQuestion;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "poll")
    private Set<VariantAnswer> variantAnswers = Collections.EMPTY_SET;


    public Poll() {
    }


    public Poll(Long pollId, String pollQuestion) {
        this.pollId = pollId;
        this.pollQuestion = pollQuestion;
    }

    public Poll(String pollQuestion, Set<VariantAnswer> variantAnswers) {
        this.pollQuestion = pollQuestion;
        this.variantAnswers = variantAnswers;
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

    public Set<VariantAnswer> getVariantAnswers() {
        return variantAnswers;
    }

    public void setVariantAnswers(Set<VariantAnswer> variantAnswers) {
        this.variantAnswers = variantAnswers;
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
