import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                monthlyReport.readMonthlyReports();
            } else if (command == 2) {
                yearlyReport.readFileContentsOrNull();
            } else if (command == 3) {
                ReviseReports.reviseSumReports(monthlyReport.monthlyReportsMaps, yearlyReport.yearlyReportMap);
            } else if (command == 4) {
                monthlyReport.outputInfoMonthlyReports();
            } else if (command == 5) {
                yearlyReport.outputInfoYearlyReport();
            } else if (command == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Ошибка, такой команды нет.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
}