// JavaScript Document
$(document).ready(function(e) {

	$.getJSON('http://localhost:8080/gmfws1/v1/cities','', function(data){ 
	console.log();


var obj = jQuery.parseJSON(JSON.stringify(data));
var dat="<option value='-1'>Select City</option>";
var l=obj.cities;
for(var i=0;i<l.length;i++){
	dat=dat+"<option value='"+l[i]+"'>"+l[i]+"</option>"
}

document.getElementById("cities").innerHTML=dat;
	});
});

$("#rests li").click(function(e) {
	console.log($(this).attr("value"));
	$("#restname").html($(this).attr("value"));
	$("#rests li.highlight").removeClass("highlight");
	$(this).addClass("highlight");
   $("#panel3").css("display","inline-block");
});

function changed(area) {

	console.log("fuck"+area);
	$.getJSON('http://localhost:8080/gmfws1/v1/areas/'+area,'', function(data){ 
	console.log();


var obj = jQuery.parseJSON(JSON.stringify(data));
var dat="<option value='-1'>Select City</option>";
var l=obj.areasOfCity;
for(var i=0;i<l.length;i++){
	dat=dat+"<option value='"+l[i].areaCode+"'>"+l[i].areaName+"</option>"
}

document.getElementById("areas").innerHTML=dat;
	});
}


    $("#search").click(function(e) {
    $('#panel2').css('display','inline-block');
	 $('#panel1').css('display','none');
		$.getJSON('http://localhost:8080/gmfws1/v1/restaurants/'+$("#areas").val(),'', function(data){ 
	console.log();


var obj = jQuery.parseJSON(JSON.stringify(data));
var dat="";
var l=obj.restaurantsOfArea;
for(var i=0;i<l.length;i++){
	dat=dat+"<li value='"+l[i].Restaurant_Id+"/"+l[i].Name+"' >"+l[i].Name+"</li>";
}

document.getElementById("rests").innerHTML=dat;
	});
	console.log($("#areas").val());
    });
	
	
	
	
		$(document).on('click', '#rests li', function(){ 
		var temp=$(this).attr("value");
		var temp2=temp.split("/");
   		$.getJSON('http://localhost:8080/gmfws1/v1/menutype/'+temp2[0],'', function(data){ 
	
var obj = jQuery.parseJSON(JSON.stringify(data));
var dat="";
var l=obj.ItemTypesRestaurant;
for(var i=0;i<l.length;i++){
	dat=dat+"<li value='"+temp2[0]+"/"+obj.ItemTypesRestaurant[i]+"' >"+obj.ItemTypesRestaurant[i]+"</li>";
}
document.getElementById("menutype").innerHTML=dat;
	});
	$("#restname").html(temp2[1]);
	$("#rests li.highlight").removeClass("highlight");
	$(this).addClass("highlight");
   $("#panel3").css("display","inline-block");
    
 });
 
 	$(document).on('click', '#menutype li', function(){ 
				var temp=$(this).attr("value");
		var temp2=temp.split("/");
  		$.getJSON('http://localhost:8080/gmfws1/v1/menu/'+$(this).attr("value"),'', function(data){ 	
var obj = jQuery.parseJSON(JSON.stringify(data));
var dat="";
var l=obj.menuOfRestaurant;
for(var i=0;i<l.length;i++){
	dat=dat+"<li value='"+temp2[0]+"/"+l[i].Item_Code+"' >"+l[i].Name+"</li>";
}
document.getElementById("menuitem").innerHTML=dat;
	});


			$("#menuname").html(temp2[1]);
	$("#menutype li.highlight").removeClass("highlight");
	$(this).addClass("highlight");
        $("#panel4").css("display","inline-block");
		  $("#panel5").css("display","inline-block");
	});
	var total=0;
  	$(document).on('click', '#menuitem li', function(){
					var temp=$(this).attr("value");
		var temp2=temp.split("/"); 
  		$.getJSON('http://localhost:8080/gmfws1/v1/cart/add/'+temp+'/1','', function(data){ 	
var obj = jQuery.parseJSON(JSON.stringify(data));
var dat="";
var l=obj.cartItems;
var sum=0;

for(var i=0;i<l.length;i++){
	sum=parseInt(l[i].itemPrice)*parseInt(l[i].itemQty);
	total=total+sum;
	dat=dat+
	"<tr><td>"+l[i].itemName+"</td><td><div class='minus' data-id='"+i+"'></div></td>"+
"<td ><input id='"+i+"' type='text' class='qty' disabled='disabled' value='"+l[i].itemQty+"'/></td>"+
"<td><div data-id='"+i+"' class='plus'></div></td>"+
"<td><input id='r"+i+"' class='rate' data-id='"+l[i].itemPrice+"' type='text' disabled='disabled' value='260.00'/></td></tr>";
}
document.getElementById("pay").innerHTML=dat;
	});


			$("#total").val(total);

	});
