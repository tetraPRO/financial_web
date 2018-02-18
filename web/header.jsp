<%-- 
    Document   : header
    Created on : Sep 16, 2017, 6:04:16 AM
    Author     : tetraPRO( )
                     : Software Wizard
                    : Grand-Master_Philip Caputo
                  : **Fullstack Engineer**
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="CSS/dropdownMenu.css"/>
        <style>
            /* This is the 'tetraPRO() Financial' heading*/
            #_1heading{
                margin:  0%;
                margin-top: 5px;
            }
            
            /* This is the color and margin settings
                tag for the scrolling marquee text.*/
            #goalText{
                margin-top: 5%;
                color: #ffffff;
            }
        </style>        
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
        <!--Click dropdown menu-->
        <nav class="container">
            <ul>
              <li class="dropdown">
                  <a href="javascript:void(0)" class="dropbtn">File</a>
                  <div class="dropdown-content">
                      <a href="index.jsp">Dashboard</a>
                      <a href="paperwallet.jsp">Paper Wallet</a>
                      <a  id="modal_open">Budget</a>
                      <a id="open_income">Add Income</a>
                      <a  id="open_assets">Starting Assets</a>
                      <a  id="open_liabilities">Starting Liabilities</a>
                  </div>
              </li>
            <li class="dropdown">
                  <a href="javascript:void(0)" class="dropbtn">Edit</a>
                  <div class="dropdown-content">
                      <a  id="">Edit</a>
                      <a id="">Delete</a>
                      <a id="">Add Expense Account</a>
                      <a  id="open_transfer">Transfer</a>
                  </div>
              </li>
              <li class="dropdown">
                  <a href="javascript:void(0)" class="dropbtn">View</a>
                  <div class="dropdown-content">
                      <a  id="open_viewIncome">Income</a>
                      <a href="displayBTCgraph.jsp">Bitcoin Graph</a>
                      <a id="view_total_budget_open">Total Budget</a>
                      <a id="open_view_budget">Budget Expenses</a>
                  </div>
              </li>
              <li class="dropdown">
                  <a href="javascript:void(0)" class="dropbtn">Analysis</a>
                  <div class="dropdown-content">
                      <a  href="analysis.jsp">Details</a>
                  </div>
              </li>
                <li class="dropdown">
                  <a href="javascript:void(0)" class="dropbtn">Goals</a>
                  <div class="dropdown-content">
                      <a  id="income_goal_open">Income</a>
                      <a id="assets_goal_open">Assets</a>
                      <a id="debts_goal_open">Debts</a>
                      <a href="travel.jsp">Travel</a>
                      <a href="#">Goal Card</a><!--used to set the scrolling text across top-->
                  </div>
              </li>
                <li class="dropdown">
                  <a href="javascript:void(0)" class="dropbtn">Opportunities</a>
                  <div class="dropdown-content">
                      <a  href="income.jsp">Income</a>
                      <a  id="realEstate_open">Real Estate</a>
                      <a id="options_open">Options</a>
                      <a id="bitcoin_open">Bitcoin</a>
                  </div>
              </li>
              <li class="dropdown">
                  <a href="javascript:void(0)" class="dropbtn">Training</a>
                  <div class="dropdown-content">
                      <a  href="https://cardoneuniversity.com/">Sales Training</a>
                      <a  href="https://lesbrowninstitute.com/">Leadership</a>
                  </div>
              </li>
              <li>
                  <marquee behavior="scroll" direction="left" id="goalText" width="100%">I AM AWESOME!</marquee>
              </li>
            </ul>
        </nav>
        <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
            <center>
                <h1 id="_1heading">tetraPRO( )Financial</h1>
                 <data id="time_stamp"></data>
            </center>
        
        <script>
            function updateTime(){
                var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
                var days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
                
                var currentTime = new Date();
                var day = days[currentTime.getDay()];
                var dayOfMonth = currentTime.getDate();
                var month = months[currentTime.getMonth()];
                
                var year = currentTime.getUTCFullYear();
                var hours = currentTime.getHours();
                var minutes = currentTime.getMinutes();
                if (minutes < 10){
                    minutes = "0" + minutes;
                }
                var t_str = hours + ":" + minutes;
                if(hours > 11){
                    t_str += "PM " + day + " " + " Day "+ dayOfMonth +" of " + month + " " + year;
                } else {
                    t_str += "AM " + day + " " + " Day "+ dayOfMonth +" of " + month + " " + year;
                }
                document.getElementById('time_stamp').innerHTML = t_str;
            }
            setInterval(updateTime, 1000);
        </script>
        