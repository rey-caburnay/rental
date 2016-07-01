package com.shinn.dao.users;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.model.User;


public interface UserDao {
    
    public List<String> getUsernames();
    public User getUser(Integer id);

}
