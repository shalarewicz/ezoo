<!--
  ____                 _                  
 |  _ \ _____   ____ _| |_ _   _ _ __ ___ 
 | |_) / _ \ \ / / _` | __| | | | '__/ _ \
 |  _ <  __/\ V / (_| | |_| |_| | | |  __/
 |_| \_\___| \_/ \__,_|\__|\__,_|_|  \___|
 
-->

	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
	  
		<h1>eZoo <small>Template</small></h1>
		
		Content

	  </div>
	</header>
	
	<section>
	  <div class="container">
	    
	    <h1><small>Another Section</small></h1>
	    Content
	    
	  </div>
	</section>

	<section class="success">
	  <div class="container">

		<h1><small>Another Section</small></h1>
	    Content

	  </div>
	</section>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />