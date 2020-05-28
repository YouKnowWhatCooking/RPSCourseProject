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
    <title>Edit Clothes</title>
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
    <h1 class="title">Edit Clothes</h1>
    <form:form action="saveClothes" method="post" modelAttribute="clothes">
        <table>
            <form:hidden path="id"/>
            <tr>
                <td>Title:</td>
                <td><form:input path="title" value="${clothes.title}"/></td>
            </tr>
            <tr>
                <td>Type:</td>
                <td><form:input path="type" value="${clothes.type}"/></td>
            </tr>
            <tr>
                <td>imageLink:</td>
                <td><form:input path="imageLink" value="${clothes.imageLink}"/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><form:input path="price" value="${clothes.price}"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><button type="submit">Save</button></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>