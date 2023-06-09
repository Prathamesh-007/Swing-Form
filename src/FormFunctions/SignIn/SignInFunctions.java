package FormFunctions.SignIn;

import Main.commandExecute;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignInFunctions {

    private static Map<String, String> countryCodes = new HashMap<>();
    private static List<String> languages = new ArrayList<>();


    public static void createLanguageBox(JComboBox<String> j) {
        String[] s = {"EN", "FR", "ES", "DE"};
        j.addItem("EN");
        j.addItem("FR");
        j.addItem("ES");
        j.addItem("DE");
    }

    public static void createCountryCodeBox(JComboBox<String> cc) {
        cc.addItem("India");
        cc.addItem("France");
        cc.addItem("Germany");
        cc.addItem("Spain");
        cc.addItem("USA");
    }

    public static Map<String, String> addCountryCodes() {
        countryCodes.put("India", "+91");
        countryCodes.put("USA", "+1");
        countryCodes.put("Germany", "+49");
        countryCodes.put("Spain", "+34");
        countryCodes.put("France", "+33");

        return countryCodes;
    }

    public static void addUser(Connection con, Map details) throws SQLException {
        String command = "INSERT INTO users(name, email, contact, password) VALUES('" +
                details.get("name") + "', '" +
                details.get("email") + "', '" +
                details.get("contact") + "', '" +
                details.get("password") + "');";

        commandExecute.executeCommand(con, command);
    }
}
