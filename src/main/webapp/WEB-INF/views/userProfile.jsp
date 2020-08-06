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
        
        <h2>Welcome ${user.firstName}</h2>
        <h2><small><strong>My Profile</strong></small></h2>
        <hr class="paw-primary">
        
        <h3 style="text-align:left;">Account Information</h3>
        <table class="info-table" style="width:50%;">
        <tbody>
            <tr>
	            <td width="20%" class="field-label"><strong>Name:</strong></td>
	            <td width="80%" class="field"><strong>${user.firstName} ${user.lastName}</strong></td>
            </tr>
            <tr>
                <td width="20%" class="field-label"><strong>Email:</strong></td>
                <td width="80%" class="field"><strong>${user.email}</strong></td>
            </tr>
            <tr>
                <td width="20%" class="field-label"><strong>Phone:</strong></td>
                <td width="80%" class="field"><strong>${user.phone}</strong></td>
            </tr>
            <tr>
                <td width="20%" class="field-label"><strong>Username:</strong></td>
                <td width="80%" class="field"><strong>${user.username}</strong></td>
            </tr>
        </tbody>
        </table>
        </security:authorize>
      </div>
    </header>
    
    <security:authorize access="isAuthenticated()">
    <section>
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
                    <a href="${pageContext.request.contextPath}/event/update/${event.eventId}">Edit</a> | 
                    <a href="${pageContext.request.contextPath}/event/cancel/${event.eventId}">Cancel</a>
                </li>
            </c:forEach>
            <li><a href="${pageContext.request.contextPath}/user/events">See all events</a></li>
        </ul>       
      </div>
      
      <div class="container float-right">
        
        <h1><small>My Upcoming Events</small></h1>
        <ul>
            <c:if test="${empty userEvents}">
                <li>You have not registered for any events. <a href="${pageContext.request.contextPath}/event/home">See all upcoming events</a></li>
            </c:if>
            
            <c:forEach items="${userEvents}" var="event">
                <fmt:parseDate value="${event.time}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
                <fmt:formatDate value="${parsedDate}" pattern="d MMM, yyyy hh:mm a" var="formattedDate"/>
                <li>${formattedDate}: ${event.name} <a href="${pageContext.request.contextPath}/event/cancelRSVP/${event.eventId}">Cancel RSVP</a></li>
            </c:forEach>
            <li><a href="${pageContext.request.contextPath}/user/events">See all events</a></li>
        </ul>       
      </div>
      </section>
      <section style="margin-bottom: 100px;">
      </section>
    </security:authorize>

    <!-- Footer -->
    <jsp:include page="footer.jsp" />
