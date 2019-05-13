var urlString = window.location.href;
var url = new URL(urlString);
var code = url.searchParams.get("code");
if (!code) {
	window.location = "http://localhost:8080/oauth/authorize?client_id=clientapp&response_type=code&scope=read_profile_info";
} else {
	  $.ajax({
            url: "oauth/token?grant_type=authorization_code&code=" + code,
            type: 'POST',
            headers: {
                'Authorization': 'Basic Y2xpZW50YXBwOjEyMzQ1Ng=='
            },
            success: function (result) {
            	var token = result.access_token;
	            $.ajax({
	                  url: "localhost:8080/api/v1/users/friends",
	                  type: 'GET',
	                  headers: {
	                      'Authorization': 'Bearer ' + token
	                  },
	                  success: function (result) {
	                	  var text = "Your friends are: ";
	                	  
	                	  for (var i = 0; i < result.length; i++) {
	                		  text+= result[i];
	                		  text+="\n";
	                	  }
	                	  $("#text" ).text(text);
	                	  
	                  },
	                  error: function (error) {
	                      console.log(error);
	                  }
	              });
            },
            error: function (error) {
                console.log(error);
            }
        });
}