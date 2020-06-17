<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Weather Info</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="index.jsp" />


 <br/>
<br/>
<br/> 

 
<div class="container">
 <div class="panel panel-info">
      <div class="panel-heading">The Weather Details are :</div>
      <div class="panel-body">
      		  <table class="table">
    <thead>
      <tr>
        <th>Lists</th>
        <th>Values in metric units</th>
      </tr>
    </thead>
    <tbody>
      <tr class="success">
        <td>Avg Temperature</td>
        <td>${temp}</td>

      </tr>      
      <tr class="success">
        <td>Feels Like</td>
        <td>${feels_like}</td>

      </tr>      


      <tr class="info">
        <td>Min Temperature</td>
        <td>${temp_min}</td>
       
      </tr>
      <tr class="info">
        <td>Max Temperature</td>
        <td>${temp_max}</td>
       
      </tr>
      <tr class="warning">
        <td>Pressure</td>
        <td>${pressure}</td>
        
      </tr>
      <tr class="warning">
        <td>Humidity</td>
        <td>${humidity}</td>
        
      </tr>
      <tr class="active">
        <td>Longitude</td>
        <td>${longitude}</td>
        
      </tr>
      <tr class="active">
        <td>Latitude</td>
        <td>${latitude}</td>
        
      </tr>
    </tbody>
  </table>
      
      </div>
  </div>

<a href="GeoLocation.jsp" class="btn btn-primary" role="button">Search City</a>

</div>
<footer class="container-fluid text-center">
  <a href="#myPage" title="To Top">
    <span class="glyphicon glyphicon-chevron-up"></span>
  </a>
 <p>Website developed by Aditya Mehakare</p>
 </footer>

<script>
$(document).ready(function(){
  // Add smooth scrolling to all links in navbar + footer link
  $(".navbar a, footer a[href='#myPage']").on('click', function(event) {
    // Make sure this.hash has a value before overriding default behavior
    if (this.hash !== "") {
      // Prevent default anchor click behavior
      event.preventDefault();

      // Store hash
      var hash = this.hash;

      // Using jQuery's animate() method to add smooth page scroll
      // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
      $('html, body').animate({
        scrollTop: $(hash).offset().top
      }, 900, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
    } // End if
  });
  
  $(window).scroll(function() {
    $(".slideanim").each(function(){
      var pos = $(this).offset().top;

      var winTop = $(window).scrollTop();
        if (pos < winTop + 600) {
          $(this).addClass("slide");
        }
    });
  });
})
</script>

</body>
</html>