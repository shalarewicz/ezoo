
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
	    <c:choose>
        <c:when test="${not empty message }">
          <p class="alert ${messageClass}">${message }</p>
        <%
          session.setAttribute("message", null);
          session.setAttribute("messageClass", null);
        %>
        </c:when>
        </c:choose>
        
		<h1>eZoo <small>New User Registration</small></h1>
       	<hr class="paw-primary">
       	
       	<security:authorize access="isAuthenticated()"> 
       	    <p>You are currently signed in and cannot register a new account.</p>
       	</security:authorize>
       	
       	<security:authorize access="!isAuthenticated()"> 
        
        <sf:form commandName="user" method="post" class="form-horizontal">
        
            <div class="form-group">
                <sf:label for="firstName" path="firstName" class="col-sm-4 control-label">First Name</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="text" class="form-control" id="firstName" name="firstName" path="firstName" placeholder="First Name" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="firstName" />
                </div>
            </div>
            <div class="form-group">
                <sf:label for="lastName" path="lastName" class="col-sm-4 control-label">Last Name</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="text" class="form-control" id="lastName" name="lastName" path="lastName" placeholder="Last Name" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="lastName" />
                </div>
            </div>
            <div class="form-group">
                <sf:label for="email" path="email" class="col-sm-4 control-label">Email</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="email" class="form-control" id="email" name="email" path="email" placeholder="email@example.com" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="email" />
                </div>
            </div>
            <div class="form-group">
                <sf:label for="phone" path="phone" class="col-sm-4 control-label">Phone Number</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="tel" class="form-control" id="phone" name="phone" path="phone" placeholder="(123) 456-7890"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="phone" />
                </div>
            </div>
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
                <sf:label for="confirmPassword" path="confirmPassword" class="col-sm-4 control-label">Confirm Password</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="password" class="form-control" id="confirmPassword" name="confirmPassword" path="confirmPassword" placeholder="Password" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="confirmPassword" />
                </div>
            </div>
            <div class="form-group">
                <div class = "col-sm-offset-4 col-sm-4 text-center">
                    <sf:button type="submit" class="btn btn-primary">Register</sf:button>
                </div>
            </div>
       </sf:form>
       
       </security:authorize>
       
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />