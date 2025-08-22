package com.epay.management.utilities;

import com.epay.management.entity.EmployeeEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public boolean isValidEmail(EmployeeEntity employee) {
        String regex = "^[A-Za-z0-9._%+-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(employee.getEmail());
        return !matcher.matches();
    }
}
