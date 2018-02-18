
package ServerLogic;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tetraPRO( )
*                 : Software Wizard
*                   : Grand-Master Philip Caputo
*                  : **Fullstack Engineer**
*/
public class UI_Servlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            
            //date user account debit credit notes
            String date = request.getParameter("datepicker");
            String user = (String) session.getAttribute("user");
            SQL data = new SQL();
            if(user == null ){
                response.sendRedirect("login.jsp");
            }
            
            //get parameters from form
            String toAccount = request.getParameter("toAccount");
            String fromAccount = request.getParameter("fromAccount");
            float amount = Float.parseFloat(request.getParameter("amount"));
            String notes = request.getParameter("notes");
            
            
            //Convert string date into date object
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date today = format.parse(date);
            java.sql.Date sqlDate = new java.sql.Date(today.getTime());
            
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            //if the transaction is successful we will forward the request back to the index.jsp
            if(data.addTransaction(sqlDate, user, toAccount, amount, 0, notes)){
                if(data.addTransaction(sqlDate, user, fromAccount, 0, amount, notes)){
                     response.sendRedirect("index.jsp");
                }       
            }else{
                out.println("Sorry no dice! + <br>");
            }
        } catch (ParseException ex) {
            Logger.getLogger(UI_Servlet.class.getName()).log(Level.SEVERE, null, ex);
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
