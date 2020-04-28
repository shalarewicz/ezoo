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
			
			<h1>eZoo <small>Feeding Schedules</small></h1>
			<hr class="paw-primary">
			<table class="table table-striped table-hover table-responsive ezoo-datatable">
				<thead>
					<tr>
						<th class="text-center">ID</th>
						<th class="text-center">Time</th>
						<th class="text-center">Recurrence</th>
						<th class="text-center">Food</th>
						<th class="text-center">Notes</th>
						<th class="text-center">Animals</th>
						<th class="text-center"></th>
						<th class="text-center"></th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="schedule" items="${schedules}">
						<tr>
							<td><c:out value="${schedule.scheduleId}" /></td>
							<td><c:out value="${schedule.feedingTime}" /></td>
							<td><c:out value="${schedule.recurrence}" /></td>
							<td><c:out value="${schedule.food}" /></td>
							<td><c:out value="${schedule.notes}" /></td>
							<td>
								<c:forEach var="item" items="${schedule.animals}">
									${item.name}<br />
								</c:forEach>
							</td>
							<td>
								<form class=form-horizontal>
								    <div class="col-sm-4">
	                                    <button formaction="feedingSchedule/${schedule.scheduleId}" formmethod="get" type="submit">Update</button>
	                                </div>
	                                <div class="col-sm-4">
	                                    <button formaction="feedingSchedule/delete/${schedule.scheduleId}" formmethod="post" type="submit">Delete</button>
	                                </div>
	                            </form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />