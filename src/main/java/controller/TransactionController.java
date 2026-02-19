package controller;

import dto.TransactionResponseDto;
import dto.TransactionShortDto;
import entity.Transactions;
import service.TransactionService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class TransactionController {

    private final TransactionService service = new TransactionService();

    public String makePayment(String cardNumber, String terminalCode) {
        return service.makePayment(cardNumber, terminalCode);
    }

    public void fillBalance(String cardNumber, double amount) {
        service.fillBalance(cardNumber, amount);
    }

    public List<Transactions> getTransactionsByProfileId(UUID profileId) {
        return service.getTransactionsByProfileId(profileId);
    }

    public List<TransactionResponseDto> transactionList() {
        return service.transactionList();
    }

    public List<TransactionShortDto> getTodayPayment() {
        return service.getTodayPayment();
    }

    public List<TransactionShortDto> getDailyPayment(LocalDate day) {
        return service.getDailyPayment(day);
    }
}
