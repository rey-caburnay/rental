package com.shinn.dao.users;

import java.lang.annotation.Annotation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDao;
import com.shinn.model.User;

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao{

    public UserDaoImpl() throws Exception {
        super();

    }

    public List<User> getUsernames() {
        
        ResultSet result = null;
        List<User> users = new ArrayList<User>();
        try {
              result = query("get-users");
              users = getListResult(result, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        
        }
        System.out.println(users.toString());
        return users;
    }
    
    public User getUser(Integer id) {
        User user = null;
       try {
           ResultSet result = query("get-users-by-id",id);
           if(result.next()) {
               user = (User) transform(result, User.class);
           }

           return user;
       }catch (Exception e) {
           return null;
       }
//       closeConnectionObjects(connection, preparedStatement)
    }

}
