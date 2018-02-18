package ServerLogic;

import Beans.BudgetExpense;
import Beans.Transaction;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
     *  fact a registered user.
     * @param email
     * @param pass
     * @return returns true if the user is already registered
     *  otherwise returns false.
     */
    public boolean verifyUser(String email, String pass){
        //if email or password is not null AND not empty --> proceed
        if((email != null && !email.isEmpty()) || pass != null && !pass.isEmpty()){
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
        }
        }else{//otherwise return false
            return false;
        }return false;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public boolean setUserDetails(String first, String last, String email, String password){
        
         if((first != null && !first.isEmpty()) || (last != null && !last.isEmpty()) ||
                 (email != null && !email.isEmpty()) || password != null && !password.isEmpty()){
              try{
            //build and execute sql
            String sql = "insert into user values (?,?,?,?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, email);
            pStmt.setString(2, password);
            pStmt.setString(3, first);
            pStmt.setString(4, last);
            pStmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(pStmt != null){
                    pStmt.close();
                } 
            }catch(SQLException ex){
                  Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }else{//otherwise return false
            return false;
        }return false;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  This method is used to add transactions to the database.
     * @param date
     * @param user
     * @param account
     * @param debit
     * @param credit
     * @param notes
     * @return returns true if the data is successfully added to
     *  the database otherwise returns false.
     */
    public boolean addTransaction(Date date, String user, String account, float debit, float credit, String notes){
        
        if(date == null || user == null || account == null || notes == null){
            return false;
        }else{
            try{
            //build and execute sql
            String sql = "insert into Ledger (Date, User, Account, Debit, Credit, Notes) values (?,?,?,?,?,?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setDate(1, date);
            pStmt.setString(2, user);
            pStmt.setString(3, account);
            pStmt.setFloat(4, debit);
            pStmt.setFloat(5, credit);
            pStmt.setString(6, notes);
            pStmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                try{
                    if(pStmt != null){
                        pStmt.close();
                    }
                }catch(SQLException ex){
                  Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }return false;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  This method is used to get the 
     *  transactions from the database
     *  and display them back on the page.
     * @param user
     * @return returns arraylist of transactions
     *  given as a string
     */
    public ArrayList<Transaction> getTransactions(String user){        
        ArrayList<Transaction> list = new ArrayList(); 
        try{
            //build and execute sql
            String sql = "select * from Ledger where user = '"+ user+"' and Date between  date_format(now() ,'%Y-%m-01') "
                    + "and now() order by ID desc";
            
           stmt = conn.createStatement();
            rs =  stmt.executeQuery(sql);
            
            while(rs.next()){
                //construct the model for data
                Transaction data = new Transaction();
                
                data.setID(rs.getInt(1));
                 data.setDate(rs.getDate(3));
                data.setAccount(rs.getString(4));
                data.setDebit(rs.getFloat(5));
                data.setCredit(rs.getFloat(6));
                data.setNotes(rs.getString(7));
                
                list.add(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{//clean up resources
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
        }return list;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  Returns the list of transactions for the given
     *  user and the given account for the given
     *  time scale.
     * @param user
     * @param account
     * @param timeScale 1 = Daily, 2 = Monthly, 3 = Yearly
     * @return 
     */
    public ArrayList<Transaction> getListOfTransactions(String user, String account,  int timeScale){
        ArrayList<Transaction> list = new ArrayList<>();
        try{
            //build and execute sql
            String sql = "select * from Ledger where user = '"+ user+"' and Date between  date_format(now() ,'%Y-%m-01') "
                    + "and last_day(now()) and Account = '" + account + "' order by ID desc";
            
           stmt = conn.createStatement();
            rs =  stmt.executeQuery(sql);
            
            while(rs.next()){
                //construct the model for data
                Transaction data = new Transaction();
                
                data.setDate(rs.getDate(3));
                data.setAccount(rs.getString(4));
                data.setDebit(rs.getFloat(5));
                data.setCredit(rs.getFloat(6));
                data.setNotes(rs.getString(7));
                
                list.add(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{//clean up resources
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
        }
        return list;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  This method sums all the expenses
     *  and returns the value as a string
     * @param user
     * @return string value of the sum
     *  of the expenses.
     */
    public String getSumExpenses(String user){
        float sumExpenses = 0;
        String sum = "";
         try{
            //build and execute sql
            String sql = "select sum(Debit) from Ledger where User = '" + user + "' and Notes != 'Starting balance'"
                    +" and Account != 'Bank' and Account != 'Cash' and Account != 'Bitcoin' and Account != 'SNAP'"
                    +" and Account != 'Silver' and Account != 'Gold' and Account != 'Vehicle(s)' and Account != 'Real Estate'"
                    +" and Account != 'Commodities' and Account != 'Stocks/Bonds'"
                    + " and Date between  date_format(now() ,'%Y-%m-01') and now()";
            
           stmt = conn.createStatement();
            rs =  stmt.executeQuery(sql);
            
            while(rs.next()){
                sumExpenses = rs.getFloat(1);
            }
            sum = String.format("%.2f", sumExpenses);
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             try{
                 if(stmt != null){
                     stmt.close();
                 }
                 if(rs != null){
                     rs.close();
                 }
             }catch (SQLException ex) {
                Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
             }
         }return sum;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * 
     * @param user
     * @param account
     * @param date1
     * @param date2
     * @return 
     */
    public String getSumSpentOnAccount(String user, String account, String date1, String date2){
        String amount = "";
        
        try{
            String sql = "SELECT SUM(Debit) FROM Ledger WHERE User = ? AND Account = ? BETWEEN ? and ?;";
            
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, user);
            pStmt.setString(2, account);
            pStmt.setString(3, date1);
            pStmt.setString(4, date2);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                amount = rs.getString(1);
            }return amount;
        }catch(SQLException ex){
           Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             try{
                 if(stmt != null){
                     stmt.close();
                 }
                 if(rs != null){
                     rs.close();
                 }
             }catch (SQLException ex) {
                Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
             }
        }return amount;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * 
     * @return 
     */
    public String getTotalBudget(){
        String total = "";
        float amount = 0;
        
        try{
            //build and execute sql
            String sql = "select sum(Amount) from budget";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                amount = rs.getFloat(1);
            }
            
            total = String.format("%.2f", amount);            
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
                if(rs != null){
                    rs.close();
                }
            }catch(SQLException ex){
              Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            }return total;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  This method is used to set the budget values
     * @param account string
     * @param amount float
     * @param user
     */
    public void setBudgetValues(String account, float amount, String user){
        
        if(account == null){
            //do nothing here
        }else{
            try{
            //build and execute sql
            String sql = "insert into budget (Account, Amount, User) values (?,?,?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, account);
            pStmt.setFloat(2, amount);
            pStmt.setString(3, user);
            pStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(pStmt != null){
                    pStmt.close();
                }
                if(stmt != null){
                    stmt.close();
                }
            }catch(SQLException ex){
              Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public ArrayList<BudgetExpense> getBudgetList(String user){
        ArrayList<BudgetExpense> list = new ArrayList<>();
        try{
            String sql = "select Account, Amount from budget where User = '" + user + "'";
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                BudgetExpense account = new BudgetExpense();
                
               account.setAccount(rs.getString(1));
               account.setBudgetAmount(rs.getFloat(2));
               
               list.add(account);
            }
        }catch(SQLException ex){
             Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
                if(rs != null){
                    rs.close();
                }
            }catch(SQLException ex){
             Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }return list;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  This method is used to get the desired account 
     *  and budgetted amount as a ratio of what has 
     *  been spent in the particular account thus far.
     * @param user
     * @param account
     * @return arraylist of type BudgetExpensRatio
     */
    public String getBudgetExpenseRatio(String user, String account){
       float budgetAmount = 0;
       float spentAmount  = 0;
       String ratio = "";
        try{
            String sqlBudget = "select (Amount) from budget where User = '" + user + "' and Account = '" + account + "'";
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlBudget);
            
            while(rs.next()){
               budgetAmount = rs.getFloat(1);
            }
            
            String sqlSumDebit = "select sum(Debit) from Ledger where User ='"  + user + "' and "
                    +"Account = '" + account + "' and "
                    + "Date between date_format(now() ,'%Y-%m-01') and now()";
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlSumDebit);
            
            while(rs.next()){
               spentAmount = rs.getFloat(1);
            }
            
             ratio = String.format("%.2f", spentAmount / budgetAmount);
        }catch(SQLException ex){
             Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
                if(rs != null){
                    rs.close();
                }
            }catch(SQLException ex){
             Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }return ratio;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  This method sums the Amount column from 
     *  the budget table.
     * @return 
     */
    public String getSumBudget(){
         float sumBudget = 0;
        String sum = "";
         try{
            //build and execute sql
            String sql = "select sum(Amount) from budget";
            
           stmt = conn.createStatement();
            rs =  stmt.executeQuery(sql);
            
            while(rs.next()){
                sumBudget = rs.getFloat(1);
            }
            sum = String.format("%.2f", sumBudget);
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }return sum;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * 
     * @param user
     * @param account
     * @param amount 
     */
    public void setStartingAssets(String user, String account, float amount){
         
        if(account == null){
            //do nothing here
        }else{
            try{
            //build and execute sql
            String sql = "insert into Ledger (User, Date, Account, Debit, Credit, Notes) values (?,now(),?,?,?,?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, user);
            pStmt.setString(2, account);//debit the debit account increases the account
            pStmt.setFloat(3, amount);
            pStmt.setFloat(4, 0);
            pStmt.setString(5, "Starting balance");
            pStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(pStmt != null){
                    pStmt.close();
                }
            }catch(SQLException ex){
              Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * 
     * @param user
     * @param account
     * @return 
     */
    public String getDebitBalance(String user, String account){
        float sumDebit = 0;
        String amount = "";
        
        if(account == null){
            //do nothing here
        }else{
            try{
            //build and execute sql
            String getSQLDebit = "select sum(Debit) from Ledger where  User = '" + user+"' and Account = '" + account + "'";
            
            stmt = conn.createStatement();
            rs =  stmt.executeQuery(getSQLDebit);
            
            while(rs.next()){
                sumDebit = rs.getFloat(1);
            }
            amount = String.format("%.2f", sumDebit);
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
                if(rs != null){
                    rs.close();
                }
            }catch(SQLException ex){
              Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }return amount;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * 
     * @param user
     * @param account
     * @return 
     */
    public String getCreditBalance(String user, String account){
        float sumCredit = 0;
        String amount = "";
        
        if(account == null){
            //do nothing here
        }else{
            try{
            //build and execute sql
            String getSQLCredit = "select sum(Credit) from Ledger where  User = '" + user+"' and Account = '" + account + "'";
            
            stmt = conn.createStatement();
            rs =  stmt.executeQuery(getSQLCredit);
            
            while(rs.next()){
                sumCredit = rs.getFloat(1);
            }
            amount = String.format("%.2f", sumCredit);
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
                if(rs != null){
                    rs.close();
                }
            }catch(SQLException ex){
              Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }return amount;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * 
     * @param user
     * @param account
     * @return 
     */
    public String getAsset(String user, String account){
        float sumDebit = 0;
        float sumCredit = 0;
        float balance = 0;
        String amount = "";
        
        if(account == null){
            //do nothing here
        }else{
            try{
            //build and execute sql
            String getSQLDebit = "select sum(Debit) from Ledger where  User = '" + user+"' and Account = '" + account + "'";
            
            stmt = conn.createStatement();
            rs =  stmt.executeQuery(getSQLDebit);
            
            while(rs.next()){
                sumDebit = rs.getFloat(1);
            }
            
            String getSQLCredit = "select sum(Credit) from Ledger where  User = '" + user+"' and Account = '" + account + "'";
            
            stmt = conn.createStatement();
            rs =  stmt.executeQuery(getSQLCredit);
            
            while(rs.next()){
                sumCredit = rs.getFloat(1);
            }
            
            balance = sumDebit - sumCredit;
            amount = String.format("%.2f", balance);
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
                if(rs != null){
                    rs.close();
                }
            }catch(SQLException ex){
              Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }return amount;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * 
     * @param user
     * @param account
     * @param amount 
     */
    public void setStartingLiablities(String user, String account, float amount){
         if(account == null){
            //do nothing here
        }else{
            try{
            //build and execute sql
            String sql = "insert into Ledger (User, Date, Account, Debit, Credit, Notes) values (?,now(),?,?,?,?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, user);
            pStmt.setString(2, account);
            pStmt.setFloat(3, 0);
            pStmt.setFloat(4, amount);
            pStmt.setString(5, "Starting balance");
            pStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(pStmt != null){
                    pStmt.close();
                }
            }catch(SQLException ex){
              Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void getLiability(String user, String account){
    
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public boolean setTransfer(String user, String date, String from, String to, float amount, String notes){
       
        try{
            
            //Convert string date into date object
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date today = format.parse(date);
            java.sql.Date sqlDate = new java.sql.Date(today.getTime());
            
            String sqlFrom = "insert into Ledger (User, Date, Account, Debit, Credit, Notes) values (?,?,?,?,?,?)";
            
            pStmt = conn.prepareStatement(sqlFrom);
            pStmt.setString(1, user);
            pStmt.setDate(2, sqlDate);
            pStmt.setString(3, from);
            pStmt.setFloat(4, 0);
            pStmt.setFloat(5, amount);
            pStmt.setString(6, "Transfer");
            pStmt.execute();
            
             String sqlTo = "insert into Ledger (User, Date, Account, Debit, Credit, Notes) values (?,?,?,?,?,?)";
            
            pStmt = conn.prepareStatement(sqlTo);
            pStmt.setString(1, user);
            pStmt.setDate(2, sqlDate);
            pStmt.setString(3, to);
            pStmt.setFloat(4, amount);
            pStmt.setFloat(5, 0);
            pStmt.setString(6, notes);
            pStmt.execute();
            
            return true;
        }catch(SQLException | ParseException ex){
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             try{
                if(pStmt != null){
                    pStmt.close();
                }
            }catch(SQLException ex){
              Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }return false;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public String getEarnedIncome(String user){
        float paychecks = 0;
        float tips = 0;
        float total = 0;
        String amount = "";
        
            try{
            //build and execute sql
            String sqlPaycheck = "select sum(Debit) from Ledger where Notes = 'Paycheck' and User = '" + user + "'"
                    + " and Date between  date_format(now() ,'%Y-%m-01') and now()";
            
            stmt = conn.createStatement();
            rs =  stmt.executeQuery(sqlPaycheck);
            
            while(rs.next()){
                paychecks = rs.getFloat(1);
            }
            
            String sqlTips = "select sum(Debit) from Ledger where Notes = 'Tips' and User = '" + user + "' "
                    + " and Date between  date_format(now() ,'%Y-%m-01') and now()";
            
            stmt = conn.createStatement();
            rs =  stmt.executeQuery(sqlTips);
            
            while(rs.next()){
                tips = rs.getFloat(1);
            }
            
            total = paychecks + tips;
            amount = String.format("%.2f", total);
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
                if(rs != null){
                    rs.close();
                }
            }catch(SQLException ex){
              Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            }return amount;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public String getBitcoin(String user){
        BigDecimal total = BigDecimal.ZERO;
        
        try{
            
            String sql = "SELECT Notes FROM Ledger where User = '" + user + "' and Account = 'Bitcoin' "
                    + "and Notes != 'Starting balance'";
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                BigDecimal bit = new BigDecimal(rs.getString("Notes"));
                total = total.add(bit);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
                if(rs != null){
                    rs.close();
                }
            }catch(SQLException ex){
              Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            }return total.toString();
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
