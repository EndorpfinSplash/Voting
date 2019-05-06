package com.itacademy.repository;

import com.itacademy.domain.Poll;
import com.itacademy.domain.VariantAnswer;

import java.sql.ResultSet;
import java.util.List;

public interface VariantsAnswerDao extends GenericDao<VariantAnswer, Long> {

    List<VariantAnswer> findVariantAnswersForPull(Poll poll);

    VariantAnswer getAnswerFromRow(ResultSet resultSet, int i);

}
