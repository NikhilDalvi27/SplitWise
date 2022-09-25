package model;

import java.util.List;

public class ExactExpense extends Expense{
    public ExactExpense(double amount, User paidBy, List<Split> splits, ExpenseMeta expenseMeta) {
        super(amount, paidBy, splits, expenseMeta);
    }

    @Override
    public boolean validate() {
        for(Split split : getSplits()){
            if(!(split instanceof ExactSplit)){
                return false;
            }
        }

        double totalAmount = getAmount();
        double totalSplitAmount = 0.0;
        for( Split split : getSplits()){
            totalSplitAmount += split.getAmount();
        }
        if(totalSplitAmount!=totalAmount){
            return false;
        }
        return true;
    }
}
