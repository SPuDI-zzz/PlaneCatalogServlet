<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Plane list</title>
    <link rel="stylesheet" href="<c:url value="/styles/planes.css"/>">
</head>
<body>
<div class="logout">
    <a href="<c:url value="/user/logout"/>">Logout</a>
</div>
<h1>Your Plane list</h1>
<div class="btn-container">
    <a class="btn" href="planes/new">Add new plane</a>
</div>
<c:if test="${empty planeList}">
    <div class="empty">It's empty!</div>
</c:if>
<c:if test="${not empty planeList}">
    <table>
        <thead>
        <tr>
            <th>Mark</th>
            <th>Model</th>
            <th>Type</th>
            <th>Mileage</th>
            <th>Price</th>
            <th>Edit</th>
            <th>Remove</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${planeList}" var="plane">
            <tr>
                <td><c:out value="${plane.getMark()}"/>
                <td><c:out value="${plane.getModel()}"/>
                <td><c:out value="${plane.getType()}"/>
                <td><c:out value="${plane.getMileage()}"/>
                <td><c:out value="${plane.getPrice()}"/>
                <td>
                    <form action="planes/edit" method="get">
                        <input type="hidden" name="idPlane" value="<c:out value="${plane.getId()}"/>"><br>
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="planes/remove" method="post">
                        <input type="hidden" name="idPlane" value="<c:out value="${plane.getId()}"/>"><br>
                        <input type="submit" value="Remove">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
