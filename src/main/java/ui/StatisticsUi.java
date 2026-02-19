package ui;

import dto.TransactionShortDto;
import utils.ScannerUtil;

import java.time.LocalDate;
import java.util.List;

public class StatisticsUi {

    private final TransactionUi transactionUi = new TransactionUi();

    public void run() {
        while (true){
            switch (menu()){
                case 1 -> todayPayment();
                case 2 -> dailyPayment();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private int menu() {
        System.out.println("1.Today's payments");
        System.out.println("2.Daily payments");
        System.out.println("0.Exit");
        System.out.print(">>>> ");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }

    public void show(List<TransactionShortDto> list){
        if(list.isEmpty()){
            System.out.println("No payment yet");
            return;
        }

        int cnt = 1;
        for (TransactionShortDto transactionShortDto : list) {
            System.out.println("=====   Transaction #" + (cnt++) + "   =====");
            System.out.println(transactionShortDto.toString());
            System.out.println();
        }
    }

    private void todayPayment() {
        List<TransactionShortDto> list = transactionUi.getTodayPayment();
        show(list);
    }

    private void dailyPayment() {
        System.out.print("Enter day (yyyy-MM-dd): ");
        String day = ScannerUtil.SCANNER_STR.next();

        List<TransactionShortDto> list = transactionUi.getDailyPayment(LocalDate.parse(day));

        show(list);
    }
}
