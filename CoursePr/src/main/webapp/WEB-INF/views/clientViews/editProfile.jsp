<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit profile</title>
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
<div align="center">
    <h1 class="title">Edit Profile</h1>
    <form:form action="saveProfile" method="post" modelAttribute="user">
        <table>
            <form:hidden path="id"/>
            <form:hidden path="password" value="${user.password}"/>
            <form:hidden path="login" value="${user.login}"/>
            <form:hidden path="role" value="${user.role}"/>
            <form:hidden path="balance" value="${user.balance}"/>
            <tr>
                <td>firstName:</td>
                <td><form:input path="firstName" value="${user.firstName}"/></td>
            </tr>
            <tr>
                <td>lastName:</td>
                <td><form:input path="lastName" value="${user.lastName}"/></td>
            </tr>
            <tr>
                <td>email:</td>
                <td><form:input path="email" value="${user.email}"/></td>
            </tr>
            <tr>
                <td>address:</td>
                <td><form:input path="address" value="${user.address}"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><button type="submit">Save</button></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
