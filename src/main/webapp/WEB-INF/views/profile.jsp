<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
        <title>Profile</title>
    </head>
    <body>
        <h1>Your Profile</h1>
        <c:out value="${spitter.username}" /><br/>
        <c:out value="${spitter.firstName}" />
        <c:out value="${spitter.lastName}" />
    </body>
</html>
