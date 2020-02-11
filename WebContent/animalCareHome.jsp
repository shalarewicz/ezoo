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
					<th class="text-center"></th>
					<th class="text-center"></th>
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
						<td><c:out value="${(animal.feedingSchedule) == 0 ? '' : animal.feedingSchedule}" /></td>
						<td>
							<form action="updateAnimal" method="get" class=form-horizontal>
							    <div class="col-sm-4">
							    	<input type="hidden" class="form-control" id="id" name="id" value="${animal.animalID}"/>
							    	<input type="hidden" class="form-control" id="name" name="name" value="${animal.name}"/>
									<input type="hidden" class="form-control" id="kingdom" name="taxKingdom" value="${animal.taxKingdom}"/>
									<input type="hidden" class="form-control" id="phylum" name="taxPhylum" value="${animal.taxPhylum}"/>
									<input type="hidden" class="form-control" id="class" name="taxClass" value="${animal.taxClass}"/>
									<input type="hidden" class="form-control" id="order" name="taxOrder" value="${animal.taxOrder}"/>
									<input type="hidden" class="form-control" id="family" name="taxFamily" value="${animal.taxFamily}"/>
									<input type="hidden" class="form-control" id="genus" name="taxGenus" value="${animal.taxGenus}"/>
									<input type="hidden" class="form-control" id="species" name="taxSpecies" value="${animal.taxSpecies}"/>
									<input type="hidden" class="form-control" id="height" name="height" value="${animal.height}"/>
									<input type="hidden" class="form-control" id="weight" name="weight" value="${animal.weight}"/>
									<input type="hidden" class="form-control" id="type" name="type" value="${animal.type}"/>
									<input type="hidden" class="form-control" id="healthStatus" name="healthStatus" value="${animal.healthStatus}"/>
									<input type="hidden" class="form-control" id="feedingSchedule" name="feedingSchedule" value="${animal.feedingSchedule}"/>
									<input type="submit" name="updateAnimal" value="Update" />
							    </div>
							</form>
						</td>
						<td>
							<form action="deleteAnimal" method="post" class=form-horizontal>
								<div class="col-sm-4">
									<input type="submit" name="deleteAnimal" value="Delete" />
								    <input type="hidden" class="form-control" id="id" name="id" value="${animal.animalID}"/>
							    </div>
							</form>
						</td>
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
