package service;

import model.*;

import java.util.List;

public class ExpenseService {

    //NOTE this being a utility method, it's declared static
    public static Expense createExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits, ExpenseMeta expenseMetadata){
        Expense expense =null;
        switch (expenseType){
            case EQUAL :
                expense = new EqualExpense(amount,paidBy,splits,expenseMetadata);
                break;
            case PERCENT:
                expense = new PercentExpense(amount,paidBy,splits,expenseMetadata);
                break;
            case EXACT:
                expense = new ExactExpense(amount,paidBy,splits,expenseMetadata);
                break;
            default:
                return null;

        }

        if(!expense.validate()){
            return null;
        }
        return expense;
    }
}
