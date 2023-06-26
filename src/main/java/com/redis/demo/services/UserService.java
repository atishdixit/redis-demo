package com.redis.demo.services;

import com.redis.demo.model.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
		void setUserAsString(String idKey, String User);
		String getUserAsString(String key);
		void AddToUsersList(User user);
		List<User> getUsersListMembers();
		Long getUsersListCount();
		void AddToUsersSet(User... users);
		Set<User> getUsersSetMembers();
		boolean isSetMember(User user);
		void savePHash(User user);
		void updatePHash(User user);
		Map<Integer, User> findAllPHash();
		User findPInHash(int id);
		void deletePhash(int id);
}
