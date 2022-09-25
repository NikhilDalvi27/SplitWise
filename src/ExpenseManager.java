import model.*;
import service.ExpenseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {
    List<Expense> expenses;
    Map<String, User> userMap;
    Map<String,Map<String,Double>> balanceSheet;

    public ExpenseManager() {
        expenses = new ArrayList<>();
        userMap = new HashMap<>();
        balanceSheet = new HashMap<>();
    }

    public void addUser(User user){
        userMap.put(user.getId(),user);
        balanceSheet.put(user.getId(),new HashMap<>());
    }

    public void addExpense(ExpenseType expenseType, double amount, String paidBy, List<Split>splits, ExpenseMeta expenseMeta){
        Expense expense = ExpenseService.createExpense(expenseType,amount,userMap.get(paidBy),splits,expenseMeta);
        expenses.add(expense);

        for(Split split : expense.getSplits()){
            String paidFor = split.getUser().getId();

            HashMap<String,Double> balances = (HashMap<String, Double>) balanceSheet.get(paidBy);
            if(!balances.containsKey(paidFor)){
                balances.put(paidFor,0.0);
            }
            updateBalance(balances.get(paidFor) + split.getAmount(), paidFor, balances);


            balances = (HashMap<String, Double>) balanceSheet.get(paidFor);
            if(!balances.containsKey(paidBy)){
                balances.put(paidBy,0.0);
            }
            updateBalance(balances.get(paidBy)-split.getAmount(),paidBy,balances);
        }
    }

    private static void updateBalance(Double newAmount, String userId, HashMap<String, Double> balances) {
        if(newAmount!=0) {
            balances.put(userId, newAmount);
        }else{
            balances.remove(userId);
        }
    }

    public void showBalance(String userId){
        boolean isEmpty = true;

        for(Map.Entry<String, Double> userBalance:balanceSheet.get(userId).entrySet()){
            if(userBalance.getValue()!=0) {
                printBalance(userId, userBalance.getKey(), userBalance.getValue());
                isEmpty = false;
            }
        }

        if(isEmpty){
            System.out.println("No Balances");
        }
    }

    public void showBalances(){
        boolean isEmpty = true;

        for(Map.Entry<String,Map<String,Double>> allBalances : balanceSheet.entrySet()) {
            for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                if (userBalance.getValue() > 0) {
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                    isEmpty = false;
                }
            }
        }

        if(isEmpty){
            System.out.println("No Balances");
        }
    }

    private void printBalance(String paidBy, String paidFor, Double value) {
        if(value>0){
            System.out.println(paidFor+" owes "+ paidBy+" "+value);
        }else{
            System.out.println(paidBy+" owes "+ paidFor+" "+Math.abs(value));
        }
        System.out.println("=====================");
    }

}
