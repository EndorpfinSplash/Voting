package com.itacademy.domain.hibernateDomain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "voting.variant_answers")
public class VariantAnswer {
    @Id
    @Column(name = "answer_id")
    Long answerId;

    @Column(name = "")
    String variantAnswer;

    @Column(name = "answer_order")
    Long answerOrder;

    @Column(name = "сorrectness")
    Boolean correctness;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    Poll poll;


    public VariantAnswer() {
    }

    public VariantAnswer(Long answerId, String variantAnswer, Long answerOrder, Long pollId, Boolean correctness) {
        this.answerId = answerId;
        this.variantAnswer = variantAnswer;
        this.answerOrder = answerOrder;
        this.correctness = correctness;
    }


    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
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
        return Objects.equals(answerId, that.answerId) &&
                Objects.equals(variantAnswer, that.variantAnswer) &&
                Objects.equals(answerOrder, that.answerOrder) &&
                Objects.equals(correctness, that.correctness);
    }

    @Override
    public int hashCode() {

        return Objects.hash(answerId, variantAnswer, answerOrder, correctness);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
