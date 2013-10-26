package br.com.romalopes.andercidagastos.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.romalopes.andercidagastos.form.LoginForm;
import br.com.romalopes.andercidagastos.model.bean.User;
import br.com.romalopes.andercidagastos.service.UserService;
import br.com.romalopes.andercidagastos.validator.LoginValidator;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	protected final Logger logger = Logger.getLogger(getClass());
    public LoginController(){
    }
    
        
    @Autowired
    private UserService userService;
    
    //Craete the validator which will be called to validate the form. 
    LoginValidator validator = null;
    
    public LoginValidator getValidator() {
        return validator;
    }
    
    @Autowired
    public void setValidator(LoginValidator validator) {
        this.validator = validator;
    }
    
    
    @RequestMapping(method=RequestMethod.GET)
    public String showForm(ModelMap model){
    	LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        loginForm.setPassword("teste");
        System.out.println("show Form login: " + loginForm.getEmail());
        return "login";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    //A first validation can be defined in LoginForm
    public String processForm(@ModelAttribute(value="loginForm") @Valid LoginForm loginForm,BindingResult result){
        validator.validate(loginForm, result);
		System.out.println("login: " + loginForm.getEmail());
		System.out.println("password: " + loginForm.getPassword());

		List<ObjectError> errors = result.getAllErrors();
		for(int i=0;i<errors.size();i++)
		{
			ObjectError error = errors.get(i);
			System.out.println("error:" + error.getObjectName() + " : " + error.getDefaultMessage());
		}
		User user = userService.getUser(loginForm.getEmail());
		if(user!= null)
			System.out.println("User: " + user);
		
		List<User> userList = userService.findUsers("PrimeiroNome");
		for(int i=0; i<userList.size(); i++) {
			System.out.println("firstName: " + userList.get(i));
		}
		
		userList = userService.getUsers();
		for(int i=0; i<userList.size(); i++) {
			System.out.println("User: " + userList.get(i));
		}
		
		
        if(result.hasErrors()){
        	return "login";
        }
        else {
        	return "main";
        }
/*        if(result.hasErrors()){
        	System.out.println("error");
        	return new ModelAndView("login", "loginForm", loginForm);
        }else{
        	System.out.println("ok");
        	return new ModelAndView("main", "loginForm", loginForm);
        }
*/    }
    

/*	@RequestMapping(value = "/login", method = RequestMethod.POST)          
   //A first validation can be defined in LoginForm
    public ModelAndView login(@ModelAttribute("login") @Valid LoginForm loginForm,
    									BindingResult result) {
		
		logger.debug("login: " + loginForm.getEmail());
		System.out.println("login: " + loginForm.getEmail());
		
        // Some code to work on the data received from the user.                                                                      
        return new ModelAndView("main", "login", new LoginForm());    
	} 

*/}