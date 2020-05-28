<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cart page</title>
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
    <h1 class="title">Cart Page</h1>
    <form:form action="saveOrder" method="post">
        <table border="1">
            <th>Remove</th>
            <th>Clothes title</th>
            <th>Clothes Image</th>
            <th>Print title</th>
            <th>Print Image</th>
            <th>Quantity</th>
            <th>Total Sum</th>
            <th>+</th>
            <th>-</th>
            <c:forEach var="item" items="${sessionScope.cart}" varStatus="status">
                <tr>
                    <td><a class = "choose" href="removeLine?clothesID=${item.clothes.id}&templateID=${item.print.template.id}&imageID=${item.print.image.id}">Remove line</a></td>
                    <td>${item.clothes.title}</td>
                    <td><img src="${item.clothes.imageLink}" width="50" height="50"></td>
                    <td>${item.print.image.title}</td>
                    <td><img src="${item.print.image.link}" width="50" height="50"></td>
                    <td>${item.quantity}</td>
                    <td>${(item.print.template.price + item.clothes.price)*item.quantity}</td>
                    <td><a class = "choose" href="incQuantity?clothesID=${item.clothes.id}&templateID=${item.print.template.id}&imageID=${item.print.image.id}">+</a></td>
                    <td><a class = "choose" href="decQuantity?clothesID=${item.clothes.id}&templateID=${item.print.template.id}&imageID=${item.print.image.id}">-</a></td>
                </tr>
            </c:forEach>
        </table>
        <a class="choose" href="listClothes">Продолжить выбор</a>
        <button style="align:center" type="submit" >Оформить заказ</button>
    </form:form>
</div>
</body>
</html>