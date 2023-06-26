package com.redis.demo.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.redis.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	public static final String REDIS_LIST_KEY = "UserList";
	public static final String REDIS_SET_KEY  = "UserSet";
	public static final String REDIS_HASH_KEY = "UserHash";

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	@Qualifier("listOperations")
	private ListOperations<String, User> ListOps;
	
	@Autowired
	@Qualifier("setOperations")
	private SetOperations<String, User> setOps;
	
	@Autowired
	private HashOperations<String, Integer, User> hashOps;

	@Override
	public void setUserAsString(String idKey, String User) {
		redisTemplate.opsForValue().set(idKey, User);
		redisTemplate.expire(idKey, 10, TimeUnit.SECONDS);
	}

	@Override
	public String getUserAsString(String idKey) {
		return (String) redisTemplate.opsForValue().get(idKey);
	}

	@Override
	public void AddToUsersList(User user) {
		 ListOps.leftPush(REDIS_LIST_KEY, user);
	}

	@Override
	public List<User> getUsersListMembers() {
		return ListOps.range(REDIS_LIST_KEY, 0, -1);
	}

	@Override
	public Long getUsersListCount() {
		return ListOps.size(REDIS_LIST_KEY);
	}
	
	@Override
	public void AddToUsersSet(User... users) {
		setOps.add(REDIS_SET_KEY, users);
	}

	@Override
	public Set<User> getUsersSetMembers() {
		return setOps.members(REDIS_SET_KEY);
	}

	@Override
	public boolean isSetMember(User user) {
		return setOps.isMember(REDIS_SET_KEY, user);
	}
	@Override
	public void saveHash(User user) {
		hashOps.put(REDIS_HASH_KEY, user.getId(), user);
	}

	@Override
	public void updateHash(User user) {
		hashOps.put(REDIS_HASH_KEY, user.getId(), user);
	}

	@Override
	public Map<Integer, User> findAllHash() {
		return hashOps.entries(REDIS_HASH_KEY);
	}

	@Override
	public User findInHash(int id) {
		return hashOps.get(REDIS_HASH_KEY, id);
	}

	@Override
	public void deleteHash(int id) {
		hashOps.delete(REDIS_HASH_KEY, id);
	}
}
