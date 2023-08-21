import java.util.HashMap;

public class Checker {
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;

    HashMap<Integer, Integer> checkerProfit = new HashMap<>();
    HashMap<Integer, Integer> checkerSpent = new HashMap<>();

    public Checker (MonthlyReport monthlyReport, YearlyReport yearlyReport){
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

    public void check(){
        if(monthlyReport.monthTransactions.size() == 0 || yearlyReport.yearTransactions.size() == 0){ // Проверка были ли считаны файлы
            System.out.println("Для сверки сначала считайте отчет.");
            return;
        }

        monthlyReport.getIncomeAndSpend(); //Метод определяет расходы и доходы по разным хэш таблицам

        if (yearlyReport.profitOrders.equals(monthlyReport.profitOrders)){ //Сверка отчетов по доходам
            System.out.println("Сверка отчетов о доходах прошла успешно.");
        } else {
            checkerProfit = monthlyReport.profitOrders;
            for (Integer month : yearlyReport.profitOrders.keySet()) {
                if(checkerProfit.containsKey(month)){
                    if(!checkerProfit.get(month).equals(yearlyReport.profitOrders.get(month))){
                        if(month == 1){
                            System.out.println("Отчет о доходах за Январь не сошелся.");
                        } else if(month == 2){
                            System.out.println("Отчет о доходах за Февраль не сошелся.");
                        } else if(month == 3){
                            System.out.println("Отчет о доходах за Март не сошелся.");
                        }
                    }
                }
            }
        }

        if(yearlyReport.spendOrders.equals(monthlyReport.spendOrders)){ //Сверка отчетов по расходам
            System.out.println("Сверка отчетов о расходах прошла успешно.");
        } else {
            checkerSpent = monthlyReport.spendOrders;
            for (Integer month : yearlyReport.spendOrders.keySet()) {
                if(checkerSpent.containsKey(month)){
                    if(!checkerSpent.get(month).equals(yearlyReport.spendOrders.get(month))){
                        if(month == 1){
                            System.out.println("Отчет о расходах за Январь не сошелся.");
                        } else if (month == 2){
                            System.out.println("Отчет о расходах за Февраль не сошелся.");
                        } else if (month == 3){
                            System.out.println("Отчет о расходах за Март не сошелся.");
                        }
                    }
                }
            }
        }
    }
}
