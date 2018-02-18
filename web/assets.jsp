<%-- 
    Document   : assets
    Created on : Aug 4, 2017, 7:55:52 PM
    Author     : tetraPRO( )
                     : Software Wizard
                    : Grand-Master_Philip Caputo
                  : **Fullstack Engineer**
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="ServerLogic.SQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->

<%
    HttpSession session4 = request.getSession();
     String user = (String) session4.getAttribute("user");
     SQL data = new SQL();
     if(user == null){
         response.sendRedirect("login.jsp");
     }
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
    
    <%
        String bank = data.getAsset(user, "Bank");
        String cash = data.getAsset(user, "Cash");
        String snap = data.getAsset(user, "SNAP");
        String bitcoin = data.getBitcoin(user);//need quantity from notes column
        String silver = data.getAsset(user, "Silver");
        String gold = data.getAsset(user, "Gold");
        String vehicles = data.getDebitBalance(user, "Vehicle(s)");//need only asset value
        String realEstate = data.getDebitBalance(user, "Real Estate");//need only asset value
        String commodities = data.getDebitBalance(user, "Commodities");//need only asset value
        String stocksBonds = data.getDebitBalance(user, "Stocks/Bonds");//need only asset value
        %>
    
 <center>
<table>
    <tr>
        <td>Bank</td>
        <td style="float: right" id="bank">$<%=getFormattedAmount(bank)%></td>
    </tr>
    <tr>
        <td>Cash</td>
        <td style="float: right" id="cash">$<%=getFormattedAmount(cash)%></td>
    </tr>
    <tr>
        <td>SNAP</td>
        <td style="float: right">$<%=getFormattedAmount(snap)%></td>
    </tr>
    <tr>
        <td>Bitcoin <data id="last"></data></td>
        <td style="float: right" id="bitcoin"></td><!--want to use the amount of bitcoin x current price-->
    </tr>
    <tr>
        <td>Silver</td>
        <td style="float: right">$<%=getFormattedAmount(silver)%></td>
    </tr>
    <tr>
        <td>Gold</td>
        <td style="float: right">$<%=getFormattedAmount(gold)%></td>
    </tr>
    <tr>
        <td>Vehicle(s)</td><!-- need to include ONLY asset value and not the equity-->
        <td style="float: right">$<%=getFormattedAmount(vehicles)%></td>
    </tr>
    <tr>
        <td>Real Estate</td><!-- need to include ONLY asset value and not the equity-->
        <td style="float: right">$<%=getFormattedAmount(realEstate)%></td>
    </tr>
    <tr>
        <td>Commodities</td>
        <td style="float: right">$<%=getFormattedAmount(commodities)%></td>
    </tr>
    <tr>
        <td>Stocks/Bonds</td><!-- need to include ONLY asset value and not the equity-->
        <td style="float: right">$<%=getFormattedAmount(stocksBonds)%></td>
    </tr>
</table>
 </center>
<br>

<%
    BigDecimal bigBank = new BigDecimal(bank);
    BigDecimal bigCash = new BigDecimal(cash);
    BigDecimal bigSnap = new BigDecimal(snap);
    BigDecimal bigBitcoin = new BigDecimal(bitcoin);
    BigDecimal bigSilver = new BigDecimal(silver);
    BigDecimal bigGold = new BigDecimal(gold);
    BigDecimal bigVehicle = new BigDecimal(vehicles);
    BigDecimal bigRE = new BigDecimal(realEstate);
    BigDecimal bigCommodities = new BigDecimal(commodities);
    BigDecimal bigStocks = new BigDecimal(stocksBonds);
    
    BigDecimal totalAssets = bigBank.add(bigCash).add(bigSnap).add(bigBitcoin).add(bigSilver).add(bigGold)
    .add(bigRE).add(bigCommodities).add(bigStocks).add(bigVehicle);
    %>

    <%
        String total = totalAssets.toString();
        HttpSession session6 = request.getSession();
        session6.setAttribute("assets", total);
        %>
    
        <center><strong>Total Assets:  <data>$<%=getFormattedAmount(total)%></data></strong></center>
<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
<!--  

-->
<img id="qr_Bitcoin" 
     alt="QR_Code"
     src="https://i.imgur.com/FXgBmph.png"
     style="margin-left: 30%;
     margin-top: 15%;">

<!--bitcoin price pulled from bitfinex.com-->
<script type="text/javascript">
            var ws = new WebSocket("wss://api.bitfinex.com/ws");
            
            ws.onopen = function(){
                ws.send(JSON.stringify({"event":"subscribe", "channel":"ticker", "pair":"BTCUSD"}));
            };
            
            //upon receiving the response message, we can work with it
            ws.onmessage = function(msg){
                var response = JSON.parse(msg.data);
                var hb = response[1];
                if(hb !== "hb"){
                    var price = document.getElementById("last").innerHTML = "$" + response[3];
                    
                    var subPrice = price.substring(1, price.length);
                    var p = parseFloat(subPrice) ;
                    var q =parseFloat(<%=bitcoin%>);
                    document.getElementById("bitcoin").innerHTML = "$" +  (p * q).toFixed(2);
                }
            };
            
           
        </script>