    <!-- Header -->
    <jsp:include page="header.jsp" />
    
    <!-- JSTL includes -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    
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
        
        <h1>eZoo <small>Event Information</small></h1>
        <hr class="paw-primary">
        
        <jsp:include page="eventDetails.jsp" />

         <security:authorize access="isAuthenticated()">
         <form class="form-horizontal">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
            <table class="table">
            <tbody>
               <tr>
                   <td width="50%" class="text-right">
                       <button formaction="${pageContext.request.contextPath }/event/register/${event.eventId}" formmethod="post" type="submit" class="btn btn-primary" >Register</button>
                    </td>
                    <td width="50%" class="text-left">
                       <button formaction="${pageContext.request.contextPath }/event/home" formmethod="get" type="submit" class="btn btn-primary">Event Home</button>
                    </td>
               </tr>
            </tbody>
            </table>
            </div>
        </form>
        
        
        </security:authorize>

      </div>

    </header>

    <!-- Footer -->
    <jsp:include page="footer.jsp" />