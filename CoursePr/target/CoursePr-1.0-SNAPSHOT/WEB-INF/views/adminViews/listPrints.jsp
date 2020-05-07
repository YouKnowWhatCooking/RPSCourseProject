<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List of Prints (Admin View)</title>
</head>
<body>
<div align="center">
    <h1>Print List</h1>
    <h3><a href="newPrint">New Print</a></h3>
    <table border="1">
        <th>№</th>
        <th>Template Location</th>
        <th>Image</th>
        <th>Price</th>

        <c:forEach var="prints" items="${listPrints}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${prints.template.location}</td>
                <td><img src="${prints.image.link}" width="50" height="50"></td>
                <td>${prints.template.price}</td>
                <td>
                    <a href="editPrint?id=${prints.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="deletePrint?id=${prints.id}">Delete</a>
                </td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>