package br.com.romalopes.andercidagastos.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller  
public class LinkController {  

	 protected final Logger logger = Logger.getLogger(getClass());
	 
    @RequestMapping("/")
    public String printHelloWorld(Model model) {
    	System.out.println("LinkController - /");
        model.addAttribute("message", "Hello World!");
 
        return "hello";
    }
	
  @RequestMapping(value="/hello-page")  
  public ModelAndView goToHelloPage() {
	  System.out.println("LinkController - goToHelloPage");
	  logger.error("Returning index view");
	  logger.debug("Returning index view");
	  ModelAndView view = new ModelAndView();  
      view.setViewName("hello"); //name of the jsp-file in the "page" folder  
        
      String str = "Anderson Lopes Teste!";  
      view.addObject("message", str); //adding of str object as "message" parameter  
        
      return view;  
  }  
    
}  