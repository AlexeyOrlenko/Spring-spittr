<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
        <title>Spittr</title>
    </head>
    <body>
        <h1>Welcome to Spittr</h1>
        <a href="<c:url value="/spittles" />">Spittles</a> |
        <a href="<c:url value="/spitter/register" />">Register</a>
    </body>
</html>
