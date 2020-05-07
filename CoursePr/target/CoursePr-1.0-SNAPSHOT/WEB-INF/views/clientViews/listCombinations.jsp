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
        <a class="logo" href="listClothes">LOGO</a>
        <nav class="nav">
            <a class="nav_link" href="listClothesByType?type=Hoodie">Hoodie</a>
            <a class="nav_link" href="listClothesByType?type=Pants">Pants</a>
            <a class="nav_link" href="listImages">Изображения</a>
        </nav>
        <nav class="nav">
            <a class="enter" href="">Войти</a> /
            <a class="enter" href="">Зарегистрироваться</a>
        </nav>
    </header>
</div>

<c:forEach var="combinations" items="${listCombinations}" varStatus="status">
<div class="container">
    <h1>${combinations.clothes.title}</h1>
    <div class="poduct-main">
        <div class="product">
            <h2>${combinations.print.template.location}</h2>
            <div class="product-img"><img src="${combinations.combinedImageLink}" width="362" height="481"></div>
            <div class="bot">
                <p class="price">${combinations.clothes.price + combinations.print.template.price}</p>
                <a class="choose" href="addCombinationToCart?id=${combinations.id}">Добавить в корзину</a>
            </div>
        </div>
        </c:forEach>


    </div>
</div>
</body>
</html>