package com.redis.demo.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.redis.demo.dao.UserRepository;
import com.redis.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public void setUserAsString(String idKey, String User) {
		repository.setUserAsString(idKey,User);
	}

	@Override
	public String getUserAsString(String key) {
		return 	repository.getUserAsString(key);
	}

	@Override
	public void AddToUsersList(User user) {
		repository.AddToUsersList(user);
	}

	@Override
	public List<User> getUsersListMembers() {
		return  repository.getUsersListMembers();
	}

	@Override
	public Long getUsersListCount() {
		return repository.getUsersListCount();
	}

	@Override
	public void AddToUsersSet(User... users) {
		repository.AddToUsersSet(users);
	}

	@Override
	public Set<User> getUsersSetMembers() {
		return repository.getUsersSetMembers();
	}

	@Override
	public boolean isSetMember(User user) {
		return repository.isSetMember(user);
	}

	@Override
	public void savePHash(User user) {
		repository.saveHash(user);
	}

	@Override
	public void updatePHash(User user) {
		repository.updateHash(user);
	}

	@Override
	public Map<Integer, User> findAllPHash() {
		return repository.findAllHash();
	}

	@Override
	public User findPInHash(int id) {
		return repository.findInHash( id);
	}

	@Override
	public void deletePhash(int id) {
		repository.deleteHash(id);
	}
}
