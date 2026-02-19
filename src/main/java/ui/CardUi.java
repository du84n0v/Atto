package ui;

import controller.CardController;
import dto.CardDto;
import entity.Card;
import enums.Status;
import utils.ScannerUtil;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CardUi {

    private final CardController controller = new CardController();

    public void run(){
        while (true){
            switch (menu()){
                case 1 -> createCard();
                case 2 -> showAllCard();
                case 3 -> updateCardExpiredDate();
                case 4 -> changeCardStatus();
                case 5 -> deleteCard();
                case 9 -> generateCardNumber();
                case 0 -> {
                    return;
                }
            }
        }
    }

    public int menu(){
        System.out.println("1.Create Card");
        System.out.println("2.Show all Card");
        System.out.println("3.Extend card expired date");
        System.out.println("4.Change card status");
        System.out.println("5.Delete card");
        System.out.println("9.Generate card number");
        System.out.print(">>>> ");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }

    public void createCard(){
        System.out.print("Enter card number: ");
        String cardNumber = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter expired data: ");
        String expiredData = ScannerUtil.SCANNER_STR.next();

        controller.save(new CardDto(cardNumber, expiredData));
    }

    public void showAllCard(){
        int cnt = 1;
        for (Card card : getAllCard()) {
            System.out.println("=====   Card #" + (cnt++));
            System.out.println(card.toString());
            System.out.println();
        }
    }

    public List<Card> getAllCard() {
         return controller.getAllCard();
    }

    public void updateCardExpiredDate() {
        showAllCard();
        System.out.print("Enter card number: ");
        String num = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter expired data: ");
        String expiredDate = ScannerUtil.SCANNER_STR.next();

        controller.updateCardExpiredDate(num, expiredDate);
    }

    public void changeCardStatus() {
        showAllCard();
        System.out.print("Enter card number: ");
        String cardNumber = ScannerUtil.SCANNER_STR.next();
        controller.changeCardStatus(cardNumber);
    }

    public void changeUserStatus(String cardNumber){
        controller.changeCardStatus(cardNumber);
    }

    public void deleteCard() {
        showAllCard();
        System.out.print("Enter card number: ");
        String cardNumber = ScannerUtil.SCANNER_STR.next();
        controller.deleteCard(cardNumber);
    }

    private void generateCardNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder("9987");
        for (int i = 0; i < 12; i++) {
            sb.append(random.nextInt(10));
        }
        System.out.println(sb);
    }

    public String assignCardToProfile(String cardNumber, UUID profileId) {
        return controller.assignCardToProfile(cardNumber, profileId);
    }

    public List<Card> getProfileCards(UUID profileId) {
        return controller.getProfileCards(profileId);
    }

    public void deleteUserCard(String cardNumber) {
        controller.deleteUserCard(cardNumber);
    }

    public boolean existCard(String cardNumber) {
        return getAllCard().stream()
                .anyMatch(card -> card.getCardNumber().equals(cardNumber) &&
                        card.getStatus().equals(Status.ACTIVE));
    }

    public void fillBalance(String cardNumber, double amount) {
        controller.fillBalance(cardNumber, amount);
    }

    public void companyCardBalance() {
        System.out.println("Balance: " + controller.getCompanyCardBalance());
    }
}
