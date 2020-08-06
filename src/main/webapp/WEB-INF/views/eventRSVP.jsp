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
        <security:authorize access="!isAuthenticated()"> 
        <p>You are are not authorized to view this page.</p>
        </security:authorize>
 
        <security:authorize access="isAuthenticated()">
        
        <h1>eZoo <small>Cancel RSVP</small></h1>
        <hr class="paw-primary">
        
        <jsp:include page="eventDetails.jsp" />
        <p>Are you sure you want to cancel your RSVP for this event? You can register for this event on the event home page.</p>
        
         <form class="form-horizontal">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
            <table class="table">
            <tbody>
               <tr>
                   <td width="50%" class="text-right">
                       <button formaction="${pageContext.request.contextPath }/event/cancelRSVP/${event.eventId}" formmethod="post" type="submit" class="btn btn-primary" >Cancel RSVP</button>
                    </td>
                    <td width="50%" class="text-left">
                       <button formaction="${pageContext.request.contextPath }/user/profile" formmethod="get" type="submit" class="btn btn-primary">No</button>
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