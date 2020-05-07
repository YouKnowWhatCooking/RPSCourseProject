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
            <c:forEach var="type" items="${clothesType}" varStatus="status">
                <a class="nav_link" href="listClothesByType?type=${type}">${type}</a>
            </c:forEach>
            <a class="nav_link" href="listImages">Изображения</a>
            <a class="nav_link" href="listCombinations">Готовые комбинации</a>
        </nav>
        <nav class="nav">
            <a class="enter" href="">Войти</a> /
            <a class="enter" href="">Зарегистрироваться</a>
        </nav>
    </header>
</div>

<c:forEach var="clothes" items="${listClothes}" varStatus="status">
<div class="container">
    <h1>${clothes.title}</h1>
    <div class="poduct-main">
        <div class="product">
            <h2>${clothes.type}</h2>
            <div class="product-img"><img src="${clothes.imageLink}" width="362" height="481"></div>
            <div class="bot">
                <p class="price">${clothes.price}</p><a class="choose" href="chooseImage?clothesID=${clothes.id}">Выбрать принт</a>
            </div>
        </div>
</c:forEach>


    </div>
</div>
</body>
</html>
