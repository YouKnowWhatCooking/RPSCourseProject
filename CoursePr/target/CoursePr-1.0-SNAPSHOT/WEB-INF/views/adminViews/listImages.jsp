<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List of images (Admin View)</title>
</head>
<body>
<div align="center">
    <h1>Images List</h1>
    <h3><a href="newImage">New Image</a></h3>
    <table border="1">
        <th>№</th>
        <th>Title</th>
        <th>Type</th>
        <th>image</th>
        <th>price</th>

        <c:forEach var="image" items="${listImages}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${image.title}</td>
                <td>${image.theme}</td>
                <td><img src="${image.link}" width="50" height="50"></td>
                <td>
                    <a href="editImage?id=${image.id}">Edit image</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="deleteImage?id=${image.id}">Delete image</a>
                </td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>