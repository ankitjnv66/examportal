package com.exam.service.impl;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //Creating User.
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User existingUser = this.userRepository.findByUserName(user.getUserName());
        if(existingUser != null) {
            System.out.println("User already exists!!");
            throw new Exception("User Already Exists");
        } else {
            //Save User
            for(UserRole userRole : userRoles) {
                this.roleRepository.save(userRole.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            existingUser = this.userRepository.save(user);
        }
        return existingUser;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }
}
