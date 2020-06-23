package com.shinn.dao.factory;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.shinn.sql.SQLStatement;

public abstract class AbstractDaoImpl<T extends Serializable> {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AbstractDaoImpl.class);

    @Autowired
    private static Connection connection;

    @Autowired
    private DataSource dataSource;



    Class<T> clzz;
    PreparedStatement pStmnt = null;
    
//    NamedParameterStatement namedParameterStmnt = null;
    
    NamedParameterPreparedStatement namedParameterStmnt = null;

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
     * 
     * @param autoCommit
     * @throws Exception
     */
    public void setAutoCommit(boolean autoCommit) throws Exception {
        try {
            connection.setAutoCommit(autoCommit);
        } catch (Exception e) {
            throw new Exception("SQLException:" + " Error on set auto commit", e);
        }
    }
    /**
     * 
     * @return
     * @throws SQLException
     */
    public Savepoint setSavePoint() throws SQLException {
        return connection.setSavepoint();
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
    
    public static void setValues(final NamedParameterPreparedStatement preparedStatement, final Object... values)
            throws SQLException {
        for (int i = 0; i < values.length; i++) {
//            preparedStatement.setObject(i + 1, values[i]);
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
            this.verifyConnection();
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
//            logger.info(e.getMessage());
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
//                    logger.info("column name:" + columnName);
                    value = result.getObject(columnName);
                    if (value != null) {
                      PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), clzz);
                      Method method = propertyDescriptor.getWriteMethod();
//                      logger.info(method.toString());
//                      logger.info(instance.toString());
//                      logger.info(value.toString());
                      method.invoke(instance, value);
                  }
                } catch (Exception e) {
//                    logger.info("No column name in RS:"+ columnName);
                }

            }
            return instance;

        } catch (Exception e) {
          e.printStackTrace();
//            logger.info(e.getCause() +" " + columnName);
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
    public int executeSaveUpate(String sqlStmnt, Object... parameters) throws Exception {
        try {
            this.verifyConnection();
            pStmnt = connection.prepareStatement(sqlStatement.getProperty(sqlStmnt), PreparedStatement.RETURN_GENERATED_KEYS);
            this.setValues(pStmnt, parameters);
            pStmnt.executeUpdate();
            ResultSet rs = pStmnt.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);
            }
        }catch (Exception e) {
            logger.debug(e.getMessage());
            throw new Exception ("Failed to saved: " + e.getMessage());
        }
        return 0; 

    }
    /**
     * 
     * @param sqlStmnt
     * @param parameters
     * @return
     * @throws Exception
     */
//    public int executeSaveUpate(String sqlStmnt, Map<String, Object> parameters) throws Exception {
//        try {
//            this.verifyConnection();
//            namedParameterStmnt = NamedParameterPreparedStatement.prepare(connection, sqlStatement.getProperty(sqlStmnt), PreparedStatement.RETURN_GENERATED_KEYS);
//            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
//                namedParameterStmnt.setObject(entry.getKey(), entry.getValue());     
//            }
//            namedParameterStmnt.executeUpdate();
//            ResultSet rs = namedParameterStmnt.getGeneratedKeys();
//            if(rs.next()) {
//                return rs.getInt(1);
//            }
//        }catch (Exception e) {
//            logger.debug(e.getMessage());
//            throw new Exception ("Failed to saved: " + e.getMessage());
//        }
//        return 0; 
//
//    }

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
            this.verifyConnection();
            this.connection.commit();
        }catch(Exception e) {
            logger.error("error commit:" + e.getMessage());
        }
    }

    /**
     * roll back the transaction
     */
    public final void rollback(Savepoint savePoint) {
        try {
            if (null == this.connection) {
                return;
            }
            this.connection.rollback(savePoint);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    
    public final void rollback() {
        try {
            if (null == this.connection) {
                return;
            }
            this.connection.rollback();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    
    public void setSavePoint(String name) {
        try {
            if (name == null) {
                connection.setSavepoint();
            } else {
                connection.setSavepoint(name);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    /**
     * 
     * @return
     */
    private void verifyConnection() {
        try {
            if(connection == null || connection.isClosed()) {
                connection = null;
                connection = dataSource.getConnection();
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        return connection;
    }

    public int executeSaveUpate(String sqlStmnt, Object type) throws SQLException {
        Map<String, List<Integer>> indexMap = new HashMap<>();
        int generatedKey = 0;
        boolean isNew = false;
        try {
            String sql = NamedParameterParser.parse( sqlStatement.getProperty(sqlStmnt), indexMap);
            if (sql.contains("INSERT")) {
                isNew = true;
            }
            this.verifyConnection();
            if (isNew) {
                namedParameterStmnt = NamedParameterPreparedStatement.prepare(connection, sqlStatement.getProperty(sqlStmnt), PreparedStatement.RETURN_GENERATED_KEYS);

            } else {
                namedParameterStmnt = NamedParameterPreparedStatement.prepare(connection, sqlStatement.getProperty(sqlStmnt));
            }
            logger.info(namedParameterStmnt.toString());
            for (PropertyDescriptor pd : Introspector.getBeanInfo(type.getClass()).getPropertyDescriptors()) {
                if (pd.getReadMethod() != null && !"class".equals(pd.getName()))
                    if (indexMap.containsKey(pd.getName())) {
                        try {
                            namedParameterStmnt.setObject(pd.getName(), pd.getReadMethod().invoke(type));
                        } catch (Exception e) {
                            logger.debug(e.getMessage() + "property name :" + pd.getName());
                        }
                    }
              }
            int rows = namedParameterStmnt.executeUpdate();
            if (isNew) {
                ResultSet rs = namedParameterStmnt.getGeneratedKeys();
                if(rs.next()) {
                    generatedKey = rs.getInt(1);
                } 
            } else {
                generatedKey = rows;
            }
           
        } catch (Exception e) {
            logger.error("Saveupdate:{}",e);
            generatedKey = 0;
            throw (SQLException) e.getCause();
            
        }
        return generatedKey;
    }
}
