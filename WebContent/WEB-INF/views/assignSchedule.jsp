	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
	
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
			
			<h1>eZoo <small>Assign Feeding Schedule</small></h1>
			<hr class="paw-primary">
		
			<form method="post" class="form-horizontal">
				<div class="form-group">
				    <label for="feedingSchedule" class="col-sm-4 control-label">Feeding Schedule ID</label>
				    <div class="col-sm-4">
				       <select required="required" name="feedingSchedule" id="feedingSchedule" class="form-control">
				            <c:forEach var="item" items="${feedingSchedules}">
                                <option value="${item}">${item}</option>
                            </c:forEach>
				       </select>
				    </div>
			  	</div>
			  	<div class="form-group">
				    <label for="animal" class="col-sm-4 control-label">Animal</label>
				    <div class="col-sm-4">
				      <select required="required" name="animal" id="animal" class="form-control">
                            <c:forEach var="animal" items="${animals}">
                                <option value="${animal.animalID}">
                                    ${String.format("%s (%s %s)", animal.name, animal.taxPhylum, animal.taxSpecies)}
                                </option>
                            </c:forEach>
                       </select>
				    </div>
			  	</div>
				
				<div class="form-group">
			    <div class="col-sm-offset-4 col-sm-1">
			      <button type="submit" class="btn btn-primary">Assign</button>
			    </div>
			  	</div>
			</form>		
		
	  </div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />