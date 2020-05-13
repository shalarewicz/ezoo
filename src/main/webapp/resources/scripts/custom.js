/**
 * 
 */
$(document).ready(function(){
 
	// remove any error messages after 3 seconds
	setTimeout(function(){
		$(".alert").fadeOut("slow");
	} ,5000);
	
	
	
	//setup dataTables
	$(".ezoo-datatable").DataTable({
		stateSave: true,
		lengthChange: false,
		info:false
	});
	
    // Script to Activate the Carousel
    $('.carousel').carousel({
//        interval: 5000 //changes the speed
    });

	
//	$(".ezoo-simple-datatable").DataTable({
//		paging: false,
//		searching: false,
//		info: false,
//		ordering: false
//		
//	});	
});