<%-- 
    Document   : Display
    Created on : Jul 30, 2017, 12:58:12 PM
    Author     : tetraPRO( )
                     : Software Wizard
                    : Grand-Master_Philip Caputo
                  : **Fullstack Engineer**
--%>

<%@page import="Beans.Transaction"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="ServerLogic.SQL"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <link rel="stylesheet" type="text/css" href="CSS/styles.css"/>

<%
        String dataURL = "jdbc:mysql://localhost:3306/Financial_DB";
        String uName_DB = "root";
        String uPass_DB = "admin";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
          try {
            //load driver so we can connect to database
            Class.forName("com.mysql.jdbc.Driver");
            
            //get database connection
            conn = DriverManager.getConnection(dataURL, uName_DB, uPass_DB);
            
             //build and execute sql
            String sql = "select * from ledger order by ID desc";
            
           stmt = conn.createStatement();
            rs =  stmt.executeQuery(sql);
            
        } catch (ClassNotFoundException  ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
             Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
          %>
          
<table border="0">
            <tr>
                <td class="display_Headers">ID</td>
                <td class="display_Headers">Date</td>
                <td class="display_Headers">To</td> 
                <td class="display_Headers">From</td>
                <td class="display_Headers">Amount</td>
                <td class="display_Headers">Notes</td>
            </tr>
            <% while(rs.next()){ %>
            <tr class="displayData">
                <td class="display_Data"><%= rs.getInt("ID")%></td>
                <td class="display_Data"><%= rs.getString("Date")%></td>
                <td class="display_Data"><%= rs.getString("To_Account")%></td>
                <td class="display_Data"><%= rs.getString("From_Account")%></td>
                <td class="display_Data"><%= rs.getFloat("Amount")%></td>
                <td class="display_Data"><%= rs.getString("Notes")%></td>
            </tr>
            <% } %>
        </table>
