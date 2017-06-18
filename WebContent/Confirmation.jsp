<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import=" com.google.gson.Gson"
	import=" java.util.*" import="com.gmf.ws.dao.AddressManagementDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GetMeFood</title>
<script src="js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/component.css" />
<link rel="stylesheet" type="text/css" href="css/content.css" />
<link rel="stylesheet" href="css/style2.css">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/typography.css">
<link rel="stylesheet" type="text/css" href="css/stylez.css">

<link rel="stylesheet" href="css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" type="text/css" href="css/checkout.css" />
<script src="js/modernizr.custom.js"></script>
<script type="text/javascript"
	src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places"></script>
	<style>
	.centered {
  margin:0 auto;
 
}
#confbox{
	background: rgba(0, 0, 0, 0.8);
	   width: 600px;
    height: 300px;
   padding:20px;
   color:#fff;

    position: absolute;
    top:0;
    bottom: 0;
    left: 0;
    right: 0;

    margin: auto;

	min-height: 300px;
}
#confbox h1,#confbox h2{
   color:#fff;
}
	</style>
</head>
<body>
	<div id=header>
		<div class="container">

			<jsp:include page="header.jsp"></jsp:include>
		</div>
	</div>
	<section id="one"> <%
 	Object sess=session.getAttribute("cart");
    	Object usersess=session.getAttribute("userid");
    	Object orderconf=session.getAttribute("Ordercon");
   // 	if(usersess==null){

 	//	response.sendRedirect("index.php");
    //	}
      // 	if(usersess==null){

 	//	response.sendRedirect("index.php");
    //	}
 %>
 <dvi id="confbox" class="centered">
 <h1 align="center">Confirmation</h1>
 <p align="center">Your order is successfully being processed.Our customer care executive would contact you soon.</p>
 <p align="center">Your Order ID is:</p>
 <h2 align="center"><%=orderconf.toString() %></h2>
 <p align="center">Thank you for purchasing at <b>Getmefood.in</b></p>
 </dvi> 

	</section>

	<script src="js/main.js"></script>
	<script src="js/classie.js"></script>
	<script src="js/uiMorphingButton_fixed.js"></script>

</body>
</html>