	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
	
	<header>
	  <div class="container">
	     <c:if test="${param.error != null}">
            <p class="alert alert-danger">
                Failed to login.
                <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                  Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                </c:if>
            </p>
        </c:if>
        <!-- the configured LogoutConfigurer#logoutSuccessUrl is /login?logout and contains the query param logout -->
        <c:if test="${param.logout != null}">
            <p class="alert alert-success">
                You have been logged out.
            </p>
        </c:if>
	  
	    <c:choose>
        <c:when test="${not empty message }">
          <p class="alert ${messageClass}">${message }</p>
        <%
          session.setAttribute("message", null);
          session.setAttribute("messageClass", null);
        %>
        </c:when>
        </c:choose>
        
		<h1>eZoo <small>User Login</small></h1>
		<hr class="paw-primary">

        
        <sf:form commandName="user" method="post" class="form-horizontal">
        
            <div class="form-group">
                <sf:label for="username" path="username" class="col-sm-4 control-label">Username</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="text" class="form-control" id="username" name="username" path="username" placeholder="Username" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="username" />
                </div>
            </div>
            <div class="form-group">
                <sf:label for="password" path="password" class="col-sm-4 control-label">Password</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="password" class="form-control" id="password" name="password" path="password" placeholder="Password" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="password" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4 text-center">
                <!-- TODO Forgot Password link -->
                    <sf:button type="submit" class="btn btn-primary">Login</sf:button><br />
                    <span style="color: blue;"><security:authorize access="!isAuthenticated()"><a href="${pageContext.request.contextPath }/register">Register</a></security:authorize></span>
                </div>
            </div>
       </sf:form>

	  </div>
	</header>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />