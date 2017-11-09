package com.spring.couchbase.SpringCassandraDemo.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.couchbase.SpringCassandraDemo.dto.UserDto;
import com.spring.couchbase.SpringCassandraDemo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
		ResponseEntity<?> responseEntity = null;
		UserDto newUserDto = userService.addUser(userDto);
		if (Objects.nonNull(newUserDto))
			responseEntity = new ResponseEntity<>(newUserDto, HttpStatus.OK);
		else
			responseEntity = new ResponseEntity<>("User not created", HttpStatus.BAD_REQUEST);
		return responseEntity;
	}

	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUser() {
		ResponseEntity<?> responseEntity = null;
		List<UserDto> users = userService.getAllUser();
		if (Objects.nonNull(users))
			responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
		else
			responseEntity = new ResponseEntity<>("User not created", HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	
	
	@RequestMapping(value = "/getByFirstName/{firstName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getByFirstName(@PathVariable String firstName) {
		ResponseEntity<?> responseEntity = null;
		List<UserDto> users = userService.getByFirstName(firstName);
		if (Objects.nonNull(users))
			responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
		else
			responseEntity = new ResponseEntity<>("User not created", HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	
	@RequestMapping(value = "/getByLastName/{lastName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getByLastName(@PathVariable String lastName) {
		ResponseEntity<?> responseEntity = null;
		List<UserDto> users = userService.getByLastName(lastName);
		if (Objects.nonNull(users))
			responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
		else
			responseEntity = new ResponseEntity<>("User not created", HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	
	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
		ResponseEntity<?> responseEntity = null;
		UserDto nwUserDto = userService.updateUser(id, userDto);
		if (Objects.nonNull(nwUserDto))
			responseEntity = new ResponseEntity<>(nwUserDto, HttpStatus.OK);
		else
			responseEntity = new ResponseEntity<>("User not updated", HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteUser(@PathVariable String id) {
		ResponseEntity<?> responseEntity = null;
		boolean flag = userService.deleteUser(id);
		if (flag)
			responseEntity = new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
		else
			responseEntity = new ResponseEntity<>("User not deleted", HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
}
