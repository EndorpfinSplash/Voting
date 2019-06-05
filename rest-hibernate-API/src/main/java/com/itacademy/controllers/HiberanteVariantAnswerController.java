package com.itacademy.controllers;

import com.itacademy.domain.VariantAnswer;
import com.itacademy.repository.VariantAnswerDao;
import com.itacademy.requests.VariantAnswerCreateRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/rest/hiber/variantAnswers")
public class HiberanteVariantAnswerController {

    @Autowired
    @Qualifier("variantAnswerDaoImpl")
    VariantAnswerDao variantAnswerDao;

    @ApiOperation(value = "Get all variants answers")
    @GetMapping
    public ResponseEntity<List<VariantAnswer>> getAllVariantsAnswers() {
        List<VariantAnswer> allListVariantsAnswers = variantAnswerDao.findAll();
        return new ResponseEntity(allListVariantsAnswers, HttpStatus.OK);
    }


    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting answer"),
            @ApiResponse(code = 400, message = "Invalid answer ID supplied"),
            @ApiResponse(code = 401, message = "You are unauthorized"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiOperation(value = "Get variant answer")
    @GetMapping
    @RequestMapping(value = "/{answer_id}", method = RequestMethod.GET)
    public ResponseEntity<VariantAnswer> getVariantAnswer(@ApiParam(value = "Answer id")
                                                          @PathVariable("answer_id") Long answerId) {
        VariantAnswer variantAnswer = variantAnswerDao.findById(answerId);
        return new ResponseEntity(variantAnswer, HttpStatus.OK);
    }


    @ApiOperation(value = "Save variant answer")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{answer_id}", method = RequestMethod.POST)
    public ResponseEntity<VariantAnswer> saveVariantAnswer(@RequestBody VariantAnswerCreateRequest request,
                                                           @PathVariable("answer_id") Long answerId) {
        VariantAnswer variantAnswer = variantAnswerDao.findById(answerId);

        variantAnswer.setVariantAnswer(request.getVariantAnswer());
        variantAnswer.setAnswerOrder(request.getAnswerOrder());
        variantAnswer.setCorrectness(request.getCorrectness());

        VariantAnswer savedVariantAnswer = variantAnswerDao.save(variantAnswer);
        return new ResponseEntity<>(savedVariantAnswer, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update variant answer")
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{answer_id}", method = RequestMethod.PUT)
    public ResponseEntity<VariantAnswer> updateVariantAnswer(@RequestBody VariantAnswerCreateRequest request,
                                                             @PathVariable("answer_id") Long answerId) {
        VariantAnswer variantAnswer = variantAnswerDao.findById(answerId);

        variantAnswer.setVariantAnswer(request.getVariantAnswer());
        variantAnswer.setAnswerOrder(request.getAnswerOrder());
        variantAnswer.setCorrectness(request.getCorrectness());

        VariantAnswer savedVariantAnswer = variantAnswerDao.update(variantAnswer);
        return new ResponseEntity<>(savedVariantAnswer, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Delete variant answer")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deleteVariantAnswer(@PathVariable Long id) {
        variantAnswerDao.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


}
