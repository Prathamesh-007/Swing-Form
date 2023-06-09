import FormFunctions.Login.loginFunctions;
import FormFunctions.MD5;
import FormFunctions.SignIn.SignInFunctions;
import Main.SQLConnections;
import Main.showMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class Login extends JFrame{
    private JPanel panelLogin;
    private JTextField user_id;
    private JLabel lblUser_id;
    private JLabel lblPassword;
    private JTextField password;
    private JLabel lblLanguage;
    private JButton btnLogin;
    private JButton btnSignUp;
//    private JButton btnLang;
    private JComboBox<String> languages;


    public Login() {
        SignInFunctions.createLanguageBox(languages);


        languages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String langCode = (String) languages.getSelectedItem();
                Locale l = new Locale(langCode);
                ResourceBundle r = ResourceBundle.getBundle("Main/Properties/Bundle", l);
                lblLanguage.setText(r.getString("Login.lblLanguage"));
                lblPassword.setText(r.getString("Login.lblPassword"));
                lblUser_id.setText(r.getString("Login.lblUser_id"));
                btnLogin.setText(r.getString("Login.btnLogin"));
                btnSignUp.setText(r.getString("Login.btnSignUp"));
            }
        });

        //Check is the User Exists
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String acc_no = user_id.getText();
                String pass = MD5.getMd5(password.getText());
                loginFunctions l = new loginFunctions();
                Map<String, String> details = new HashMap<>();
                details.put("acc_no", acc_no);
                details.put("password", pass);
                Connection con = SQLConnections.getCon();
                if(l.checkUser(con, details)) {
                    showMessage.message("Valid User!");
                }
                else{
                    showMessage.message("Invalid User!");
                }
                SQLConnections.closeCon(con);
            }
        });

        //New User will be redirected to Sign Up
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SignUp.run();

            }
        });
    }

    public static void run() {
        Login t = new Login();
        t.setContentPane(t.panelLogin);
        t.setTitle("AddUser");
        t.setBounds(300, 50, 500, 500);
        t.setVisible(true);
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
