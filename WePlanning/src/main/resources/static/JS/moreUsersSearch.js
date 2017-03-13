$(document).ready(function(){
  var counter = 0;
  $("#btnMoreAllUsers").on("click", function() {
	  $('#spinner').html("<img src='/IMG/spinner.gif>");
	  counter++;
	  $.get("/moreUsersSearch", {
		  page:counter
      })
      .done(function(data) {
    	$('#spinner').empty();
        if (!$.trim(data)) {
          $("#btnMoreAllUsers").html("No hay más planes");
        } else {
          $("#AllUsers").append(data);
        }
      });
  });
});
