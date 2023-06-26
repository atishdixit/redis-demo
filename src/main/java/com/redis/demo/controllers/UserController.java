package com.redis.demo.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.redis.demo.model.User;
import com.redis.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService UserService;

	@RequestMapping(method = RequestMethod.POST, value = "/string")
	public void addUser(@RequestBody User user) throws JsonProcessingException {
		logger.info("Started- addUser");
		ObjectMapper mapper = new ObjectMapper();
		UserService.setUserAsString(String.valueOf(user.getId()),
				mapper.writeValueAsString(user));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/string/{id}")
	public String readString(@PathVariable String id) {
		logger.info("Started- readString");
		return UserService.getUserAsString(id);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/list")
	public void addToUserList(@RequestBody User user) {
		logger.info("Started- addToUserList");
		UserService.AddToUsersList(user);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public List<User> getUserListMembers() {
		logger.info("Started- getUserListMembers");
		return UserService.getUsersListMembers();
	}

	@RequestMapping(method = RequestMethod.GET, value ="/count")
	public Long getUserListCount() {
		logger.info("Started- getUserListCount");
		return UserService.getUsersListCount();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/set")
	public void AddToUsersToSet(@RequestBody User... users) {
		logger.info("Started- AddToUsersToSet");
		UserService.AddToUsersSet(users);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/set")
	public Set<User> getUsersSetMembers() {
		logger.info("Started- getUsersSetMembers");
		return UserService.getUsersSetMembers();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/set/member")
	public boolean isSetMember(@RequestBody User user) {
		logger.info("Started- isSetMember");
		return UserService.isSetMember(user);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/hash")
	public void savePHash(@RequestBody User user) {
		logger.info("Started- savePHash");
		UserService.savePHash(user);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/hash")
	public void updateHash(@RequestBody User user) {
		logger.info("Started- updateHash");
		UserService.updatePHash(user);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/hash")
	public Map<Integer, User> findAllHash() {
		logger.info("Started- findAllHash");
		return UserService.findAllPHash();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/hash/{id}")
	public User findInHash(@PathVariable int id) {
		logger.info("Started- FindPInHash");
		return UserService.findPInHash(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/hash/{id}")
	public void deletePhash(@PathVariable int id) {
		logger.info("Started- deletePhash");
		UserService.deletePhash(id);
	}
}
