<%-- 
    Document   : travel
    Created on : Sep 27, 2017, 4:11:20 PM
    Author     : capphil1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/index.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/viewBudget.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/viewIncome.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/totalBudget.css"/>
        <title>tetraPRO( )</title>
    </head>
    <body><!---->
        <jsp:include page="header.jsp"/>
    <center>
        <h1>Travel Goals!</h1>
        <iframe width="600" height="450" frameborder="0" style="border:0" 
               src="https://www.google.com/maps/embed/v1/directions?origin=place_id:ChIJK-0sC0Fl1oYRFccWTTgtw3M&destination=place_id:ChIJg_YbhDVI0IkRq0S8ZOWFVnk&key=AIzaSyDEC2zzqK-o6kdW-8Me5KVWoZdVw2ewSQY" allowfullscreen>
        </iframe>
    </center>
         <script type="text/javascript" src="JS/viewBudget.js"></script>
        <script type="text/javascript" src="JS/viewIncome.js"></script>
        <script type="text/javascript" src="JS/totalBudget.js"></script>
    </body>
</html>
