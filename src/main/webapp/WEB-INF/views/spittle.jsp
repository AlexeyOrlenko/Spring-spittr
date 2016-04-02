<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
        <title>Spittle</title>
    </head>
    <body>
        <div class="spittleView">
            <div class="spittleMessage"><c:out value="${spittle.message}" /></div>
            <div>
                <span class="spittleTime"><c:out value="${spittle.time}" /></span>
            </div>
        </div>
    </body>
</html>
