// JavaScript Document
$(document).ready(function(e) {

	$.getJSON('/gmfws1/v1/cities', '', function(data) {
		console.log();

		var obj = jQuery.parseJSON(JSON.stringify(data));
		var dat = "<option value='-1'>Select City</option>";
		var l = obj.cities;
		for (var i = 0; i < l.length; i++) {
			dat = dat + "<option value='" + l[i] + "'>" + l[i] + "</option>"
		}

		document.getElementById("cities").innerHTML = dat;
		$("#panel1").addClass("fadeIn");
	});
});

$("#rests li").click(function(e) {
	console.log($(this).attr("value"));
	$("#restname").html($(this).attr("value"));
	$("#rests li.highlight").removeClass("highlight");
	$(this).addClass("highlight");
	$("#panel3").css("display", "inline-block");
});
var areaobj;
function changed(area) {
	if (area == "-1") {
alert("Select Location");
	} else {
		$.getJSON('/gmfws1/v1/areas/' + area, '', function(data) {
			console.log();

			var obj = jQuery.parseJSON(JSON.stringify(data));
			var dat = "<option value='-1'>Select City</option>";
			var l = obj.areasOfCity;
			areaobj=l;
			for (var i = 0; i < l.length; i++) {
				dat = dat + "<option value='" + l[i].areaCode + "'>"
						+ l[i].areaName + "</option>"
			}

			document.getElementById("areas").innerHTML = dat;
		});
	}
}

$("#search").click(
		function(e) {
			var area = $("#areas").val()
			var cities = $("#cities").val();
		
			if (area == "-1") {
				alert("Select Areas");
				return false;
			}
			if (cities =="-1" ) {
				alert("Select Location");
				return false;
			} else {
				$('#panel2').addClass("slideDown");
				$('#panel1').css('display', 'none');
				$.getJSON('/gmfws1/v1/restaurants/' + $("#areas").val(), '',
						function(data) {
							console.log();

							var obj = jQuery.parseJSON(JSON.stringify(data));
							var dat = "";
							var l = obj.restaurantsOfArea;
							for (var i = 0; i < l.length; i++) {
								dat = dat + "<li value='" + l[i].Restaurant_Id
										+ "/" + l[i].Name + "' >" + l[i].Name+ "<a href='#' class='btn btn-primary btn-lg' data-toggle='modal' data-target='#myModal' ><i  class='icon-info'></i></a></li>";
							}

							document.getElementById("rests").innerHTML = dat;
						
			
								$.cookie("area", cities, {
									   expires : 1,           // expires in 10
																// days
									});
						
								localStorage.setItem('area', cities);
						
						});
			$.getJSON('/gmfws1/v1/restaurants/tags', '',
						function(data) {
							var pre="<div id='arrow'></div>";
							var obj = jQuery.parseJSON(JSON.stringify(data));
							var l=obj.tags;
							var datastring="";
							datastring="<span class='filter'><input type='checkbox' name='filter[]' value='' id='group0'/><label for='group0'>All</label></span> ";
						for(var i=1;i<=l.length;i++){
							datastring+="<span class='filter'><input type='checkbox' name='filter[]' value='"+l[i-1].tagid+"' id='group"+i+"'/><label for='group"+i+"'>"+l[i-1].tagname+"</label></span> "}
$("#menu").html(pre+datastring);
						});
				console.log($("#areas").val());
			}
		});

$(document).on(
		'click',
		'#rests li',
		function() {
			var temp = $(this).attr("value");
			var temp2 = temp.split("/");
			$.getJSON('/gmfws1/v1/menutype/' + temp2[0], '', function(data) {

				var obj = jQuery.parseJSON(JSON.stringify(data));
				var dat = "";
				var l = obj.ItemTypesRestaurant;
				for (var i = 0; i < l.length; i++) {
					dat = dat + "<li value='" + temp2[0] + "/"
							+ obj.ItemTypesRestaurant[i] + "' >"
							+ obj.ItemTypesRestaurant[i] + "</li>";
				}
				document.getElementById("menutype").innerHTML = dat;
			});
			$("#restname").html(temp2[1]);
			$("#rests li.highlight").removeClass("highlight");
			$(this).addClass("highlight");
			$("#panel3").addClass("slideDown");

		});

$(document).on(
		'click',
		'#menutype li',
		function() {
			var temp = $(this).attr("value");
			var temp2 = temp.split("/");
			$.getJSON('/gmfws1/v1/menu/' + $(this).attr("value"), '', function(
					data) {
				var obj = jQuery.parseJSON(JSON.stringify(data));
				var dat = "";
				var l = obj.menuOfRestaurant;
				for (var i = 0; i < l.length; i++) {
					dat = dat + "<li value='" + temp2[0] + "/" + l[i].Item_Code
							+ "' >" + l[i].Name + "</li>";
				}
				document.getElementById("menuitem").innerHTML = dat;
			});

			$("#menuname").html(temp2[1]);
			$("#menutype li.highlight").removeClass("highlight");
			$(this).addClass("highlight");
			$("#panel4").addClass("slideDown");
			$("#panel5").addClass("slideLeft");
		});
var total = 0;
$(document).on(
		'click',
		'#menuitem li',
		function() {
			var temp = $(this).attr("value");
			var temp2 = temp.split("/");
			$.ajax({
				url : "/gmfws1/v1/cart/add",
				type : "POST",
				async : "true",
				data : "restID=" + temp2[0] + "&itemID=" + temp2[1] + "&qty=1",
				success : function(data) {
					var total = 0;
					var finalstring = "";
					var count = 0;
					var itemcount=0;
					$.each(data.cartItems, function(cartkey, cartcontents) {

						$.each(cartcontents, function(itemkey, items) {

							finalstring += '<tr>' + '<td>' + items.itemName
									+ '</td>'
									+ '<td><div class="minus" data-id="'
									+ count + '"></div></td>'
									+ '<td><input id="' + count
									+ '" type="text" class="qty"'
									+ 'disabled="disabled" value="'
									+ items.itemQty + '" /></td>'
									+ '<td><div data-id="' + count
									+ '" class="plus"></div></td>'
									+ '<td><input id="r' + count
									+ '" class="rate" data-id="'
									+ items.itemPrice + '" type="text"'
									+ 'disabled="disabled" value="'
									+ items.itemTotal + '" /></td>' + '</tr>';
							total += parseFloat(items.itemTotal);
							count++;
								itemcount+=parseInt(items.itemQty);
						});
					});
					$("#pay").html(finalstring);
					$("#total").val(total);
					$("#itemcount").val(itemcount);
				}

			});

		});
