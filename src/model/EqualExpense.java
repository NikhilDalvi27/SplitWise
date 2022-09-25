package model;

import java.util.List;

public class EqualExpense extends Expense{
    public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseMeta expenseMeta) {
        super(amount, paidBy, splits, expenseMeta);
        int totalSplits = splits.size();
        double splitAmount = amount/(totalSplits*1.0);
        for (Split split : splits) {
            split.setAmount(splitAmount);
        }
        splits.get(0).setAmount(splitAmount + (amount - totalSplits*splitAmount));
        setSplits(splits);
    }

    @Override
    public boolean validate() {
       for(Split split : getSplits()){
           if(!(split instanceof EqualSplit)){
               return false;
           }
       }
       return true;
    }
}
