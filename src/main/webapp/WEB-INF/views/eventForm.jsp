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
        
        <h1>eZoo <small>Create Event</small></h1>
        
        <hr class="paw-primary">
        
        <security:authorize access="!isAuthenticated()"> 
                <p>You are are not authorized to view this page.</p>
        </security:authorize>
        
        <security:authorize access="isAuthenticated()">
        
        <sf:form commandName="event" method="post" class="form-horizontal">

            <div class="form-group">
                <sf:label for="name" path="name" class="col-sm-4 control-label">Name</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="text" class="form-control" id="name" name="name" path="name" value="${event.name}" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="name" />
                </div> 
            </div>
            <div class="form-group">
                <sf:label for="type" path="time" class="col-sm-4 control-label">Date and Time</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="datetime-local" class="form-control" id="time" name="time" path="time" value="${event.time}" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="time" />
                </div> 
            </div>
            <div class="form-group">
                <sf:label for="location" path="location" class="col-sm-4 control-label">Location</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="text" class="form-control" id="location" name="location" path="location" value="${event.location}" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="location" />
                </div> 
            </div>
            <div class="form-group">
            
                <sf:label for="description" path="description" class="col-sm-4 control-label">Description</sf:label>
                <div class = "col-sm-4">
                    <sf:textarea class="form-control" id="description" name="description" path="description" value="${event.description}" />
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="description" />
                </div> 
                
            </div>
            
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-1" style="text-align: center;">
                    <sf:button type="submit" class="btn btn-primary">Save</sf:button>
                </div>
            </div>
        </sf:form>
        
        </security:authorize>
        
      </div>
    </header>
    
    <!--  Footer -->
    <jsp:include page="footer.jsp" />
    