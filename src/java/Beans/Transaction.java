package Beans;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tetraPRO( )
 *                  : Software Wizard
 *                  : Grand-Master Philip Caputo
 *                : **Fullstack Engineer**
 */
public class Transaction {
    private int ID;
    private Date date;
    private String toAccount;
    private String fromAccount;
    private float amount;
    private String notes;
    
    public Transaction(ResultSet rs) throws SQLException{
        this.ID = rs.getInt(1);
        this.date = rs.getDate(2);
        this.toAccount = rs.getString(3);
        this.fromAccount = rs.getString(4);
        this.amount = rs.getFloat(5);
        this.notes = rs.getString(6);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
}
