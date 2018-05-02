package com.cafe24.lms.service;

import com.cafe24.lms.domain.User;
import com.cafe24.lms.repository.UserRepository;
import com.cafe24.security.Auth;
import com.cafe24.security.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void join(User user){
        user.setSalt(SHA256Util.generateSalt());
        user.setPassword(SHA256Util.getEncrypt(user.getPassword(), user.getSalt()));
        user.setRole(Auth.Role.USER);
        userRepository.save(user);
    }

    public User findUserByEmailAndPassword(User user){
        User fromDB = userRepository.findUserByEmail(user.getEmail());

        if(fromDB != null && fromDB.getPassword().equals(SHA256Util.getEncrypt(user.getPassword(), fromDB.getSalt()))){
            return fromDB;
        }

        return null;
    }

    @Transactional
    public void modify(User user){
        if(user.getPassword() == null || "".equals(user.getPassword())){
            userRepository.updateWithoutPassword(user.getName(), user.getGender());
        }else{
            user.setSalt(SHA256Util.generateSalt());
            user.setPassword(SHA256Util.getEncrypt(user.getPassword(), user.getSalt()));
            userRepository.updateWithPassword(user.getName(), user.getGender(), user.getSalt(), user.getPassword());
        }
    }

    public boolean isEmailExists(String email){
        return userRepository.findUserByEmail(email) != null;
    }
}
