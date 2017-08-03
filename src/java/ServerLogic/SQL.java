package ServerLogic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tetraPRO( )
 *                  : Software Wizard
 *                  : Grand-Master Philip Caputo
 *                : **Fullstack Engineer**
 */
public class SQL {
     //database creditials
    String dataURL = "jdbc:mysql://localhost:3306/Financial_DB";
    String uName_DB = "root";
    String uPass_DB = "admin";
        
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Database constructor. Builds the connection
     *  object to the database that the other methods
     *  will use to query and update the database.
     */
    public SQL(){
        try {
            //load driver so we can connect to database
            Class.forName("com.mysql.jdbc.Driver");
            
            //get database connection
            conn = DriverManager.getConnection(dataURL, uName_DB, uPass_DB);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  This method is used to verify if the user is in 
     *  fact a register user.
     * @param email
     * @param pass
     * @return returns true if the user is already registered
     *  otherwise returns false.
     */
    public boolean verifyUser(String email, String pass){
        try{
            //build and execute sql
            String sql = "select* from user";
            stmt = conn.createStatement();
            rs =  stmt.executeQuery(sql);
            
            //loop through user list
            while(rs.next()){
                String uEmail = rs.getString("Email");//column name
                String uPass = rs.getString("Password");//column name
                //test if user email and password is in database
                return uEmail.equals(email) &&
                        uPass.equals(pass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            } 
            }catch(SQLException ex){
                  Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }return false;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  This method is used to add transactions to the database.
     * @param date
     * @param toAccount
     * @param fromAccount
     * @param amount
     * @param notes
     * @return returns true if the data is successfully added to
     *  the database otherwise returns false.
     */
    public boolean addTransaction(Date date, String toAccount, String fromAccount, String amount, String notes){
        
        if(date == null || toAccount == null || fromAccount == null || 
                amount == null || notes == null){
            return false;
        }else{
            try{
            //build and execute sql
            String sql = "insert into ledger (Date, To_Account, From_Account, Amount, Notes) values (?,?,?,?,?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setDate(1, date);
            pStmt.setString(2, toAccount);
            pStmt.setString(3, fromAccount);
            pStmt.setString(4, amount);
            pStmt.setString(5, notes);
            pStmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        }return false;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  This method is used to get the 
     *  transactions from the database
     *  and display them back on the page.
     * @return returns true if successful query
     *  otherwise returns falase.
     */
    public ResultSet getTransactions(){        
         try{
            //build and execute sql
            String sql = "select * from ledger order by ID  desc";
            
           stmt = conn.createStatement();
            rs =  stmt.executeQuery(sql);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }return rs;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public String getSumExpenses(){
        float sumExpenses = 0;
        String sum = "";
         try{
            //build and execute sql
            String sql = "select sum(Amount) from ledger";
            
           stmt = conn.createStatement();
            rs =  stmt.executeQuery(sql);
            
            while(rs.next()){
                sumExpenses = rs.getFloat(1);
            }
            sum = String.format("%.2f", sumExpenses);
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }return sum;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
