package com.itacademy.repository.impl;

import com.itacademy.domain.Poll;
import com.itacademy.domain.VariantAnswer;
import com.itacademy.repository.PollDao;
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
@Qualifier("pollDaoImpl")
public class PollDaoImpl implements PollDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static final String POLL_ID = "poll_id";
    public static final String POLL_QUESTION = "poll_question";

    public Poll getPollFromRow(ResultSet resultSet, int i) throws SQLException {
        Poll poll = new Poll();
        poll.setPollId(resultSet.getLong(POLL_ID));
        poll.setPollQuestion(resultSet.getString(POLL_QUESTION));
        return poll;
    }

    @Override
    public List<Poll> findAll() {
        final String getAllPolls = "select * from voting.poll";
        return jdbcTemplate.query(getAllPolls, this::getPollFromRow);
    }

    @Override
    public Poll findById(Long id) {
        final String findById = "select * from voting.poll where poll_id = ?";
        return jdbcTemplate.queryForObject(findById, new Object[]{id}, this::getPollFromRow);
    }

    @Override
    public void delete(Long id) {
        String deletePoll = "Delete from voting.poll where poll_id =:poolId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("poolId", id);
        namedParameterJdbcTemplate.update(deletePoll, params);
    }

    @Override
    public Poll save(Poll entity) {

        String insertPoll = "insert into voting.poll (poll_id, poll_question ) values(:pollId, :pollQuestion)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("pollId", entity.getPollId());
        params.addValue("pollQuestion", entity.getPollQuestion());
        namedParameterJdbcTemplate.update(insertPoll, params, keyHolder);
        long createdPollId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdPollId);
    }

    @Override
    public Poll update(Poll entity) {
        String updatePoll = "update voting.poll set poll_question =:pollQuestion where poll_id = :pollId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("pollQuestion", entity.getPollQuestion());
        params.addValue("pollId", entity.getPollId());
        namedParameterJdbcTemplate.update(updatePoll, params);

        return findById(entity.getPollId());
    }


    @Autowired
    @Qualifier("variantAnswerDaoImpl")
    VariantAnswerDao variantAnswerDao;

    @Override
    public List<VariantAnswer> getAnswersForPoll(Poll poll) {
        return variantAnswerDao.findVariantAnswersForPull(poll);
    }

}
