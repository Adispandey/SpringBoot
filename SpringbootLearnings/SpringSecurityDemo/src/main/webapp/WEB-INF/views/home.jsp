<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        h2 {
            color: #336699;
            text-align: center;
            margin-top: 50px;
        }
    </style>
</head>
<body>
    <h2>Home page</h2>



    <hr>

    	<!-- display user name and role -->

    	<p>
    		User: <security:authentication property="principal.username" />
    		<br><br>
    		Role(s): <security:authentication property="principal.authorities" />
    	</p>

    	<hr>

      <!-- Add a link to point to /leaders ... this is for the managers -->

        <security:authorize access="hasRole('Manager')">

  		<!-- Add a link to point to /leaders ... this is for the managers -->

  		<p>
  			<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
  			(Only for Manager)
  		</p>

  	</security:authorize>



	    <security:authorize access="hasRole('Admin')">

    		<!-- Add a link to point to /systems ... this is for the admins -->

    		<p>
    			<a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
    			(Only for Admin)
    		</p>

    	</security:authorize>



        <!-- Add a logout button -->
        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
        	<input type="submit" value="Logout" />


    	</form:form>

</body>
</html>
