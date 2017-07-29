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
        <title>tetraPRO( )</title>
    </head>
    <body>
    <center><h1>tetraPRO( ) Financials</h1></center>
        <form name="userverify" action="Auth_Servlet" method="POST" >
            <center>
                <table border="0" align="center">
                    <tbody>
                        <tr>
                            <td>Email: </td>
                            <td><input type="text" name="email"/></td>
                        </tr>
                        <tr>
                            <td>Password: </td>
                            <td><input type="text" name="password"/></td>
                        <tr>
                            <td></td>
                            <td align='center'><input type="submit" value="Let's Go!"/></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </center>
        </form>        
    </body>
</html>
