// The following code show execute only after the page is fully loaded
var placeSearch, autocomplete;
var placeSearch, autocomplete;
var componentForm = {
	street_number : 'short_name',
	route : 'long_name',
	locality : 'long_name',
	administrative_area_level_1 : 'short_name',
	country : 'long_name',
	postal_code : 'short_name'
};

function geolocate() {

	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var geolocation = new google.maps.LatLng(position.coords.latitude,
					position.coords.longitude);
			autocomplete.setBounds(new google.maps.LatLngBounds(geolocation,
					geolocation));
		});
	}
}

$(document)
		.ready(
				function() {

					// Create the autocomplete object, restricting the search
					// to geographical location types.
					autocomplete = new google.maps.places.Autocomplete(
					/** @type {HTMLInputElement} */
					(document.getElementById('FullAddress')), {
						types : [ 'geocode' ]
					});

					// When the user selects an address from the dropdown,
					// populate the address fields in the form.
					google.maps.event
							.addListener(
									autocomplete,
									'place_changed',
									function() {
										var place = autocomplete.getPlace();
										// Get each component of the address from the place details
										// and fill the corresponding field on the form.
										var source = localStorage.getItem('area');
										  for (var i = 0; i < place.address_components.length; i++) {
											  var addressType = place.address_components[i].types[0];
											  if(addressType=="locality"){
												  if (place.address_components[i][componentForm[addressType]] != source) {
											alert("Select Places in "+source+" Only");
											document.getElementById("FullAddress").value="";
										}
											  }
										  }
									});
					autocomplete2 = new google.maps.places.Autocomplete(
					/** @type {HTMLInputElement} */
					(document.getElementById('FulldestAddress')), {
						types : [ 'geocode' ]
					});
					
					google.maps.event
							.addListener(
									autocomplete2,
									'place_changed',
									function() {
										var place = autocomplete2.getPlace();

										// Get each component of the address from the place details
										// and fill the corresponding field on the form.
										var destination = document
										.getElementById("destinationcity").value;
										  for (var i = 0; i < place.address_components.length; i++) {
											  var addressType = place.address_components[i].types[0];
											  if(addressType=="locality"){
												  if (place.address_components[i][componentForm[addressType]] != destination) {
											alert("Select Places in "+destination+" Only");
											document.getElementById("FulldestAddress").value="";
										}
											  }
										  }
									});
					
					autocomplete3 = new google.maps.places.Autocomplete(
							/** @type {HTMLInputElement} */
							(document.getElementById('baddress')), {
								types : [ 'geocode' ]
							});
					google.maps.event
					.addListener(
							autocomplete2,
							'place_changed',
							function() {
							
								  
							});
			
					if ($('#MyForm').exists()) {

						// Enable jQuery Validation for the form
						$("#MyForm").validate({
							onkeyup : false
						});

						// Add validation rules to the FullAddress fieldh
						$("#FullAddress").rules("add", {
							fulladdress : true,
							required : true,
							messages : {
								fulladdress : "Please enter a valid address."
							}
						});

						// This function will be executed when the form is submitted
						function FormSubmit() {
							$.submitForm = true;
							if (!$('#MyForm').valid()) {
								return false;
							} else {
								if ($("#FullAddress").data("IsChecking") == true) {
									$("#FullAddress").data("SubmitForm", true);
									return false;
								}
								//initialize();
								//    codeAddress($("#FullAddress").val());
								return true; // Uncomment to submit the form.
								//return false;     // Supress the form submission for test purpose.

							}
						}

						// Attach the FormSubmit function to the Submit button
						if ($('#Submit').exists()) {
							$("#Submit").click(FormSubmit);
						}

						// Execute the ForumSubmit function when the form is submitted
						$('#MyForm').submit(FormSubmit);
					}
				});

// Create a jQuery exists method
jQuery.fn.exists = function() {
	return jQuery(this).length > 0;
}

// Position the Google Map
function Map(elementId, geolocation) {
	geocoder = new google.maps.Geocoder();
	var latlng = new google.maps.LatLng(-34.397, 150.644);
	var myOptions = {
		zoom : 8,
		center : latlng
	}
	var map = new google.maps.Map(document.getElementById(elementId), myOptions);
	map.setCenter(geolocation);
	$("#map_canvas").css("border", "2px solid #09f");
}

// FullAddress jQuery Validator
function FullAddressValidator(value, element, paras) {

	// Convert the value variable into something a bit more descriptive
	var CurrentAddress = value;

	// If the address is blank, then this is for the required validator to deal with.
	if (value.length == 0) {
		return true;
	}

	// If we've already validated this address, then just return the previous result
	if ($(element).data("LastAddressValidated") == CurrentAddress) {
		return $(element).data("IsValid");
	}

	// We have a new address to validate, set the IsChecking flag to true and set the LastAddressValidated to the CurrentAddress
	$(element).data("IsChecking", true);
	$(element).data("LastAddressValidated", CurrentAddress);

	// Google Maps doesn't like line-breaks, remove them
	CurrentAddress = CurrentAddress.replace(/\n/g, "");

	// Create a new Google geocoder
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({
		'address' : CurrentAddress
	}, function(results, status) {

		// The code below only gets run after a successful Google service call has completed.
		// Because this is an asynchronous call, the validator has already returned a 'true' result
		// to supress an error message and then cancelled the form submission.  The code below
		// needs to fetch the true validation from the Google service and then re-execute the
		// jQuery form validator to display the error message.  Futhermore, if the form was
		// being submitted, the code below needs to resume that submit.

		// Google reported a valid geocoded address
		if (status == google.maps.GeocoderStatus.OK) {

			// Get the formatted Google result
			var address = results[0].formatted_address;

			// Count the commas in the fomatted address.
			// This doesn't look great, but it helps us understand how specific the geocoded address
			// is.  For example, "CA" will geocde to "California, USA".
			numCommas = address.match(/,/g).length;

			// A full street address will have at least 3 commas.  Alternate techniques involve
			// fetching the address_components returned by Google Maps.  That code looks even more ugly.
			if (numCommas >= 3) {

				// Replace the first comma found with a line-break

				// Remove USA from the address (remove this, if this is important to you)
				address = address.replace(/, USA$/, "");

				// Check for the map_canvas, if it exists then position the Google Map
				if ($("#map_canvas").exists()) {
					$("#map_canvas").show();
					Map("map_canvas", results[0].geometry.location);
				}

				// Set the textarea value to the geocoded address
				$(element).val(address);

				// Cache this latest result
				$(element).data("LastAddressValidated", address);

				// We have a valid geocoded address
				$(element).data("IsValid", true);
			} else {
				// Google Maps was able to geocode the address, but it wasn't specific
				// enough (not enough commas) to be a valid street address.
				$(element).data("IsValid", false);
			}

			// Otherwise the address is invalid
		} else {
			$(element).data("IsValid", false);
		}

		// We're no longer in the midst of validating
		$(element).data("IsChecking", false);

		// Get the parent form element for this address field
		var form = $(element).parents('form:first');

		// This code is being run after the validation for this field,
		// if the form was being submitted before this validtor was
		// called then we need to re-submit the form.
		if ($(element).data("SubmitForm") == true) {
			form.submit();
		} else {
			// Re-validate this property so we can return the result.
			form.validate().element(element);
		}
	});

	// The FullAddress validator always returns 'true' when initially called.
	// The true result will be return later by the geocode function (above)
	return true;
}

// Define a new jQuery Validator method
$.validator.addMethod("fulladdress", FullAddressValidator);
