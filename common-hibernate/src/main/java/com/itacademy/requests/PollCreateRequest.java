package com.itacademy.requests;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class PollCreateRequest {
    private String pollQuestion;

    public PollCreateRequest() {
    }

    public PollCreateRequest(String pollQuestion) {
        this.pollQuestion = pollQuestion;
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
        PollCreateRequest poll = (PollCreateRequest) o;
        return  Objects.equals(pollQuestion, poll.pollQuestion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pollQuestion);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
