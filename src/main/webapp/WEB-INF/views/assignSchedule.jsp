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
            
            <h1>eZoo <small>Assign Feeding Schedule</small></h1>
            <hr class="paw-primary">
            
            <security:authorize access="!isAuthenticated()"> 
                <p>You are are not authorized to view this page.</p>
            </security:authorize>
            
            <security:authorize access="isAuthenticated()"> 
            
            <sf:form commandName="animalSchedule" method="post" class="form-horizontal">
                <div class="form-group">
                    <sf:label for="feedingSchedule" path="feedingSchedule" class="col-sm-4 control-label">Feeding Schedule ID</sf:label>
                    <div class="col-sm-4">
                       <sf:select required="required" name="feedingSchedule" id="feedingSchedule" path="feedingSchedule" class="form-control">
                            <sf:options items="${feedingSchedules}"/>
                       </sf:select>
                    </div>
                </div>
                <div class="form-group">
                    <sf:label for="animal" path="animal" class="col-sm-4 control-label">Animal</sf:label>
                    <div class="col-sm-4">
                      <sf:select required="required" name="animal" id="animal" path="animal.animalID" class="form-control">
                            <c:forEach var="animal" items="${animals}">
                                <sf:option value="${animal.animalID}">${animal.getName()} (${animal.getTaxPhylum()}, ${animal.getTaxSpecies()})</sf:option>
                            </c:forEach>
                       </sf:select>
                    </div>
                </div>
                
                <div class="form-group">
                <div class="col-sm-offset-4 col-sm-1">
                  <sf:button type="submit" class="btn btn-primary">Assign</sf:button>
                </div>
                </div>
            </sf:form>
            
            </security:authorize>
        
      </div>
    </header>
    
    <!-- Footer -->
    <jsp:include page="footer.jsp" />
