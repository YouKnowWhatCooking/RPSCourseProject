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
    <h3><a href="newTemplate">New Clothes</a></h3>
    <table border="1">
        <th>№</th>
        <th>Location</th>
        <th>Price</th>


        <c:forEach var="templates" items="${listTemplates}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${templates.location}</td>
                <td>${templates.price}</td>
                <td>
                    <a href="editTemplate?id=${templates.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="deleteTemplate?id=${templates.id}">Delete</a>
                </td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>