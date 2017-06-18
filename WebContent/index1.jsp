<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GetMeFood</title>
<link rel="shortcut icon" href="../favicon.ico">
   <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="css/component.css" />
	<link rel="stylesheet" type="text/css" href="css/content.css" />
	<link rel="stylesheet" href="css/style2.css">
		<link rel="stylesheet" type="text/css" href="css/reset.css">
			<link rel="stylesheet" type="text/css" href="css/typography.css">
				<link rel="stylesheet" type="text/css" href="css/stylez.css">
					<link rel="stylesheet" href="css/jquery.mCustomScrollbar.css">
						<link rel="stylesheet" type="text/css" href="css/main.css" />
						<link rel="stylesheet" href="css/animations.css"/>
						<script src="js/modernizr.custom.js"></script>
</head>
<body>
	<div id=header>
		<div class="container" id="head">

			<jsp:include page="header.jsp"></jsp:include>
		</div>
	</div>
	<section id="one">

	<div class="container">

		<div id="panel1" class="panel">
			<i
				style="background: url(img/locationicon.png) no-repeat; float: left; margin-top: 6px; margin-right: 10px; width: 15px; height: 30px;"></i>
			<h3 style="float: left;">Select the Location</h3>
			<br /> <br /> <span>Select The City</span><br /> <br /> <select
				name="city" id="cities" class="balck"
				onchange="changed(this.value);">

			</select> <span>Select The Place</span><br /> <br /> <select id="areas"
				class="balck">

			</select> <input id="search" type="button" name="man"
				value="Search Restaurant" class="button submitbutton" />
		</div>

		<div id="panel2" class="panel" style="">

			<h3>
				<i></i>Select Your Resturant
			</h3>
			<ul>
				<div class="listview">
					<div class="contentz mCustomScrollbar">
						<div id="rests"></div>
					</div>
				</div>
			</ul>
		</div>

		<div id="panel3" class="panel" style="">
			<h3 id="restname">Panel 3</h3>
			<ul>
				<div class="listview">
					<div class="contentz mCustomScrollbar">
						<div id="menutype"></div>
					</div>
				</div>
			</ul>
		</div>

		<div id="panel4" class="panel" style="">
			<h3 id="menuname">Panel 4</h3>
			<input type="hidden" id="itemcount">
			<ul>
				<div class="listview">
					<div class="contentz mCustomScrollbar">
						<div id="menuitem"></div>
					</div>
				</div>
			</ul>
		</div>
		<div id="panel5" class="panel1" style="">
			<h3>Payment Cart</h3>
			<div class="listviewz">
				<div class="contentz" style="height: 300px">

					<table id="pay" cellpadding="" cellspacing="" class="">

					</table>

				</div>
				<div>
					<hr />
					<table id="paytotal" cellpadding="" cellspacing="">
						<tr>
							<td align="right">TOTAL :</td>
							<td><input id="total" class="rate" data-id="" type="text"
								disabled="disabled" value="0.00" /></td>
							<td><input type="button" class="butt" id="chkoutbut"
								value="Check Out"></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</section>
	<section id="footer"> <span
		style="color: #FFF; float: left; margin-top: 20px; margin-left: 10px;">&copy;Copyrights
		belong to Getmefood.in</span>
	<div style="margin-bottom: 30px;">
		<div class="face"></div>
		<div class="twit"></div>
	</div>
	</section>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/main.js"></script>
	<script src="js/classie.js"></script>
	<script src="js/uiMorphingButton_fixed.js"></script>
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
	</script>
	<script type="text/javascript" src="js/waypoints.min.js"></script>
	<script type="text/javascript" src="js/waypoints-sticky.min.js"></script>
	<script type="text/javascript" src="js/jquery.tabslet.min.js"></script>

	<script src="js/initializers.js"></script>
	<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>

	<script>
		(function($) {
			$(window).load(function() {

				$("#content-1").mCustomScrollbar({
					theme : "minimal"
				});

			});
		})(jQuery);
	</script>
	<script src="js/jquery.cookie.js" type="application/javascript"></script>
	<script src="js/request.js" type="application/javascript"></script>
	<script>
		$(".plus").click(function(e) {
			var g = $(this).attr("data-id");
			var rate = parsefloat($("#r" + g).attr("data-id"));
			var v = parseInt($("#" + g).val());
			$("#" + g).val(v + 1);
			$("#r" + g).val(rate * (v + 1));

		});
		$(".minus").click(function(e) {
			var g = $(this).attr("data-id");
			var v = parseInt($("#" + g).val());
			var rate = parseInt($("#r" + g).attr("data-id"));
			if ((v - 1) >= 0) {
				$("#" + g).val(v - 1);
				$("#r" + g).val(rate * (v - 1));
			}

		});
	</script>
	<script src="js/User.js"></script>
</body>
</html>
