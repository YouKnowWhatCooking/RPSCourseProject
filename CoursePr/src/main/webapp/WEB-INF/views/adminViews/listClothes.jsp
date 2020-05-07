<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List of Clothes (Admin View)</title>
</head>
<body>
<div align="center">
    <h1>Contact List</h1>
    <h3><a href="newClothes">New Clothes</a></h3>
    <table border="1">
        <th>№</th>
        <th>Title</th>
        <th>Type</th>
        <th>image</th>
        <th>price</th>

        <c:forEach var="clothes" items="${listClothes}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${clothes.title}</td>
                <td>${clothes.type}</td>
                <td><img src="${clothes.imageLink}" width="50" height="50"></td>
                <td>${clothes.price}</td>
                <td>
                    <a href="editClothes?id=${clothes.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="deleteClothes?id=${clothes.id}">Delete</a>
                </td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>