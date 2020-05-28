<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <title>Clothes</title>
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
    <h1 class="title">Clothes List</h1>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <h2><a href="newClothes">New Clothes</a></h2>
    </sec:authorize>
    <div class="col-left">
        <c:forEach var="type" items="${clothesType}" varStatus="status">
            <br><a class="left-panel" href="listClothesByType?type=${type}">${type}</a>
        </c:forEach>
    </div>
    <div class="product-main" style="margin: 0 0 0 15%">
        <c:forEach var="clothes" items="${listClothes}" varStatus="status">
            <div class="product">
                <h1>${clothes.title}</h1>
                <h2>${clothes.type}</h2>
                <div class="product-img"><img src="${clothes.imageLink}" width="362" height="481"></div>
                <div class="bot">
                    <p class="price">${clothes.price}</p>
                    <sec:authorize access="isAuthenticated()">
                        <a class="choose" href="chooseImage?clothesID=${clothes.id}">Выбрать принт</a>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <br><a class = "choose" href="editClothes?id=${clothes.id}">Edit</a>                &nbsp;&nbsp;&nbsp;&nbsp;
                        <a class = "choose"  href="deleteClothes?id=${clothes.id}">Delete</a>
                    </sec:authorize>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
