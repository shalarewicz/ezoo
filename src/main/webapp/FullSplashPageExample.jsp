<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>eZoo</title>
<link rel="shortcut icon" href="resources/imgs/favicon.png" type="image/x-icon">

<!-- CSS - Custom fonts -->
<link href="resources/libraries/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
<!-- <link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css"> -->

<!-- CSS - jQuery DataTables -->
<link href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />

<!-- CSS - Bootstrap -->
<link rel="stylesheet" type="text/css" href="resources/libraries/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="resources/libraries/css/freelancer.css"/>
<link rel="stylesheet" type="text/css" href="resources/libraries/css/half-slider.css"/>

<!-- CSS - Custom -->
<link rel="stylesheet" type="text/css" href="resources/styles/custom.css"/>

</head>
<body id="page-top" class="index">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
<!--                 <a class="navbar-brand" href="#page-top">PubHub</a> -->
                <a class="navbar-brand" href="${pageContext.request.contextPath }">eZoo</a>
                <c:if test="${authUser != null }">
                  <p class="navbar-text">Welcome: ${authUser.username}</p>
				</c:if>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Animal Care<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          <li><a href="animalCare">Home (Level 100)</a></li>
                          <li class="divider"></li>
                          <li><a href="">Add Animal</a></li>
                          <li><a href="">Feeding Schedules</a></li>
                          <li><a href="">Add Feeding Schedule</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Event Management<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          <li><a href="eventManagement">Home (Level 200)</a></li>
                          <li class="divider"></li>
					      <li><a href="">Create Event</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Employee Management<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          <li><a href="employeeManagement">Home (Level 300)</a></li>
                          <li class="divider"></li>
                          <li><a href="#">Link 2</a></li>
                          <li><a href="#">Link 3</a></li>
                        </ul>
                    </li>
                    <c:choose>
                    <c:when test="${authUser == null }">
                      <li>
                          <a href="login">Login</a>
                      </li> 
                    </c:when>
                    <c:otherwise>
                      <li>
                          <a href="logout">Logout</a>
                      </li> 
                    </c:otherwise>
                    </c:choose>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

	
    <!-- Header -->
    <header>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
<!--                     <img class="img-responsive" src="resources/libraries/img/profile.png" alt=""> -->
                    <div class="intro-text">
                        <span class="name">eZoo</span>
                        <hr class="paw-primary">
                        <span class="skills">Belong again.</span>
                    </div>
                </div>
            </div>
        </div>
    </header>
	<section id="myCarousel" class="carousel slide">
<!-- 	<div class="container"> -->
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1" class=""></li>
            <li data-target="#myCarousel" data-slide-to="2" class=""></li>            
        </ol>

        <!-- Wrapper for Slides -->
        <div class="carousel-inner">
            <div class="item active">
                <!-- Set the first background image using inline CSS below. -->
                <div class="fill" style="background-image:url('${request.contextPath}resources/imgs/ezoo1.jpg');"></div>
                <div class="carousel-caption">
                    <h2>Explore.</h2>
                </div>
            </div>
            <div class="item">
                <!-- Set the second background image using inline CSS below. -->
                <div class="fill" style="background-image:url('${request.contextPath}resources/imgs/ezoo2.jpg');"></div>
                <div class="carousel-caption">
                    <h2>Imagine.</h2>
                </div>
            </div>
            <div class="item">
                <!-- Set the third background image using inline CSS below. -->
                <div class="fill" style="background-image:url('${request.contextPath}resources/imgs/ezoo3.jpg');"></div>
                <div class="carousel-caption">
                    <h2>Belong again.</h2>
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>
<!--     </div> -->
    </section>

    <!-- About Section -->
    <section class="" id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>About</h2>
                    <hr class="paw-primary light">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus eget ligula eget nisl rutrum fringilla. Nullam justo tellus, condimentum eu libero eu, aliquam hendrerit ipsum. Praesent fringilla lorem at pretium egestas. Nam velit mi, scelerisque eget faucibus vel, posuere quis sapien. Donec iaculis interdum dolor sit amet efficitur. Maecenas viverra magna id dolor hendrerit semper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi tincidunt magna vitae dignissim luctus. Etiam a lorem rhoncus, gravida ex nec, tempus tellus. Nulla laoreet tellus ex, sit amet laoreet tortor faucibus non. Curabitur quis mi vitae nisi venenatis vulputate. Ut laoreet tempor gravida. Aenean a sagittis ligula.
                    </p>
                </div>
                <div class="col-lg-12 text-center"><i class="fa fa-fire fa-1"></i></div>
                <div class="col-lg-12">
                    <p>
                    Maecenas mattis cursus lobortis. Maecenas consequat mauris ut pulvinar semper. Morbi id dolor vehicula, sagittis metus malesuada, venenatis libero. Praesent pellentesque id turpis sit amet elementum. Cras sed dolor pulvinar, pharetra ex et, efficitur lacus. Nam mollis sit amet velit sed placerat. Praesent felis arcu, viverra at iaculis in, pharetra quis nunc. Ut convallis magna et ultricies sagittis. Fusce eget ante quam. Morbi quis mollis diam. Morbi urna neque, aliquet sed placerat ut, tempus vitae ante. Fusce pretium ac justo nec convallis. Nunc ultrices turpis vehicula leo aliquet porta. Donec posuere libero at sapien rhoncus laoreet. Fusce lacinia turpis vel tortor maximus, quis facilisis ex eleifend. Vestibulum interdum lacinia lacus, id congue nibh gravida ut.
                    </p>
                </div>
                

            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer class="text-center">
        
        <div class="footer-below">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        Copyright &copy; eZoo 2016
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="resources/libraries/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="resources/libraries/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="resources/libraries/js/classie.js"></script>
    <script src="resources/libraries/js/cbpAnimatedHeader.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="resources/libraries/js/jqBootstrapValidation.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="resources/libraries/js/freelancer.js"></script>
    
<!--     jQuery DataTables -->
    <script type="text/javascript" src="https://cdn.datatables.net/t/bs/dt-1.10.11/datatables.js"></script>
    
<!--     Custom Javascript -->
    <script src="resources/scripts/custom.js"></script>
</body>
</html>