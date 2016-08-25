package com.shinn.dao.factory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.shinn.sql.SQLStatement;

public abstract class AbstractDaoImpl<T extends Serializable> {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AbstractDaoImpl.class);

    @Autowired
    private Connection connection;

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;


    Class<T> clzz;
    PreparedStatement pStmnt = null;

    SQLStatement sqlStatement = SQLStatement.getInstance();

    public AbstractDaoImpl() throws Exception {

    }

    public void setClazz(Class<T> clzz) {
        this.clzz = clzz;
    }

//    @Autowired
//    public void setDataSource(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }


    /**
     * Calling the Rollback implementation for the connection.
     *
     * @param conn
     *            - a connection object tot rollback
     * @param method
     *            - a string
     * @param ex
     *            - an exception object
     * @throws DaoException
     *             - thrown when an exception occurs
     */
    public final void rollBack(final Connection conn, final String method, final Exception ex) throws Exception {
        if (null == conn) {
            return;
        }

        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new Exception("SQLException:" + " Error on calling Rollback", e);
        }
    }

    /**
     * Closes the ResultSet, Prepared Statement and Connection Afterwards.
     *
     * @param connection
     *            The Connection Object to be closed
     * @param preparedStatement
     *            The Prepared Statement to be closed
     * @return true if successful, false if not
     * @throws DaoException
     *             - thrown when an exception occurs
     */
    public final boolean closeConnectionObjects(final Connection connection, final PreparedStatement preparedStatement)
            throws Exception {
        return closeConnectionObjects(connection, preparedStatement, null);
    }

    /**
     * Method called to close a Connection Object.
     *
     * @param connection
     *            The Connection to be closed
     * @return Return true if closing of the connection is successful, else
     *         false when closing of the connection failed.
     */
    public final boolean closeObject(Connection connection) {
        boolean hasErrorOccur = false;

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                hasErrorOccur = true;
            } finally {
                connection = null;
            }
        }

        return hasErrorOccur;

    }

    /**
     * Method called to close a ResultSet Object.
     *
     * @param resultSet
     *            The ResultSet to be closed
     * @return Return true if closing of the resultSet is successful, else false
     *         when closing of the resultSet failed.
     */
    public final boolean closeObject(ResultSet resultSet) {
        boolean hasErrorOccur = false;

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                hasErrorOccur = true;
            } finally {
                resultSet = null;
            }
        }

        return hasErrorOccur;
    }

    /**
     * Method called to close a PreparedStatement Object.
     *
     * @param preparedStatement
     *            The PreparedStatement to be closed
     * @return Return true if closing of the preparedStatement is successful,
     *         else false when closing of the preparedStatement failed.
     */
    public final boolean closeObject(PreparedStatement preparedStatement) {
        boolean hasErrorOccur = false;

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                hasErrorOccur = true;
            } finally {
                preparedStatement = null;
            }
        }

        return hasErrorOccur;
    }

    /**
     * Closes the ResultSet, Prepared Statement and Connection Afterwards.
     *
     * @param connection
     *            The Connection Object to be closed
     * @param preparedStatement
     *            The Prepared Statement to be closed
     * @param resultSet
     *            The ResultSet to be closed
     * @return true if error closing the objects.
     */
    public final boolean closeConnectionObjects(final Connection connection, final PreparedStatement preparedStatement,
            final ResultSet resultSet) throws Exception {
        boolean hasErrorOccur = false;

        hasErrorOccur = (hasErrorOccur) || closeObject(resultSet);
        hasErrorOccur = (hasErrorOccur) || closeObject(preparedStatement);
        hasErrorOccur = (hasErrorOccur) || closeObject(connection);

        if (hasErrorOccur) {
            throw new Exception("SQLException: " + "Error closing database resources.");
        }

        return hasErrorOccur;
    }

    /**
     * Sets the parameter values of PreparedStatement.
     *
     * @param preparedStatement
     *            The PreparedStatement to set the parameter values in.
     * @param values
     *            The parameter values to be set in the PreparedStatement.
     * @throws SQLException
     *             If fails when setting the PreparedStatement values.
     */
    public static void setValues(final PreparedStatement preparedStatement, final Object... values)
            throws SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
    }

    /**
     *
     * @param propertyName
     * @param parameters
     * @return
     * @throws Exception
     */
    public ResultSet query(String propertyName, Object... parameters) throws Exception {
        ResultSet result = null;
        try {
            if(connection.isClosed()) {
                connection = null;
                connection = dataSource.getConnection();
                connection.setAutoCommit(false);
            }

            pStmnt = connection.prepareStatement(sqlStatement.getProperty(propertyName));
            this.setValues(pStmnt, parameters);
            result = pStmnt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }


    /**
     *
     * @param result
     * @param model
     * @return
     * @throws Exception
     */
    public <T> List<T> getListResult(String sqlStment, Object... parameters) {
        List<T> list = new ArrayList<T>();
        try {
            ResultSet result = query(sqlStment, parameters);
            while (result.next()) {
                list.add((T) transform(result));
            }
            closeConnectionObjects(connection, pStmnt);
        } catch (Exception e) {
            list = null;

        }

        return list;
    }

    /**
     * get result set map by an object
     * @param sqlStmnt
     * @param parameters
     * @return
     */
    public <T> T getObject(String sqlStmnt, Object... parameters) {
        ResultSet result;
        T o = null;
        try {
            result = query(sqlStmnt,parameters);
            if(result.next()) {
            	o = (T) transform(result);
            }
            closeConnectionObjects(connection, pStmnt);
        } catch (Exception e) {
            logger.info(e.getMessage());
//            closeConnectionObjects(connection, sqlStmnt)
            return null;
        }
       return o;
    }

    /**
     *
     * @param result
     * @param model
     * @return
     */
    public <T> Object transform(ResultSet result) {

        /* Iterate the column-names */
        String columnName = "";
        try {
            T instance = (T) clzz.newInstance();
            for (Field f : clzz.getDeclaredFields()) {
                Object value = null;
                try {
                    columnName = f.getName();
                    logger.info("column name:" + columnName);
                    value = result.getObject(columnName);
                } catch (Exception e) {
                    logger.debug("column name:"+ columnName);
                }
                if (value != null) {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), clzz);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
            }
            return instance;

        } catch (Exception e) {
            logger.info(e.getCause() +" " + columnName);
            return null;
        }
    }

    /**
     * execute insert or update statment
     * @param sqlStmnt sql insert or update statement
     * @param parameters
     * @return
     * @throws Exception
     */
    public void executeSaveUpate(String sqlStmnt, Object... parameters) throws Exception {
        try {
            pStmnt = connection.prepareStatement(sqlStatement.getProperty(sqlStmnt));
            this.setValues(pStmnt, parameters);
            pStmnt.executeUpdate();
        }catch (Exception e) {
            logger.debug(e.getMessage());
            throw new Exception ("Failed to saved: " + e.getMessage());
        }

    }

    /**
     * get the current auto increment key
     * from the table
     * @return
     */
    public Integer getCurrentKey(String table) {
        Integer key = 1;
        try {
            ResultSet result = query("get-current-key", table);
            if(result.next()) {
                key = result.getInt(1);
            }
        }catch(Exception e) {
            logger.error(e.getMessage());
        }
        return key;
    }
    /**
     *
     */
    public final void commit() {
        try {
            this.connection.commit();
        }catch(Exception e) {
            logger.error("error commit:" + e.getMessage());
        }
    }

    /**
     * roll back the transaction
     */
    public final void rollback() {
        if (null == this.connection) {
            return;
        }

        try {
            this.connection.rollback();
        } catch (SQLException e) {
            logger.error("SQLException:" + " Error on calling Rollback", e);
        }
    }

}
