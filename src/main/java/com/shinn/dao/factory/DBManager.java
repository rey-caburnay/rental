package com.shinn.dao.factory;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBManager {
    Connection getConnection() throws SQLException;
}
