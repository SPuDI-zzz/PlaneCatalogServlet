<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register page</title>
    <link rel="stylesheet" href="<c:url value="/styles/form.css"/>">
</head>
<body>
<div class="flex-container">
    <h1>REGISTER</h1>
    <c:if test="${not empty error}">
        <div>${error}</div>
    </c:if>
    <form action="register" method="post">
        <label>Login:<br>
            <input type="text" name="login" maxlength="50" size="30" pattern="^[A-Za-z0-9_]+$" required>
        </label>
      <label>Password:<br>
          <input type="text" name="password" maxlength="50" size="30" pattern="^[A-Za-z0-9!@#$%^&*_=+-]+$" required>
      </label>
      <div>
          Already have an account? <a href="login">Log in</a>
      </div>
      <input type="submit" value="Register">
    </form>
</div>
</body>
</html>
