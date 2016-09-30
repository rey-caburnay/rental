package com.shinn.dao.repos;

import java.lang.annotation.Annotation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.User;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao{

    public UserDaoImpl() throws Exception {
        super();
        setClazz(User.class);

    }

    public List<User> getUsernames() {
        
        ResultSet result = null;
        List<User> users = new ArrayList<User>();
        try {
              result = query("get-users");
              users = null;
        } catch (Exception e) {
            e.printStackTrace();
        
        }
        System.out.println(users.toString());
        return users;
    }
    
    public User getUser(Integer id) {
        return getObject("get-users-by-id", id);
    }

    public User getById(Integer id) throws Exception {
        return getObject("get-users-by-id",id);
    }

    public List<User> findAll() throws Exception {
        return getListResult("get-users");
    }

    public boolean update(String sqlStmnt, Object... parameters) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    public void saveUpdate(User model) throws Exception {
        // TODO Auto-generated method stub
        
    }

   

}
