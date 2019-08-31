package com.ledVan.controller;

import com.ledVan.RequestMapper.Login;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.ledVan.exception.ResourceNotFoundException;
import com.ledvan.model.User;
import com.ledvan.repository.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/v1")
@Api(value = "User Management System", description = "Operations pertaining to user in admin Management System")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "View a list of available user", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/employees")
    public List<User> getAllEmployees() {
        return userRepository.findAll();
    }

    @ApiOperation(value = "Get an employee by Id")
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getEmployeeById(
            @ApiParam(value = "User id from which user object will retrieve", required = true)
            @PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        User employee = userRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @ApiOperation(value = "Add user")
    @PostMapping("/user")
    public User createEmployee(
            @ApiParam(value = "user object store in database table", required = true)
            @Valid @RequestBody User employee) {
        return userRepository.save(employee);
    }

    @ApiOperation(value = "Update user")
    @PutMapping("/user/{id}")
    public ResponseEntity<User> update(
            @ApiParam(value = "User Id to update user object", required = true)
            @PathVariable(value = "id") Long employeeId,
            @ApiParam(value = "Update user object", required = true)
            @Valid @RequestBody User employeeDetails) throws ResourceNotFoundException {
        User employee = userRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());

        employee.setMobileNo(employeeDetails.getMobileNo());
        employee.setUserName(employeeDetails.getUserName());
        employee.setUserPassword(employeeDetails.getUserPassword());

        final User updatedEmployee = userRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @ApiOperation(value = "Delete an user")
    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> delete(
            @ApiParam(value = "User Id from which User object will delete from database table", required = true)
            @PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        User employee = userRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + employeeId));

        userRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @ApiOperation(value = "Change password")
    @PutMapping("/user/changepassword/{id}")
    public ResponseEntity<User> changePassword(
            @ApiParam(value = "Change password", required = true)
            @PathVariable(value = "id") Long id,
            @ApiParam(value = "Change password", required = true)
            @RequestParam String password) throws ResourceNotFoundException {
        User employee = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        employee.setUserPassword(password);
        final User updatedEmployee = userRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @ApiOperation(value = "User Login")
    @PostMapping("/user/login")
    public ResponseEntity<User> login(
            @ApiParam(value = "User Login", required = true)
            @Valid @RequestBody Login login) {
        User user = userRepository.getUser(login.getUserName(), login.getPassword());
        if (user == null) {
            ResponseEntity.ok(new ResourceNotFoundException("User not found for this id :: " + login.getUserName()));
        }
        return ResponseEntity.ok(user);
    }
}
