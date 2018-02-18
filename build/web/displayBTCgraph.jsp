<%-- 
    Document   : displayBTCgraph
    Created on : Sep 9, 2017, 12:48:12 PM
    Author     : capphil1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        
        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <style>
            body{
                background-color: #666666;
                margin: 0%;
            }
            
           #btc_trans_view{
               border: 2px solid black;
               border-radius: 15px;
               float: right;
               margin-top: 2%;
               margin-right: 1%;
               padding: 16px 24px;      
            }
            
            #btc_graph{
                float: left;
                margin-top: 2%;
                margin-left: 5%;
            }
        </style>
        <title>tetraPRO( )</title>
    </head>
    <body>
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        <jsp:include page="header.jsp"/>
         
        <div id="btc_graph">
            <!-- TradingView Widget BEGIN -->
            <script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script>
            <script type="text/javascript">
                    new TradingView.widget({
                          "width": 880,
                          "height": 510,
                          "symbol": "BITSTAMP:BTCUSD",
                          "interval": "60",
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
        </div>

        <div id="btc_trans_view"></div><!-- list of transactions unconfirmed-->
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        
        <script type="text/javascript" src="JS/setTimestamp.js"></script>
        <script>
              (function(b,i,t,C,O,I,N) {
                window.addEventListener('load',function() {
                  if(b.getElementById(C))return;
                  I=b.createElement(i),N=b.getElementsByTagName(i)[0];
                  I.src=t;I.id=C;N.parentNode.insertBefore(I, N);
                },false);   
              })(document,'script','https://widgets.bitcoin.com/widget.js','btcwdgt');
    </script>
    <script>
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
                    var amount = response.x.out[0].value;console.log(response.x.out);
                    var calAmount = amount / 100000000;
                    //var liveBTCprice = getLivePrice(BTC);
                    
                    
                    var dollarAmount = (calAmount * 3955).toFixed(2);//need to insert variable for live bitcoin price
                    //if test if current price is greater or not then color code background.
                    
                     $('#btc_trans_view').prepend("<p> $" + dollarAmount  + " : " +  calAmount + "BTC" + "</p>");       
                    };
                    
                    // 
            </script>
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
                }
            };
        </script>
    </body>
</html>

