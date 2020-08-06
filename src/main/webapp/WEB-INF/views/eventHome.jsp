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
 
        <h1>eZoo <small>Events</small></h1>
        <hr class="paw-primary">
        <table class="table table-striped table-hover table-responsive ezoo-datatable">
            <thead>
                <tr>
                    <th class="text-center">Name</th>
                    <th class="text-center">Time</th>
                    <th class="text-center">Location</th>
                    <security:authorize access="isAuthenticated()">
                    <th class="text-center"></th>
                    </security:authorize>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="event" items="${events}">
                    <tr>
                        <td><a style="text-decoration:none" href="${pageContext.request.contextPath }/event/details/${event.eventId}"><c:out value="${event.name}" /></a></td>
                        <fmt:parseDate value="${event.time}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
                        <fmt:formatDate value="${parsedDate}" pattern="d MMM, yyyy hh:mm a" var="formattedDate"/>
                        <td><c:out value="${formattedDate}" /></td>
                        <td><c:out value="${event.location}" /></td>
                        <security:authorize access="isAuthenticated()">
                        <td>
                            <sf:form class="form-horizontal">
                                <button type="submit" formaction="${pageContext.request.contextPath }/event/register/${event.eventId}" formmethod="post">Register</button>
                            </sf:form>
                        </td>
                        </security:authorize>
                    </tr>
                </c:forEach>
            </tbody>
        </table>    

      </div>
    </header>
    
    <section>
      <div class="container">
        
        <h1><small>Upcoming Events</small></h1>
        <!-- TODO -->        
      </div>
    </section>

    <!-- Footer -->
    <jsp:include page="footer.jsp" />
