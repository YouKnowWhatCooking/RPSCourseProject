<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Contact</title>
</head>
<body>
<div align="center">
    <h1>New/Edit Contact</h1>
    <form:form action="saveClothes" method="post" modelAttribute="clothes">
        <table>
            <form:hidden path="id"/>
            <tr>
                <td>Title:</td>
                <td><form:input path="title" value="${clothes.title}"/></td>
            </tr>
            <tr>
                <td>Type:</td>
                <td><form:input path="type" value="${clothes.type}"/></td>
            </tr>
            <tr>
                <td>imageLink:</td>
                <td><form:input path="imageLink" value="${clothes.imageLink}"/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><form:input path="price" value="${clothes.price}"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>