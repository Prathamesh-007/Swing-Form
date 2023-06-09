package FormFunctions.Login;

import Main.commandExecute;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class loginFunctions implements Login {

    @Override
    public boolean checkUser(Connection con, Map<String, String> details) {
        String command = "SELECT * FROM USERS WHERE acc_no = " + details.get("acc_no") +
                " and password = '" + details.get("password") + "';";
        System.out.println(command);
        try {
            ResultSet rs = commandExecute.executeCommand(con, command);
            boolean flag = rs.next();
            if(flag) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
