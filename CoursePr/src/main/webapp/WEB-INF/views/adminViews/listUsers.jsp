<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
    <style>
        <%@include file="/resources/style.css" %>
    </style>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/style.css">
    <title>Users</title>
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
<div class="container" style="margin: 5% 10%">
    <h1 class="title">User List</h1>
    <div class="col-left">
        <c:forEach var="role" items="${usersRoles}" varStatus="status">
            <h2><a class="left-panel" href="listUsersByRole?role=${role}">${role}</a></h2>
        </c:forEach>
    </div>
    <div class="product-main" style="margin: 0 0 0 15%">
    <table border="1">
        <th>Login</th>
        <th>Role</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Address</th>
        <th>Balance</th>
        <th></th>

        <c:forEach var="user" items="${listUsers}" varStatus="status">
            <tr>
                <td>${user.login}</td>
                <td>${user.role}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.address}</td>
                <td>${user.balance}</td>
                <td>
                    <a class = "choose" href="editRole?id=${user.id}">Edit</a>
                    <a class = "choose"  href="deleteUser?id=${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    </div>
</div>
</body>
</html>
