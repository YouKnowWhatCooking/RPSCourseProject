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
    <title>My Orders</title>
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
    <h1 class="title">My Order List</h1>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <h2><a href="listOrders">ВСЕ ЗАКАЗЫ</a></h2>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_PRINT')">
        <h2><a href="listOrders">ОЧЕРЕДЬ ЗАКАЗОВ</a></h2>
    </sec:authorize>
    <table border="1">
        <th>№</th>
        <th>Date</th>
        <th>Client</th>
        <th>Cost</th>
        <th>Status</th>
        <th>Email</th>
        <th>Address</th>
        <th></th>

        <c:forEach var="order" items="${listOrders}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${order.date}</td>
                <td><sec:authentication property="principal.username" /></td>
                <td>${order.cost}</td>
                <td>${order.status}</td>
                <td>${order.email}</td>
                <td>${order.address}</td>
                <td>
                    <a href="getOrderContent?id=${order.id}">Содержимое заказа</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <c:if test = "${order.status == 'Создан'}">
                        <div class="bot">
                            <a href="paymentForm?id=${order.id}">Оплатить</a>
                            <a href="deleteOrder?id=${order.id}">Отменить</a>
                        </div>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
