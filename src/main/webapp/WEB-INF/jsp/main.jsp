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
