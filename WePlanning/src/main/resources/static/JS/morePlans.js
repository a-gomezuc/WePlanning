$(document).ready(function(){
  var counter = 0;
  $("#btnMorePlans").on("click", function() {
	  $('#spinner').html("<img src='/IMG/spinner.gif>");
	  counter++;
	  $.get("/morePlans", {
		  page:counter
      })
      .done(function(data) {
    	$('#spinner').empty();
        if (!$.trim(data)) {
          $("#btnMorePlans").html("No hay más planes");
        } else {
          $("#plans").append(data);
        }
      });
  });
});
