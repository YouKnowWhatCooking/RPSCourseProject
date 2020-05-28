<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    <title>New Combination</title>
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
    <h1 class="title">New Combination</h1>
    <form:form action="saveCombination" method="post" modelAttribute="combination">
        <table>
            <form:hidden path="id"/>
            <tr>
                <td>
                    <select name="clothes">
                        <c:forEach items="${listClothes}" var="clothes">
                            <option value="${clothes.id}"> ${clothes.title}</option>
                        </c:forEach>
                    </select>
                </td>

            </tr>
            <tr>
                <td>
                    <select name="print">
                        <c:forEach items="${listPrints}" var="print">
                            <option value="${print.id}"> ${print.template.location} + ${print.image.title}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>imageLink:</td>
                <td><form:input path="combinedImageLink" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><button type="submit">Save</button></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>