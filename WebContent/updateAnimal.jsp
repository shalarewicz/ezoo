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
	
		<h1>eZoo <small>Update Animal</small></h1>
		
		<hr class="paw-primary">
		
		<form action="updateAnimal" method="post" class="form-horizontal">
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">ID</label>
				<div class = "col-sm-4">
					<input type="text" readonly="readonly" class="form-control" id="id" name="id" value="${updatedAnimal.animalID}" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">Name</label>
				<div class = "col-sm-4">
					<input type="text" class="form-control" id="name" name="name" value="${updatedAnimal.name}" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">Health Status</label>
				<div class = "col-sm-4">
					 <select required="required" name="healthStatus" class="form-control">
	    				<c:forEach var="item" items="${healthStatuses}">
	       					<option value="${item}" ${item == updatedAnimal.healthStatus ? 'selected="selected"' : ''}>${item}</option>
					    </c:forEach>
					 </select>
				</div>
			</div>
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">Type</label>
				<div class = "col-sm-4">
				     <select required="required" name="type" class="form-control">
	    				<c:forEach var="item" items="${types}">
	       					<option value="${item}" ${item == updatedAnimal.type ? 'selected="selected"' : ''}>${item}</option>
					    </c:forEach>
					 </select>
				</div>
			</div>
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">Height (in)</label>
				<div class = "col-sm-4">
					<input type="number" class="form-control" id="height" name="height" value="${updatedAnimal.height}" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">Height (lbs)</label>
				<div class = "col-sm-4">
					<input type="number" class="form-control" id="weight" name="weight" value="${updatedAnimal.weight}" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">Kingdom</label>
				<div class = "col-sm-4">
					<input type="text" class="form-control" id="kingdom" name="kingdom" value="${updatedAnimal.taxKingdom}" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">Phylum</label>
				<div class = "col-sm-4">
					<input type="text" class="form-control" id="phylum" name="phylum" value="${updatedAnimal.taxPhylum}" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">Class</label>
				<div class = "col-sm-4">
					<input type="text" class="form-control" id="class" name="class" value="${updatedAnimal.taxClass}" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">Order</label>
				<div class = "col-sm-4">
					<input type="text" class="form-control" id="order" name="order" value="${updatedAnimal.taxOrder}" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">Family</label>
				<div class = "col-sm-4">
					<input type="text" class="form-control" id="family" name="family" value="${updatedAnimal.taxFamily}" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">Genus</label>
				<div class = "col-sm-4">
					<input type="text"  class="form-control" id="genus" name="genus" value="${updatedAnimal.taxGenus}" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">Species</label>
				<div class = "col-sm-4">
					<input type="text"  class="form-control" id="species" name="species" value="${updatedAnimal.taxSpecies}" required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label for="id" class="col-sm-4 control-label">Feeding Schedule</label>
				<div class = "col-sm-4">
					<input type="number"  class="form-control" id="feedingSchedule" name="feedingSchedule" value="${updatedAnimal.feedingSchedule == 0 ? '' : updatedAnimal.feedingSchedule}"/>
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
	