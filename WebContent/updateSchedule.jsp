	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
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
	
		<h1>eZoo <small>Update Feeding Schedule</small></h1>
		
		<hr class="paw-primary">
		
		<form action="updateSchedule" method="post" class="form-horizontal">
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">ID</label>
				<div class = "col-sm-4">
					<input type="text" readonly="readonly" class="form-control" id="id" name="id" value="${schedule.scheduleId}" required="required"/>
				</div>
			</div>
			<div class="form-group">
			    <label for="name" class="col-sm-4 control-label">Time</label>
			    <div class="col-sm-4">
			      <input type="time" class="form-control" id="feedingTime" name="feedingTime" value="${schedule.feedingTime}" required="required"/>
			    </div>
			</div>
			<div class="form-group">
			    <label for="name" class="col-sm-4 control-label">Recurrence</label>
			    <div class="col-sm-4">
			    <select required="required" name="recurrence" class="form-control">
    				<c:forEach var="item" items="${recurrences}">
       					<option value="${item}" ${item == schedule.recurrence ? 'selected="selected"' : ''}>${item}</option>
				    </c:forEach>
				</select>
			    </div>
			</div>
			<div class="form-group">
			    <label for="name" class="col-sm-4 control-label">Food</label>
			    <div class="col-sm-4">
			      <input type="text" class="form-control" id="food" name="food" value="${schedule.food}" required="required"/>
			    </div>
			</div>
			<div class="form-group">
			    <label for="name" class="col-sm-4 control-label">Notes</label>
			    <div class="col-sm-4">
			      <input type="text" class="form-control" id="notes" name="notes" value="${schedule.notes}"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-1">
					<button type="submit" class="btn btn-primary">Update</button>
				</div>
			</div>
		</form>
		
	  </div>
	</header>
	
	<!--  Footer -->
	<jsp:include page="footer.jsp" />
	