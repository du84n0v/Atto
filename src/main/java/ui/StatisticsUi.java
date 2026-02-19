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
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void todayPayment() {
        List<TransactionShortDto> list = transactionUi.getTodayPayment(LocalDate.now());
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

    private int menu() {
        System.out.println("1.Today's payments");
        System.out.println("0.Exit");
        System.out.print(">>>> ");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }
}
