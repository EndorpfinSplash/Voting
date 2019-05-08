package com.itacademy.controller.requests;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class VariantAnswer {
    String variantAnswer;
    Long answerOrder;
    Long pollId;
    Boolean correctness;

    public VariantAnswer() {
    }

    public VariantAnswer(String variantAnswer, Long answerOrder, Long pollId, Boolean correctness) {
        this.variantAnswer = variantAnswer;
        this.answerOrder = answerOrder;
        this.pollId = pollId;
        this.correctness = correctness;
    }

    public String getVariantAnswer() {
        return variantAnswer;
    }

    public void setVariantAnswer(String variantAnswer) {
        this.variantAnswer = variantAnswer;
    }

    public Long getAnswerOrder() {
        return answerOrder;
    }

    public void setAnswerOrder(Long answerOrder) {
        this.answerOrder = answerOrder;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public Boolean getCorrectness() {
        return correctness;
    }

    public void setCorrectness(Boolean correctness) {
        this.correctness = correctness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariantAnswer that = (VariantAnswer) o;
        return Objects.equals(variantAnswer, that.variantAnswer) &&
                Objects.equals(answerOrder, that.answerOrder) &&
                Objects.equals(pollId, that.pollId) &&
                Objects.equals(correctness, that.correctness);
    }

    @Override
    public int hashCode() {

        return Objects.hash(variantAnswer, answerOrder, pollId, correctness);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
