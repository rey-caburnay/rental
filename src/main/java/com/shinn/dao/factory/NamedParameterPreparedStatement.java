package com.shinn.dao.factory;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;

import java.io.InputStream;
import java.io.Reader;

import java.math.BigDecimal;

import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;

import java.lang.reflect.Proxy;

/**
 * This class extends a {@link PreparedStatement} to allow setting parameters by name instead of by
 * index. This eliminates any confusion as to which parameter index represents what. This also means
 * that rearranging the SQL statement or adding a parameter doesn't involve renumbering your
 * indices. Code such as this:
 *
 * Connection con = getConnection(); String query = "select * from my_table where name = ? or
 * address = ?"; PreparedStatement p = con.prepareStatement(query); p.setString(1, "bob");
 * p.setString(2, "123 terrace ct"); ResultSet rs = p.executeQuery();
 *
 * can be replaced with:
 *
 * Connection con = getConnection(); String query = "select * from my_table where name = :name or
 * address = :address"; NamedParameterPreparedStatement.java p = new
 * NamedParameterPreparedStatement.java(con, query); p.setString("name", "bob");
 * p.setString("address", "123 terrace ct"); ResultSet rs = p.executeQuery();
 *
 * @see http://www.javaworld.com/article/2077706/core-java/named-parameters-for-preparedstatement.html
 */
public interface NamedParameterPreparedStatement extends PreparedStatement {
    /**
     * Returns a new instance of {@code NamedParameterPreparedStatement} that may be used any number
     * of times to execute parameterized requests on the database server.
     *
     * Subject to JDBC driver support, this operation will attempt to send the precompiled version
     * of the statement to the database. If the driver does not support precompiled statements, the
     * statement will not reach the database server until it is executed. This distinction
     * determines the moment when {@code SQLException}s get raised.
     *
     * By default, {@code ResultSet}s from the returned object will be
     * {@link ResultSet#TYPE_FORWARD_ONLY} type with a {@link ResultSet#CONCUR_READ_ONLY} mode of
     * concurrency.
     *
     * @param connection the SQL connection.
     * @param sql the SQL statement.
     * @return the {@code PreparedStatement} containing the supplied SQL statement.
     * @throws SQLException if there is a problem accessing the database.
     */
    public static NamedParameterPreparedStatement prepare(Connection connection, String sql)
            throws SQLException {
        Map<String, List<Integer>> indexMap = new HashMap<>();
        PreparedStatement s =
                connection.prepareStatement(NamedParameterParser.parse(sql, indexMap));
        return construct(s, indexMap);
    }

    /**
     * Creates a default {@code NamedParameterPreparedStatement} that can retrieve automatically
     * generated keys. Parameter {@code autoGeneratedKeys} may be used to tell the driver whether
     * such keys should be made accessible. This is only relevant when the {@code sql} statement is
     * an {@code insert} statement.
     *
     * An SQL statement which may have {@code IN} parameters can be stored and precompiled in a
     * {@code PreparedStatement}. The {@code PreparedStatement} can then be then be used to execute
     * the statement multiple times in an efficient way.
     *
     * Subject to JDBC driver support, this operation will attempt to send the precompiled version
     * of the statement to the database. If the driver does not support precompiled statements, the
     * statement will not reach the database server until it is executed. This distinction
     * determines the moment when {@code SQLException}s get raised.
     *
     * By default, {@code ResultSet}s from the returned object will be
     * {@link ResultSet#TYPE_FORWARD_ONLY} type with a {@link ResultSet#CONCUR_READ_ONLY} mode of
     * concurrency.
     *
     * @param connection the SQL connection.
     * @param sql the SQL statement.
     * @param autoGeneratedKeys one of the following generated key options:
     *        <ul>
     *        <li>{@link Statement#RETURN_GENERATED_KEYS}</li>
     *        <li>{@link Statement#NO_GENERATED_KEYS}</li>
     *        </ul>
     * @return a new {@code PreparedStatement} instance representing the input SQL statement.
     * @throws SQLException if there is a problem accessing the database.
     */
    public static NamedParameterPreparedStatement prepare(Connection connection, String sql,
            int autoGeneratedKeys) throws SQLException {
        Map<String, List<Integer>> indexMap = new HashMap<>();
        PreparedStatement s = connection.prepareStatement(NamedParameterParser.parse(sql, indexMap),
                autoGeneratedKeys);
        return construct(s, indexMap);
    }

