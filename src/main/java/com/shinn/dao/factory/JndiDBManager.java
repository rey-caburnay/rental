package com.shinn.dao.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;



/**
 * JndiDBManager is a class implementation for DBManager.
 * This class is a Database Manager.
 *
 * @see     DBManager
 */
public class JndiDBManager  {
    /**
     * The initial Context used by the class.
     */

    protected InitialContext initContext;
    /**
     * The only instance of the class.
     */

    protected static volatile DataSource dataSource;
    /**
     * The Autocommit flag.
     */
    private static boolean allowAutocommit = true;

    /**
     * Constructor of the class.
     * @throws Exception 
     * @throws DaoException - thrown when an exception occurs
     */
    public JndiDBManager() throws Exception {    	
        this.getMysqlConnection();
    }

    /**
     * Sets the initial context of the DBManager.
     *
     * @param initContextToSet - instance of the initial context to be set
     */
    public void setInitialContext(final InitialContext initContextToSet) {
        this.initContext = initContextToSet;
    }

    /**
     * Gets the initial context of the class.
     *
     * @return    The initial context of the class
     */
    public InitialContext getInitialContext() {
        return this.initContext;
    }

    /**
     * Get a connection from the DataSource used by the class.
     * @return Connection - a connection object used to interact with database
     * @throws SQLException Exception thrown when getting the connection fails.
     */
    public Connection getConnection() throws SQLException {
        try {
            Connection conn = dataSource.getConnection();
            if (conn.getAutoCommit()
                    && (!JndiDBManager.allowAutocommit)) {
                conn.setAutoCommit(false);
            }
            return conn;
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Prevents the Cloning of the class object because it is a singleton.
     * @return object - unused
     * @throws CloneNotSupportedException Exception thrown
     *      when a cloning of the object took place.
     */
    public Object clone() throws CloneNotSupportedException {

            throw new CloneNotSupportedException();
    }

    /**
     * Set AutoCommit to false.
     */
    public static void setAutoCommitFalse() {
        JndiDBManager.allowAutocommit = false;
    }
    
    private void getMysqlConnection () throws Exception  {
        
        try {
            setInitialContext(new InitialContext());
            Context env = (Context) this.initContext.lookup("java:comp/env");
            dataSource = (DataSource) env.lookup("jdbc/MySQL");
        } catch (NamingException e) {
            throw new Exception("NamingException: Unable to find datasource", e);
        }
    }
    
}
