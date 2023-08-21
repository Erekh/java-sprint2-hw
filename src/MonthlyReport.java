import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    FileReader fileReader = new FileReader();

    ArrayList<Transaction> transactions = new ArrayList<>();                // Все операции трат и расходов
    HashMap<Integer, ArrayList<Transaction>> monthTransactions = new HashMap<>();   // Операции трат и расходов по месяцам
    HashMap<Integer, Integer> profitOrders = new HashMap<>();                // Доходы
    HashMap<Integer, Integer> spendOrders = new HashMap<>();                 // Расходы

    public void loadFile() { // Метод считывает 3 файла отчета по месяцам, приводит значения файла к соответствующим типам и определяет их в хэш таблицу
        for (int month = 1; month <= 3; month++) {
            transactions = new ArrayList<>();
            String fileName =  "m.20210" + month + ".csv";
            ArrayList<String> content = fileReader.readFileContents(fileName);
            String contentToParts = String.join("\n", content);
            String[] lines = contentToParts.split("\n");
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                String[] parts = line.split(",");
                String title = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                int unitPrice = Integer.parseInt(parts[3]);
                Transaction transaction = new Transaction(title, isExpense, quantity, unitPrice);
                transactions.add(transaction);
            }
            monthTransactions.put(month, transactions);
            System.out.println("Данные файла '" + fileName + "' считаны.");
        }
    }

    void getMaxProfitOrder(Integer month){ //Поиск максимального дохода по месяцам
        int maxOrder = 0;
        String maxOrderTitle = null;
        if(monthTransactions.containsKey(month)){
            ArrayList<Transaction> freqs = monthTransactions.get(month);
            for (Transaction freq : freqs) {
                if (!freq.isExpense) {
                    int order = freq.quantity * freq.unitPrice;
                    if (order > maxOrder) {
                        maxOrder = order;
                        maxOrderTitle = freq.title;
                    }
                }
            }
        }
        System.out.println("Самый прибыльный товар - " + maxOrderTitle + " и его стоимость: " + maxOrder);
    }

    void getMaxSpendOrder(Integer month){ //Поиск максимальной траты
        int maxSpend = 0;
        String maxSpendTitle = null;
        if(monthTransactions.containsKey(month)){
            ArrayList<Transaction> freqs = monthTransactions.get(month);
            for (Transaction freq : freqs) {
                if(freq.isExpense){
                    int spend = freq.quantity * freq.unitPrice;
                    if(spend > maxSpend){
                        maxSpend = spend;
                        maxSpendTitle = freq.title;
                    }
                }
            }
        }
        System.out.println("Самая большая трата - " + maxSpendTitle + " и сумма траты: " + maxSpend);
    }

    void getIncomeAndSpend(){  //Метод определяет расходы и доходы по разным хэш таблицам
        int income = 0;
        int spend = 0;
        for (Integer month : monthTransactions.keySet()) {
            ArrayList<Transaction> transaction = monthTransactions.get(month);
            for (Transaction sale : transaction) {
                int sum = sale.quantity * sale.unitPrice;
                if(!sale.isExpense){
                    income += sum;
                    profitOrders.put(month, income);
                } else {
                    spend += sum;
                    spendOrders.put(month, spend);
                }
            }
            income = 0;
            spend = 0;
        }
    }

    void getMontyReport(){ //Вывод информации о доходах и расходах по месяцам
        if(monthTransactions.size() == 0){
            System.out.println("Ошибка! Для начала считайте файлы.");
        }
        for (Integer month : monthTransactions.keySet()) {
            if (month == 1){
                System.out.println("Январь");
                getMaxProfitOrder(month);
                getMaxSpendOrder(month);
            } else if (month == 2) {
                System.out.println("Февраль");
                getMaxProfitOrder(month);
                getMaxSpendOrder(month);
            } else if (month == 3) {
                System.out.println("Март");
                getMaxProfitOrder(month);
                getMaxSpendOrder(month);
            } else {
                System.out.println("Такого месяца еще нет");
            }
        }
    }
}
