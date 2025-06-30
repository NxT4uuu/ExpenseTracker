package expense;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExpenseTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Expenses> expen = ExpenseStorage.loadExpenses();

        while (true){
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. Update Expense");
            System.out.println("3. Delete Expense");
            System.out.println("4. View All Expenses");
            System.out.println("5. View Summary of All Expenses");
            System.out.println("6. View Summary of Expenses for a Specific Month");
            System.out.println("7. Exit");

            int choice = -1;
            while (true){
                System.out.println("Enter your Choice: ");
                String input = scanner.nextLine();
                try {
                    choice = Integer.parseInt(input);
                    if (choice >= 1 && choice <= 7){
                        break;
                    }else{
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                    }
                }catch (NumberFormatException e){
                    System.out.println("Invalid ");
                }
        }

            switch (choice){
                case 1:
                    addExpense(scanner, expen);
                    break;
                case 2:
                    updateExpense(scanner, expen);
                    break;
                case 3:
                    deleteExpense(scanner, expen);
                    break;
                case 4:
                    viewAllExpense(expen);
                    break;
                case 5:
                    viewSummary(expen);
                    break;
                case 6:
                    viewMonthlySummary(scanner, expen);
                    break;
                case 7:
                    ExpenseStorage.savedExpenses(expen);
                    System.out.println("Expenses saved.\nExiting....");
                    return;

            }
    }
}
    private static void addExpense(Scanner scanner, ArrayList<Expenses> expen){
        System.out.println("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.println("Enter description: ");
        String description = scanner.nextLine();
        double amount = -1;
        while (true){
            System.out.println("Enter amount: ");
            String amountInput = scanner.nextLine();
            try {
                amount = Double.parseDouble(amountInput);
                if (amount >= 0){
                    break;
                }else {
                    System.out.println("Amount cannot be negative. Please enter a valid amount.");
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid amount. Please enter a valid amount.");
            }
        }
        System.out.println("Enter Category: ");
        String category = scanner.nextLine();

        expen.add(new Expenses(date,description,category,amount));
        System.out.println("Expense added.");
    }

    private static void updateExpense(Scanner scanner, ArrayList<Expenses> expen){
        int index = -1;
        while (true){
            System.out.println("Enter the index of the expense to update: ");
            String indexInput = scanner.nextLine();
            try{
                index = Integer.parseInt(indexInput);
                if (index >= 0 && index < expen.size()){
                    break;
                }else {
                    System.out.println("Invalid index. Please enter a valid index.");
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid input.Please enter a valid number.");
            }
        }
        System.out.println("Enter the new date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.println("Enter new description: ");
        String description = scanner.nextLine();

        double amount = -1;
        while (true){
            System.out.println("Enter amount: ");
            String amountInput = scanner.nextLine();
            try {
                amount = Double.parseDouble(amountInput);
                if (amount >= 0){
                    break;
                }else {
                    System.out.println("Amount cannot be negative. Please enter a valid amount.");
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid amount. Please enter a valid amount.");
            }
        }
        System.out.println("Enter new Category: ");
        String category = scanner.nextLine();

        expen.set(index, new Expenses(date, description,category, amount));
        System.out.println("Expense updated.");
    }

    private static void deleteExpense(Scanner scanner, ArrayList<Expenses> expen){
        int index = -1;
        while (true){
            System.out.println("Enter the index of the expense to delete: ");
            String indexInput = scanner.nextLine();
            try {
                index = Integer.parseInt(indexInput);
                if (index >= 0 && index < expen.size()){
                    break;
                }
                else{
                    System.out.println("Invalid Index. Please enter a valid number.");
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        expen.remove(index);
        System.out.println("Expense deleted.");
    }

    private static void viewAllExpense(ArrayList<Expenses> expen){
        for (int i = 0; i < expen.size(); i++){
            System.out.println(i + ": "+ expen.get(i));
        }
    }

    private static void viewSummary(ArrayList<Expenses> expen){
        double total = 0;
        for (Expenses e: expen){
            total += e.getAmount();
        }
        System.out.println("Total expenses: "+total);
    }

    private static void viewMonthlySummary(Scanner scanner, ArrayList<Expenses> expen){
        String month = "";
        while (true){
            System.out.println("Enter month (MM): ");
            month = scanner.nextLine();
            if (month.matches("^(0[1-9]|[0-2])$")){
                break;
            }else{
                System.out.println("Invalid month format. Please enter a valid month (01-12).");
            }
        }

        double total = 0;
        for (Expenses e: expen){
            String date = e.getDate();
            if (date != null && date.length() >= 7){
                if (date.substring(5, 7).equals(month)){
                    total += e.getAmount();
                }
            }
        }
        System.out.println("Total expenses for month " + month + ": " + total);
    }

}
