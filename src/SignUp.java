import FormFunctions.MD5;
import FormFunctions.MyMail;
import FormFunctions.SignIn.SignInFunctions;
import FormFunctions.Validate;
import Main.SQLConnections;
import Main.showMessage;

import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class SignUp extends JFrame{
    private JPanel SignUpPanel;
    private JLabel name_label;
    private JTextField name;
    private JLabel country_label;
    private JComboBox<String> country;
    private JLabel contact_label;
    private JTextField country_code;
    private JTextField contact;
    private JLabel email_label;
    private JTextField email;
    private JButton addUser;
    private JComboBox<String> language_box;
    private JButton btnLang;
    private JLabel password_label;
    private JTextField password;
    private JButton btnLogin;


    public SignUp() {
        country_code.setEditable(false);
        SignInFunctions.createCountryCodeBox(country);
//        country.addItem("India");
//        country.addItem("France");
//        country.addItem("Germany");
        Map<String, String> mp = SignInFunctions.addCountryCodes();


//        language_box.addItem("EN");
//        language_box.addItem("FR");
//        language_box.addItem("DE");
//        language_box.addItem("ES");
        SignInFunctions.createLanguageBox(language_box);


        // Event Listener to get the code of the selected country
        country.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                country_code.setText(mp.get(country.getSelectedItem()));
            }
        });

        // Event Listener to add user in database
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Validate v = new Validate();
                if(v.checkEmail(email.getText()) && v.checkPhone(contact.getText()) && country_code.getText().length()!=0) {
                    Map<String, String> details = new HashMap<>();
                    Connection con = SQLConnections.getCon();
                    details.put("name", name.getText());
                    details.put("email", email.getText());
                    details.put("contact",country_code.getText() + "-" + contact.getText());
                    details.put("password", MD5.getMd5(password.getText()));
                    try {
                        SignInFunctions.addUser(con, details);
                        String s = "Welcome " + name.getText();
                        showMessage.message(s);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    try {
                        MyMail.sendEmail(email.getText(),
                                "Welcome To ABC Bank",
                                "Hello User! Welcome to the ABC Bank");
                    } catch (MessagingException ex) {
                        throw new RuntimeException(ex);
                    }


                }
                else {
                    JFrame abc = new JFrame();
                    JOptionPane.showMessageDialog(abc, "Invalid Email/Contact");
                }
            }
        });
        language_box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String langCode = (String) language_box.getSelectedItem();
                Locale l = new Locale(langCode);
                ResourceBundle r = ResourceBundle.getBundle("Main/Properties/Bundle", l);
                name_label.setText(r.getString("SignUp.name_label"));
                country_label.setText(r.getString("SignUp.country_label"));
                contact_label.setText(r.getString("SignUp.contact_label"));
                email_label.setText(r.getString("SignUp.email_label"));
                addUser.setText(r.getString("SignUp.addUser"));
                btnLang.setText(r.getString("SignUp.btnLang"));
                password_label.setText(r.getString("SignUp.password_label"));

            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login.run();
            }
        });
    }

    public static void run() {
        SignUp t = new SignUp();
        t.setContentPane(t.SignUpPanel);
        t.setTitle("AddUser");
        t.setBounds(300, 50, 500, 500);
        t.setVisible(true);
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
