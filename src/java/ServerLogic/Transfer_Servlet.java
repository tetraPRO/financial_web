package ServerLogic;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *@Author     : tetraPRO( )
 *                   : Software Wizard
 *                  : Grand-Master_Philip Caputo
 *                : **Fullstack Engineer**
 */
public class Transfer_Servlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session9 = request.getSession();
        String user = (String) session9.getAttribute("user");
        SQL data = (SQL)session9.getAttribute("data");
        
        if(user == null || data == null){
            response.sendRedirect("login.jsp");
        }
        
        
        try (PrintWriter out = response.getWriter()) {
            
            String date = request.getParameter("date");
            String fromAccount = request.getParameter("from");
            String toAccount = request.getParameter("to");
            float amount = Float.parseFloat(request.getParameter("amount"));
            String notes = request.getParameter("notes");
            
             if(data.setTransfer(user, date, fromAccount, toAccount, amount, notes)){
                    request.getRequestDispatcher("index.jsp").forward(request, response);  
                }
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
