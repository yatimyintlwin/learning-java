package com.example.redisdemo.service;

import com.example.redisdemo.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String KEY = "Person";

    public void save(Person person) {
        redisTemplate.opsForHash().put(KEY, person.getId(), person);
    }

    public Person findById(String id) {
        return (Person) redisTemplate.opsForHash().get(KEY, id);
    }

    public void update(Person person) {
        save(person);
    }

    public void delete(String id) {
        redisTemplate.opsForHash().delete(KEY, id);
    }
}
