package com.epay.management.utilities;

import com.epay.management.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validator {
    public boolean isValidEmail(EmployeeEntity employee) {
        String regex = "^[A-Za-z0-9._%+-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(employee.getEmail());
        return !matcher.matches();
    }
}
