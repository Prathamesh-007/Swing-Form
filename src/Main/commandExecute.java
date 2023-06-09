package Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class commandExecute {
    public static ResultSet executeCommand(Connection con, String command) throws SQLException {
        Statement s = con.createStatement();
        if(s.execute(command)) {
            ResultSet rs = s.getResultSet();
            return rs;
        }

        else
            return null;
    }


}
