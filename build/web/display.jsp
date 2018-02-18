<%-- 
    Document   : Display
    Created on : Jul 30, 2017, 12:58:12 PM
    Author     : tetraPRO( )
                     : Software Wizard
                    : Grand-Master_Philip Caputo
                  : **Fullstack Engineer**
--%>

 <%@page import="java.util.GregorianCalendar"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="Beans.Transaction"%>
<%@page import="ServerLogic.SQL"%>
<%@page import="java.util.ArrayList"%>

<%
        HttpSession session2 = request.getSession();
        String user = (String) session2.getAttribute("user");
        SQL data = new SQL();
        if(user == null){
            response.sendRedirect("login.jsp");
        }
        
        ArrayList<Transaction> list = data.getTransactions(user);//monthly transactions
%>
          
<table border="0">
            <tr>
                <td class="display_Headers">ID</td>
                <td class="display_Headers">Date</td>
                <td class="display_Headers">Account</td> 
                <td class="display_Headers">Debit</td>
                <td class="display_Headers">Credit</td>
                <td class="display_Headers">Notes</td>
            </tr>
            
            <%for(int i=0;i<list.size();i++){%>
            <%String dark = "#b5b5b5";%><%--dark grey--%>
            <%String light = "#e2e2e2";%><%--light grey--%>
            <%String color = dark;%>
            <%if(i%2==0){
                color = light;                
            }%>
            <tr class="displayData"  bgcolor="<%=color%>">
                <td><%=list.get(i).getID()%></td><!--ID-->
                <td><%=list.get(i).getDate()%></td><!--date-->
                 <td><%=list.get(i).getAccount()%></td><!--to which is the debit-->
                <td><%=list.get(i).getDebit()%></td><!--Debit-->
                <td><%=list.get(i).getCredit()%></td><!--Credit-->
                <td><%=list.get(i).getNotes()%></td><!--notes-->
            </tr>
            <% } %>
</table>
