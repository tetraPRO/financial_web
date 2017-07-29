/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerLogic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author capphil1
 */
@WebServlet(name = "Auth_Servlet", urlPatterns = {"/Auth_Servlet"})
public class Auth_Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PrintWriter out = response.getWriter();
        
        try {
             response.setContentType("text/html;charset=UTF-8");
             
             //database creditials
            String dataURL = "jdbc:mysql://localhost:3306/Financial_DB";
            String uName = "root";
            String uPass = "admin";
            
            //load driver so we can connect to database
            Class.forName("com.mysql.jdbc.Driver");
            
            //get database connection
            conn = DriverManager.getConnection(dataURL, uName, uPass);
            
            //build and execute sql
            String sql = "select* from user";
            stmt = conn.createStatement();
            rs =  stmt.executeQuery(sql);
            
            //get user email and password
            String uEmail = request.getParameter("email");
            String pass = request.getParameter("password");
            
            //loop through user list
            while(rs.next()){
                uEmail = rs.getString("Email");//column name
                pass = rs.getString("Password");//column name
                
                //test if user email and password is in database
                if(uEmail.equals(request.getParameter("email")) &&
                pass.equals(request.getParameter("password"))){//if it isforward to index
                    RequestDispatcher dispatcher =request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                    break;
                }else{//if it is not forward to login again
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                    dispatcher.forward(request, response);
                }
            }
            
            rs.close();
            stmt.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
           out.println("No dice! " + ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
