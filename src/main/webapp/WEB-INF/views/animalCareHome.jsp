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
 
		<h1>eZoo <small>Animal Care</small></h1>
		<hr class="paw-primary">
		<table class="table table-striped table-hover table-responsive ezoo-datatable">
			<thead>
				<tr>
					<th class="text-center">Name</th>
					<th class="text-center">Kingdom</th>
					<th class="text-center">Phylum</th>
					<th class="text-center">Class</th>
					<th class="text-center">Order</th>
					<th class="text-center">Family</th>
					<th class="text-center">Genus</th>
					<th class="text-center">Species</th>
					<th class="text-center">Height(ft)</th>
					<th class="text-center">Weight(lbs)</th>
					<th class="text-center">Type</th>
					<th class="text-center">Health Status</th>
					<th class="text-center">Feeding Schedule</th>
					<security:authorize access="isAuthenticated()">
					<th class="text-center"></th>
					</security:authorize>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="animal" items="${animals}">
					<tr>
						<td><c:out value="${animal.name}" /></td>
						
						<td><c:out value="${animal.taxKingdom}" /></td>
						<td><c:out value="${animal.taxPhylum}" /></td>
						<td><c:out value="${animal.taxClass}" /></td>
						<td><c:out value="${animal.taxOrder}" /></td>
						<td><c:out value="${animal.taxFamily}" /></td>
						<td><c:out value="${animal.taxGenus}" /></td>
						<td><c:out value="${animal.taxSpecies}" /></td>
						
						<td><fmt:formatNumber value="${animal.height}"/></td>
						<td><fmt:formatNumber value="${animal.weight}"/></td>
						
						<td><c:out value="${animal.type}" /></td>
						<td><c:out value="${animal.healthStatus}" /></td>
						<td><c:out value="${(animal.feedingSchedule.scheduleId) == 0 ? '' : animal.feedingSchedule.scheduleId}" /></td>
						<security:authorize access="isAuthenticated()">
   						<td>
       						<form class="form-horizontal">
		                        <button type="submit" formaction="${pageContext.request.contextPath }/animal/${animal.animalID}" formmethod="get">Update</button>
		                    </form>
       						<form class="form-horizontal">
       						    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
							    <button type="submit" formaction="${pageContext.request.contextPath }/animal/delete/${animal.animalID}" formmethod="post">Delete</button>
							</form>
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
	    
	    <h1><small>Cool Facts</small></h1>
		<h4>Largest Animal (lbs):
		  <small>
		    <c:if test="${not empty largestAnimal}">
		      ${largestAnimal.name}, at ${largestAnimal.weight } lbs
		    </c:if>
		  </small>
		</h4>
		<h4>Longest Named Animal:
		  <small>
		    <c:if test="${not empty longestNamedAnimal}">
		      ${longestNamedAnimal.name }
		    </c:if>
		  </small>
		</h4>
	    
	  </div>
	</section>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
