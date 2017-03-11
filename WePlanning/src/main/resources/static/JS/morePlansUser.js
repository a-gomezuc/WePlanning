$(document).ready(function(){
  var counter = 0;
  var valor=null;
  $("#btnMorePlans").on("click", function() {
	  valor = $("#btnMorePlans").value
	  $('#spinner').html("<img src='/IMG/spinner.gif>");
	  counter++;
	  $.get("/morePlansUser", {
		  page:counter,
		  id:valor
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