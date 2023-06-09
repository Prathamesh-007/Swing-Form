package FormFunctions;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate implements Validation{
    private static Scanner scan;
    public boolean checkEmail(String email){
        //Regular Expression
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    @Override
    public boolean checkPhone(String phone) {

        String regex = "[1-9]\\d{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();
    }

}
