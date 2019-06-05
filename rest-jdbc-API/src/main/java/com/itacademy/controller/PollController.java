package com.itacademy.controller;

import com.itacademy.domain.Poll;
import com.itacademy.domain.VariantAnswer;
import com.itacademy.repository.PollDao;
import com.itacademy.repository.VariantAnswerDao;
import com.itacademy.requests.PollCreateRequest;
import com.itacademy.requests.VariantAnswerCreateRequest;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/rest/polls")
public class PollController {

    @Autowired
    @Qualifier("pollDaoImpl")
    PollDao pollDao;


    @ApiOperation(value = "Get poll from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting poll"),
            @ApiResponse(code = 400, message = "Invalid poll ID supplied"),
            @ApiResponse(code = 401, message = "You are unauthorized"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Poll> getPollById(@ApiParam(value = "PollCreateRequest Path id")
                                            @PathVariable Long id) {
        Poll poll = pollDao.findById(id);
        return new ResponseEntity(poll, HttpStatus.OK);
    }


    @ApiOperation(value = "Get all polls ")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Poll>> getAllPolls() {
        List<Poll> pollList = pollDao.findAll();
        return new ResponseEntity(pollList, HttpStatus.OK);
    }

    @ApiOperation(value = "Create poll by pollID")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Poll> createPoll(@RequestBody PollCreateRequest req) {
        Poll poll = new Poll();
        poll.setPollQuestion(req.getPollQuestion());
        Poll savedPoll = pollDao.save(poll);
        return new ResponseEntity<>(savedPoll, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Update poll by pollID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Poll has been updated successfully"),
            @ApiResponse(code = 400, message = "Invalid poll ID supplied"),
            @ApiResponse(code = 404, message = "Poll was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })

    @PutMapping
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Poll> updatePoll(@PathVariable Long id,
                                           @RequestBody PollCreateRequest req) {
        Poll poll = pollDao.findById(id);
        poll.setPollQuestion(req.getPollQuestion());
        Poll updated = pollDao.update(poll);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


    @ApiOperation(value = "Delete poll by pollID")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deletePoll(@PathVariable Long id) {
        pollDao.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    @Autowired
    @Qualifier("variantAnswerDaoImpl")
    VariantAnswerDao variantAnswerDao;

    @GetMapping
    @RequestMapping(value = "/{id}/variantAnswers", method = RequestMethod.GET)
    public ResponseEntity<List<VariantAnswer>> getVariantsAnswersForPull(@ApiParam(value = "Get variants answer for poll by Poll id")
                                                                         @PathVariable Long id) {
        List<VariantAnswer> listVariantsAnswersForPull = variantAnswerDao.findVariantAnswersForPull(id);
        return new ResponseEntity(listVariantsAnswersForPull, HttpStatus.OK);
    }


    @GetMapping
    @RequestMapping(value = "/{id}/variantAnswers/{answer_id}", method = RequestMethod.GET)
    public ResponseEntity<VariantAnswer> getVariantAnswerForPull(@ApiParam(value = "Answer id")
                                                                 @PathVariable("answer_id") Long answerId) {
        VariantAnswer variantAnswer = variantAnswerDao.findById(answerId);
        return new ResponseEntity(variantAnswer, HttpStatus.OK);
    }

    @ApiOperation(value = "Create variant answer")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{id}/variantAnswers", method = RequestMethod.POST)
    public ResponseEntity<VariantAnswer> createVariantAnswerForPull(@RequestBody VariantAnswerCreateRequest request,
                                                                    @PathVariable("id") Long pollId
    ) {
        VariantAnswer variantAnswer = new VariantAnswer();
        variantAnswer.setPollId(pollId);
        variantAnswer.setVariantAnswer(request.getVariantAnswer());
        variantAnswer.setCorrectness(request.getCorrectness());
        variantAnswer.setAnswerOrder(request.getAnswerOrder());

        VariantAnswer savedVariantAnswer = variantAnswerDao.save(variantAnswer);
        return new ResponseEntity<>(savedVariantAnswer, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update variant answer for pull")
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{poll_id}/variantAnswers/{answer_id}", method = RequestMethod.PUT)
    public ResponseEntity<VariantAnswer> updateVariantAnswerForPull(@RequestBody VariantAnswerCreateRequest request,
                                                                    @PathVariable("poll_id") Long pollId,
                                                                    @PathVariable("answer_id") Long answerId
    ) {
        VariantAnswer variantAnswer = variantAnswerDao.findById(answerId);
        variantAnswer.setVariantAnswer(request.getVariantAnswer());
        variantAnswer.setCorrectness(request.getCorrectness());
        variantAnswer.setAnswerOrder(request.getAnswerOrder());

        VariantAnswer savedVariantAnswer = variantAnswerDao.update(variantAnswer);
        return new ResponseEntity<>(savedVariantAnswer, HttpStatus.CREATED);
    }


}
