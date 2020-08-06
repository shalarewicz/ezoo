    <!-- Header -->
    <jsp:include page="header.jsp" />
    
    <!-- JSTL includes -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
    
<!--    Just some stuff you need -->
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
        
	        <h1>eZoo <small>My Events</small></h1>
	        <hr class="paw-primary">

         </security:authorize>
       </div>
    </header>
    <security:authorize access="isAuthenticated()">
    
    <section style="margin-top:0px; margin-bottom:100px;">
       <div class="container float-left">
       
        <h1><small>Events You Created</small></h1>
        <ul>
            <c:if test="${empty createdEvents}">
                <li>You have not created any events.</li>
            </c:if>
            
            <c:forEach items="${createdEvents}" var="event">
                <fmt:parseDate value="${event.time}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
                <fmt:formatDate value="${parsedDate}" pattern="d MMM, yyyy hh:mm a" var="formattedDate"/>
                <li>
                    ${formattedDate}: ${event.name} 
                    <a href="${pageContext.request.contextPath}/event/${event.eventId}">Edit</a> | 
                    <a href="${pageContext.request.contextPath}/event/cancel/${event.eventId}">Cancel</a>
                </li>
            </c:forEach>
        </ul>       
       </div>
     
       <div class="container float-right">
       
        <h1><small>Your Upcoming Events</small></h1>
        <ul>
            <c:if test="${empty userEvents}">
                <li>You have not registered for any events.</li>
            </c:if>
            
            <c:forEach items="${userEvents}" var="event">
                <fmt:parseDate value="${event.time}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
                <fmt:formatDate value="${parsedDate}" pattern="d MMM, yyyy hh:mm a" var="formattedDate"/>
                <li>${formattedDate}: ${event.name} <a href="${pageContext.request.contextPath}/event/cancelRegistration/${event.eventId}">Cancel RSVP</a></li>
            </c:forEach>
            <c:if test="${empty userEvents}">
                <li><a href="${pageContext.request.contextPath}/user/events">See all upcoming events.</a>.</li>
            </c:if>
        </ul>       
      </div>
      </section>
      
    </security:authorize>

    
    <!-- Footer -->
    <jsp:include page="footer.jsp" />
