package br.com.romalopes.andercidagastos.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.romalopes.andercidagastos.form.LoginForm;

@Component
public class LoginValidator implements Validator{
 
	@Override
    public boolean supports(Class<?> clazz) {
        return LoginForm.class.isAssignableFrom(clazz);
    }
 
    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("Entrou loginValidator.validateForm");
    	LoginForm loginForm = (LoginForm) target;
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
    	
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        List<User> userList = session.createQuery("from User where username ='" + loginuser.getEmail() + "' and password ='" + loginuser.getPassword() + "'").list();
//        if(userList.size() == 0) {
//            errors.reject("wrongcredential","Wrong Username or Password!!!");   
//        }
//    	 if(!loginForm.getPassword().equals(loginForm.getEmail()))
//            errors.rejectValue("password","password.required.loginForm.password");

        System.out.println("loginForm:"+loginForm.getEmail());
    }
}