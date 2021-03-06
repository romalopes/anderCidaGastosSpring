anderCidaGastosSpring
=====================

+ Passos do Projeto


++ In�cio
+++ O que usaremos
* Spring 3 MVC
 * [http://img.viralpatel.net/2010/10/spring3-hibernate-application-architecture.png Spring MVC Archtecture]
* Hibernate 4
* Maven 
* Eclipse

++ Primeira parte(fazendo funcionar em Spring)
# Download do STS
# Cria��o do Projeto STS no Eclipse( http://flanzer.wordpress.com/2011/08/30/spring-mvc-using-maven-in-eclipse/)
 #  Maven Dynamic Web Project in Eclipse
  # Create mavem project with or.apache.maven.archetypes -> webapp
  # Include Spring-mvc in POM.xml
  # Include springMVC with org.springframework.web.servlet.DispatcherServlet in web.xml/servlet-name
 # Maven->updateProject
 # Create the bean configuration file (WEB-INF/springMVC-servlet.xml)
 # Include a bean for controller (MainSpringController)
 #  Create the MainSpringController inicial implementing Controller
  # Method ModelAndView handleRequest
 # Configure JRE and Server(Tomcat) 
# Create Project
# Include code in GitHub
 # Create repository
 # PULL to github 
 # In Github windows software do "Clone to" and syncronize
 # AndercidaGastosSpring on Github is [*https://github.com/romalopes/anderCidaGastosSpring.git ]
 # Download of gitHub for eclipse
  # http://eclipse.org/egit/download/
++ Mappings and Configuration
* Fullfill pom.xml which imports all dependencies(jars)
* Configure web.xml (to set spring as dipatcher)

[[code type="XML"]]
	<!--  Listener of context.  Show the files that listener should find informations -->
 	<listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:/spring/applicationContext.xml, classpath:/spring/hibernateContext.xml</param-value>
    </context-param>

	<!--  Servlet. DispatcherServlet.  Every request will pass throug here -->
    <servlet>
        <servlet-name>appServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value></param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

	<!--  Mapping of DispatcherServlet -->
    <servlet-mapping>
        <servlet-name>appServlet</servlet-name>
        <url-pattern>*.htm</url-pattern>
        <url-pattern>/rest/*</url-pattern> 
    </servlet-mapping>

[[/code]]

* Creation of main/resource/spring/applicationContext.xml ( configure the directory of "component-scan", annotation-driven, view resolver)
[[code type="XML"]]
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/mvc
                           http://www.springframework.org/schema/mvc/spring-mvc.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd">


	<!-- Application Message Bundle.  where the messages are-->
     <bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
        <property name="basename" value="/WEB-INF/messages" />
        <property name="cacheSeconds" value="3000" />
    </bean>

	<!--  Package where Spring will look for beans -->
    <context:component-scan base-package="br.com.romalopes.andercidagastos"/>
    <mvc:annotation-driven />


    <mvc:view-controller path="/login" view-name="login" />
    <mvc:view-controller path="/protected" view-name="protected" />

	<!-- Resolver of view resource.  Where spring will look for jsp and which suffixes -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/" />
        <property name="suffix" value=".jsp" />
    </bean>

</beans>
[[/code]]

++ First Example

**Request with Param** should call: http://localhost:8080/AnderCidaGastosSpring/user.htm?userId=1 or ../rest/user?userId=1
[[code type="Java"]]
	@RequestMapping(value = "/user")  
	public ModelAndView getUserByRequestParamInTheUrl(@RequestParam(value="userId", required=true) String userId, 
								HttpServletRequest request, HttpServletResponse response) {       
	    System.out.println("Got request param: " + userId);  
	      
	    ModelAndView modelAndView = new ModelAndView("main");  
	      
	    //Add the userId to the request so it can be displayed in userinfo page   
	    modelAndView.addObject("message", userId);  
	      
	    return modelAndView;  
	}  
[[/code]]


**Request in Rest**  Should call: http://localhost:8080/AnderCidaGastosSpring/rest/page2/1
[[code type="Java"]]
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
[[/code]]

* Rest with JSON ** should call: http://localhost:8080/AnderCidaGastosSpring/rest/userTest/Anderson
     Should include Jackson-mapper-asl to return json
[[code type="XML" ]]
	<dependency>
		<groupId>org.codehaus.jackson</groupId>
		<artifactId>jackson-mapper-asl</artifactId>
		<version>1.9.12</version>
	</dependency>
[[/code]]

Java Code
[[code type="java" ]]
	@RequestMapping(value="/userTest/{name}", method = RequestMethod.GET)
	public @ResponseBody User getUserInJSON(@PathVariable String name) {
                System.out.println("Name:"+name);
		User user = new User();
		user.setFirstName(name);
		user.setUserName("romalopes@yahoo.com.br");
		user.setLastName("lastName");
 
		return user;
 
	}

[[/code]]

* example jsp creation

[[code type="HTML"]]
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><%--  UTF-8 --%>
<title>Main</title>
</head>
<body>

    <h1>Main ${message}</h1>
     <c:out value="${message}"/><br>
	email : ${loginForm.email}.<br>
</body>
</html>
    <p><a href="hello-page.htm">Hello world link</a></p>
</body>
</html>
[[/code]]

+++ Validation

* In main/java/resources/spring/applicationContext.xml
[[code type="XML"]]
	<!-- Application Message Bundle.  where the messages are-->
     <bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
        <property name="basename" value="/WEB-INF/messages" />
        <property name="cacheSeconds" value="3000" />
    </bean>
[[/code]]

* In WEB-INF/messages.properties
[[code type="XML"]]
NotEmpty.loginform.email = Email is required!
NotEmpty.loginform.password = password field is mandatory.
password.required= password field is mandatory.
password.required.loginForm.password= password field is mandatory.
[[/code]]

* Create a Form
[[code type="Java"]]
package br.com.romalopes.andercidagastos.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
	@Email(message="it is not a email format")
    @NotEmpty(message="email field is mandatory.")
	String email;
    @NotEmpty(message="password field is mandatory.")
	String password;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
[[/code]]

* Create the Controller
[[code type="Java"]]

//Create the validator which will be called to validate the form. 
LoginValidator validator = null;
    
public LoginValidator getValidator() {   return validator;   }
    
@Autowired
public void setValidator(LoginValidator validator) {
     this.validator = validator;
}

@RequestMapping(value = "/login", method = RequestMethod.POST)          
//A first validation can be defined in LoginForm
public String login(@ModelAttribute("login") @Valid LoginForm loginForm,
    									BindingResult result) {
         validator.validate(loginForm, result);		
		
        // Some code to work on the data received from the user.                                                                      
        if(result.hasErrors()){
        	return "login";
        }
        else {
        	return "main";
        }

} 
[[/code]]

* Create the Validator

[[code type="Java"]]
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
[[/code]]

* Login Page
[[code type="HTML"]]
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<style>
            .form-item { margin: 20px 0; }
            .form-label { font-weight: bold; }
            .form-error-field { background-color: #FFC; }
            .form-error-message { font-weight: bold; color: #900; }
        </style>
</head>
<body>
<form:form method="POST" commandName="loginForm" action="login.htm">
    <table>
        <tr>
            <td></td>
            <td></td>
            <td><form:errors/></td>
        </tr>
        <tr>
            <td>Email :</td>
            <td><form:input path="email" /></td>
            <td><form:errors path="email" cssClass="form-error-message" /></td>
        </tr>
        <tr>
            <td>Password :</td>
            <td><form:password path="password" /></td>
            <td><form:errors path="password" cssClass="form-error-message" /></td>
        </tr>
    </table>
    <tr>
            <td colspan="3"><input type="submit" value="Login"></td>
        </tr>
</form:form>
</body>
</html>
[[/code]]

* 
++ Hibernate
http://www.cavalr.com/blog/Spring_3_and_Annotation_Based_Hibernate_4_Example
* Insert hibernate references in pom.xml

[[code type="XML"]]
    <!-- Hibernate -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>${hibernate.version}</version>
    </dependency>
	<dependency>
		<groupId>org.hibernate</groupId>
		<artifactId>hibernate-entitymanager</artifactId>
		<version>${hibernate.version}</version>
	</dependency>
 	<dependency>
		<groupId>org.hibernate</groupId>
        <artifactId>hibernate-validator</artifactId>
        <version>${hibernate.version}</version>
     </dependency>
	<dependency>
			<groupId>commons-dbcp</groupId>
			<artifactId>commons-dbcp</artifactId>
			<version>1.4</version>
	</dependency>

[[/code]]

* Definitions of dataSource in src/main/resource/database.properties

[[code]]
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/bancoDados
jdbc.dialect=org.hibernate.dialect.MySQLDialect
jdbc.username=root
jdbc.password=admin
[[/code]]

* create java/resources/spring/hibernateContext.xml. To define hibernate and mapping configuration.
[[code type="XML"]]
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans-3.1.xsd
            http://www.springframework.org/schema/tx
            http://www.springframework.org/schema/tx/spring-tx-3.1.xsd">

	<!--  Where to look for values -->
    <bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        <property name="locations">
            <list>
                <value>classpath:database.properties</value>
            </list>
        </property>
    </bean>

    <!-- DataSource.  Data are in database.properties -->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="${jdbc.driver}" />
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />
    </bean>

	<!-- Create the sessionFactory Bean -->
    <bean id="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">
    	<!-- Reference to dataSource -->
        <property name="dataSource" ref="dataSource"/>
        <!-- Where beans are located  -->
        <property name="packagesToScan" value="br.com.romalopes.andercidagastos.model.bean" />
        <property name="hibernateProperties">
            <props>
                <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
                <prop key="hibernate.show_sql">true</prop>
            </props>
        </property>
    </bean>

	<!--  Consider Transaction -->
    <tx:annotation-driven transaction-manager="transactionManager"/>

    <bean id="transactionManager" class="org.springframework.orm.hibernate4.HibernateTransactionManager">
        <property name="sessionFactory" ref="sessionFactory"/>
    </bean>
</beans>
[[/code]]

* Define the mapped classes 

[[code type="Java"]]
package br.com.romalopes.andercidagastos.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User{
	@Id
    @Column(name="user_name")
    private String userName;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;    
    @Column(name="password")
    private String password;
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
	public String toString() {
		return "email: " + userName + " firstName:" + firstName + " lastName:" + lastName;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        User user = (User) o;
 
        return userName.equals(user.userName);
    }
 
    @Override
    public int hashCode() {
        return 13 * userName.hashCode();
    }
	
}
[[/code]]

* Create HibernateUtil
 * The HibernateUtil class helps in creating the SessionFactory from the Hibernate configuration file. A org.hibernate.SessionFactory is used to obtain org.hibernate.Session instances. A org.hibernate.Session represents a single-threaded unit of work. The org.hibernate.SessionFactory is a thread-safe global object that is instantiated once.
 * In this version, HibernateUtil is not necessary

* Include AutoWired Service in controller
[[code type="Java"]]


    @Autowired
    private UserService userService;
....
    User user = userService.getUser(loginForm.getEmail());
		if(user!= null)
			System.out.println(user);

[[/code]]

* Create UserService and UserServiceImpl
[[code type="Java ]]
package br.com.romalopes.andercidagastos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.romalopes.andercidagastos.dao.UserDAO;
import br.com.romalopes.andercidagastos.model.bean.User;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public List<User> getUsers() {
		return userDAO.getUsers();
	}
	
	@Override
    @Transactional(readOnly = false)
	public void saveUser(User user) {
		userDAO.saveUser(user);
	}

	@Override
	@Transactional
	public User getUser(String email){
		System.out.println("Entrou UserServiceImpl.getUser");
		return userDAO.getUser(email);
	}
	
	@Override
	@Transactional
	public List<User> findUsers(String firstName) {
		System.out.println("Entrou UserServiceImpl.findUsers");
		return userDAO.findUsers(firstName);
	}

}
[[/code]] 

* Create DAOs

AbstractDaoImpl
[[code type="Java"]]
package br.com.romalopes.andercidagastos.dao;

public abstract class AbstractDaoImpl<E, I extends Serializable> implements AbstractDao<E,I> {

    private Class<E> entityClass;

    protected AbstractDaoImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired(required=true)
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public E findById(I id) {
        return (E) getCurrentSession().get(entityClass, id);
    }

    @Override
    public void saveOrUpdate(E e) {
        getCurrentSession().saveOrUpdate(e);
    }

    @Override
    public void delete(E e) {
        getCurrentSession().delete(e);
    }

    @Override
    public List<E> findByCriteria(Criterion criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        return criteria.list();
    }
}
[[/code]]

 UserDAOImpl
[[code type="Java"]]
package br.com.romalopes.andercidagastos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.romalopes.andercidagastos.model.bean.User;

@Repository
public class UserDAOImpl extends AbstractDaoImpl<User, String> implements UserDAO { 

	 protected UserDAOImpl() {
	        super(User.class);
	 }

	@Override
	public User getUser(String email) {
		System.out.println("Entrou UserDAOImpl.getUser");
		List<User> userList = findByCriteria(Restrictions.like("userName", email, MatchMode.START));
		if(userList.size() > 0)
			return userList.get(0);
		
		return null;
	}
	
	@Override
	public List<User> getUsers() {
		System.out.println("Entrou UserDAOImpl.getUsers");
		return new ArrayList<User>();
	}
	
	@Override
	public List<User> findUsers(String firstName) {
		System.out.println("Entrou UserDAOImpl.findUsers");
		return findByCriteria(Restrictions.like("firstName", firstName, MatchMode.START));
	}

	@Override
	public void saveUser(User user) {
		 saveOrUpdate(user);
		
	}
	
}

[[/code]]
