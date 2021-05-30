/**
 * 
 */

function checkBillingAddress() {
	if($("#theSameAsShippingAddress").is(":checked")) {
		$(".billingAddress").prop("disabled", true);
	} else {
		$(".billingAddress").prop("disabled", false);
	}
}

function checkPasswordMatch() {
	var password = $("#txtNewPassword").val();
	var confirmPassword = $("#txtConfirmPassword").val();
	
	if(password == "" && confirmPassword =="") {
		$("#checkPasswordMatch").html("");
		$("#updateUserInfoButton").prop('disabled', false);
	} else {
		if(password != confirmPassword) {
			$("#checkPasswordMatch").html("Passwords do not match!");
			$("#updateUserInfoButton").prop('disabled', true);
		} else {
			$("#checkPasswordMatch").html("Passwords match");
			$("#updateUserInfoButton").prop('disabled', false);
		}
	}
}

$(document).ready(function(){
	$(".cartItemQty").on('change', function(){
		var id=this.id;
		
		$('#update-item-'+id).css('display', 'inline-block');
	});
	$("#theSameAsShippingAddress").on('click', checkBillingAddress);
	$("#txtConfirmPassword").keyup(checkPasswordMatch);
	$("#txtNewPassword").keyup(checkPasswordMatch);
});

$('span:contains("Neutral")').html("&#128528;").addClass("text-center");
$('span:contains("Positive")').html("&#x1F603;").addClass("text-center");
$('span:contains("Negative")').html("&#128542;").addClass("text-center");
$('span:contains("Very positive")').html("&#128513;").addClass("text-center");
$('span:contains("Very negative")').html("&#128544;").addClass("text-center");

function redirectpage() {
    var radios = document.getElementsByName('rating');
    var length = radios.length;
    var selected_value = false;
    for (var i = 0; i < length; ++i) {
        if (radios[i].checked) {
            selected_value = radios[i].value;
            break;
        }
    }

switch(selected_value) {
    case "1":
        window.location = "page1.html";
        break;
    case "2":
        window.location = "page2.html";
        break;
    case "3":
        window.location = "page3.html";
        break;
    case "4":
        window.location = "page4.html";
        break; 
    case "5":
        window.location = "page5.html";
        break;       
    default:
        document.getElementById('notice-message').innerHTML = "Please select one";
        break;
}

}
