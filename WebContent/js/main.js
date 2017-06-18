/*// JavaScript Document



		    $("#search3").click(function(e) {
        $("#panel4").css("display","inline-block");
		  $("#panel5").css("display","inline-block");
    });

	
	
	var prev;
$("#rests li").click(function(e) {
	console.log($(this).attr("value"));
	$("#restname").html($(this).attr("value"));
	$("#rests li.highlight").removeClass("highlight");
	$(this).addClass("highlight");
   $("#panel3").css("display","inline-block");
});

$("#menutype li").click(function(e) {

	$("#menuname").html($(this).attr("value"));
	$("#menutype li.highlight").removeClass("highlight");
	$(this).addClass("highlight");
        $("#panel4").css("display","inline-block");
		  $("#panel5").css("display","inline-block");
});*/