    /**
     * Creates a default {@code NamedParameterPreparedStatement} that can retrieve the
     * auto-generated keys designated by a supplied array. If {@code sql} is an SQL {@code INSERT}
     * statement, the parameter {@code
     * columnIndexes} is expected to hold the index values for each column in the statement's
     * intended database table containing the autogenerated-keys of interest. Otherwise
     * {@code columnIndexes} is ignored.
     *
     * Subject to JDBC driver support, this operation will attempt to send the precompiled version
     * of the statement to the database. If the driver does not support precompiled statements, the
     * statement will not reach the database server until it is executed. This distinction
     * determines the moment when {@code SQLException}s get raised.
     *
     * By default, {@code ResultSet}s from the returned object will be
     * {@link ResultSet#TYPE_FORWARD_ONLY} type with a {@link ResultSet#CONCUR_READ_ONLY}
     * concurrency mode.
     *
     * @param connection the SQL connection.
     * @param sql the SQL statement.
     * @param columnIndexes the indexes of the columns for which auto-generated keys should be made
     *        available.
     * @return the PreparedStatement containing the supplied SQL statement.
     * @throws SQLException if a problem occurs accessing the database.
     */
    public static NamedParameterPreparedStatement prepare(Connection connection, String sql,
            int[] columnIndexes) throws SQLException {
        Map<String, List<Integer>> indexMap = new HashMap<>();
        PreparedStatement s = connection.prepareStatement(NamedParameterParser.parse(sql, indexMap),
                columnIndexes);
        return construct(s, indexMap);
    }

    /**
     * Creates a {@code NamedParameterPreparedStatement} that generates {@code ResultSet}s with the
     * specified values of {@code
     * resultSetType} and {@code resultSetConcurrency}.
     *
     * @param connection the SQL connection.
     * @param sql the SQL statement. It can contain one or more {@code '?'} {@code IN} parameter
     *        placeholders.
     * @param resultSetType one of the following type specifiers:
     *        <ul>
     *        <li>{@link ResultSet#TYPE_SCROLL_SENSITIVE}</li>
     *        <li>{@link ResultSet#TYPE_SCROLL_INSENSITIVE}</li>
     *        <li>{@link ResultSet#TYPE_FORWARD_ONLY}</li>
     *        </ul>
     * @param resultSetConcurrency one of the following concurrency mode specifiers:
     *        <ul>
     *        <li>{@link ResultSet#CONCUR_READ_ONLY}</li>
     *        <li>{@link ResultSet#CONCUR_UPDATABLE}</li>
     *        </ul>
     * @return a new instance of {@code PreparedStatement} containing the SQL statement {@code sql}.
     *         {@code ResultSet}s emitted from this {@code PreparedStatement} will satisfy the
     *         specified {@code
     *         resultSetType} and {@code resultSetConcurrency} values.
     * @throws SQLException if a problem occurs accessing the database.
     */
    public static NamedParameterPreparedStatement prepare(Connection connection, String sql,
            int resultSetType, int resultSetConcurrency) throws SQLException {
        Map<String, List<Integer>> indexMap = new HashMap<>();
        PreparedStatement s = connection.prepareStatement(NamedParameterParser.parse(sql, indexMap),
                resultSetType, resultSetConcurrency);
        return construct(s, indexMap);
    }

