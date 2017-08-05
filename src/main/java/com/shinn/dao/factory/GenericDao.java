package com.shinn.dao.factory;

import java.io.Serializable;
import java.sql.Savepoint;
import java.util.List;

public interface GenericDao<T> {
    /**
     * query by id
     * @param id
     * @return
     * @throws Exception
     */
	public T getById(Integer id) throws Exception;
	/**
	 * query all 
	 * @return
	 * @throws Exception
	 */
	public List<T> findAll() throws Exception;
	/**
	 * execute save
	 * @param model
	 * @throws Exception
	 */
	public int saveUpdate(T model) throws Exception;
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Integer getCurrentKey(String table) throws Exception;
	
	public void commit();
	
	public void rollback();
	public void rollback(Savepoint savePoint);
	
	public void setSavePoint(String name) throws Exception;
	
//	public Transaction
	
	

}
