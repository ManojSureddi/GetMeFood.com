<script src="js/jquery-2.1.1.min.js"></script>
<div id="cities"></div>
<script>
		var addr="http://ec2-54-186-234-240.us-west-2.compute.amazonaws.com:8080/gmfws/v1/cities";
$.ajax({
	url:addr,
	type:'POST',
	//	data:"url="+encodeURI(addr),
	success: function(data)
	{
var g=data.split("/");
var obj = jQuery.parseJSON(g[1]);
var dat="";
var l=obj.cities;
for(var i=0;i<l.length;i++){
	dat=dat+"<option value='"+l[i]+"'>"+l[i]+"</option>"
}
document.getElementById("cities").innerHTML=dat;

	}
	});


</script>