    /**
     * Creates a {@code NamedParameterPreparedStatement} that generates {@code ResultSet}s with the
     * specified type, concurrency and holdability
     *
     * @param connection the SQL connection.
     * @param sql the SQL statement. It can contain one or more {@code '?' IN} parameter
     *        placeholders.
     * @param resultSetType one of the following type specifiers:
     *        <ul>
     *        <li>{@link ResultSet#TYPE_SCROLL_SENSITIVE}</li>
     *        <li>{@link ResultSet#TYPE_SCROLL_INSENSITIVE}</li>
     *        <li>{@link ResultSet#TYPE_FORWARD_ONLY}</li>
     *        </ul>
     * @param resultSetConcurrency one of the following concurrency mode specifiers:
     *        <ul>
     *        <li>{@link ResultSet#CONCUR_READ_ONLY}</li>
     *        <li>{@link ResultSet#CONCUR_UPDATABLE}</li>
     *        </ul>
     * @param resultSetHoldability one of the following holdability mode specifiers:
     *        <ul>
     *        <li>{@link ResultSet#HOLD_CURSORS_OVER_COMMIT}</li>
     *        <li>{@link ResultSet#CLOSE_CURSORS_AT_COMMIT}</li>
     *        </ul>
     * @return a new instance of {@code PreparedStatement} containing the SQL statement {@code sql}.
     *         {@code ResultSet}s emitted from this {@code PreparedStatement} will satisfy the
     *         specified {@code
     *         resultSetType}, {@code resultSetConcurrency} and {@code
     *         resultSetHoldability} values.
     * @throws SQLException if a problem occurs accessing the database.
     */
    public static NamedParameterPreparedStatement prepare(Connection connection, String sql,
            int resultSetType, int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        Map<String, List<Integer>> indexMap = new HashMap<>();
        PreparedStatement s = connection.prepareStatement(NamedParameterParser.parse(sql, indexMap),
                resultSetType, resultSetConcurrency, resultSetHoldability);
        return construct(s, indexMap);
    }

    /**
     * Creates a default {@code NamedParameterPreparedStatement} that can retrieve the
     * auto-generated keys designated by a supplied array. If {@code sql} is an SQL {@code INSERT}
     * statement, {@code columnNames} is expected to hold the names of each column in the
     * statement's associated database table containing the autogenerated-keys of interest.
     * Otherwise {@code columnNames} is ignored.
     *
     * Subject to JDBC driver support, this operation will attempt to send the precompiled version
     * of the statement to the database. Alternatively, if the driver is not capable of handling
     * precompiled statements, the statement will not reach the database server until it is
     * executed. This will have a bearing on precisely <i>when</i> {@code SQLException} instances
     * get raised.
     *
     * By default, ResultSets from the returned object will be {@link ResultSet#TYPE_FORWARD_ONLY}
     * type with a {@link ResultSet#CONCUR_READ_ONLY} concurrency mode.
     *
     * @param connection the SQL connection.
     * @param sql the SQL statement.
     * @param columnNames the names of the columns for which auto-generated keys should be made
     *        available.
     * @return the PreparedStatement containing the supplied SQL statement.
     * @throws SQLException if a problem occurs accessing the database.
     */
    public static NamedParameterPreparedStatement prepare(Connection connection, String sql,
            String[] columnNames) throws SQLException {
        Map<String, List<Integer>> indexMap = new HashMap<>();
        PreparedStatement s =
                connection.prepareStatement(NamedParameterParser.parse(sql, indexMap), columnNames);
        return construct(s, indexMap);
    }

    /**
     * Construct an InvocationHandler that proxies toa PreparedStatement.
     */
    /* package */ static NamedParameterPreparedStatement construct(PreparedStatement statement,
            Map<String, List<Integer>> indexMap) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        Object proxy =
                Proxy.newProxyInstance(loader, new Class[] {NamedParameterPreparedStatement.class},
                        new NPPSInvocationHandler(statement, indexMap));

