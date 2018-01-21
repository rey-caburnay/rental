/**
 * this package is to map the ResultSet to java object.
 * Data type should be an object and it should match
 * with the SQL data type use.
 * 
 * SQL data type    Java data type
 * -------------------------------------
    Simply mappable Object mappable
   ------------------------------------------------
    CHARACTER               String
    
    VARCHAR                 String
    LONGVARCHAR             String
    NUMERIC                 java.math.BigDecimal
    DECIMAL                 java.math.BigDecimal
    BIT                     Boolean
    TINYINT                 Integer
    SMALLINT                Integer
    INTEGER                 Integer
    BIGINT                  Long
    REAL                    Float
    FLOAT                   Double
    DOUBLE PRECISION        Double
    BINARY                  byte[]
    VARBINARY               byte[]
    LONGVARBINARY           byte[]
    DATE                    java.sql.Date
    TIME                    java.sql.Time
    TIMESTAMP               java.sql.Timestamp
 *
 */
/**
 * @author rc185213
 *
 */
package com.shinn.service.model;