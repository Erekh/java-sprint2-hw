import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        Checker checker = new Checker(monthlyReport, yearlyReport);
        Scanner scanner = new Scanner(System.in);

        while(true){
            printMenu();
            int userInput = scanner.nextInt();

            if (userInput == 1){            //Считать все месячные отчёты
                monthlyReport.loadFile();
            } else if (userInput == 2) {    //Считать годовой отчёт
                yearlyReport.loadFile("y.2021.csv");
            } else if (userInput == 3) {    //Сверить отчёты
                checker.check();
            } else if (userInput == 4) {    //Вывести информацию обо всех месячных отчётах
                monthlyReport.getMontyReport();
            } else if (userInput == 5) {    //Вывести информацию о годовом отчёте
                yearlyReport.printYearStat();
            } else if (userInput == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Такой команды нет.");
            }
        }
    }

    public static void printMenu(){
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
}