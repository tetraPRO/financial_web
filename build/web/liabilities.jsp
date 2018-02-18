<%-- 
    Document   : liabilities
    Created on : Aug 4, 2017, 7:56:35 PM
    Author     : tetraPRO( )
                     : Software Wizard
                    : Grand-Master_Philip Caputo
                  : **Fullstack Engineer**
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="ServerLogic.SQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession session5 = request.getSession();
     String user = (String) session5.getAttribute("user");
     SQL data = new SQL();
     String assets = (String)session5.getAttribute("assets");
%>
    
<%!
     public String getFormattedAmount(String amount){
     if(amount.equals("0.00")){
     return "0.00";
    }else{
    double asDouble = Double.parseDouble(amount);
      DecimalFormat formatter = new DecimalFormat("###,###.00");
        String formattedExpenese = formatter.format(asDouble);
        return formattedExpenese;
    }
    }
    %>
    
    <%String realEstate = data.getCreditBalance(user, "Real Estate");%>
    <%String vehicles = data.getCreditBalance(user, "Vehicle(s)");%>
    <%String studentDebt = data.getCreditBalance(user, "Student Debt");%>
    <%String creditCards = data.getCreditBalance(user, "Credit Cards");%>
    <%String personal = data.getCreditBalance(user, "Personal Loans");%>
    <%String business = data.getCreditBalance(user, "Business");%>
    

<table>
    <tr>
        <td>Real Estate</td>
        <td style="float:right">$<%=getFormattedAmount(realEstate)%></td>
    </tr>
    <tr>
        <td>Vehicle(s)</td>
        <td style="float: right">$<%=getFormattedAmount(vehicles)%></td>
    </tr>
    <tr>
        <td>Student Debt</td>
        <td style="float:right">$<%=getFormattedAmount(studentDebt)%></td>
    </tr>
    <tr>
        <td>Credit Cards</td>
        <td style="float:right">$<%=getFormattedAmount(creditCards)%></td>
    </tr>
    <tr>
        <td>Personal Loan(s)</td>
        <td style="float:right">$<%=getFormattedAmount(personal)%></td>
    </tr>
    <tr>
        <td>Business(es)</td>
        <td style="float:right">$<%=getFormattedAmount(business)%></td>
    </tr>
</table>
<br><br><br>

<%
    BigDecimal re = new BigDecimal(realEstate);
    BigDecimal vehicle = new BigDecimal(vehicles);
    BigDecimal student = new BigDecimal(studentDebt);
    BigDecimal cc = new BigDecimal(creditCards);
    BigDecimal person = new BigDecimal(personal);
    BigDecimal biz = new BigDecimal(business);
    
    BigDecimal totalDebt = re.add(vehicle).add(student).add(cc).add(person).add(biz);
    %>

    <center>Total Liabilities:  $<%=getFormattedAmount(totalDebt.toString())%></center>
    <br><br><br>
    <%
        BigDecimal assetTotal = new BigDecimal(assets);
        BigDecimal equity = assetTotal.subtract(totalDebt);
        %>
    
        <center><strong><label>Total Equity: $<%=getFormattedAmount(equity.toString())%></label></strong></center>