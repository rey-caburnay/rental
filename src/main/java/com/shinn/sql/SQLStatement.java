package com.shinn.sql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;


/**
 * SQLStatement is the class which handles the reading of sql statements
 *     from the file(.xml format) by mapping the key-value.
 *
 */
public class SQLStatement {	
	
    /**
     * The Instance of the class.
     */
    private static volatile SQLStatement instance;
    /**
     * The file path of the Sql Statements.
     */
    private static  String filePath = "sql_statements.xml";
    /**
     * The InputStream of the class.
     */
    private static InputStream inputStream;
    /**
     * The Properties of the class.
     */
    private Properties properties;


    /**
     * The Default Constructor.
     * @throws Exception  Exception thrown when instantiation fails.
     */
    protected SQLStatement() throws Exception {
    	properties = new Properties();
        readFile();
    }

     /**
     * Set the InputStream for the class.
     * @param inputstream the new InputStream for the class
     */
    public static void setInputStream(final InputStream inputstream) {
        SQLStatement.instance = null;
        SQLStatement.inputStream = inputstream;
    }

    /**
     * Setter for the filePath of the SQL Statements.
     * @param filepath  Path of file
     */
    public static void setFilepath(final String filepath) {
        //when filePath has been re-set it is expected
        //that this class InputStream and Instance would be Renewed
        //afterwards
        SQLStatement.instance = null;
        SQLStatement.filePath = filepath;
    }


    /**
     * Creates a SQLStatement instance.
     * @return SQLStatement instance.
     * @throws Exception    Exception when error occurs.
     */
    private static synchronized SQLStatement tryCreateInstance()
            throws Exception {
        if (instance == null) {
            instance = new SQLStatement();
        }
        return instance;
    }

    /**
      * Retrieves SQLStatement instance.
      * @return SQLStatement instance
      * @throws Exception   Exception when error occurs.
      */
    public static SQLStatement getInstance() throws Exception {
        SQLStatement sqlStatement = instance;
        if (sqlStatement == null) {
            sqlStatement = tryCreateInstance();
        }
        return sqlStatement;
    }

    /**
      * Reads the properties represented by the XML document.
      * @throws Exception   Exception when error occurs.
      */
    private void readFile() throws Exception {

        //Is the InputStream empty?
        //If yes, try to set the InputStream
        if (null == inputStream) {
            File file = new File(filePath);
            if (filePath.equals(file.getName())) {
                setInputStream(SQLStatement.class
                        .getResourceAsStream(filePath));
            } else {
                try {
                    setInputStream(new FileInputStream(file));
                } catch (FileNotFoundException e) {
                    throw
                    new Exception("File not found!=" + filePath, e);
                }
            }
        }

        //Is the InputStream still empty?
        //If yes, then throw an error stating that
        //the filePath was unknown.
        if (null == inputStream) {
            throw new Exception("custom_exception:"
                    + " filePath not found "
                    + filePath, new Exception());
        }

        try {
            properties.loadFromXML(inputStream);
        } catch (InvalidPropertiesFormatException e) {
            throw new Exception(
                    "InvalidPropertiesFormatException: cannot load " + filePath,
                    e);
        } catch (IOException e) {
            throw new Exception("IOException: cannot load "
                    + filePath, e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                	
                }
            }
        }

    }

    /**
      * Retrieves property value.
      * @param property name
      * @throws Exception
      * @return property value
      */
    public final String getProperty(final String property) {
        return properties.getProperty(property);
    }
}
