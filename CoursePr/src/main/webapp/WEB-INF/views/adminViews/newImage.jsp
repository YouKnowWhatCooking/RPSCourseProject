<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Image</title>
</head>
<body>
<div align="center">
    <h1>New/Edit Image</h1>
    <form:form action="saveImage" method="post" modelAttribute="image">
        <table>
            <form:hidden path="id"/>
            <tr>
                <td>Title:</td>
                <td><form:input path="title" /></td>
            </tr>
            <tr>
                <td>Theme:</td>
                <td><form:input path="theme" /></td>
            </tr>
            <tr>
                <td>link:</td>
                <td><form:input path="link" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
