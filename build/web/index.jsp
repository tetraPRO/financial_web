<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Beans.BudgetExpense"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.math.BigDecimal"%>
<%@page  import="java.lang.Integer" %>
<%@page import="ServerLogic.SQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--Document   : index
    Created on : Jul 22, 2017, 11:06:53 AM
    Author     : tetraPRO( )
                     : Software Wizard
                    : Grand-Master_Philip Caputo
                  : **Fullstack Engineer**  
-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/index.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/styles.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/budget.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/asset.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/liability.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/income.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/transfer.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/viewBudget.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/viewIncome.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/totalBudget.css"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
        <link rel="stylesheet" href="/resources/demos/style.css"/>
        <title>tetraPRO( )</title>
        <style>
            #transaction_list{
                background-image: URL("http://blvcccvrd.com/images/ancient-scroll-border-cliparts-12.jpg");
               background-repeat: no-repeat;
                                   
            }
        </style>
    </head>
    <body>
        <%
            HttpSession session8 = request.getSession();
            String userFistname = (String)session8.getAttribute("first_Name");
            %>
            
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        <jsp:include page="header.jsp"/>
        
        <%
            String user = (String) session8.getAttribute("user");
            SQL data = new SQL();
            
             if(user == null){
                response.sendRedirect("login.jsp");
             }%>
    
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        <div id="container">
        <!-- This is the user interface where we input the details of the transaction -->
        <section id="user_interface">
            <form name="userInterface" action="UI_Servlet" method="POST">
                <center>
                    <table>
                    <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                   <!-- 1st row has 3 columns -->
                    <tr>
                        <td><input class="datepicker" type="text"  name="datepicker"  placeholder="Date" required="true"/></td>
                        <td><select id="toAccount"  name="toAccount">
                         <%ArrayList<BudgetExpense> budgetList = data.getBudgetList(user);%>
                         <%for(int i=0;i<budgetList.size();i++){%>
                                    <option><%=budgetList.get(i).getAccount()%></option>
                                    <%}%>
                            </select>
                        </td>
                        <td><input id="amount" type="text"  placeholder="Amount" name="amount" required="true"/></td>
                    </tr>
                    <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                    <!-- 2nd row has 3 columns -->
                    <tr>
                        <td></td>
                        <td><select id="fromAccount"  name="fromAccount">
                                    <option>Bank</option>
                                    <option>Cash</option>
                                    <option>SNAP</option>
                                    <option>Bitcoin</option>
                                    <option>Silver</option>
                                    <option>Gold</option>
                                    <option>Vehicle(s)</option>
                                    <option>Stocks/Bonds</option>
                                    <option>Commodities</option> 
                                    <option>Derivatives</option> 
                                    <option>Real Estate </option>
                                    <option>Business(es)</option>
                                    <option>Credit Card</option>
                                </select>
                        </td>
                        <td><input  id="notes" type="text" placeholder="Notes" name="notes" required="true"/></td>
                    </tr>
                    
                </table>
                    <div id="buttons">
                        <input  id="update" type="submit" value="Update"/>
                    </div>
                </center>
            </form>
        </section>
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        
        <%
            String expenses = data.getSumExpenses(user);
            double expAsDouble = Double.parseDouble(expenses);
             DecimalFormat formatter = new DecimalFormat("###,###,###.00");
            String formattedExpenese = formatter.format(expAsDouble);
            String budget = data.getSumBudget();
            BigDecimal exp = new BigDecimal(expenses);
            BigDecimal bud = new BigDecimal(budget);
            BigDecimal percentOfBudget  = null;
            if(bud.doubleValue() != 0.00){
                percentOfBudget = exp.divide(bud, 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            }
        %>        
        <%!
                 public String getFormattedAmount(String amount){
                     if(amount.equals("0.00")){
                        return "0.00";
                    }else{
                        double asDouble = Double.parseDouble(amount);
                          DecimalFormat formatter = new DecimalFormat("###,###,###.00");
                            String formattedExpenese = formatter.format(asDouble);
                            return formattedExpenese;
                    }
                }
    %>
        
        <div id="progressBar"></div>
        
        <section>
            <div id="transaction_list">
                  <center><h1>Ledger ($<%=formattedExpenese%>)</h1></center>
                    <center><jsp:include page="display.jsp"/></center>
            </div>
        </section>
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        <aside id="assetBalances">
            <center><h2 id="_heading">Assets</h2></center>
            <jsp:include page="assets.jsp"/>
        </aside>
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        <aside id="liabilityBalances">
            <center><h2 id="_heading">Liabilities</h2></center>
            <jsp:include page="liabilities.jsp"/>
        </aside>
        </div><!--container -->
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        <!--Budget form-->
        
        <div id="modal_wrapper">
        <div id="modal_window">

        <div style="text-align: right;"><a id="modal_close" href="#">close <b>X</b></a></div>

        <center><p>Enter your budget for each expense.</p></center>
        <%ArrayList<BudgetExpense> list = data.getBudgetList(user);%>
        <form id="modal_feedback" method="POST" action="Budget_Servlet" accept-charset="UTF-8">
            <table>
                <%for(int i=0;i<list.size();i++){%>
                <tr>
                    <td style="float: right"> <label><%=list.get(i).getAccount()%></label></td>
                    <td><input type="text"  required name="<%=list.get(i).getAccount().toLowerCase()%>"></td>
                </tr>
                <%}%>
            </table>
            <center><input type="submit" name="budgetForm" value="Update"></center>
        </form>
        </div> <!-- #modal_window -->
        </div> <!-- #modal_wrapper -->
      <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
      <!--Starting Balances form-->
        
        <div id="asset_wrapper">
        <div id="asset_window">

        <div style="text-align: right;"><a id="asset_close" href="#">close <b>X</b></a></div>

        <p>Enter your balance for each account.</p>

        <form id="asset_feedback" method="POST" action="Asset_Servlet" accept-charset="UTF-8">
            <table>
                <tr>
                    <td style="float: right"> <label>Bank: </label></td>
                    <td><input type="text" autofocus required name="bank" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Cash: </label></td>
                    <td><input type="text" required name="cash" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>SNAP: </label></td>
                    <td><input type="text" required name="snap" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Bitcoin: </label></td>
                    <td><input type="text" required name="bitcoin" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Silver: </label></td>
                    <td><input type="text" required name="silver" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Gold: </label></td>
                    <td><input type="text" required name="gold" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Real Estate: </label></td>
                    <td><input type="text" required name="real_estate" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Commodities: </label></td>
                    <td><input type="text" required name="commodities" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Stocks/Bonds: </label></td>
                    <td><input type="text" required name="stocks_bonds" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Business(es): </label></td>
                    <td><input type="text" required name="business" value=""></td>
                </tr>
            </table>
            <center><input type="submit" name="assetForm" value="Update"></center>
        </form>
        </div> <!-- #asset_window -->
        </div> <!-- #asset_wrapper -->
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
      <!--Starting Liabilities form-->
        
        <div id="liability_wrapper">
        <div id="liability_window">

        <div style="text-align: right;"><a id="liability_close" href="#">close <b>X</b></a></div>

        <p>Enter the amount you owe on each account.</p>

        <form id="liability_feedback" method="POST" action="Liability_Servlet" accept-charset="UTF-8">
            <table>
                <tr>
                    <td style="float: right"> <label>Real Estate: </label></td>
                    <td><input type="text" autofocus required name="realEstate" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Vehicle(s): </label></td>
                    <td><input type="text" required name="vehicles" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Student Debt: </label></td>
                    <td><input type="text" required name="studentDebt" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Credit Cards: </label></td>
                    <td><input type="text" required name="creditCards" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Personal: </label></td>
                    <td><input type="text" required name="personal" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Business(es): </label></td>
                    <td><input type="text" required name="business" value=""></td>
                </tr>
            </table>
            <center><input type="submit" name="liabilityForm" value="Update"></center>
        </form>
        </div> <!-- #liability_window -->
        </div> <!-- #liability_wrapper -->
      <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
      <!--Starting Income form-->
        
        <div id="income_wrapper">
        <div id="income_window">

        <div style="text-align: right;"><a id="income_close" href="#">close <b>X</b></a></div>

        <center><p>Enter your income.</p></center>

        <form id="income_feedback" method="POST" action="Income_Servlet" accept-charset="UTF-8">
            <table>
                <tr>
                    <td style="float: right"> <label>Date: </label></td>
                    <td><input class="datepicker" type="text"  name="datepicker"  placeholder="Date" required="true"/></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Account: </label></td>
                    <td><select name="account" style="width: 100%">
                                    <option>Bank</option>
                                    <option>Cash</option>
                                    <option>SNAP</option>
                                    <option>Bitcoin</option>
                                    <option>Silver</option>
                                    <option>Gold</option>
                                    <option>Vehicle(s)</option>
                                    <option>Stocks/Bonds</option>
                                    <option>Commodities</option> 
                                    <option>Real Estate </option>
                                    <option>Business(es)</option>
                                </select></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Amount: </label></td>
                    <td><input type="text" required name="amount" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Tax: </label></td>
                    <td><input type="text" required name="tax" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Notes: </label></td>
                    <td><select name="notes" style="width: 100%">
                                    <option>Paycheck</option>
                                    <option>Tips</option>
                                    <option>Gift</option>
                                    <option>Reimbursement</option>
                                    <option>Rental</option>
                                    <option>Options</option>
                                    <option>Dividends</option>
                                    <option>Portfolio</option>
                        </select></td>
                </tr>
            </table><br>
            <center><input type="submit" name="incomeForm" value="Add Income"></center>
        </form>
        </div> <!-- #income_window -->
        </div> <!-- #income_wrapper -->
      <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
      <!--Transfer form-->
        
        <div id="transfer_wrapper">
        <div id="transfer_window">

        <div style="text-align: right;"><a id="transfer_close" href="#">close <b>X</b></a></div>

        <p>Enter the details of your transfer.</p>

        <form id="transfer_feedback" method="POST" action="Transfer_Servlet" accept-charset="UTF-8">
            <table>
                <tr>
                    <td style="float: right"> <label>Date: </label></td>
                    <td><input class="datepicker" type="text"  name="date"  placeholder="Date" required="true"/></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>From: </label></td>
                    <td><select name="from" style="width: 100%">
                                  <option>Bank</option>
                                    <option>Cash</option>
                                    <option>SNAP</option>
                                    <option>Bitcoin</option>
                                    <option>Silver</option>
                                    <option>Gold</option>
                                    <option>Vehicle(s)</option>
                                    <option>Stocks/Bonds</option>
                                    <option>Commodities</option> 
                                    <option>Real Estate </option>
                                    <option>Business(es)</option>
                                </select></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>To: </label></td>
                    <td><select name="to" style="width: 100%">
                                   <option>Bank</option>
                                    <option>Cash</option>
                                    <option>SNAP</option>
                                    <option>Bitcoin</option>
                                    <option>Silver</option>
                                    <option>Gold</option>
                                    <option>Vehicle(s)</option>
                                    <option>Stocks/Bonds</option>
                                    <option>Commodities</option> 
                                    <option>Real Estate </option>
                                    <option>Business(es)</option>
                        </select></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Amount: </label></td>
                    <td><input type="text" required name="amount" value=""></td>
                </tr>
                <tr>
                    <td style="float: right"> <label>Notes: </label></td>
                    <td><input type="text" required name="notes" value=""></td>
                </tr>
            </table><br>
            <center><input type="submit" name="transferForm" value="Transfer"></center>
        </form>
        </div> <!-- #transfer_window -->
        </div> <!-- #transfer_wrapper -->
      <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
      <!--view budget thus far-->
      <div id="view_budget_wrapper">
        <div id="view_budget_window">

        <div style="text-align: right;"><a id="view_budget_close" href="#">close <b>X</b></a></div>

        <center>
            <p>The ratio of your budgetted expenses.</p>
        <form id="view_budget_feedback">
            <table>
                
                <%for(int i=0;i<budgetList.size() ;i++){%>
                <tr>
                    <td style="text-align: right;"><%=budgetList.get(i).getAccount()%>: </td><!--account name-->
                     <!--this has to be the ratio of budgetted to spent-->
                    <%BigDecimal ratioBudget = new BigDecimal(data.getBudgetExpenseRatio(user, budgetList.get(i)
                                 .getAccount())).multiply(new BigDecimal("100"));%>
                    <td><progress value="<%=ratioBudget%>" max="100"></progress></td>
                    <td><%=data.getBudgetExpenseRatio(user, budgetList.get(i).getAccount())%></td><!--account ratio of spent to budgetted-->
                </tr>
                
                    <%}%>
            </table>
            <br>
                    <button id="view_budget_close2">Got it!</button>
        </form>
         </center>
        </div> <!-- #view_budget_window -->
        </div> <!-- #view_budget_wrapper -->
      <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
      <!--View Income form-->
        
        <div id="viewIncome_wrapper">
        <div id="viewIncome_window">

        <div style="text-align: right;"><a id="viewIncome_close" href="#">close <b>X</b></a></div>

        <center>
            <p>Look at all the income you earned!</p>

        <form id="viewIncome_feedback">
            <table>
                <tr>
                    <td style="float: right"> <label>Total Income: </label></td>
                    <%
                       String earned = data.getEarnedIncome(user);
                        double earnedAsDouble = Double.parseDouble(earned);
                         DecimalFormat formate = new DecimalFormat("###,###,###.00");
                        String formattedEarned = formate.format(earnedAsDouble);
                        
                    %>
                    <td><strong>$<%=formattedEarned%></strong></td>
                </tr>
            </table>
                <br>
                <button id="viewIncome_close2">Got it!</button>
        </form>
            </center>
        </div> <!-- #viewIncome_window -->
        </div> <!-- #viewIncome_wrapper -->
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
      <!--View Total Budget form-->
        
        <div id="view_total_budget_wrapper">
        <div id="view_total_budget_window">

        <div style="text-align: right;"><a id="view_total_budget_close" href="#">close <b>X</b></a></div>

        <center>
            <p>Look at your total budget for the month!</p>

        <form id="view_total_budget_feedback">
            <table>
                <tr>
                    <td style="float: right"> <label>Total Budget: </label></td>
                    <td><strong>$<%=getFormattedAmount(data.getTotalBudget())%></strong></td>
                </tr>
            </table>
                <br>
                <button id="view_total_budget_close">Got it!</button>
        </form>
            </center>
        </div> <!-- #view_total_budget_window -->
        </div> <!-- #view_total_budget_wrapper -->
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        <!-- Javascript & JQuery -->
        <script type="text/javascript" src="JS/budget.js"></script>
        <script type="text/javascript" src="JS/assets.js"></script>
        <script type="text/javascript" src="JS/liabilities.js"></script>
        <script type="text/javascript" src="JS/income.js"></script>
        <script type="text/javascript" src="JS/transfer.js"></script>
        <script type="text/javascript" src="JS/viewBudget.js"></script>
        <script type="text/javascript" src="JS/viewIncome.js"></script>
        <script type="text/javascript" src="JS/totalBudget.js"></script>
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script type="text/javascript">
            $(function(){
                $(".datepicker").datepicker();
            });
            
              $( function() {
                $( "#progressBar" ).progressbar({
                  value: <%=percentOfBudget%>
                });
            } );
                        
           
            //connect to live bitcoin transaction blockchain.info
           var btcs = new WebSocket('wss://ws.blockchain.info/inv');
           
           btcs.onopen = function(){

	btcs.send(JSON.stringify({"op":"unconfirmed_sub"}));
	};
        
        btcs.onmessage = function(onmsg){

	var response = JSON.parse(onmsg.data);
	};

        btcs.onmessage = function(onmsg){

	var response = JSON.parse(onmsg.data);

	var amount = response.x.out[0].value;

	var calAmount = amount / 100000000;
        
                                    $('#messages').prepend("<p>" + calAmount + "</p>");
	};

        </script>
    </body>
</html>
