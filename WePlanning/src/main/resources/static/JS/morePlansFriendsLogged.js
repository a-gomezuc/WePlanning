$(document).ready(function(){
  var counter = 0;
  $("#btnMoreFriendsPlans").on("click", function() {
	  $('#spinner').html("<img src='/IMG/spinner.gif>");
	  counter++;
	  $.get("/morePlansFriendsLogged", {
		  page:counter
      })
      .done(function(data) {
    	$('#spinner').empty();
        if (!$.trim(data)) {
          $("#btnMoreFriendsPlans").html("No hay más planes");
        } else {
          $("#friendsPlans").append(data);
        }
      });
  });
});
