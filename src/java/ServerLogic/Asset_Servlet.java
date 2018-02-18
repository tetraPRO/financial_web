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
public class Asset_Servlet extends HttpServlet {

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
        SQL data = new SQL();
        if(user == null ){
            response.sendRedirect("login.jsp");
        }
        
        //get parameters from the request object
        String bank = request.getParameter("bank");
        String cash = request.getParameter("cash");
        String snap = request.getParameter("snap");
        String bitcoin = request.getParameter("bitcoin");
        String silver = request.getParameter("silver");
        String gold = request.getParameter("gold");
        String realEstate = request.getParameter("real_estate");
        String commodities = request.getParameter("commodities");
        String stocksBonds = request.getParameter("stocks_bonds");
        String business = request.getParameter("busniness");
        
        float startingBank = Float.parseFloat(bank);
        data.setStartingAssets(user, "Bank", startingBank);
        
        float startingCash= Float.parseFloat(cash);
        data.setStartingAssets(user, "Cash", startingCash);
        
        float startingSnap = Float.parseFloat(snap);
        data.setStartingAssets(user, "Snap", startingSnap);
        
        float startingBitcoin = Float.parseFloat(bitcoin);
        data.setStartingAssets(user, "Bitcoin", startingBitcoin);
        
        float startingSilver = Float.parseFloat(silver);
        data.setStartingAssets(user, "Silver", startingSilver);
        
        float startingGold = Float.parseFloat(gold);
        data.setStartingAssets(user, "Gold", startingGold);
        
        float startingRE = Float.parseFloat(realEstate);
        data.setStartingAssets(user, "Real Estate", startingRE);
        
        float startingCommodities = Float.parseFloat(commodities);
        data.setStartingAssets(user, "Commodities", startingCommodities);
        
        float startingStocksBonds = Float.parseFloat(stocksBonds);
        data.setStartingAssets(user, "Stocks/Bonds", startingStocksBonds);
        
        float startingBusiness = Float.parseFloat(business);
        data.setStartingAssets(user, "Business", startingBusiness);
        
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
