package expense.src;

public class Expenses {
    private String date;
    private String description;
    private String category;
    private double amount;

    public Expenses(String date, String description, String category, double amount){
        this.date = date;
        this.description = description;
        this.category = category;
        this.amount = amount;
    }
    public String getDate(){
        return date;
    }
    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}
