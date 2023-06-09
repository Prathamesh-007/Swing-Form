package FormFunctions.Search;

import Main.commandExecute;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class searchFunctions {
    public static ResultSet searchUser(Connection con, Map<String, String> details) throws SQLException {
        String command = "SELECT * FROM Users WHERE " +
                "name like '" + details.get("name") + "' OR " +
                "email like '" + details.get("email") + "';";

        ResultSet rs = commandExecute.executeCommand(con, command);

        return rs;
    }
}
