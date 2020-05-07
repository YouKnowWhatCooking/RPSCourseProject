<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <a class="logo" href="">LOGO</a>
        <nav class="nav">
            <a class="nav_link" href="listImagesByType?type=Фентези">Фентези</a>
            <a class="nav_link" href="listImagesByType?type=Наука">Наука</a>
            <a class="nav_link" href="listClothes">Список одежды</a>
        </nav>
        <nav class="nav">
            <a class="enter" href="cartPage">Корзина</a>  /
            <a class="enter" href="">Войти</a> /
            <a class="enter" href="">Зарегистрироваться</a>
        </nav>
    </header>
</div>

<c:forEach var="template" items="${listTemplates}" varStatus="status">
<div class="container">
    <h1>${template.location}</h1>
    <div class="poduct-main">
        <div class="product">
            <h2>${template.price}</h2>
            <div class="bot">
                <a class="choose" href="addCustomCombination?clothesID=${clothes.id}&imageID=${image.id}&templateID=${template.id}">В корзину</a>
            </div>
        </div>
        </c:forEach>


    </div>
</div>
</body>
</html>