package Beans;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author tetraPRO( )
 *                  : Software Wizard
 *                  : Grand-Master Philip Caputo
 *                : **Fullstack Engineer**
 */
public class BudgetExpense implements Serializable {
    
    private String account;
    private float budgetAmount;
    
    private final PropertyChangeSupport propertySupport;
    
    public BudgetExpense() {
        propertySupport = new PropertyChangeSupport(this);
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public float getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(float budgetAmountRatio) {
        this.budgetAmount = budgetAmountRatio;
    }
   
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
}
