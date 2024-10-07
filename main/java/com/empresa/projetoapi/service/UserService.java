package com.empresa.projetoapi.service;

import com.empresa.projetoapi.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList;

    public UserService(){
        userList = new ArrayList<>();

        User user1 = new User(1,"Maria",23,"maria@gmail.com");
        User user2 = new User(2,"João",23,"Joãoa@gmail.com");
        User user3 = new User(3,"José",23,"José@gmail.com");
        User user4 = new User(4,"Pedro",23,"Pedro@gmail.com");
        User user5 = new User(5,"Paulo",23,"Paulo@gmail.com");

        userList.addAll(Arrays.asList(user1,user2,user3,user4,user5));
    }

    public Optional<User> getUser(Integer id){
        Optional<User> optional = Optional.empty();
        for(User user:userList){
            if (id == user.getId()){
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }

    public List<User> getUserList() {
        return userList;
    }

    public User createUser(User user){
        userList.add(user);
        return user;
    }

    public Optional<User> updateUser(Integer id, User updatedUser) {
        Optional<User> userOptional = getUser(id);
        userOptional.ifPresent(user -> {
            user.setName(updatedUser.getName());
            user.setAge(updatedUser.getAge());
            user.setEmail(updatedUser.getEmail());
        });
        return userOptional;
    }

    public boolean deleteUser(Integer id) {
        return userList.removeIf(user -> Integer.valueOf(user.getId()).equals(id));
    }
}