package com.example.Hazelcastdemo.config;

import com.example.Hazelcastdemo.model.user.User;
import com.example.Hazelcastdemo.repository.user.UserRepository;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.MapStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class UserMapStore implements MapStore<Integer, User> {

    HazelcastInstance hazelcast = Hazelcast.getHazelcastInstanceByName( "user-hazelcast-instance" );
    IMap<Integer, User> hzStock = hazelcast.getMap("user");
    @Override
    public void store(Integer key, User user) {
        hzStock.put(key, user);
    }

    @Override
    public void storeAll(Map<Integer, User> map) {
        hzStock.putAll(map);
    }

    @Override
    public void delete(Integer key) {
        hzStock.delete(key);
    }

    @Override
    public void deleteAll(Collection<Integer> keys) {
        log.info("999999999999999999999999999999999999999999999");

    }

    @Override
    public User load(Integer key) {
//        userRepository.findUserById(key);
        hzStock.get(key);
        return null;
    }

    @Override
    public Map<Integer, User> loadAll(Collection<Integer> keys) {
        return null;
    }

    @Override
    public Set<Integer> loadAllKeys() {
//        hzStock.localKeySet();
        log.info(String.valueOf(hzStock.size()));
        log.info(String.valueOf(hzStock.get(1)));
//        log.info(;
//        hzStock.localKeySet(id->);

        hzStock.localKeySet().forEach(System.out::println);
        log.info("999999999999999999999999999999999999999999999");

        return null;
    }
}
