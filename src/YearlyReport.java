import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
public class YearlyReport {

    HashMap<Integer, ArrayList<Double>> yearlyReportMap = new HashMap<>();
    static String pathCatalogReports = ("resources");
    static String formatYearlyReport = "y.\\d{4}.csv";
    static String patternFormatYearlyReport = "y.YYYY.csv";
    static String nameFileYearlyReport;
    int numberYear;
    File catalogReports;
    Integer month;
    double amount;
    String isExpense;

    void readFileContentsOrNull() {

        catalogReports = new File(pathCatalogReports);
        try {
            File[] pathFileReport = catalogReports.listFiles((dir, name) -> name.matches(formatYearlyReport));

            if (pathFileReport != null && pathFileReport.length == 1) {

                nameFileYearlyReport = pathFileReport[0].getName();
                numberYear = Integer.parseInt(nameFileYearlyReport.replaceFirst("y.", "")
                        .replaceFirst(".csv", ""));
                BufferedReader readerYearlyReport = new BufferedReader(new FileReader(pathFileReport[0]));
                String lineYearlyReport;
                int count = 1;
                ArrayList<Double> profitAndExpense = new ArrayList<>(2);
                profitAndExpense.add(0.0);
                profitAndExpense.add(0.0);

                while ((lineYearlyReport = readerYearlyReport.readLine()) != null) {

                    String[] LinesYearlyReport = lineYearlyReport.split(",");
                    ArrayList<String> arrayLinesYearlyReport = new ArrayList<>();

                    for (int i = 0; i < LinesYearlyReport.length; i++) {
                        arrayLinesYearlyReport.add(i, LinesYearlyReport[i]);
                    }
                    if (arrayLinesYearlyReport.get(0).matches("\\d\\d")) {

                        month = Integer.parseInt(arrayLinesYearlyReport.get(0));
                        amount = Double.parseDouble(arrayLinesYearlyReport.get(1));
                        isExpense = arrayLinesYearlyReport.get(2);

                        if (isExpense.equalsIgnoreCase("false")) {

                            profitAndExpense.set(0, amount);

                        } else if (isExpense.equalsIgnoreCase("true")) {

                            profitAndExpense.set(1, amount);
                        }
                        if (count % 2 == 0) {

                            yearlyReportMap.put(month, profitAndExpense);
                            profitAndExpense = new ArrayList<>(2);
                            profitAndExpense.add(0.0);
                            profitAndExpense.add(0.0);
                        }
                        count++;
                    }
                }
                readerYearlyReport.close();
            } else {
                windowsErrorYearlyReport();
            }
        } catch (IOException e) {
            windowsErrorYearlyReport();
        }
        System.out.println("Считывание годового отчёта завершено.");
    }



    void outputInfoYearlyReport() {
        ArrayList<Double> arrayProfitAndExpense;

        if (yearlyReportMap.size() != 0) {

            System.out.println("Рассматриваемый год: " + numberYear);

            double sumProfit = 0.0;
            double sumExpense = 0.0;

            for (Integer keyYearlyReportMap : yearlyReportMap.keySet()) {

                arrayProfitAndExpense = yearlyReportMap.get(keyYearlyReportMap);

                double profitMonthlyReport = arrayProfitAndExpense.get(0) - arrayProfitAndExpense.get(1);
                sumProfit += arrayProfitAndExpense.get(0);
                sumExpense += arrayProfitAndExpense.get(1);

                System.out.println(" Прибыль по месяцу: " + ReviseReports.calendar(keyYearlyReportMap) + " равна " + profitMonthlyReport + ";");
            }
            double averageSumProfit = sumProfit / yearlyReportMap.size();
            double averageSumExpense = sumExpense / yearlyReportMap.size();

            System.out.println(" Средний расход за все месяцы в году: " + -averageSumExpense + "; ");
            System.out.println(" Средний доход за все месяцы в году: " + averageSumProfit + ". ");
        } else {
            System.out.println("Перед Выводом информации о годовом отчёте необходимо: \n" +
                    "2 - Считать годовой отчёт.");
        }
    }
        String windowsErrorYearlyReport() {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно, файл не находится в нужной директории или сохранен в неправильном формате.");
            return null;
        }
    }

