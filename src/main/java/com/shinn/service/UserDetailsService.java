package com.shinn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shinn.dao.repos.AuthorityDao;
import com.shinn.dao.repos.UserDao;
import com.shinn.exception.UserNotEnabledException;
import com.shinn.service.model.Authority;
import com.shinn.service.model.User;
import com.shinn.util.PasswordDigest;
import com.shinn.util.RentStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthorityDao authorityDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        User user = userDao.findByUsername(login);
        if (user == null) {
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        } else if (!user.getStatus().equals(RentStatus.ACTIVE)) {
            throw new UserNotEnabledException("User " + login + " was not enabled");
        }
        List<Authority> authorities = authorityDao.findByUserId(user.getId()); 
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (Authority authority : authorities) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }
//        String password = PasswordDigest.digest(user, salt)  
//        String password = PasswordDigest.digest(passwordToHash, salt);
        return new org.springframework.security.core.userdetails.User(login, user.getPassword(),
                grantedAuthorities);
    }
}