        return (NamedParameterPreparedStatement) proxy;
    }


    /**
     * Sets the value of a specified parameter to the supplied {@code Array}.
     *
     * @param parameterName the parameter name.
     * @param theArray a {@code java.sql.Array} giving the new value of the parameter named
     *        {@code parameterName}.
     * @throws SQLException if a database error happens.
     * @see Array
     */
    public void setArray(String parameterName, Array theArray) throws SQLException;

    /**
     * Sets the value of a specified parameter to the content of a supplied {@code InputStream},
     * which has a specified number of bytes.
     * <p>
     * This is a good method for setting an SQL {@code LONGVARCHAR} parameter where the length of
     * the data is large. Data is read from the {@code
     * InputStream} until end-of-file is reached or the specified number of bytes is copied.
     *
     * @param parameterName the parameter name.
     * @param theInputStream the ASCII {@code InputStream} carrying the data to which the parameter
     *        named {@code parameterName} is set.
     * @param length the number of bytes in the {@code InputStream} to copy to the parameter.
     * @throws SQLException if a database error happens.
     */
    public void setAsciiStream(String parameterName, InputStream theInputStream, int length)
            throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied {@code
     * java.math.BigDecimal} value.
     *
     * @param parameterName the parameter name.
     * @param theBigDecimal the value to which the parameter named {@code parameterName} is set.
     * @throws SQLException if a database error happens.
     * @see java.math.BigDecimal
     */
    public void setBigDecimal(String parameterName, BigDecimal theBigDecimal) throws SQLException;

    /**
     * Sets the value of a specified parameter to the content of a supplied binary
     * {@code InputStream}, which has a specified number of bytes.
     * <p>
     * Use this method when a large amount of data needs to be set into a {@code LONGVARBINARY}
     * parameter.
     *
     * @param parameterName the parameter name.
     * @param theInputStream the binary {@code InputStream} carrying the data to update the
     *        parameter.
     * @param length the number of bytes in the {@code InputStream} to copy to the parameter.
     * @throws SQLException if a database error happens.
     */
    public void setBinaryStream(String parameterName, InputStream theInputStream, int length)
            throws SQLException;

    /**
     * Sets the value of a specified parameter to the given {@code Blob} object.
     *
     * @param parameterName the parameter name.
     * @param theBlob the {@code java.sql.Blob} to which the parameter named {@code
     *            parameterName} is set.
     * @throws SQLException if a database error happens.
     * @see Blob
     */
    public void setBlob(String parameterName, Blob theBlob) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied {@code boolean} value.
     *
     * @param parameterName the parameter name.
     * @param theBoolean the boolean value to which the parameter named {@code
     *            parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setBoolean(String parameterName, boolean theBoolean) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied {@code byte} value.
     *
     * @param parameterName the parameter name.
     * @param theByte the byte value to which the parameter named {@code
     *            parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setByte(String parameterName, byte theByte) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied array of bytes. The array is mapped to
     * a {@code VARBINARY} or {@code LONGVARBINARY} in the database.
     *
     * @param parameterName the parameter name.
     * @param theBytes the array of bytes to which the parameter named {@code
     *            parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setBytes(String parameterName, byte[] theBytes) throws SQLException;

    /**
     * Sets the value of a specified parameter to the character content of a {@code Reader} object,
     * with the specified length of character data.
     * <p>
     * Data is read from the {@code
     * Reader} until end-of-file is reached or the specified number of characters are copied.
     *
     * @param parameterName the parameter name.
     * @param reader the {@code java.io.Reader} containing the character data.
     * @param length the number of characters to be read.
     * @throws SQLException if a database error happens.
     */
    public void setCharacterStream(String parameterName, Reader reader, int length)
            throws SQLException;

    /**
     * Sets the value of a specified parameter to the given {@code Clob} object.
     *
     * @param parameterName the parameter name.
     * @param theClob a {@code java.sql.Clob} holding the data to which the parameter named
     *        {@code parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setClob(String parameterName, Clob theClob) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied {@code
     * java.sql.Date} value.
     *
     * @param parameterName the parameter name.
     * @param theDate a {@code java.sql.Date} to which the parameter named {@code
     *            parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setDate(String parameterName, Date theDate) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied {@code
     * java.sql.Date} value, using a supplied {@code Calendar} to map the Date. The {@code Calendar}
     * allows the application to control the timezone used to compute the SQL {@code DATE} in the
     * database - without the supplied {@code Calendar}, the driver uses the VM defaults. See
     * "<a href="../util/Locale.html#default_locale">Be wary of the default locale</a>".
     *
     * @param parameterName the parameter name.
     * @param theDate a {@code java.sql.Date} to which the parameter named {@code
     *            parameterName} is set.
     * @param cal a {@code Calendar} to use to construct the SQL {@code DATE} value.
     * @throws SQLException if a database error happens.
     * @see Date
     * @see java.util.Calendar
     */
    public void setDate(String parameterName, Date theDate, Calendar cal) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied {@code double} value.
     *
     * @param parameterName the parameter name.
     * @param theDouble the {@code double} value to which the parameter named {@code
     *            parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setDouble(String parameterName, double theDouble) throws SQLException;

    /**
     * Sets the value of a specified parameter to to a supplied {@code float} value.
     *
     * @param parameterName the parameter name.
     * @param theFloat the {@code float} value to update the parameter.
     * @throws SQLException if a database error happens.
     */
    public void setFloat(String parameterName, float theFloat) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied {@code int} value.
     *
     * @param parameterName the parameter name.
     * @param theInt the {@code int} value to which the parameter named {@code
     *            parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setInt(String parameterName, int theInt) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied {@code long} value.
     *
     * @param parameterName the parameter name.
     * @param theLong the {@code long} value to which the parameter named {@code
     *            parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setLong(String parameterName, long theLong) throws SQLException;

    /**
     * Sets the value of a specified parameter to SQL {@code NULL}. Don't use this version of
     * {@code setNull} for <i>User Defined Types</i> (UDT) or for REF type parameters.
     *
     * @param parameterName the parameter name.
     * @param sqlType the SQL type of the parameter, as defined in {@code
     *            java.sql.Types}.
     * @throws SQLException if a database error happens.
     */
    public void setNull(String parameterName, int sqlType) throws SQLException;

    /**
     * Sets the value of a specified parameter to SQL {@code NULL}. This version of {@code setNull}
     * should be used for <i>User Defined Types</i> (UDTs) and also REF types. UDTs can be
     * {@code STRUCT}, {@code DISTINCT}, {@code
     * JAVA_OBJECT} and named array types.
     * <p>
     * Applications must provide the SQL type code and also a fully qualified SQL type name when
     * supplying a {@code NULL} UDT or REF. For a UDT, the type name is the type name of the
     * parameter itself, but for a REF parameter the type name is the type name of the referenced
     * type.
     *
     * @param paramIndex the parameter number index, where the first parameter has index 1.
     * @param sqlType the SQL type of the parameter, as defined in {@code
     *            java.sql.Types}.
     * @param typeName the fully qualified name of a UDT or REF type - ignored if the parameter is
     *        not a UDT.
     * @throws SQLException if a database error happens.
     * @see Types
     */
    public void setNull(int paramIndex, int sqlType, String typeName) throws SQLException;

    /**
     * Sets the value of a specified parameter using a supplied object.
     * <p>
     * There is a standard mapping from Java types to SQL types, defined in the JDBC specification.
     * The passed object is then transformed into the appropriate SQL type, and then transferred to
     * the database. {@code
     * setObject} can be used to pass abstract data types unique to the database, by using a JDBC
     * driver specific Java type. If the object's class implements the interface {@code SQLData},
     * the JDBC driver calls {@code SQLData.writeSQL} to write it to the SQL data stream. If the
     * object's class implements {@code Ref}, {@code Blob}, {@code Clob}, {@code Struct}, or
     * {@code Array}, the driver passes it to the database as a value of the corresponding SQL type.
     *
     * @param parameterName the parameter name.
     * @param theObject the object containing the value to which the parameter named
     *        {@code parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setObject(String parameterName, Object theObject) throws SQLException;

    /**
     * Sets the value of a specified parameter using a supplied object.
     * <p>
     * The object is converted to the given {@code targetSqlType} before it is sent to the database.
     * If the object has a custom mapping (its class implements the interface {@code SQLData}), the
     * JDBC driver will call the method {@code SQLData.writeSQL} to write it to the SQL data stream.
     * If the object's class implements {@code Ref}, {@code Blob}, {@code Clob}, {@code Struct}, or
     * {@code Array}, the driver will pass it to the database in the form of the relevant SQL type.
     *
     * @param parameterName the parameter name.
     * @param theObject the Object containing the value to which the parameter named
     *        {@code parameterName} is set.
     * @param targetSqlType the SQL type to send to the database, as defined in {@code
     *            java.sql.Types}.
     * @throws SQLException if a database error happens.
     */
    public void setObject(String parameterName, Object theObject, int targetSqlType)
            throws SQLException;

    /**
     * Sets the value of a specified parameter using a supplied object.
     * <p>
     * The object is converted to the given {@code targetSqlType} before it is sent to the database.
     * If the object has a custom mapping (its class implements the interface {@code SQLData}), the
     * JDBC driver will call the method {@code SQLData.writeSQL} to write it to the SQL data stream.
     * If the object's class implements {@code Ref}, {@code Blob}, {@code Clob}, {@code Struct}, or
     * {@code Array}, the driver will pass it to the database in the form of the relevant SQL type.
     *
     * @param parameterName the parameter name.
     * @param theObject the Object containing the value to which the parameter named
     *        {@code parameterName} is set.
     * @param targetSqlType the SQL type to send to the database, as defined in {@code
     *            java.sql.Types}.
     * @param scale the number of digits after the decimal point - only applies to the types
     *        {@code java.sql.Types.DECIMAL} and {@code
     *            java.sql.Types.NUMERIC} - ignored for all other types.
     * @throws SQLException if a database error happens.
     */
    public void setObject(String parameterName, Object theObject, int targetSqlType, int scale)
            throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied {@code
     * REF(<structured-type>)} value. This is stored as an SQL {@code REF}.
     *
     * @param parameterName the parameter name.
     * @param theRef a {@code java.sql.Ref} value to which the parameter named {@code
     *            parameterName} is set.
     * @throws SQLException if a database error happens.
     * @see Ref
     */
    public void setRef(String parameterName, Ref theRef) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied {@code short} value.
     *
     * @param parameterName the parameter name.
     * @param theShort a {@code short} value to which the parameter named {@code
     *            parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setShort(String parameterName, short theShort) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied string.
     *
     * @param parameterName the parameter name.
     * @param theString the value to which the parameter named {@code parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setString(String parameterName, String theString) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied {@code
     * java.sql.Time} value.
     *
     * @param parameterName the parameter name.
     * @param theTime a {@code java.sql.Time} value to which the parameter named
     *        {@code parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setTime(String parameterName, Time theTime) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied {@code
     * java.sql.Time} value, using a supplied {@code Calendar}.
     * <p>
     * The driver uses the supplied {@code Calendar} to create the SQL {@code
     * TIME} value, which allows it to use a custom timezone - otherwise the driver uses the VM
     * defaults. See "<a href="../util/Locale.html#default_locale">Be wary of the default
     * locale</a>".
     *
     * @param parameterName the parameter name.
     * @param theTime a {@code java.sql.Time} value to which the parameter named
     *        {@code parameterName} is set.
     * @param cal a {@code Calendar} to use to construct the SQL {@code TIME} value.
     * @throws SQLException if a database error happens.
     * @see Time
     * @see java.util.Calendar
     */
    public void setTime(String parameterName, Time theTime, Calendar cal) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied java.sql.Timestamp value.
     *
     * @param parameterName the parameter name.
     * @param theTimestamp the java.sql.Timestamp value to which the parameter named {@code
     *            parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setTimestamp(String parameterName, Timestamp theTimestamp) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied {@code
     * java.sql.Timestamp} value, using the supplied {@code Calendar}.
     * <p>
     * The driver uses the supplied {@code Calendar} to create the SQL {@code
     * TIMESTAMP} value, which allows it to use a custom timezone - otherwise the driver uses the VM
     * defaults. See "<a href="../util/Locale.html#default_locale">Be wary of the default
     * locale</a>".
     *
     * @param parameterName the parameter name.
     * @param theTimestamp the {@code java.sql.Timestamp} value to which the parameter named
     *        {@code parameterName} is set.
     * @param cal a {@code Calendar} to use to construct the SQL {@code
     *            TIMESTAMP} value
     * @throws SQLException if a database error happens.
     * @see Timestamp
     * @see java.util.Calendar
     */
    public void setTimestamp(String parameterName, Timestamp theTimestamp, Calendar cal)
            throws SQLException;

    /**
     * Sets the value of a specified parameter to the characters from a supplied
     * {@code InputStream}, with a specified number of bytes.
     *
     * @deprecated Use {@link #setCharacterStream(int, Reader, int)}
     * @param parameterName the parameter name.
     * @param theInputStream the {@code InputStream} with the character data to which the parameter
     *        named {@code parameterName} is set.
     * @param length the number of bytes to read from the {@code InputStream}.
     * @throws SQLException if a database error happens.
     */
    @Deprecated
    public void setUnicodeStream(String parameterName, InputStream theInputStream, int length)
            throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied {@code
     * java.net.URL}.
     *
     * @param parameterName the parameter name.
     * @param theURL the {@code URL} to which the parameter named {@code
     *            parameterName} is set.
     * @throws SQLException if a database error happens.
     * @see URL
     */
    public void setURL(String parameterName, URL theURL) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied {@code
     * java.sql.RowId}.
     *
     * @param parameterName the parameter name.
     * @param theRowId the {@code RowId} to which the parameter named {@code
     *            parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setRowId(String parameterName, RowId theRowId) throws SQLException;

    /**
     * Sets the value of a specified parameter to a supplied string.
     *
     * @param parameterName the parameter name.
     * @param theString the {@code String} to which the parameter named {@code
     *            parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setNString(String parameterName, String theString) throws SQLException;

    /**
     * Sets the value of the specified parameter to the next {@code length} characters from
     * {@code reader}.
     *
     * @param parameterName the parameter name.
     * @param reader the {@code Reader}
     * @param length character count
     * @throws SQLException if a database error happens.
     */
    public void setNCharacterStream(String parameterName, Reader reader, long length)
            throws SQLException;

    /**
     * Sets the value of the specified parameter to {@code value}.
     *
     * @param parameterName the parameter name.
     * @param value the {@code NClob} to which the parameter named {@code
     *            parameterName} is set.
     * @throws SQLException if a database error happens.
     */
    public void setNClob(String parameterName, NClob value) throws SQLException;

    /**
     * Sets the value of the specified parameter to the next {@code length} characters from
     * {@code reader}.
     *
     * @param parameterName the parameter name.
     * @param reader the {@code Reader}
     * @param length character count
     * @throws SQLException if a database error happens.
     */
    public void setClob(String parameterName, Reader reader, long length) throws SQLException;

    /**
     * Sets the value of the specified parameter to the next {@code length} bytes from
     * {@code inputStream}.
     *
     * @param parameterName the parameter name.
     * @param inputStream the {@code InputStream}
     * @param length character count
     * @throws SQLException if a database error happens.
     */
    public void setBlob(String parameterName, InputStream inputStream, long length)
            throws SQLException;

    /**
     * Sets the value of the specified parameter to the next {@code length} characters from
     * {@code reader}.
     *
     * @param parameterName the parameter name.
     * @param reader the {@code Reader}
     * @param length character count
     * @throws SQLException if a database error happens.
     */
    public void setNClob(String parameterName, Reader reader, long length) throws SQLException;

    /**
     * Sets the value of the specified parameter to the value of {@code xmlObject}.
     *
     * @param parameterName the parameter name.
     * @param xmlObject the {@code SQLXML}
     * @throws SQLException if a database error happens.
     */
    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException;

    /**
     * Sets the value of the specified parameter to the next {@code length} bytes from
     * {@code inputStream}.
     *
     * @param parameterName the parameter name.
     * @param inputStream the {@code InputStream}
     * @param length character count
     * @throws SQLException if a database error happens.
     */
    public void setAsciiStream(String parameterName, InputStream inputStream, long length)
            throws SQLException;

    /**
     * Sets the value of the specified parameter to the next {@code length} bytes from
     * {@code inputStream}.
     *
     * @param parameterName the parameter name.
     * @param inputStream the {@code InputStream}
     * @param length character count
     * @throws SQLException if a database error happens.
     */
    public void setBinaryStream(String parameterName, InputStream inputStream, long length)
            throws SQLException;

    /**
     * Sets the value of the specified parameter to the next {@code length} characters from
     * {@code reader}.
     *
     * @param parameterName the parameter name.
     * @param reader the {@code Reader}
     * @param length character count
     * @throws SQLException if a database error happens.
     */
    public void setCharacterStream(String parameterName, Reader reader, long length)
            throws SQLException;

    /**
     * Sets the value of the specified parameter to the bytes from {@code inputStream}.
     *
     * @param parameterName the parameter name.
     * @param inputStream the {@code InputStream}
     * @throws SQLException if a database error happens.
     */
    public void setAsciiStream(String parameterName, InputStream inputStream) throws SQLException;

    /**
     * Sets the value of the specified parameter to the bytes from {@code inputStream}.
     *
     * @param parameterName the parameter name.
     * @param inputStream the {@code InputStream}
     * @throws SQLException if a database error happens.
     */
    public void setBinaryStream(String parameterName, InputStream inputStream) throws SQLException;

    /**
     * Sets the value of the specified parameter to the characters from {@code reader}.
     *
     * @param parameterName the parameter name.
     * @param reader the {@code Reader}
     * @throws SQLException if a database error happens.
     */
    public void setCharacterStream(String parameterName, Reader reader) throws SQLException;

    /**
     * Sets the value of the specified parameter to the characters from {@code reader}.
     *
     * @param parameterName the parameter name.
     * @param reader the {@code Reader}
     * @throws SQLException if a database error happens.
     */
    public void setNCharacterStream(String parameterName, Reader reader) throws SQLException;

    /**
     * Sets the value of the specified parameter to the characters from {@code reader}.
     *
     * @param parameterName the parameter name.
     * @param reader the {@code Reader}
     * @throws SQLException if a database error happens.
     */
    public void setClob(String parameterName, Reader reader) throws SQLException;

    /**
     * Sets the value of the specified parameter to the bytes from {@code inputStream}.
     *
     * @param parameterName the parameter name.
     * @param inputStream the {@code InputStream}
     * @throws SQLException if a database error happens.
     */
    public void setBlob(String parameterName, InputStream inputStream) throws SQLException;

    /**
     * Sets the value of the specified parameter to the characters from {@code reader}.
     *
     * @param parameterName the parameter name.
     * @param reader the {@code Reader}
     * @throws SQLException if a database error happens.
     */
    public void setNClob(String parameterName, Reader reader) throws SQLException;
}
