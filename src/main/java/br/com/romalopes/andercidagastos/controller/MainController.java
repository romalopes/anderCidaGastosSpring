package br.com.romalopes.andercidagastos.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.romalopes.andercidagastos.model.bean.User;

@Controller
public class MainController {
	 protected final Logger logger = Logger.getLogger(getClass());
	 
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String showUserForm(Model model) {
	    model.addAttribute("user",new User());
	    return "main";
	 }
	 
	@RequestMapping(value="/main")  
	public ModelAndView main() {
		System.out.println("MainController - main");
		logger.error("Returning index view");
		logger.debug("Returning index view");
		ModelAndView view = new ModelAndView();  
		view.setViewName("main"); //name of the jsp-file in the "page" folder  
		    
		String str = "Anderson Lopes Teste!";  
		view.addObject("message", str); //adding of str object as "message" parameter  
	        
	      return view;  
	}  

	@RequestMapping("/page{number}")
	public String printIndex(ModelMap model, @PathVariable("number") int number)
	{
	    String numberText;
	
	    switch (number)
	    {
	        case 0:
	            numberText = "Zero";
	        break;
		    case 1:
		        numberText = "One";
		        break;
		    default:
		        numberText = "Unknown";
		        break;
	    }
	
		model.addAttribute("message", numberText);
		return "main";
	}

	@RequestMapping("/page2/{number}")
	public String printIndex2(ModelMap model, @PathVariable("number") int number)
	{
	    String numberText;
	
	    switch (number)
	    {
	        case 0:
	            numberText = "Zero";
	        break;
		    case 1:
		        numberText = "One";
		        break;
		    default:
		        numberText = "Unknown";
		        break;
	    }
	
		model.addAttribute("message", numberText);
		return "main";
	}

	
	@RequestMapping("/page")
    public String printIndex(ModelMap model)
    {
        return printIndex(model, 1);
    } 
	
	 //a URL request in the follwoing pattern will mapp the request param into userId declared in the method signature:  
    //http://.../user?userId=1234     
	@RequestMapping(value = "/user")  
	public ModelAndView getUserByRequestParamInTheUrl(@RequestParam(value="userId", required=true) String userId, 
								HttpServletRequest request, HttpServletResponse response) {       
	    System.out.println("Got request param: " + userId);  
	      
	    ModelAndView modelAndView = new ModelAndView("main");  
	      
	    //Add the userId to the request so it can be displayed in userinfo page   
	    modelAndView.addObject("message", userId);  
	      
	    return modelAndView;  
	}  
  	
	@RequestMapping(value="/userTest/{name}", method = RequestMethod.GET)
	public @ResponseBody User getUserInJSON(@PathVariable String name) {
 System.out.println("Name:"+name);
		User user = new User();
		user.setFirstName(name);
		user.setUserName("romalopes@yahoo.com.br");
		user.setLastName("lastName");
 
		return user;
 
	}
}

