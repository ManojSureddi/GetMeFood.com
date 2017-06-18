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
   // 	if(usersess==null){
 %> <%
 	//	response.sendRedirect("index.php");
    //	}
 %>
	<div class="checkoutleft">
		<%
			if(sess!=null){ 
				Gson g = new Gson();
				HashMap<String, HashMap<String, String>>cartitems=(HashMap<String, HashMap<String, String>>)sess;
		%>
		<h2>Your Order:</h2>
		<div class="contentz mCustomScrollbar">
			<table id="paycarttable">
				<tr>
					<th>S.No</th>
					<th>Item Name</th>
					<th>Quantity</th>
					<th>Price</th>
				</tr>

				<%
					int count=1;
																	double total=0;
																		for(String keys:cartitems.keySet()){
																			HashMap<String, String> itemname=cartitems.get(keys);
				%>
				<tr>
					<td><%=count%></td>
					<td><%=itemname.get("itemName")%></td>
					<td><%=itemname.get("itemQty")%></td>
					<td><%=itemname.get("itemTotal")%></td>
				</tr>





				<%
					total+=Double.parseDouble(itemname.get("itemTotal"));
																		}
				%>
			</table>
		</div>
		<hr />
		<table style="float: right; width: 40%">
			<tr>
				<td>Subtotal:</td>
				<td><%=total%></td>
			</tr>
			<tr>
				<td>Tax:</td>
				<td>0</td>
			</tr>
			<tr>
				<td>VAT:</td>
				<td>0</td>
			</tr>
			<tr>
				<td>Service Charge:</td>
				<td>0</td>
			</tr>
			<tr>
				<td>Discount:</td>
				<td>0</td>
			</tr>
			<tr
				style="font-size: 15px; border-top: 2px solid #ccc; padding: 15px;">
				<td><b>Total:</b></td>
				<td><b><%=total%></b></td>
			</tr>
		</table>
		<%
			}
		%>
	</div>
	<div class="checkoutright">
		<h2>Your Delivery Address:</h2>
		<div style="height: 300px;">
			<select id="address">
				<%
					
				%>

			</select> <input type="button" id="toggleview" value="Add new"
				style="border: 0; background: rgb(17, 118, 130); color: #fff; border: 1px solid #fff; padding: 5px; margin: 10px;" />

			<div id="newaddress" style="display: none">
				<input type="hidden" name="sourcecity" id="sourcecity"
					readonly="readonly">
				<textarea id="FullAddress" name="FullAddress"
					class="fulladdressvalidator" onFocus="geolocate()"
					placeholder="Enter Address" rows="2" cols="10"></textarea>
				<input type="button" value="Save Address"
					class="submitbutton button" id="saveaddr"
					style="width: 150px; margin-top: 10px; margin-left: 0;">
			</div>
		</div>
		<input type="button" value="Confirm Order" id="confirm"
			class="submitbutton button" style="width: 300px; margin: auto;">
	</div>
	</section>

	<script src="js/main.js"></script>
	<script src="js/classie.js"></script>
	<script src="js/uiMorphingButton_fixed.js"></script>
	<script src="js/User.js"></script>
	<script>
		(function() {
			var docElem = window.document.documentElement, didScroll, scrollPosition;

			// trick to prevent scrolling when opening/closing button
			function noScrollFn() {
				window.scrollTo(scrollPosition ? scrollPosition.x : 0,
						scrollPosition ? scrollPosition.y : 0);
			}

			function noScroll() {
				window.removeEventListener('scroll', scrollHandler);
				window.addEventListener('scroll', noScrollFn);
			}

			function scrollFn() {
				window.addEventListener('scroll', scrollHandler);
			}

			function canScroll() {
				window.removeEventListener('scroll', noScrollFn);
				scrollFn();
			}

			function scrollHandler() {
				if (!didScroll) {
					didScroll = true;
					setTimeout(function() {
						scrollPage();
					}, 60);
				}
			}
			;

			function scrollPage() {
				scrollPosition = {
					x : window.pageXOffset || docElem.scrollLeft,
					y : window.pageYOffset || docElem.scrollTop
				};
				didScroll = false;
			}
			;

			scrollFn();

			[].slice.call(document.querySelectorAll('.morph-button')).forEach(
					function(bttn) {
						new UIMorphingButton(bttn, {
							closeEl : '.icon-close',
							onBeforeOpen : function() {
								// don't allow to scroll
								noScroll();
							},
							onAfterOpen : function() {
								// can scroll again
								canScroll();
							},
							onBeforeClose : function() {
								// don't allow to scroll
								noScroll();
							},
							onAfterClose : function() {
								// can scroll again
								canScroll();
							}
						});
					});

			// for demo purposes only
			[].slice.call(document.querySelectorAll('form button')).forEach(
					function(bttn) {
						bttn.addEventListener('click', function(ev) {
							ev.preventDefault();
						});
					});
		})();

		$.ajax({
			url : "/gmfws1/v1/address/retrieveAddr",
			type : "POST",
			async : true,
			dataType : 'json',
			success : function(data) {
				var datastring1 = "<option value='-1'>Select Address</option>";
				var datastring = "";
				for (var i = 0; i < data.length; i++) {
					datastring = "<option value='"+data[i].id+"'>"
							+ data[i].name + "</option>" + datastring;

				}
				$("#address").html(datastring1 + datastring);
			}

		});
		var togg = 0;
		$("#toggleview").click(function() {

			if (togg % 2 == 0) {
				$("#newaddress").css("display", "block");
				$("#address").css("display", "none");
				$("#toggleview").val("Select default Address");
			} else {
				$("#newaddress").css("display", "none");
				$("#address").css("display", "block");
				$("#toggleview").val("Add new address");
			}
			togg++;
		});

		$("#saveaddr").click(function() {
			$.ajax({
				url : "/gmfws1/v1/address/storeAddr",
				type : "POST",
				async : true,
				data : "address=" + $("#FullAddress").val(),
				dataType : 'json',
				success : function(data) {
					if (data == "Success") {
						
						$("#saveaddr").css("display","none");
					} else {
						alert("unsaved");
					}

				}

			});
		});
		$("#confirm").click(function() {
			if (togg % 2 == 0) {
				$.ajax({
					url : "/gmfws1/v1/order/place",
					type : "POST",
					async : true,
					data : "address=" + $("#address").val()+"&des=1",
					dataType : 'json',
					success : function(data) {
						if (data == "Success") {
							alert("saved");
						} else {
							alert("unsaved");
						}

					}

				});
			} else {
				$.ajax({
					url : "/gmfws1/v1/order/place",
					type : "POST",
					async : true,
					data : "address=asd&des=0",
					dataType : 'json',
					success : function(data) {
						if (data == "Success") {
							alert("saved");
						} else {
							alert("unsaved");
						}

					}

				});
			}
		});
	</script>
	<script src="js/maps.js"></script>
</body>
</html>