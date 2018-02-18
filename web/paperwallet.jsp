<%-- 
    Document   : paperwallet
    Created on : Oct 28, 2017, 10:13:47 AM
    Author     : capphil1
--%>

<%@page import="org.bitcoinj.params.MainNetParams"%>
<%@page import="org.bitcoinj.params.TestNet3Params"%>
<%@page import="org.bitcoinj.core.NetworkParameters"%>
<%@page import="org.bitcoinj.core.ECKey"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>tetraPRO( )</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="JS/jquery.min.js"></script>
        <script type="text/javascript" src="JS/qrcode.js"></script>
        <style>
            body{
                background-color: #666666;
                margin: 0%;
            }
            #qrcode_private{/*2nd quad*/
                position: absolute;
                margin-top: -412px;
                margin-left: 335px;
            }
            
            #qrcode_private1{/*1st quad*/
                position: absolute;
                margin-top: -412px;
                margin-left: 961px;
            }
            
            #qrcode_private2{/*3rd quad*/
                position: absolute;
                margin-top: -166px;
                margin-left: 335px;
            }
            
            #qrcode_private3{/*4th quad*/
                position: absolute;
                margin-top: -166px;
                margin-left: 961px;
            }
            
            #paperkeyarea{
                margin-top: -10px;
            }
            
            #keychainAndBalances{
                 margin-top: -100px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <%
            NetworkParameters params = MainNetParams.get();
            String[] keyRing =  new String[4];
            String[] pubKeyRing = new String[4];
            ECKey[] key  = new ECKey[4];
            for(int i=0;i<4;i++){
                 key[i] = new ECKey(); 
                 keyRing[i] = key[i].getPrivateKeyAsWiF(params);
                 pubKeyRing[i] = key[i].getPublicKeyAsHex();
            }
           
            %>
        
            <input id="text" type="text" value="<%=keyRing[0]%>" style="width:80%" hidden="true" /><br /><!-- need to remove this-->
            <input id="text1" type="text" value="<%=keyRing[1]%>" style="width:80%" hidden="true" /><br /><!-- need to remove this-->
            <input id="text2" type="text" value="<%=keyRing[2]%>" style="width:80%" hidden="true" /><br /><!-- need to remove this-->
            <input id="text3" type="text" value="<%=keyRing[3]%>" style="width:80%" hidden="true" /><br /><!-- need to remove this-->
        <br/><br/><br/>
        <div id="keychainAndBalances">
            List of public keys and balances...
        </div>
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        <div id="paperkeyarea">
            <div id="keyarea" class="keyarea art">
                <div id="artwallet" class="artwallet"><center>
                    <img id="papersvg1" class="papersvg" src="Images/paperwallet.jpg" width="750px" height="375px"/></center>
                    <div id="qrcode_private" class="qrcode_private">
                        <canvas style="width: 55px; height: 55px;" height="1025" width="1025"></canvas>
                    </div>
                    <div id="qrcode_private1" class="qrcode_private">
                        <canvas style="width: 55px; height: 55px;" height="1025" width="1025"></canvas>
                    </div>
                     <div id="qrcode_private2" class="qrcode_private">
                        <canvas style="width: 55px; height: 55px;" height="1025" width="1025"></canvas>
                    </div>
                     <div id="qrcode_private3" class="qrcode_private">
                        <canvas style="width: 55px; height: 55px;" height="1025" width="1025"></canvas>
                    </div>
                </div>
            </div>
        </div>
        
<script type="text/javascript">
var qrcode = new QRCode(document.getElementById("qrcode_private"), {
	width : 75,
	height : 75
});

var qrcode1 = new QRCode(document.getElementById("qrcode_private1"), {
	width : 75,
	height : 75
});

var qrcode2 = new QRCode(document.getElementById("qrcode_private2"), {
	width : 75,
	height : 75
});

var qrcode3 = new QRCode(document.getElementById("qrcode_private3"), {
	width : 75,
	height : 75
});

function makeCode () {	
                                //need to generate 4 private keys here...
                                //generatePrivateKey(); 
                                
                                
	var elText = document.getElementById("text");//do not need this at all...or can add the PK to it!
	var elText1 = document.getElementById("text1");//do not need this at all...or can add the PK to it!
                                    var elText2 = document.getElementById("text2");//do not need this at all...or can add the PK to it!
                                    var elText3 = document.getElementById("text3");//do not need this at all...or can add the PK to it!
        
	if (!elText.value) {
		alert("Input a text");
		elText.focus();
		return;
	}
	
	qrcode.makeCode(elText.value);//unique PK here
                                    qrcode1.makeCode(elText1.value);//unique PK here
                                    qrcode2.makeCode(elText2.value);//unique PK here
                                    qrcode3.makeCode(elText3.value);//unique PK here...
}

makeCode();

$("#text").
	on("blur", function () {
		makeCode();
	}).
	on("keydown", function (e) {
		if (e.keyCode === 13) {
			makeCode();
		}
	});
</script>
        
    </body>
</html>