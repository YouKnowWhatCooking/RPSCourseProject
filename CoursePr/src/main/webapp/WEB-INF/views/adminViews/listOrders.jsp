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
    <title>List of Orders</title>
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
<div class="container"></div>
<div align="center">
    <h1 class="title">Order List</h1>
    <table border="1">
        <th>№</th>
        <th>Date</th>
        <th>Client</th>
        <th>Cost</th>
        <th>Status</th>
        <th>Email</th>
        <th>Address</th>
        <th></th>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <c:forEach var="order" items="${listOrders}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${order.date}</td>
                <td>${order.client}</td>
                <td>${order.cost}</td>
                <td>${order.status}</td>
                <td>${order.email}</td>
                <td>${order.address}</td>
                <td>
                    <a href="getOrderContent?id=${order.id}">Список содержимого</a>
                <c:if test="${order.status.equals('Ожидает получения')}">
                        <a href="releaseOrder?id=${order.id}">Отпустить заказ</a>
                </c:if>
                </td>
            </tr>
        </c:forEach>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_PRINT')">
            <c:forEach var="order" items="${listOrders}" varStatus="status">
                <c:if test="${order.status.equals('Оплачен') || order.status.equals('Принят службой печати')}">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${order.date}</td>
                    <td>${order.client}</td>
                    <td>${order.cost}</td>
                    <td>${order.status}</td>
                    <td>${order.email}</td>
                    <td>${order.address}</td>
                    <td>
                        <a href="getOrderContent?id=${order.id}">Список содержимого</a>
                    <c:if test="${order.status.equals('Оплачен')}">
                        <a href="takeOrder?id=${order.id}">Взять на печать</a>
                    </c:if>
                     <c:if test="${order.status.equals('Принят службой печати')}">
                        <a href="executeOrder?id=${order.id}">Выполнен</a>
                     </c:if>
                    </td>
                </tr>
                </c:if>
            </c:forEach>
        </sec:authorize>
    </table>
</div>
</div>
</body>
</html>
