<%-- 
    Document   : login
    Created on : Jul 22, 2017, 7:02:53 AM
    Author     : tetraPRO( )
                     : Software Wizard
                    : Grand-Master Philip Caputo
                  : **Fullstack Engineer**
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            body{
                background-color: #999a9b;  
            }
            #contrastContainer{
                border: solid black 2px;
                border-radius: 35px;
                background-color: #333;
                color: #cccccc;
                margin: auto;
                padding: 55px 25px;
                width: 33%;
            }
        </style>
         <link rel="stylesheet" type="text/css" href="CSS/register.css"/>
        <title>tetraPRO( )</title>
    </head>
    <body>
        <button id="register_open" style="float: right; margin: 10px">Register</button>
        
        
        <br><br><br>
    <center><h1>tetraPRO( ) Financials</h1></center>
    <div id="contrastContainer">
        <form name="userverify" action="Auth_Servlet" method="POST" >
            <center>
                <table border="0" align="center">
                    <tbody>
                        <tr>
                            <td><label>Email: </label></td>
                            <td><input type="text" name="email"/></td>
                        </tr>
                        <tr>
                            <td><label>Password: </label></td>
                            <td><input type="password" name="password"/></td>
                        <tr>
                    </tbody>
                </table><br>
                <input type="submit" value="Let's Go!"/>
            </center>
        </form>   
    </div>
    
    <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
      <!--Starting Registration form-->
        
        <div id="register_wrapper">
        <div id="register_window">

        <div style="text-align: right;"><a id="register_close" href="#">close <b>X</b></a></div>

        <center><p>Register with tetraPRO( ) Financials.</p></center>

        <form id="register_feedback" method="POST" action="Register_Servlet" accept-charset="UTF-8">
            <table>
                <tr>
                    <td style="float: right"> <label>First Name: </label></td>
                    <td><input type="text" autofocus required name="firstName" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Last Name: </label></td>
                    <td><input type="text" required name="lastName" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Email: </label></td>
                    <td><input type="text" required name="email" value=""></td>
                </tr>
                 <tr>
                    <td style="float: right"> <label>Password: </label></td>
                    <td><input type="text" required name="password" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Confirm: </label></td>
                    <td><input type="text" required name="confirm" value=""></td>
                </tr>
            </table><br>
            <center><input type="submit" name="registerForm" value="Register Now"></center>
        </form>
        </div> <!-- #register_window -->
        </div> <!-- #register_wrapper -->
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        <script src="JS/register.js"></script>
    </body>
</html>
