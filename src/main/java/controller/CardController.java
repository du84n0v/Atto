package controller;

import dto.CardDto;
import entity.Card;
import service.CardService;

import java.util.List;
import java.util.UUID;

public class CardController {

    private final CardService service = new CardService();

    public void save(CardDto cardDto) {
        service.save(cardDto);
    }

    public List<Card> getAllCard() {
        return service.getAllCard();
    }

    public void updateCardExpiredDate(String num, String expiredDate) {
        service.updateCardExpiredDate(num, expiredDate);
    }

    public void changeCardStatus(String cardNumber) {
        service.changeCardStatus(cardNumber);
    }

    public void deleteCard(String cardNumber) {
        service.deleteCard(cardNumber);
    }

    public String assignCardToProfile(String cardNumber, UUID profileId) {
        return service.assignCardToProfile(cardNumber, profileId);
    }

    public List<Card> getProfileCards(UUID profileId) {
        return service.getProfileCards(profileId);
    }

    public void deleteUserCard(UUID cardId) {
        service.deleteUserCard(cardId);
    }

    public void fillBalance(String cardNumber, double amount) {
        service.fillBalance(cardNumber, amount);
    }
}
