package com.itacademy.controllers;

import com.itacademy.domain.Role;
import com.itacademy.domain.User;
import com.itacademy.domain.VariantAnswer;
import com.itacademy.repository.RoleDao;
import com.itacademy.repository.UserDao;
import com.itacademy.repository.VariantAnswerDao;
import com.itacademy.requests.UserCreateRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/hiber/users")
public class UserController {

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDao;


    @ApiOperation(value = "Get all users")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Get user from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting user"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 401, message = "You are unauthorized"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping(value = "/{user_id}")
    public ResponseEntity<User> getUserById(@ApiParam("User Path Id")
                                            @PathVariable("user_id") Long id) {
        User user = userDao.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest request) {
        User user = new User();
        user.setUserName(request.getUserName());
        user.setUserSurname(request.getUserSurname());
        user.setRegistrationDate(request.getRegistrationDate());
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());

        User savedUser = userDao.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PutMapping(value = "/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@PathVariable("user_id") Long userId,
                                           @RequestBody UserCreateRequest request) {
        User user = userDao.findById(userId);
        user.setUserName(request.getUserName());
        user.setUserSurname(request.getUserSurname());
        user.setRegistrationDate(request.getRegistrationDate());
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());

        User updatedUser = userDao.update(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteUser(@PathVariable("user_id") Long userId) {
        userDao.delete(userId);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }





    @GetMapping(value = "/{user_id}/roles")
    public ResponseEntity<List<Role>> getUsersRoles(@ApiParam("Get user's roles Id")
                                                    @PathVariable("user_id") Long id) {
        List<Role> userVariantsAnswer = new ArrayList<>(userDao.findById(id).getRoles());
        return new ResponseEntity<>(userVariantsAnswer, HttpStatus.OK);
    }

    @PutMapping(value = "/{user_id}/roles/{role_id}")
    public void addUserRole(@ApiParam("Save user's role")
                               @PathVariable("user_id") Long userId,
                               @ApiIgnore @ModelAttribute @PathVariable(value = "role_id") Long roleId) {
        userDao.findById(userId).getRoles().add(roleDao.findById(roleId));
    }

    @Autowired
    @Qualifier("roleDaoImpl")
    private RoleDao roleDao;

    @DeleteMapping(value = "/{user_id}/roles/{role_id}")
    public void deleteUserRole(@ApiParam("Update user's role")
                                 @PathVariable("user_id") Long userId,
                                 @PathVariable("role_id") Long roleId
    ) {
        Role role = roleDao.findById(roleId);
        userDao.findById(userId).getRoles().remove(role);
    }




    @Autowired
    @Qualifier("variantAnswerDaoImpl")
    private VariantAnswerDao variantAnswerDao;

    @GetMapping(value = "/{user_id}/variantsAnswers")
    public ResponseEntity<List<VariantAnswer>> getUsersAnswers(@ApiParam("Get user's answers")
                                                               @PathVariable("user_id") Long id) {

        User user = userDao.findById(id);
        List<VariantAnswer> userVariantsAnswer = new ArrayList<>(user.getVariantAnswers());
        return new ResponseEntity<>(userVariantsAnswer, HttpStatus.OK);
    }

    @PostMapping(value = "/{user_id}/variantsAnswers")
    public void saveUserAnswer(@ApiParam("save user's variant answer")
                               @PathVariable("user_id") Long userId,
                               @ApiIgnore @ModelAttribute VariantAnswer answer) {
        //TODO: possible problems with null-params mapping into model
        User user = userDao.findById(userId);
        user.getVariantAnswers().add(answer);
        userDao.update(user);
    }

    @DeleteMapping(value = "/{user_id}/variantsAnswers/{answer_id}")
    public void deleteUserAnswer(@ApiParam("Delete user's variant answer")
                                 @PathVariable("user_id") Long userId,
                                 @PathVariable("answer_id") Long answerId
    ) {
        User user = userDao.findById(userId);
        user.getVariantAnswers().remove(variantAnswerDao.findById(answerId));
    }


}