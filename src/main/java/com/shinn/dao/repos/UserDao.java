package com.shinn.dao.repos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.GenericDao;
import com.shinn.service.model.User;


public interface UserDao extends GenericDao<User> {
  

  public User findByUsername(String username);
}
