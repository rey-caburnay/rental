package com.shinn.dao.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.shinn.dao.factory.AbstractDao;


public class UserDaoImpl extends AbstractDao implements UserDao{

    public UserDaoImpl() throws Exception {
        super();

    }

    public List<String> getUsernames() {
        PreparedStatement selectPermission = null;
        ResultSet result = null;
        try {
                result = query('')
            } else {
                LOGGER.logAlert(progName, functionName, Logger.RES_EXCEP_DAO,
                        "No User Group Permission with GroupCode of "
                                + groupcode);
            }
        } catch (SQLStatementException e) {
            LOGGER.logAlert(progName, functionName,
                    Logger.RES_EXCEP_SQLSTATEMENT,
                    "Failed to retrieve User Group Permission"
                            + " with GroupCode of " + groupcode);
        } catch (SQLException e) {
            LOGGER.logAlert(progName, functionName, Logger.RES_EXCEP_SQL,
                    "Failed to retrieve User Group Permission"
                            + " with GroupCode of " + groupcode);
        } finally {
            boolean hasErrorOccur = closeConnectionObjects(null,
                    selectPermission, result);
            if (hasErrorOccur) {
                LOGGER.logAlert(progName, functionName, Logger.RES_EXCEP_SQL,
                        "Failed to close SQL related objects "
                                + "on User Group Permission"
                                + " with GroupCode of " + groupcode);
            }

        }
        return permission;
        return null;
    }

}
