<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
        <title>Spittr</title>
    </head>
    <body>
        <h1>Register</h1>
        
        <form method="POST">
            First Name: <input type="text" name="firstName" /> <br/>
            Last Name: <input type="text" name="lastName" />   <br/>
            Username: <input type="text" name="username" />    <br/>
            Password: <input type="password" name="password" /><br/>
            <input type="submit" value="Register" />
        </form>
    </body>
</html>