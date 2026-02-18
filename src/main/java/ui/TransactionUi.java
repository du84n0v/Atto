package ui;

import controller.TransactionController;
import entity.Transactions;

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
}
