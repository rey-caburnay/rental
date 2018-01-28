package com.shinn.dao.repos;

import java.util.List;

import com.shinn.dao.factory.GenericDao;
import com.shinn.service.model.Authority;

public interface AuthorityDao extends GenericDao<Authority> {

  List<Authority> findByUserId(Integer id);
}
