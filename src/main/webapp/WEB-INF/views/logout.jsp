	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
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
        
		<h1>eZoo <small>Logout</small></h1>
    	<hr class="paw-primary">

        <h3>Do you wish to logout?</h3>
	    <form class="form-horizontal" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <div class="form-group">
	        <table class="table">
            <tbody>
	           <tr>
                   <td width="50%" class="text-right">
                       <button formaction="logout" type="submit" class="btn btn-primary" >Log Out</button>
                    </td>
                    <td width="50%" class="text-left">
                       <button formaction="${pageContext.request.contextPath }" type="submit" class="btn btn-primary">Cancel</button>
                    </td>
               </tr>
	        </tbody>
	        </table>
	        </div>
        </form>
      </div>

	</header>

    <!-- Footer -->
    <jsp:include page="footer.jsp" />