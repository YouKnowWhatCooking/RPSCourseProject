<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <title>Registration</title>
</head>

<body>
<div class="headContainer">
    <header class="header">
        <a class="logo" href="listClothes">LOGO</a>
        <nav class="nav">
            <a class="nav_link" href="listClothes">Одежда</a>
            <a class="nav_link" href="listImages">Изображения</a>
            <a class="nav_link" href="listTemplates">Шаблоны</a>
            <a class="nav_link" href="listCombinations">Готовые комбинации</a>
        </nav>
        <nav class="nav">
            <sec:authorize access="isAuthenticated()">
                <a class="enter" href="cartPage">Корзина</a>  /
                <a class="enter" href="account">Личный кабинет</a>  /
                <a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <a class="enter" href="login">Войти</a> /
                <a class="enter" href="registration">Зарегистрироваться</a>
            </sec:authorize>
        </nav>
    </header>
</div>
<div class="container">
    <div class="wrapper">
        <form:form method="POST" modelAttribute="userForm" class="form-signin">
            <h1>Create your account</h1>
            <spring:bind path="login">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="login" class="form-control" placeholder="login" autofocus="true"></form:input>
                    <form:errors path="login"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                    <form:errors path="password"></form:errors>
                </div>
            </spring:bind>
            <spring:bind path="firstName">
            <div class="form-group">
                <form:input type="text" path="firstName" class="form-control" placeholder="firstName"></form:input>
            </div>
            </spring:bind>
            <spring:bind path="lastName">
                <div class="form-group">
                    <form:input type="text" path="lastName" class="form-control" placeholder="lastName"></form:input>
                </div>
            </spring:bind>
            <spring:bind path="email">
                <div class="form-group">
                    <form:input type="text" path="email" class="form-control" placeholder="email"></form:input>
                </div>
            </spring:bind>
            <spring:bind path="address">
                <div class="form-group">
                    <form:input type="text" path="address" class="form-control" placeholder="address"></form:input>
                </div>
            </spring:bind>
            <button type="submit">Зарегистрироваться</button>
        </form:form>
    </div>
</div>
</body>
</html>
