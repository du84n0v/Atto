package ui;

import controller.TransactionController;
import dto.TransactionResponseDto;
import dto.TransactionShortDto;
import entity.Transactions;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class TransactionUi {

    private final TransactionController controller = new TransactionController();

    public String makePayment(String cardNumber, String terminalCode) {
        return controller.makePayment(cardNumber, terminalCode);
    }

    public void fillBalance(String cardNumber, double amount) {
        controller.fillBalance(cardNumber, amount);
    }

    public List<Transactions> getTransactionsByProfileId(UUID profileId) {
        return controller.getTransactionsByProfileId(profileId);
    }

    public void transactionList() {
        List<TransactionResponseDto> response = controller.transactionList();
        if(response.isEmpty()){
            System.out.println("No transaction yet");
            return;
        }
        int cnt = 1;
        for (TransactionResponseDto tt : response) {
            System.out.println("=====    Transaction #" + (cnt++) + "    =====");
            System.out.println(tt.toString());
            System.out.println();
        }

    }

    public List<TransactionShortDto> getTodayPayment() {
        return controller.getTodayPayment();
    }

    public List<TransactionShortDto> getDailyPayment(LocalDate day) {
        return controller.getDailyPayment(day);
    }
}
