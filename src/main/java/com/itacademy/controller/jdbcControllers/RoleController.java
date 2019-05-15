package com.itacademy.controller.jdbcControllers;

import com.itacademy.controller.requests.RoleCreateRequest;
import com.itacademy.domain.Role;
import com.itacademy.repository.RoleDao;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/rest/roles")
public class RoleController {

    @Autowired
    @Qualifier("roleDaoImpl")
    RoleDao roleDao;


    @ApiOperation(value = "Get role from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting role"),
            @ApiResponse(code = 400, message = "Invalid role ID supplied"),
            @ApiResponse(code = 401, message = "You are unauthorized"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Role> getRoleById(@ApiParam(value = "RoleCreateRequest Path id") @PathVariable Long id) {
        Role role = roleDao.findById(id);
        return new ResponseEntity(role, HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roleList = roleDao.findAll();
        return new ResponseEntity(roleList, HttpStatus.OK);
    }

    @ApiOperation(value = "Create role by roleID")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Role> createRole(@RequestBody RoleCreateRequest req) {
        Role role = new Role();
        role.setRoleName(req.getRoleName());
        Role savedRole = roleDao.save(role);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Update role by roleID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Role has been updated successfully"),
            @ApiResponse(code = 400, message = "Invalid role ID supplied"),
            @ApiResponse(code = 404, message = "Role was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })

    @PutMapping
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Role> updateRole(@PathVariable Long id,
                                           @RequestBody RoleCreateRequest req) {
        Role role = roleDao.findById(id);
        role.setRoleName(req.getRoleName());
        Role updated = roleDao.update(role);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


    @ApiOperation(value = "Delete role by roleID")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deleteRole(@PathVariable Long id) {
        roleDao.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


}
