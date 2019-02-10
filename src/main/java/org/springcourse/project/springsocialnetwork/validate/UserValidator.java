package org.springcourse.project.springsocialnetwork.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springcourse.project.springsocialnetwork.model.User;
import org.springcourse.project.springsocialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        
        if (user.getName().length() > 128) {
            errors.rejectValue("name", "Size.userForm.name");
        }
        if (userService.findByName(user.getName()) != null) {
            errors.rejectValue("name", "Duplicate.userForm.name");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {  
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);  
            Matcher matcher = pattern.matcher(user.getEmail());  
            if (!matcher.matches()) {  
                errors.rejectValue("email", "Corr.userForm.email");  
            }  
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }

}
