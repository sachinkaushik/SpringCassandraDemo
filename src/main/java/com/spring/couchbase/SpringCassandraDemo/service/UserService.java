package com.spring.couchbase.SpringCassandraDemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.datastax.driver.core.utils.UUIDs;
import com.spring.couchbase.SpringCassandraDemo.document.User;
import com.spring.couchbase.SpringCassandraDemo.dto.UserDto;
import com.spring.couchbase.SpringCassandraDemo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public UserDto addUser(UserDto userDto) {
		User user = new User();
		//BeanUtils.copyProperties(userDto, user);
		
		user.setId(UUIDs.timeBased());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setAddress(userDto.getAddress());
		User nwUsr = userRepository.save(user);
		
		BeanUtils.copyProperties(nwUsr, userDto);
		
		return userDto;
	}


	public List<UserDto> getAllUser() {
		Iterable<User> users = userRepository.findAll();
		List<UserDto> userDtos = new ArrayList<>();
		for(User u: users){
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(u, userDto);
			userDtos.add(userDto);
		}
		return userDtos;
	}


	public List<UserDto> getByFirstName(String firstName) {
		List<User> users = userRepository.findByFirstName(firstName);
		List<UserDto> userDtos = new ArrayList<>();
		for(User u: users){
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(u, userDto);
			userDtos.add(userDto);
		}
		return userDtos;
	}
	
	public List<UserDto> getByLastName(String lastName) {
		List<User> users = userRepository.findByLastName(lastName);
		List<UserDto> userDtos = new ArrayList<>();
		for(User u: users){
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(u, userDto);
			userDtos.add(userDto);
		}
		return userDtos;
	}


	public boolean deleteUser(String id) {
		UUID uuid=UUID.fromString(id);
		User user = userRepository.findById(uuid);
		//User user = userRepository.findOne(id);
		boolean flag = false;
		if(Objects.nonNull(user)){
			userRepository.delete(user);
			flag = true;
		}
			
		return flag;
	}


	public UserDto updateUser(String id, UserDto userDto) {
		
		UUID uuid=UUID.fromString(id);
		User user = userRepository.findById(uuid);
		//User user = userRepository.findOne(uuid.toString());
		if(Objects.isNull(user))
			System.out.println("User doesnt exist");
		
		if(Objects.nonNull(userDto.getAddress()) && !StringUtils.isEmpty(userDto.getAddress()))
			user.setAddress(userDto.getAddress());
		if(Objects.nonNull(userDto.getFirstName()) && !StringUtils.isEmpty(userDto.getFirstName()))
			user.setFirstName(userDto.getFirstName());
		if(Objects.nonNull(userDto.getLastName()) && !StringUtils.isEmpty(userDto.getLastName()))
			user.setLastName(userDto.getLastName());
		
		User usr = userRepository.save(user);
		UserDto nwUsrDto = new UserDto();
		nwUsrDto.setAddress(usr.getAddress());
		nwUsrDto.setFirstName(usr.getFirstName());
		nwUsrDto.setLastName(usr.getLastName());
		nwUsrDto.setId(usr.getId());
		return nwUsrDto;
	}

}
