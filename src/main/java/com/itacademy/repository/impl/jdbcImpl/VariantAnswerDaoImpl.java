package com.itacademy.repository.impl.jdbcImpl;

import com.itacademy.domain.Poll;
import com.itacademy.domain.VariantAnswer;
import com.itacademy.repository.VariantAnswerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@Qualifier("variantAnswerDaoImpl")
public class VariantAnswerDaoImpl implements VariantAnswerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static final String ANSWER_ID = "answer_id";
    public static final String VARIANT_ANSWER = "variant_answer";
    public static final String ANSWER_ORDER = "answer_order";
    public static final String CORRECTNESS = "сorrectness";
    public static final String POLL_ID = "poll_id";

    public VariantAnswer getAnswerFromRow(ResultSet resultSet, int i) throws SQLException {
        VariantAnswer variantAnswer = new VariantAnswer();
        variantAnswer.setAnswerId(resultSet.getLong(ANSWER_ID));
        variantAnswer.setVariantAnswer(resultSet.getString(VARIANT_ANSWER));
        variantAnswer.setAnswerOrder(resultSet.getLong(ANSWER_ORDER));
        variantAnswer.setCorrectness(resultSet.getBoolean(CORRECTNESS));
        variantAnswer.setPollId(resultSet.getLong(POLL_ID));
        return variantAnswer;
    }

    @Override
    public List<VariantAnswer> findAll() {
        final String getAllPolls = "select * from voting.variant_answers";
        return jdbcTemplate.query(getAllPolls, this::getAnswerFromRow);
    }

    @Override
    public VariantAnswer findById(Long id) {
        final String findById = "select * from voting.variant_answers where answer_id = ?";
        return jdbcTemplate.queryForObject(findById, new Object[]{id}, this::getAnswerFromRow);
    }

    @Override
    public void delete(Long id) {
        String deleteRow = "delete from voting.variant_answers where answer_id =:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update(deleteRow, params);
    }

    @Override
    public VariantAnswer save(VariantAnswer entity) {

        String insertVariantAnswer = "insert into voting.variant_answers (variant_answer, answer_order, сorrectness, poll_id) " +
                "values(:answer, :anserOrder, :correctness, :pollId)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("answer", entity.getVariantAnswer());
        params.addValue("answerOrder", entity.getAnswerOrder());
        params.addValue("pollId", entity.getPollId());
        params.addValue("сorrectness", entity.getCorrectness());
        namedParameterJdbcTemplate.update(insertVariantAnswer, params, keyHolder);
        long createdPollId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdPollId);
    }

    @Override
    public VariantAnswer update(VariantAnswer entity) {
        String updateEntity = "update voting.variant_answers set variant_answer =:variantOfAnswer, answer_order = :anwerOrder, сorrectness = :correctness " +
                "where answer_id = :answerId)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("variantOfAnswer", entity.getVariantAnswer());
        params.addValue("answerId", entity.getAnswerId());
        params.addValue("correctness", entity.getCorrectness());
        namedParameterJdbcTemplate.update(updateEntity, params);

        return findById(entity.getPollId());
    }

    @Override
    public List<VariantAnswer> findVariantAnswersForPull(Poll poll) {
        final String getAnswersForPoll = "select * from voting.variant_answers where poll_id = :pollId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("pollId", poll.getPollId());
        return namedParameterJdbcTemplate.query(getAnswersForPoll, params, this::getAnswerFromRow);
    }

    @Override
    public List<VariantAnswer> findVariantAnswersForPull(Long poll_id) {
        final String getAnswersForPoll = "select * from voting.variant_answers where poll_id = :pollId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("pollId", poll_id);
        return namedParameterJdbcTemplate.query(getAnswersForPoll, params, this::getAnswerFromRow);
    }
}
