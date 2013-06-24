package br.com.romalopes.andercidagastos.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	 protected final Logger logger = Logger.getLogger(getClass());
	 
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

	
}
