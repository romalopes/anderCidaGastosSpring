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