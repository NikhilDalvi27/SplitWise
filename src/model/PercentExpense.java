package model;

import java.util.List;

public class PercentExpense extends Expense{

    public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseMeta expenseMeta) {
        super(amount, paidBy, splits, expenseMeta);
        for (Split split : splits) {
            PercentSplit percentSplit = (PercentSplit) split;
            split.setAmount((amount*percentSplit.getPercent())/100.0);
        }
        setSplits(splits);
    }

    @Override
    public boolean validate() {
        for(Split split : getSplits()){
            if(!(split instanceof PercentSplit)) {
                return false;
            }
        }

        double totalPercent = 100;
        double totalSplitPercent  = 0.0;

        for(Split split : getSplits()){
            PercentSplit percentSplit = (PercentSplit) split;
            totalSplitPercent += percentSplit.getPercent();
        }

        if(totalPercent != totalSplitPercent){
            return false;
        }

        return true;
    }
}
