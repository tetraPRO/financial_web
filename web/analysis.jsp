<%-- 
    Document   : analysis
    Created on : Sep 13, 2017, 5:54:43 PM
    Author     : tetraPRO( )
                     : Software Wizard
                    : Grand-Master_Philip Caputo
                  : **Fullstack Engineer**
--%>

<%@page import="Beans.BudgetExpense"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Beans.Transaction"%>
<%@page import="ServerLogic.SQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
        <link rel="stylesheet" href="/resources/demos/style.css"/>
<style>
            body{
                background-color: #666666;
                margin: 0%;
            }
            
            #analysis_toolbar{
                position: absolute;
                float: left;
                margin-top: 15px;
                padding: 15px;
                border: solid black 2px;
                border-radius: 15px;
            }
            
            #roundCorners{
                padding: 11px;
                border-radius: 15px;
            }
            
            .account_view{
                border: solid black 2px;
                border-radius: 15px;
                padding:17px;
                margin: 0 auto;
                margin-top: 17px;
                margin-left: 21%;
                /*width: auto;*/
                margin-right: 23%;
                height: 80%;
            }
            
            .row_space{
                padding: 5px 21px;
                text-align: center;
                width: auto;
            }
        </style>
        <title>tetraPRO( )</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<%SQL data = new SQL();%>
<%String user = (String) session.getAttribute("user");
    if(user == null){
        response.sendRedirect("login.jsp");
    }%>
<%ArrayList<BudgetExpense> budgetList = data.getBudgetList(user);%>
    <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
            <aside id="analysis_toolbar">
                      <center>
                          <form id="modal_feedback" method="POST" action="Details_Servlet" accept-charset="UTF-8">
                          <table>
                              <tr>
                                  <td><input type="text"  id="datepicker" placeholder="Date" name="Date"/></td>
                              </tr>
                              <tr>
                                  <td><select style="width: 100%;" name="account">
                                                 <%for(int i=0;i<budgetList.size();i++){%>
                                                 <option><%=budgetList.get(i).getAccount()%></option>
                                                <%}%><!--end for loop-->
                                      </select></td>
                              </tr>
                          </table>
                            <br>
                            <input  id="roundCorners" type="submit" value="Let's take a look!"/>
                        </form>
                        </center>
             </aside>
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        <center>
        
        <%for(int i=0;i<budgetList.size();i++){%>
        <section class="account_view">
            <%float totalMonthly = 0;%>
              <%String dark = "#b5b5b5";%><%--dark grey--%>
            <%String color = dark;%>
            <table>
                 <%ArrayList<Transaction> list = data.getListOfTransactions(user, budgetList.get(i).getAccount(), 2);%>
                  <%for(int j=0;j<list.size();j++){%>
                  <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
                  <%--color scheme to offset either other row --%>
                        <%String light = "#e2e2e2";%><%--light grey--%>
                        <%if(j%2==0){
                            color = light;                
                        }%>
                     <!--~~~~~~~~~~~~~~~~~~~~~~~~-->
                <tr bgcolor="<%=color%>">
                    <td class="row_space"><%=list.get(j).getDate()%></td>
                    <td class="row_space" ><%=list.get(j).getAccount()%></td>
                    <td class="row_space" ><%=list.get(j).getDebit()%></td>
                    <td class="row_space" ><%=list.get(j).getNotes()%></td>
                </tr>
                <%totalMonthly += list.get(j).getDebit();%>
                  <%}%><!--end for loop-->
                  <tr bgcolor="<%=color%>">
                      <td></td>
                      <td style="text-align: right">Sum Total: </td>
                    <td class="row_space" ><%=totalMonthly%></td>
                     <td></td>
                </tr>
            </table>
        </section>
       <%}%><!--end for loop-->
        </center>
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
         <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
          <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
         <script type="text/javascript" src="JS/setTimestamp.js"></script>
        <script type="text/javascript">
            $(function(){
                $("#datepicker").datepicker();
            });
            </script>
</body>
</html>
