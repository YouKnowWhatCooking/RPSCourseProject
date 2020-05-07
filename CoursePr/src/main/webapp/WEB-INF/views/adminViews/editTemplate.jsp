<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Template</title>
</head>
<body>
<div align="center">
    <h1>New/Edit Template</h1>
    <form:form action="saveTemplate" method="post" modelAttribute="template">
        <table>
            <form:hidden path="id"/>
            <tr>
                <td>Location:</td>
                <td><form:input path="location" value="${template.location}"/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><form:input path="price" value="${template.price}"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>