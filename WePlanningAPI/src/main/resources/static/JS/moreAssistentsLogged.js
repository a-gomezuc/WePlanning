$(document).ready(function(){
  var counter = 0;
  $("#btnMoreAssistents").on("click", function() {
	  $('#spinner').html("<img src='/IMG/spinner.gif>");
	  var valor= $("#btnMoreAssistents").attr("value");
	  counter++;
	  $.get("/moreAssistentsLogged", { page:counter, id:valor})
      .done(function(data) {
    	$('#spinner').empty();
        if (!$.trim(data)) {
          $("#btnMoreAssistents").html("No hay más planes");
        } else {
          $("#listAssistents").append(data);
        }
      });
  });
});