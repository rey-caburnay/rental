package com.shinn.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.dao.repos.UserDao;
import com.shinn.model.User;


@Service("userService")
public class UserServiceImpl extends AbstractDaoImpl implements UserService{
	
    @Autowired
    UserDao userDao;
    
	public UserServiceImpl() throws Exception {
        super();
    }

    private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	public List<User> findAllUsers() {
	    System.out.println("userDao:" + userDao.toString());
		try {
            return userDao.findAll();
        } catch (Exception e) {
           return null;
        }
	}
	
	public User findById(Integer id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		for(User user : users){
			if(user.getUsername().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
//	public void saveUser(User user) {
//		user.setId(counter.incrementAndGet());
//		users.add(user);
//	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getUsername())!=null;
	}

//	private static List<User> populateDummyUsers(){
//		List<User> users = new ArrayList<User>();
//		users.add(new User(counter.incrementAndGet(),"Sam",30, 70000));
//		users.add(new User(counter.incrementAndGet(),"Tom",40, 50000));
//		users.add(new User(counter.incrementAndGet(),"Jerome",45, 30000));
//		users.add(new User(counter.incrementAndGet(),"Silvia",50, 40000));
//		return users;
//	}

	public void deleteAllUsers() {
		users.clear();
	}

    public void saveUser(User user) {
        // TODO Auto-generated method stub
        
    }

    public User findById(long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
