package com.redis.demo.dao;

import com.redis.demo.model.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserRepository {
		void setUserAsString(String idKey, String user);
		String getUserAsString(String idKey);
		void AddToUsersList(User user);
		List<User> getUsersListMembers();
		Long getUsersListCount();
		void AddToUsersSet(User... users);
		Set<User> getUsersSetMembers();
		boolean isSetMember(User user);
		void saveHash(User user);
		void updateHash(User user);
		Map<Integer, User> findAllHash();
		User findInHash(int id);
		void deleteHash(int id);
}
