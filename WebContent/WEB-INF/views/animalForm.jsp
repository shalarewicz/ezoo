    <!-- Header -->
    <jsp:include page="header.jsp" />
    
    <!-- JSTL includes -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
    
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
    
        <h1>eZoo <small>Animal Form</small></h1>
        
        <hr class="paw-primary">
        
        <sf:form action="${animalAction}" commandName="animal" method="post" class="form-horizontal">
            <div class="form-group">
                <sf:label for="id" path="animalID" class="col-sm-4 control-label">ID</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="number" readonly="${animalAction == 'updateAnimal'}" class="form-control" id="id" name="id" path="animalID" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="animalID" />
                </div>                
            </div>
            <div class="form-group">
                <sf:label for="name" path="name" class="col-sm-4 control-label">Name</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="text" class="form-control" id="name" name="name" path="name" value="${animal.name}" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="name" />
                </div> 
            </div>
            <div class="form-group">
                <sf:label for="id" path="healthStatus" class="col-sm-4 control-label">Health Status</sf:label>
                <div class = "col-sm-4">
                     <sf:select required="required" name="healthStatus" path="healthStatus" class="form-control">
                        <sf:options items = "${animalStatuses}"/>
                     </sf:select>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="healthStatus" />
                </div>
            </div>
            <div class="form-group">
                <sf:label for="type" path="type" class="col-sm-4 control-label">Type</sf:label>
                <div class = "col-sm-4">
                     <sf:select required="required" name="type" path="type" class="form-control">
                         <sf:options items="${animalTypes}"/>
                     </sf:select>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="type" />
                </div> 
            </div>
            <div class="form-group">
                <sf:label for="height" path="height" class="col-sm-4 control-label">Height (in)</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="number" class="form-control" id="height" name="height" path="height" value="${animal.height}" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="height" />
                </div> 
            </div>
            <div class="form-group">
                <sf:label for="weight" path="weight" class="col-sm-4 control-label">Weight (lbs)</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="number" class="form-control" id="weight" name="weight" path="weight" value="${animal.weight}" />
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="weight" />
                </div> 
            </div>
            <div class="form-group">
                <sf:label for="kingdom" path="taxKingdom" class="col-sm-4 control-label">Kingdom</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="text" class="form-control" id="kingdom" name="kingdom" path="taxKingdom" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="taxKingdom" />
                </div> 
            </div>
            <div class="form-group">
                <sf:label for="phylum" path="taxPhylum" class="col-sm-4 control-label">Phylum</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="text" class="form-control" id="phylum" name="phylum" path="taxPhylum" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="taxPhylum" />
                </div> 
            </div>
            <div class="form-group">
                <sf:label for="class" path="taxClass" class="col-sm-4 control-label">Class</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="text" class="form-control" id="class" name="class" path="taxClass" required="required"/>
                </div>
            </div>
            <div class="form-group">
                <sf:label for="order" path="taxOrder" class="col-sm-4 control-label">Order</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="text" class="form-control" id="order" name="order" path="taxOrder" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="taxClass" />
                </div> 
            </div>
            <div class="form-group">
                <sf:label for="family" path="taxFamily" class="col-sm-4 control-label">Family</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="text" class="form-control" id="family" name="family" path="taxFamily" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="taxFamily" />
                </div> 
            </div>
            <div class="form-group">
                <sf:label for="genus" path="taxGenus" class="col-sm-4 control-label">Genus</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="text"  class="form-control" id="genus" name="genus" path="taxGenus" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="taxGenus" />
                </div> 
            </div>
            <div class="form-group">
                <sf:label for="species" path="taxSpecies" class="col-sm-4 control-label">Species</sf:label>
                <div class = "col-sm-4">
                    <sf:input type="text"  class="form-control" id="species" name="species" path="taxSpecies" required="required"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="taxSpecies" />
                </div> 
            </div>
            <div class="form-group">
                <sf:label for="feedingSchedule" path="feedingSchedule" class="col-sm-4 control-label">Feeding Schedule</sf:label>
                <div class = "col-sm-4">
                     <sf:input type="number"  class="form-control" id="feedingSchedule" name="feedingSchedule" path="feedingSchedule"/>
                </div>
                <div class="col-sm-4 form-error">
                    <sf:errors path="feedingSchedule" value="Please enter a valid feeding schedule id"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-1" style="text-align: center;">
                    <sf:button type="submit" class="btn btn-primary">Save</sf:button>
                </div>
            </div>
        </sf:form>
        
      </div>
    </header>
    
    <!--  Footer -->
    <jsp:include page="footer.jsp" />
    