package ServerLogic;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tetraPRO( )
 *                  : Software Wizard
 *                  : Grand-Master Philip Caputo
 *                : **Fullstack Engineer**
 */
public class Income_Servlet extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            HttpSession session7 = request.getSession();
            String user = (String) session7.getAttribute("user");
            SQL data = (SQL) session7.getAttribute("data");
            
            if(user == null || data == null){
            response.sendRedirect("login.jsp");
        }
            
            String date = request.getParameter("datepicker");
            String account = request.getParameter("account");
            float amount = Float.parseFloat(request.getParameter("amount"));
            float tax = Float.parseFloat(request.getParameter("tax"));
            String notes = request.getParameter("notes");
            
            //Convert string date into date object
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date today = format.parse(date);
            java.sql.Date sqlDate = new java.sql.Date(today.getTime());
            
            if(data.addTransaction(sqlDate, user, account, amount, 0, notes)){
                if(data.addTransaction(sqlDate, user, account, 0, tax, "Tax")){
                    request.getRequestDispatcher("index.jsp").forward(request, response);  
                }
            }else{
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                     dispatcher.forward(request, response);
                }
        } catch (ParseException ex) {
            Logger.getLogger(Income_Servlet.class.getName()).log(Level.SEVERE, null, ex);
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
