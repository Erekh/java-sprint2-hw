public class YearTransaction {
    public int  month;
    public int amount;
    public boolean isExpense;

    public YearTransaction(int  month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
