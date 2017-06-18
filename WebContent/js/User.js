/**
 * 
 */
$(document)
		.ready(
				function() {
					$("#signup")
							.click(
									function() {
										var name = $("#name").val();
										var email = $("#email").val();
										var phone = $("#mobile").val();
										var pass = $("#pass").val();
										var conpass = $("#conpass").val();
										if (name == "") {
											$("#msg").html("Please enter name");
											return false;
										}

										if (email == ""
												|| !email
														.match(/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/)) {
											$("#msg").html(
													"Please enter valid Email");
											return false;
										}
										if (phone == "" || isNaN(phone)
												|| phone.length != 10) {
											$("#msg")
													.html(
															"Please enter mobile number");
											return false;
										}
										if (mobile == "") {

											return false;
										}
										if (pass == "") {

											$("#msg").html(
													"Please enter password");
											return false;
										}

										if (conpass != pass) {
											$("#msg").html(
													"Password didnot match");
											return false;
										} else {
											$("#msg").html("");

											$
													.ajax({
														url : "/gmfws1/v1/sign-up",
														type : "POST",
														async : true,
														data : "name="
																+ name
																+ "&password="
																+ pass
																+ "&emailId="
																+ email
																+ "&mobileNumber="
																+ phone,
														dataType : 'json',
														success : function(data) {
															//alert("aaa" + data);

															if (data == "success") {
																$("#msg")
																		.html(
																				"Registration successfull");
															} else {
																$("#msg").html(
																		data);
															}

														}

													});

											return false;
										}
									});
				});

$("#loginbut")
.click(
		function() {
			//alert();
			var phone = $("#umobile").val();
			var pass = $("#password").val();
			if (phone == "" || isNaN(phone)
					|| phone.length != 10) {
				$("#msg1")
						.html(
								"Please enter valid mobile number");
				return false;
			}
			if (pass == "") {
				$("#msg1").html(
						"Please enter password");
				return false;
			} else {
				$
				.ajax({
					url : "/gmfws1/v1/authenticate/login",
					type : "POST",
					async : true,
					data : "username="
							+ phone
							+ "&password="
							+ pass,
					dataType : 'json',
					success : function(data) {
						//alert("aaa" + data);

						if (data == "Success") {
							$("#msg1")
									.html(
											"Loging in");
							$("#head").load("loadheader.jsp" +
									"" +
									"?e=65656");
						} else {
							$("#msg1").html(
									data);
						}

					}

				});

		return false;
			}
		});





$("#chkoutbut").click(function(){
	var itemcnt=parseInt($("#itemcount").val());
	if(itemcnt>0){

		$
		.ajax({
			url : "/gmfws1/v1/authenticate/check",
			type : "POST",
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data == "yes") {
					window.location="checkout.jsp";
				} else {
				       alert("Please login to continue");
				       $("#logme").click();
				}

			}

		});
	}else{
		alert("Your cart is empty");
	}
	
});

