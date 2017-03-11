$(document).ready(function(){
  var counter = 0;
  $("#btnMorePlans").on("click", function() {
	  $('#spinner').html("<img src='/IMG/spinner.gif>");
	  var valor= $("#btnMorePlans").attr("value");
	  counter++;
	  $.get("/morePlansUserLogged", { page:counter, id:valor})
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