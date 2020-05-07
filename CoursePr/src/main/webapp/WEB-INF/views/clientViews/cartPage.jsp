<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List of Clothes (Admin View)</title>
</head>
<body>
<div align="center">
    <h1>Cart Page</h1>
    <table border="1">
        <th>Options</th>
        <th>Clothes title</th>
        <th>Clothes Image</th>
        <th>Print title</th>
        <th>Print Image</th>
        <th>Quantity</th>
        <th>Total Sum</th>

        <c:forEach var="item" items="${sessionScope.cart}" varStatus="status">
            <tr>
                <td align="center"><a class="choose" href="removeFromCart?id=${item.print.id}">Remove item</a></td>
                <td>${item.clothes.title}</td>
                <td><img src="${item.clothes.imageLink}" width="50" height="50"></td>
                <td>${item.print.image.title}</td>
                <td><img src="${item.print.image.link}" width="50" height="50"></td>
                <td>${item.quantity}</td>
                <td>${(item.print.template.price + item.clothes.price)*item.quantity}</td>
            </tr>
        </c:forEach>
    </table>
    <a class="choose" href="listCombinations">Continue shopping</a>
</div>
</body>
</html>