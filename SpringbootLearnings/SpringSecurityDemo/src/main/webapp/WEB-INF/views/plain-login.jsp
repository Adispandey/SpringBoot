<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>My custom login form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        h1 {
            color: #336699;
        }

        form {
            margin: 0 auto;
            max-width: 300px;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .failed {
            color: #ff0000;
        }

        p {
            margin-bottom: 10px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #336699;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #254c66;
        }

        h1{
        text-align: center;
        }
    </style>
</head>

<body>
    <h1>My custom Login form</h1>

    <form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">

        <!-- Check for login error -->
        <c:if test="${param.error != null}">
            <i class="failed">Sorry! You entered an invalid username/password.</i>
        </c:if>

        <!-- Check for logout -->

       <c:if test="${param.logout != null}">

       <div class="alert alert-success col-xs-offset-1 col-xs-10">
        You have been logged out.
       </div>

       </c:if>

        <p>Username <input type="text" name="username" /></p>
        <p>Password <input type="password" name="password" /></p>
        <input type="submit" value="Login" />

    </form:form>

</body>
</html>
