<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Contact</title>
</head>
<body>
<div align="center">
    <h1>New/Edit Print</h1>
    <form:form action="savePrint" method="post" modelAttribute="print">
        <table>
            <form:hidden path="id"/>
            <tr>
                <td>
                    <select name="template">
                        <c:forEach items="${listTemplates}" var="temp">
                            <option value="${temp}"> ${temp.location}</option>
                        </c:forEach>
                    </select>
                </td>

            </tr>
            <tr>
                <td>
                    <select name="image">
                        <c:forEach items="${listImages}" var="img">
                            <option value="${img}"> ${img.title}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>