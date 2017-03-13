$(document).ready(function(){
  var counter = 0;
  $("#btnMoreComments").on("click", function() {
	  $('#spinner').html("<img src='/IMG/spinner.gif>");
	  var valor= $("#btnMoreComments").attr("value");
	  counter++;
	  $.get("/moreComments", { page:counter, id:valor})
      .done(function(data) {
    	$('#spinner').empty();
        if (!$.trim(data)) {
          $("#btnMoreComments").html("No hay más planes");
        } else {
          $("#comments").append(data);
        }
      });
  });
});