<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Templates</title>
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
<div align="center">
    <h1 class="title">Templates List</h1>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <h2><a href="newTemplate">New Template</a></h2>
    </sec:authorize>
    <table border="1">
        <th>№</th>
        <th>Location</th>
        <th>Price</th>
        <sec:authorize access="isAuthenticated()">
            <th></th>
        </sec:authorize>
        <c:forEach var="template" items="${listTemplates}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${template.location}</td>
                <td>${template.price}</td>
                <sec:authorize access="isAuthenticated()">
                <td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a href="editTemplate?id=${template.id}">Edit</a>                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="deleteTemplate?id=${template.id}">Delete</a>
                    </sec:authorize>
                    <c:if test="${clothes.id != null && image.id != null}">
                        <a class="choose" href="addCustomCombination?clothesID=${clothes.id}&imageID=${image.id}&templateID=${template.id}">В корзину</a>
                    </c:if>
                </td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </table>
</body>
</html>