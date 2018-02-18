<%-- 
    Document   : income
    Created on : Jan 22, 2018, 5:43:29 PM
    Author     : Philip Caputo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
    <!--JSP page is a dynamic page and therefore must contain some magic!-->
    <%
        //this is where java code goes...
        String user = (String) session.getAttribute("user");

         if(user == null){
            response.sendRedirect("login.jsp");
         }
    %>
    <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>tetraPRO( )</title>
        <%
            String[] urls = 
            {"https://sykes-wpengine.netdna-ssl.com/wp-content/uploads/2013/08/commodities-624x255.png",//generic_commidities
            "https://lh3.googleusercontent.com/YPePFkXg3JLwSk19KVXODxFOO_KPfpknurXxwlI1fme1BwJBcUN8MWu8agXHp348mOOUKB_8egiaiqZnKpVmAiUigvVIQxEQ=s750",//gold
            "https://kingworldnews.com/wp-content/uploads/2017/07/King-World-News-James-Turk-This-Is-The-Real-Reason-The-Government-Created-Fridays-Flash-Crash-In-Silver-864x400_c.jpg",//silver
            "http://www.industryweek.com/sites/industryweek.com/files/styles/article_featured_standard/public/Copper-pipe-T-770_0.jpg?itok=f6yUycea",//copper
           };
        %>
        <style>
            body{
                background-image: URL('<%=urls[0]%>');
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-position: center;
                background-size: contain;
                background-color: #666666;
                 margin: 0%;
            }
            #container{
                border: solid black 2px;
                border-radius: 10px;
                padding: 10px;
                width: 33%;
                margin: auto;
            }
            #buyer{
                float: left;
                margin-left: 5%;
            }
            
            #seller{
                float: right;
                margin-right: 5%;
            }
            #buyerSpacer{
                padding-left: 40%;
            }
            
            #sellerSpacer{
               padding-left: 40%;
            }
            
            #contractSpecsLeft{
                border: solid black 2px;
                border-radius: 10px;
                float: left;
                margin-top: -550px;
                width: 21%;
                padding: 1%;
                margin-left:1%;
            }
            
            #putSellers{
                background-image: URL("http://www.adrian-kingston.com/IWillBuyOrLeaseYourHouse/images/Components/Option.jpg");
                border: solid black 2px;
                border-radius: 10px;
                float: left;
                margin-top: -400px;
                width: 21%;
                padding: 1%;
                margin-left:1%;
            }
            
             #contractSpecsRight{
                 border: solid black 2px;
                border-radius: 10px;
                 float: right;
                 margin-top:  -550px;
                 width:21%;
                 padding: 1%;
                 margin-right: 1%;
            }
            
            #callSellers{
                border: solid black 2px;
                border-radius: 10px;
                 float: right;
                 margin-top:  -400px;
                 width:21%;
                 padding: 1%;
                 margin-right: 1%;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <br/>
        <div id="container">
            <center>
                 <form  name="futuresContract" action="FutureContract_Servlet" method="POST">
                    ~Select a Contract~
                    <br/>
                     <select id="market" name="market">
                        <option>E-Mini S&P500 (ES)</option>
                        <option>E-Mini Nasdaq 100 (NQ)</option>
                        <option>Crude Oil (CL)</option>
                        <option>Natural Gas (NG)</option>
                        <option>Gold (GC)</option>
                        <option>Silver (SI)</option>
                        <option>Copper (HG)</option>
                        <option>Corn (C)</option>
                        <option>Soybeans (ZS)</option>
                        <option>Wheat (W)</option>
                    </select>
                </form>
            </center>
        </div>
        <br/>
        
        <!-- TradingView Widget BEGIN -->
<script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script>
<script type="text/javascript">
new TradingView.widget({
  "width": 640,
  "height": 400,
  "symbol": "CME_MINI:ES1!",
  "interval": "D",
  "timezone": "Etc/UTC",
  "theme": "Dark",
  "style": "1",
  "locale": "en",
  "toolbar_bg": "rgba(66, 66, 66, 1)",
  "enable_publishing": false,
  "allow_symbol_change": true,
  "hideideas": true
});
</script>
<!-- TradingView Widget END -->

<div id="contractSpecsLeft">
    Multiplier: 
    <br/>
    Delivery Date: 
    <br/>
    $1 move = $
</div>

<div id="contractSpecsRight">
    Price: $
    <br/>
    Initial Margin: $
</div>

<div id="putSellers">
    List of put options...
</div>

<div id="callSellers">
    List of call options...
</div>

<div id="buyer">
    <strong>_________________________</strong>
    <br/><span id="buyerSpacer"><strong>Buyer</strong></span>
</div>

<div id="seller">
    <strong> _________________________</strong>
    <br/><span id="sellerSpacer"><strong>Seller</strong></span>
</div>
        
    </body>
</html>
