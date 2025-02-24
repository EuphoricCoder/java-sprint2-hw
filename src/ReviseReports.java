import java.util.ArrayList;
import java.util.HashMap;


public class ReviseReports {

    static void reviseSumReports(HashMap<Integer, HashMap<String, ArrayList<Double>>> monthlyReportsMaps, HashMap<Integer, ArrayList<Double>> yearlyReportMap) {

        HashMap<Integer, ArrayList<Double>> reviseSumMonthlyReport = new HashMap<>();
        int monthError;

        if ((monthlyReportsMaps.size() != 0) && (yearlyReportMap.size() != 0)) {

            for (Integer keyMonthlyReportsMaps : monthlyReportsMaps.keySet()) {

                HashMap<String, ArrayList<Double>> monthlyReportMap = monthlyReportsMaps.get(keyMonthlyReportsMaps);
                double monthlySumsProfit = 0;
                double monthlySumsExpenses = 0;

                for (String keyMonthlyReportMap : monthlyReportMap.keySet()) {

                    ArrayList<Double> arrayProfitAndExpense = monthlyReportMap.get(keyMonthlyReportMap);
                    monthlySumsProfit += arrayProfitAndExpense.get(0);
                    monthlySumsExpenses += arrayProfitAndExpense.get(1);
                }
                ArrayList<Double> arrayMonthlySums = new ArrayList<>(2);
                arrayMonthlySums.add(0.0);
                arrayMonthlySums.add(0.0);
                arrayMonthlySums.set(0, monthlySumsProfit);
                arrayMonthlySums.set(1, monthlySumsExpenses);
                reviseSumMonthlyReport.put(keyMonthlyReportsMaps, arrayMonthlySums);
            }
            int allSuccessfulReviseReports = 0;

            for (Integer keyMonthlyReportsMaps : reviseSumMonthlyReport.keySet()) {

                boolean successfulRevise = false;
                ArrayList<Double> arrayMonthlySums = reviseSumMonthlyReport.get(keyMonthlyReportsMaps);

                for (Integer keyYearlyReportMap : yearlyReportMap.keySet()) {

                    ArrayList<Double> arrayYearlySums = yearlyReportMap.get(keyYearlyReportMap);

                    if ((keyMonthlyReportsMaps.equals(keyYearlyReportMap)) && (arrayMonthlySums.get(0).equals(arrayYearlySums.get(0))) &&
                            (arrayMonthlySums.get(1).equals(arrayYearlySums.get(1)))) {

                        successfulRevise = true;
                        allSuccessfulReviseReports += 1;
                    }
                }
                monthError = keyMonthlyReportsMaps;

                if (!successfulRevise) {

                    System.out.println("Отчет за месяц: " + calendar(monthError) + " не соответствует годовому отчету.");

                } else if (allSuccessfulReviseReports == reviseSumMonthlyReport.size()) {

                    System.out.println("Сверка отчётов успешно завершена, ошибок не обнаружено.");
                }
            }
        } else {
            System.out.println("Перед тем, как сверять отчеты необходимо:\n" +
                    "1 - Считать все месячные отчёты;\n" +
                    "2 - Считать годовой отчёт.");
        }
    }

    public static String calendar(Integer month) {

        String nameMonth = null;
        switch (month) {
            case 1: nameMonth = "Январь";
                break;
            case 2: nameMonth = "Февраль";
                break;
            case 3: nameMonth = "Март";
                break;
            case 4: nameMonth = "Апрель";
                break;
            case 5: nameMonth = "Май";
                break;
            case 6: nameMonth = "Июнь";
                break;
            case 7: nameMonth = "Июль";
                break;
            case 8: nameMonth = "Август";
                break;
            case 9: nameMonth = "Сентябрь";
                break;
            case 10: nameMonth = "Октябрь";
                break;
            case 11: nameMonth = "Ноябрь";
                break;
            case 12: nameMonth = "Декабрь";
                break;
        }
        return nameMonth;
    }
}