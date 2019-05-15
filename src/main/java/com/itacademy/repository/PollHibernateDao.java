package com.itacademy.repository;

import com.itacademy.domain.hibernateDomain.Poll;
import com.itacademy.domain.hibernateDomain.VariantAnswer;

import java.util.List;

public interface PollHibernateDao extends GenericDao<Poll, Long> {

    List<VariantAnswer> getAnswersForPoll(Poll entity);

}
