package com.itacademy.domain.hibernateDomain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "variant_answers")
public class VariantAnswer {
    @Id
    @Column(name = "answer_id")
    Long answerId;

    @Column(name = "variant_answer")
    String variantAnswer;

    @Column(name = "answer_order")
    Long answerOrder;

    @Column(name = "сorrectness")
    Boolean correctness;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    Poll poll;

    @JsonBackReference
    @ManyToMany(mappedBy = "variantAnswers", fetch = FetchType.EAGER)
    Set<User> users = Collections.emptySet();


    public VariantAnswer() {
    }

    public VariantAnswer(Long answerId, String variantAnswer, Long answerOrder, Boolean correctness, Poll poll, Set<User> users) {
        this.answerId = answerId;
        this.variantAnswer = variantAnswer;
        this.answerOrder = answerOrder;
        this.correctness = correctness;
        this.poll = poll;
        this.users = users;
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

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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
