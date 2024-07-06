package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    public User getUserByUserName(String userName);

    public void deleteUserById(Long id);

    public List<User> getAllUser();
}
