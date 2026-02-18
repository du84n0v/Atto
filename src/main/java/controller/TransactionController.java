package controller;

import service.TransactionService;

public class TransactionController {

    private final TransactionService service = new TransactionService();

    public String makePayment(String cardNumber, String terminalCode) {
        return service.makePayment(cardNumber, terminalCode);
    }

    public void fillBalance(String cardNumber, double amount) {
        service.fillBalance(cardNumber, amount);
    }
}
