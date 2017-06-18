


<div id="logo">
	<a href="index.jsp" style="background: :none;"><img src="images/logo.png"></a>
</div>
<%
	if (session.getAttribute("userid") != null) {
%>
<p style="float: right" id="userhello">Hi! <%=session.getAttribute("username").toString() %></p>


<%
	} else {
%>
<div
	class="morph-button morph-button-modal morph-button-modal-2 morph-button-fixed">
	<button id="logme" type="button" style="float: right;">Login</button>
	<div class="morph-content">
		<div>
			<div class="content-style-form content-style-form-1">
				<span class="icon icon-close">Close the dialog</span>
				<p id="msg1"></p>
				<h2 style="color: #FFF">Login</h2>
				<form>
					<p>
						<label>Mobile Number</label><input type="text" name="mobile"
							id="umobile" />
					</p>
					<p>
						<label>Password</label><input type="password" name="password"
							id="password" />
					</p>
					<p>
						<input type="button" value="Login" id="loginbut">
					</p>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- morph-button -->

<div
	class="morph-button morph-button-modal morph-button-modal-3 morph-button-fixed">
	<button id="sign" type="button" style="float: right;">Signup</button>
	<div class="morph-content">
		<div>
			<div class="content-style-form content-style-form-2">
				<span class="icon icon-close">Close the dialog</span>
				<p id="msg"></p>
				<h2 style="color: #FFF">Sign Up</h2>
				<form>
					<p>
						<input type="text" name="name" id="name" placeholder="Name" />
					</p>
					<p>
						<input type="text" name="email" id="email" placeholder="Email" />
					</p>
					<p>
						<input type="text" name="mobilenumber" id="mobile"
							placeholder="Mobile Number" />
					</p>
					<p>
						<input type="password" name="password" id="pass"
							placeholder="Password" />
					</p>
					<p>
						<input type="password" name="confirmpassword" id="conpass"
							placeholder="Confirm Password" />
					</p>
					<p>
						<input type="button" value="Sign Up" id="signup"></input>
					</p>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- morph-button -->
<%
	}
%>