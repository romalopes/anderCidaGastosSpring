<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><%--  UTF-8 --%>
<title>Insert title here</title>
</head>
<body>
     <p>Hello world: ${message}</p>
     <c:out value="${requestScope.message}"/>  
    <p>Well done!</p>  
</body>
</html>
