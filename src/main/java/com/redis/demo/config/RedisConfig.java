package com.redis.demo.config;

import com.redis.demo.model.User;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
// https://commons.apache.org/proper/commons-pool/apidocs/org/apache/commons/pool2/impl/GenericObjectPool.html
@Configuration
public class RedisConfig {

	@Value("${redis.host}")
	private String host;
	@Value("${redis.password}")
	private String password;
	@Value("${redis.port}")
	private int port;

	@Value("${redis.jedis.pool.max-total}")
	private int maxTotal;
	@Value("${redis.jedis.pool.max-idle}")
	private int maxIdle;
	@Value("${redis.jedis.pool.min-idle}")
	private int minIdle;

	@Bean
	public JedisClientConfiguration getJedisClientConfiguration() {
		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder JedisPoolingClientConfigurationBuilder = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration
				.builder();
		GenericObjectPoolConfig GenericObjectPoolConfig = new GenericObjectPoolConfig();
		GenericObjectPoolConfig.setMaxTotal(maxTotal);
		GenericObjectPoolConfig.setMaxIdle(maxIdle);
		GenericObjectPoolConfig.setMinIdle(minIdle);
		return JedisPoolingClientConfigurationBuilder.poolConfig(GenericObjectPoolConfig).build();
	}

	@Bean
	public JedisConnectionFactory getJedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(host);
		if (password!=null) {
			redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
		}
		redisStandaloneConfiguration.setPort(port);
		return new JedisConnectionFactory(redisStandaloneConfiguration, getJedisClientConfiguration());
	}

	@Bean
	public RedisTemplate redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(getJedisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
//   	 redisTemplate.setKeySerializer(new StringRedisSerializer());
//   	 redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new StringRedisSerializer()));
		return redisTemplate;
	}

	@Bean
	@Qualifier("listOperations")
	public ListOperations<String, User> listOperations(RedisTemplate<String, User> redisTemplate) {
		return redisTemplate.opsForList();
	}

	@Bean
	@Qualifier("setOperations")
	public SetOperations<String, User> SetOperations(RedisTemplate<String, User> redisTemplate) {
		return redisTemplate.opsForSet();
	}
	
    @Bean
    public HashOperations<String, Integer, User> hashOps(RedisTemplate<String, Object>  redisTemplate) {
        return redisTemplate.opsForHash();
    }
}
