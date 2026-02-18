package ui;

import entity.Card;
import entity.Profile;
import utils.ScannerUtil;

import java.util.List;
import java.util.UUID;

public class UserUi {

    private final CardUi cardUi = new CardUi();
    private final TransactionUi transactionUi = new TransactionUi();

    public void run(Profile profile){
        while (true){
            switch (menu()){
                case 1 -> addCard(profile);
                case 2 -> showMyCards(profile);
                case 3 -> changeCardStatus(profile);
                case 4 -> deleteCard(profile);
                case 5 -> fillBalance(profile);
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void fillBalance(Profile profile) {
        System.out.print("Enter card number: ");
        String cardNumber = ScannerUtil.SCANNER_STR.next();
        if(!cardUi.existCard(cardNumber)){
            System.out.println("Card not found");
            return;
        }
        System.out.print("Enter amount: ");
        double amount = ScannerUtil.SCANNER_NUM.nextDouble();
        transactionUi.fillBalance(cardNumber, amount);
        System.out.println("Successfully ");
    }

    private void deleteCard(Profile profile) {
        System.out.print("Enter card number: ");
        String cardNumber = ScannerUtil.SCANNER_STR.next();
        for (Card myCard : getMyCards(profile.getId())) {
            if(myCard.getCardNumber().equals(cardNumber)){
                cardUi.deleteUserCard(myCard.getId());
                System.out.println("Successfully deleted");
                return;
            }
        }
        System.out.println("Card not found");
    }

    private void changeCardStatus(Profile profile) {
        System.out.print("Enter card number: ");
        String cardNumber = ScannerUtil.SCANNER_STR.next();
        for (Card myCard : getMyCards(profile.getId())) {
            if(myCard.getCardNumber().equals(cardNumber)){
                cardUi.changeUserStatus(myCard.getCardNumber());
                System.out.println("Successfully changed");
                return;
            }
        }
        System.out.println("Card not found");
    }

    private void showMyCards(Profile profile) {
        List<Card> cards = getMyCards(profile.getId());
        if(cards.isEmpty()){
            System.out.println("You do not have any card yet");
            return;
        }
        int cnt = 1;
        for (Card card : cards) {
            System.out.println("=====   Card #" + (cnt++) + "   =====");
            System.out.println(card.forUser());
            System.out.println();
        }
    }

    private List<Card> getMyCards(UUID profileId) {
        return cardUi.getProfileCards(profileId);
    }

    private void addCard(Profile profile) {
        System.out.print("Enter card number: ");
        String cardNumber = ScannerUtil.SCANNER_STR.next();

        System.out.println(cardUi.assignCardToProfile(cardNumber, profile.getId()));
    }

    private int menu() {
        System.out.println("1.Add card");
        System.out.println("2.Show my cards");
        System.out.println("3.Change card status");
        System.out.println("4.Delete card");
        System.out.println("5.Fill balance");
        System.out.println("0.Exit");
        System.out.print(">>>>> ");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }
}
