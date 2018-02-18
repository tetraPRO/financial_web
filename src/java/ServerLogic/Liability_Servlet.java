
package ServerLogic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 *@Author     : tetraPRO( )
 *                   : Software Wizard
 *                 : Grand-Master_Philip Caputo
 *                 : **Fullstack Engineer**
 */
public class Liability_Servlet extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        SQL data = (SQL) session.getAttribute("data");
        
        if(user == null || data == null){
            response.sendRedirect("login.jsp");
        }
        
        String realEstate =request.getParameter("realEstate");
        String vehicles = request.getParameter("vehicles");
        String studentDebt = request.getParameter("studentDebt");
        String creditCards = request.getParameter("creditCards");
        String personal = request.getParameter("personal");
        String business = request.getParameter("business");
        
        float re = Float.parseFloat(realEstate);
        data.setStartingLiablities(user, "Real Estate", re);
        
        float vehicle = Float.parseFloat(vehicles);
        data.setStartingLiablities(user, "Vehicle(s)", vehicle);
        
        float student = Float.parseFloat(studentDebt);
        data.setStartingLiablities(user, "Student Debt", student);
        
        float cc = Float.parseFloat(creditCards);
        data.setStartingLiablities(user, "Credit Cards", cc);
        
        float person = Float.parseFloat(personal);
        data.setStartingLiablities(user, "Personal Loans", person);
        
        float biz = Float.parseFloat(business);
        data.setStartingLiablities(user, "Business", biz);
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
