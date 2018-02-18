package ServerLogic;

import java.io.IOException;
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
public class Budget_Servlet extends HttpServlet {

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
        
        
            HttpSession session10 = request.getSession();
            String user = (String) session10.getAttribute("user");
            SQL data = (SQL) session10.getAttribute("data");
            
            if(user == null || data == null){
            response.sendRedirect("login.jsp");
        }
        
        //get parameters from the request object
        String taxes = request.getParameter("tax");
        String mortgageRent = request.getParameter("mortgage/rent");
        String insurance = request.getParameter("insurance");
        String vehicle = request.getParameter("vehicle");
        String gas = request.getParameter("gas");
        String foodGrocercies = request.getParameter("food/groceries");
        String entertainment = request.getParameter("entertainment");
        String cellPhones = request.getParameter("cell phones");
        String utilities = request.getParameter("utilities");
        String medical = request.getParameter("medical");
        String clothing = request.getParameter("clothing");
        String other = request.getParameter("other");
        String fees = request.getParameter("fees");
        String education = request.getParameter("education");
        
        float taxesBudget = Float.parseFloat(taxes);
        data.setBudgetValues("Tax", taxesBudget, user);
        
        float rentBudget = Float.parseFloat(mortgageRent);
        data.setBudgetValues("Mortgage/Rent", rentBudget, user);
        
        float insuranceBudget = Float.parseFloat(insurance);
        data.setBudgetValues("Insurance", insuranceBudget, user);
        
        float vehicleBudget = Float.parseFloat(vehicle);
        data.setBudgetValues("Vehcile", vehicleBudget, user);
        
        float gasBudget = Float.parseFloat(gas);
        data.setBudgetValues("Gas", gasBudget, user);
        
        float foodBudget = Float.parseFloat(foodGrocercies);
        data.setBudgetValues("Food/Groceries", foodBudget, user);
        
        float entertainmentBudget = Float.parseFloat(entertainment);
        data.setBudgetValues("Entertainment", entertainmentBudget, user);
        
        float cellphonesBudget = Float.parseFloat(cellPhones);
        data.setBudgetValues("Cell Phones", cellphonesBudget, user);
        
        float utilitiesBudget = Float.parseFloat(utilities);
        data.setBudgetValues("Utilities", utilitiesBudget, user);
        
        float medicalBudget = Float.parseFloat(medical);
        data.setBudgetValues("Medical", medicalBudget, user);
        
        float clothingBudget = Float.parseFloat(clothing);
        data.setBudgetValues("Clothing", clothingBudget, user);
        
        float otherBudget = Float.parseFloat(other);
        data.setBudgetValues("Other", otherBudget, user);
        
         float feesBudget = Float.parseFloat(fees);
        data.setBudgetValues("Fees", feesBudget, user);
        
        float educationBudget = Float.parseFloat(education);
        data.setBudgetValues("Education", educationBudget, user);
        
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
