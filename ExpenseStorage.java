package expense;

import java.io.*;
import java.util.ArrayList;

public class ExpenseStorage {
    private static final String FILE_NAME = "exp.txt";

    public static void savedExpenses(ArrayList<Expenses> expen){
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))){
            for (Expenses e: expen){
                writer.println(e.getDate() +","+e.getDescription()+","+e.getAmount()+","+e.getCategory());
            }
        }catch (IOException e){
            System.out.println("Error saving Expenses: " + e.getMessage());
        }
    }

    public static ArrayList<Expenses> loadExpenses(){
        ArrayList<Expenses> expen = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                expen.add(new Expenses(parts[0], parts[1], parts[3], Double.parseDouble(parts[2])));
            }
        }catch (IOException e){
            System.out.println("Error Loading Expenses...." + e.getMessage());
        }
        return expen;
    }
}
