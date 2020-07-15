	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
	<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
	
	
<!-- 	Just some stuff you need -->
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
	
		<h1>eZoo <small>Save Feeding Schedule</small></h1>
		
		<hr class="paw-primary">
		
		<security:authorize access="!isAuthenticated()"> 
                <p>You are are not authorized to view this page.</p>
        </security:authorize>
		
		<security:authorize access="isAuthenticated()"> 
		<sf:form commandName="feedingSchedule" method="post" class="form-horizontal">
		
			<div class="form-group">
                <sf:label for="id" path="scheduleId" class="col-sm-4 control-label">ID</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="number" class="form-control" id="id" name="id" path="scheduleId" placeholder="ID" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="scheduleId" />
                </div>
            </div>
            <div class="form-group">
                <sf:label for="time" path="feedingTime" class="col-sm-4 control-label">Time</sf:label>
                <div class="col-sm-4">
                  <sf:input type="time" class="form-control" id="time" name="time" path="feedingTime" placeholder="time" required="required"/>
                 </div>
                 <div class="col-sm-4 form-error">
                  <sf:errors path="feedingTime" />
                </div>
            </div>
            <div class="form-group">
                <sf:label for="recurrence" path="recurrence" class="col-sm-4 control-label">Recurrence</sf:label>
                <div class="col-sm-4">
                    <sf:select required="required" name="recurrence" path="recurrence" class="form-control">
                        <sf:option value="BLANK">--SELECT--</sf:option>
                        <sf:options items="${recurrences}" />
                </sf:select>
                </div>  
                <div class="col-sm-4 form-error">
                  <sf:errors path="recurrence" />
                </div>
            </div>
            <div class="form-group">
                <sf:label for="food" path="food" class="col-sm-4 control-label">Food</sf:label>
                <div class="col-sm-4">
                  <sf:input type="text" class="form-control" id="food" name="food" path="food" placeholder="food" required="required"/>
                 </div>
                 <div class="col-sm-4 form-error">
                  <sf:errors path="food" />
                </div>
            </div>
            <div class="form-group">
                <sf:label for="notes" path="notes" class="col-sm-4 control-label">Notes</sf:label>
                <div class="col-sm-4">
                  <sf:input type="text" class="form-control" id="notes" name="notes" path="notes" placeholder="notes"/>
                 </div>
                 <div class="col-sm-4 form-error">
                  <sf:errors path="notes" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-1">
                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
            </div>
		</sf:form>
		</security:authorize>
		
	  </div>
	</header>
	
	<!--  Footer -->
	<jsp:include page="footer.jsp" />
	