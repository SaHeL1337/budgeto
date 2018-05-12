<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
<style>
.password-verdict{
color:#000;
}
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script th:src="@{/resources/pwstrength.js}"></script>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<title th:text="">form</title>
</head>
<body>
    <div class="container">
        <div >
            <h1>form</h1>
            <br/>
            <form action="/" method="POST" enctype="utf8" >
                <div class="form-group row" >
                 	<label class="col-sm-3">Username</label>
                    <span class="col-sm-5"><input class="form-control" name="name" value="Herbert" required="required"/></span>
                    <span id="nameError" class="alert alert-danger col-sm-4" style="display:none"></span>
                    
                </div>
                <div class="form-group row">
                 	<label class="col-sm-3">Email</label>
                    <span class="col-sm-5"><input type="email" class="form-control" name="email" value="test@test.me" required="required"/></span>                    
                    <span id="emailError" class="alert alert-danger col-sm-4" style="display:none"></span>
                    
                </div>
                <div class="form-group row">
                 	<label class="col-sm-3">Password</label>
                    <span class="col-sm-5"><input id="password" class="form-control" name="password" value="admin" type="password" required="required"/></span>
                    <span id="passwordError" class="alert alert-danger col-sm-4" style="display:none"></span>
                </div>
                <div class="form-group row">
                 	<label class="col-sm-3">Password repeat</label>
                    <span class="col-sm-5"><input id="matchPassword" class="form-control" name="matchingPassword" value="admin" type="password" required="required"/></span>
                    <span id="globalError" class="alert alert-danger col-sm-4" style="display:none"></span>
                </div>                
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <br/>
                <button type="submit" class="btn btn-primary" th:text="">sign up</button>
            </form>
        </div>
    </div>

<script th:inline="javascript">
/*<![CDATA[*/
var serverContext = "http://localhost:8080/Budgeto/";
$(document).ready(function () {
	$('form').submit(function(event) {
		register(event);
	});
	
	$(":password").keyup(function(){
		if($("#password").val() != $("#matchPassword").val()){
	        $("#globalError").show().html("");
	    }else{
	    	$("#globalError").html("").hide();
	    }
	});
	
	options = {
		    common: {minChar:8},
		    ui: {
		    	showVerdictsInsideProgressBar:true,
		    	showErrors:true,
		    	errorMessages:{
		    		  wordLength: "",
		    		  wordNotEmail: "",
		    		  wordSequences: "",
		    		  wordLowercase: "",
		    		  wordUppercase: "",
		    	          wordOneNumber: "",
		    		  wordOneSpecialChar: ""
		    		}
		    	}
		};
	 $('#password').pwstrength(options);
});
function register(event){
	event.preventDefault();
    $(".alert").html("").hide();
    $(".error-list").html("");
    if($("#password").val() != $("#matchPassword").val()){
    	$("#globalError").show().html("");
    	return;
    }
    var formData= $('form').serialize();
    $.post(serverContext + "user/registration",formData ,function(data){
        if(data.message == "success"){
            window.location.href = serverContext + "successRegister.html";
        }
        
    })
    .fail(function(data) {
        if(data.responseJSON.error.indexOf("MailError") > -1)
        {
            window.location.href = serverContext + "emailError.html";
        }
        else if(data.responseJSON.error == "UserAlreadyExist"){
            $("#emailError").show().html(data.responseJSON.message);
        }
        else if(data.responseJSON.error.indexOf("InternalError") > -1){
            window.location.href = serverContext + "login?message=" + data.responseJSON.message;
        }
        else{
        	var errors = $.parseJSON(data.responseJSON.message);
            $.each( errors, function( index,item ){
            	if (item.field){
            		$("#"+item.field+"Error").show().append(item.defaultMessage+"<br/>");
            	}
            	else {
            		$("#globalError").show().append(item.defaultMessage+"<br/>");
            	}
               
            });
        }
    });
}
/*]]>*/ 
</script>
</body>

</html>