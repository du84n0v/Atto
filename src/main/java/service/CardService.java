package service;

import dto.CardDto;
import dto.ProfileShortDto;
import entity.Card;
import entity.Profile;
import enums.Status;
import repository.CardRepository;

import java.time.LocalDateTime;
import java.util.*;

public class CardService {

    private final CardRepository repository = new CardRepository();
    private final ProfileCardService profileCardService = new ProfileCardService();

    public void save(CardDto cardDto) {
        Card card = new Card();

        card.setId(UUID.randomUUID());
        card.setCreatedDate(LocalDateTime.now().toString());
        card.setVisible(true);
        card.setCardNumber(cardDto.cardNumber());
        card.setExpiredDate(cardDto.expiredData());
        card.setBalance(0D);
        card.setStatus(Status.ACTIVE);

        repository.save(List.of(card));
    }

    public List<Card> getAllCard() {
        return repository.getData();
    }

    public void updateCardExpiredDate(String num, String expiredDate) {
        List<Card> cards = new LinkedList<>();
        for (Card card : getAllCard()) {
            if(card.getCardNumber().equals(num)){
                card.setExpiredDate(expiredDate);
            }
            cards.add(card);
        }
        repository.update(cards);
    }

    public void changeCardStatus(String cardNumber) {
        List<Card> cards = new LinkedList<>();

        for (Card card : getAllCard()) {
            if(card.getCardNumber().equals(cardNumber)){
                card.setStatus(card.getStatus().equals(Status.ACTIVE) ? Status.BLOCKED : Status.ACTIVE);
            }
            cards.add(card);
        }
        repository.update(cards);
    }

    public void deleteCard(String cardNumber) {
        repository.update(
                getAllCard()
                .stream()
                .filter(card -> !card.getCardNumber().equals(cardNumber))
                .toList());


    }

    public Card getCardByCardNumber(String cardNumber) {
        return repository.getData()
                .stream()
                .filter(card -> card.getCardNumber().equals(cardNumber))
                .findFirst()
                .orElse(null);
    }

    public void updateThisCard(Card card) {
        List<Card> cards = repository.getData();
        for (Card cc : cards) {
            if(cc.getCardNumber().equals(card.getCardNumber())){
                cc.setBalance(card.getBalance());
            }
        }
        repository.update(cards);
    }

    public String assignCardToProfile(String cardNumber, UUID profileId) {
        Card card = getCardByCardNumber(cardNumber);
        if(card == null){
            return "Card not found";
        }
        if(!card.getStatus().equals(Status.ACTIVE)){
            return "Card is not active";
        }

        return profileCardService.assignCardToProfile(cardNumber, profileId);
    }

    public List<Card> getProfileCards(UUID profileId) {
        List<String> cardNumbers =  profileCardService.getProfileCardNumbers(profileId);
        List<Card> cards = new LinkedList<>();
        for (Card card : repository.getData()) {
            for(String number :cardNumbers){
                if(card.getCardNumber().equals(number)) {
                    cards.add(card);
                    break;
                }
            }
        }
        return cards;
    }

    public void deleteUserCard(String cardNumber) {
        profileCardService.delete(cardNumber);
    }

    public void fillBalance(String cardNumber, double amount) {
        Card card = getCardByCardNumber(cardNumber);
        card.setBalance(card.getBalance()+amount);
        updateThisCard(card);
    }

    public Double getCompanyCardBalance() {
        return repository.getData()
                .stream()
                .filter(card -> card.getCardNumber().equals("1111111111111111"))
                .map(Card::getBalance)
                .findFirst()
                .orElse(null);
    }
}
