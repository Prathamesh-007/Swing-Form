package FormFunctions.Login;

import java.sql.Connection;
import java.util.Map;

public interface Login {
    public boolean checkUser(Connection con, Map<String, String> details);
}
