import FormFunctions.Search.searchFunctions;
import Main.SQLConnections;
import Main.showMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class Search extends JFrame{
    private JPanel panelSearch;
    private JLabel lblName;
    private JTextField name;
    private JLabel lblEmail;
    private JTextField email;
    private JComboBox languages;
    private JButton btnSearch;




    public Search() {

        languages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String langCode = (String) languages.getSelectedItem();
                Locale l = new Locale(langCode);
                ResourceBundle r = ResourceBundle.getBundle("Main/Properties/Bundle", l);
                lblName.setText(r.getString("SignUp.name_label"));
                lblEmail.setText(r.getString("SignUp.email_label"));
                btnSearch.setText(r.getString("Search.btnSearch"));
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String x = "%" + name.getText() + "%";
                if(x.length()==2)
                    x="";

                String y = "%" + email.getText() + "%";
                if(y.length()==2)
                    y="";

                Map<String, String> details = new HashMap<>();
                details.put("name", x);
                details.put("email", y);
                Connection con = SQLConnections.getCon();
                try {
                    ResultSet rs = searchFunctions.searchUser(con, details);
                    String msg = "Users:\n";
                    while(rs.next()) {
                        msg+="Name: " + rs.getString("name") +
                                "\nE-mail: " + rs.getString("email") +
                                "\n------------------------------------------\n";
                    }
                    showMessage.message(msg);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                SQLConnections.closeCon(con);
            }
        });
    }

    public static void main(String[] args) {
        Search t = new Search();
        t.setContentPane(t.panelSearch);
        t.setTitle("AddUser");
        t.setBounds(300, 50, 500, 500);
        t.setVisible(true);
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
