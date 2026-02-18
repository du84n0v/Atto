package ui;

import controller.TransactionController;

public class TransactionUi {

    private final TransactionController controller = new TransactionController();

    public String makePayment(String cardNumber, String terminalCode) {
        return controller.makePayment(cardNumber, terminalCode);
    }

    public void fillBalance(String cardNumber, double amount) {
        controller.fillBalance(cardNumber, amount);
    }
}
