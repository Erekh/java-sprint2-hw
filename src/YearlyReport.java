import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    FileReader fileReader = new FileReader();

    public ArrayList<YearTransaction> yearTransactions = new ArrayList<>();
    public HashMap<Integer, Integer> profitOrders = new HashMap<>();
    public HashMap<Integer, Integer> spendOrders = new HashMap<>();

    public void loadFile(String path){
        ArrayList<String> content = fileReader.readFileContents(path);
        String contentToString = String.join("\n", content);
        String[] lines = contentToString.split("\n");

        for (int i = 1; i < lines.length; i++) { //Метод считывает годовой отчет, приводит его значения к соответствующим типам и определяет их в хэш таблицу
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense= Boolean.parseBoolean(parts[2]);
            YearTransaction yearTransaction = new YearTransaction(month, amount, isExpense);
            yearTransactions.add(yearTransaction);

            if(yearTransaction.isExpense){
                spendOrders.put(yearTransaction.month, yearTransaction.amount);
            } else{
                profitOrders.put(yearTransaction.month, yearTransaction.amount);
            }
        }
        System.out.println("Годовой отчет считан.");
    }

    void getYearGain(){ // Прибыль по каждому месяцу
        HashMap<Integer, Integer> gainByMonth = new HashMap<>();
        for (YearTransaction month : yearTransactions) {
            if(!month.isExpense){
                gainByMonth.put(month.month, gainByMonth.getOrDefault(month.month, 0) + month.amount);
            } else {
                gainByMonth.put(month.month, gainByMonth.getOrDefault(month.month, 0) - month.amount);
            }
        }

        for (Integer month : gainByMonth.keySet()) {
            if(month == 1){
                System.out.println("Прибыль за Январь: " + gainByMonth.get(month));
            } else if(month == 2){
                System.out.println("Прибыль за Февраль: " + gainByMonth.get(month));
            } else if(month == 3){
                System.out.println("Прибыль за Март: " + gainByMonth.get(month));
            }
        }
    }

    void getAvgYearExpense(){ // Средний расход за все операции
        int count = 0;
        int sum = 0;
        for (YearTransaction month : yearTransactions) {
            if(month.isExpense){
                count++;
                sum += month.amount;
            }
        }
        System.out.println("Среднее число трат за год: " + sum/count);
    }

    void getAvgYearIncome(){ // Средний доход за все операции
        int count = 0;
        int sum = 0;
        for (YearTransaction month : yearTransactions) {
            if (!month.isExpense){
                count++;
                sum += month.amount;
            }
        }
        System.out.println("Средняя прибыль за год: " + sum/count);
    }

    void printYearStat(){ //Печать годовых отчетов
        if(yearTransactions.size() == 0) {
            System.out.println("Ошибка! Для начала считайте файлы.");
        } else {
            getYearGain();
            getAvgYearExpense();
            getAvgYearIncome();
        }
    }
}

