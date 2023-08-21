public class Transaction {
    public String title;
    public boolean isExpense;
    public int quantity;
    public int unitPrice;

    public Transaction(String title, boolean is_expense, int quantity, int unitPrice) {
        this.title = title;
        this.isExpense = is_expense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}