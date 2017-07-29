<%-- 
    Document   : index
    Created on : Jul 22, 2017, 11:06:53 AM
    Author     : tetraPRO( )
                     : Software Wizard
                    : Grand-Master_Philip Caputo
                  : **Fullstack Engineer**
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/myStyles.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/styles.css"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <title>tetraPRO( )</title>
    </head>
    <body>
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        <nav>
            <ul id="navigation">
                <li><a href="">File</a></li>
                <li><a href="">Edit</a></li>
                <li><a href="">View</a></li>
            </ul>
        </nav>
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        <!-- This is the user interface where we input the details of the transaction -->
        <section id="user_interface">
            <form name="userInterface" action="UI_Servlet" method="POST">
                <center>
                    <table>
                    <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                    <!-- 1st row has 3 columns -->
                    <tr>
                        <td><input id="datepicker" type="text" /></td>
                        <td><select id="toAccount" >
                                    <option>Taxes</option>
                                    <option>Mortgage/Rent</option>
                                    <option>Insurance</option>
                                    <option>Car Payment</option>
                                    <option>Gas</option>
                                    <option>Food/Groceries</option>
                                    <option>Entertainment</option>
                                    <option>Medical</option>
                                    <option>Clothing</option>
                                    <option>Utilities</option>
                                    <option>Other</option>
                            </select>
                        </td>
                        <td><input id="amount" type="text"  value="Amount" /></td>
                    </tr>
                    <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                    <!-- 2nd row has 3 columns -->
                    <tr>
                        <td></td>
                        <td><select id="fromAccount"  >
                                    <option>Bank</option>
                                    <option>Cash</option>
                                    <option>Bitcoin</option>
                                    <option>Silver</option>
                                    <option>Gold</option>
                                    <option>Stocks/Bonds</option>
                                    <option>Commodities</option>
                                    <option>Real Estate </option>
                                </select>
                        </td>
                        <td><input type="text" value="Notes"/></td>
                    </tr>
                </table>
                    <div id="buttons">
                        <input  id="clearButton" type="submit" value="Clear"/>
                         <input type="submit" value="Update"/>
                    </div>
                </center>
            </form>
            <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        </section>
        
        <br><br><br>
        
        <section id="transaction_list">
            This section is for the transaction list
        </section>
        
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        <!-- Javascript & JQuery -->
        <script>
            $(function(){
                $("#datepicker").datepicker();
            });
        </script>
    </body>
</html>
