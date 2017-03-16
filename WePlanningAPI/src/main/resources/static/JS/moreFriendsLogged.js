$(document).ready(function(){
  var counter = 0;
  $("#btnMoreFriends").on("click", function() {
	  $('#spinner').html("<img src='/IMG/spinner.gif>");
	  var valor= $("#btnMoreFriends").attr("value");
	  counter++;
	  $.get("/moreFriendsLogged", { page:counter, id:valor})
      .done(function(data) {
    	$('#spinner').empty();
        if (!$.trim(data)) {
          $("#btnMoreFriends").html("No hay más planes");
        } else {
          $("#friendsl").append(data);
        }
      });
  });
});