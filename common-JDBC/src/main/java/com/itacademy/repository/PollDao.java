package com.itacademy.repository;

import com.itacademy.domain.Poll;
import com.itacademy.domain.VariantAnswer;

import java.util.List;

public interface PollDao extends GenericDao<Poll, Long> {

    List<VariantAnswer> getAnswersForPoll(Poll entity);

}
