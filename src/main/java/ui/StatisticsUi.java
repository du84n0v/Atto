package ui;

import dto.TransactionShortDto;
import utils.ScannerUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class StatisticsUi {

    private final TransactionUi transactionUi = new TransactionUi();

    public void run() {
        while (true){
            switch (menu()){
                case 1 -> todayPayment();
                case 2 -> dailyPayment();
                case 3 -> interimPayment();
                case 4 -> transactionByTerminal();
                case 5 -> transactionByCard();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private int menu() {
        System.out.println("1.Today's payments");
        System.out.println("2.Daily payments");
        System.out.println("3.Interim payments");
        System.out.println("4.Transaction by terminal");
        System.out.println("5.Transaction by card");
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
        show(transactionUi.getTodayPayment());
    }

    private void dailyPayment() {
        System.out.print("Enter day (yyyy-MM-dd): ");
        String day = ScannerUtil.SCANNER_STR.next();

        show(transactionUi.getDailyPayment(LocalDate.parse(day)));
    }

    private void interimPayment() {
        System.out.print("Enter from date (yyyy-MM-dd): ");
        String fromDate = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter to day (yyyy-MM-dd): ");
        String toDate = ScannerUtil.SCANNER_STR.next();

        show(transactionUi.interimPayment(LocalDate.parse(fromDate), LocalDate.parse(toDate)));
    }

    private void transactionByTerminal() {
        System.out.print("Enter terminal number: ");
        String number = ScannerUtil.SCANNER_STR.next();

        show(transactionUi.transactionByTerminal(number));
    }

    private void transactionByCard() {
        System.out.print("Enter card number: ");
        String cardNumber = ScannerUtil.SCANNER_STR.next();
        show(transactionUi.transactionByCard(cardNumber));
    }
}
