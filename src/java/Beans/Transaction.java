package Beans;

import java.sql.Date;

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
    private String user;
    private String account;
    private float debit;
    private float credit;
    private String notes;
    
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public float getDebit() {
        return debit;
    }

    public void setDebit(float debt) {
        this.debit = debt;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
}
