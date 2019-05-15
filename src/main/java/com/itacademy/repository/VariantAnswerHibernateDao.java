package com.itacademy.repository;

import com.itacademy.domain.hibernateDomain.Poll;
import com.itacademy.domain.hibernateDomain.VariantAnswer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface VariantAnswerHibernateDao extends GenericDao<VariantAnswer, Long> {

    List<VariantAnswer> findVariantAnswersForPull(Poll poll);
    List<VariantAnswer> findVariantAnswersForPull(Long poll_id);

    default VariantAnswer getAnswerFromRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
