package service;

import dto.ProfileDto;
import dto.ProfileShortDto;
import dto.TransactionResponseDto;
import entity.*;
import enums.Status;
import enums.TransactionType;
import repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.*;

public class TransactionService {

    private final TerminalService terminalService = new TerminalService();
    private final CardService cardService = new CardService();
    private final TransactionRepository repository = new TransactionRepository();
    private final ProfileCardService profileCardService = new ProfileCardService();

    private static final double RIDE_FIRE = 1700.0;
    private static final String COMPANY_CARD_NUMBER = "1111111111111111";

    public String makePayment(String cardNumber, String terminalCode) {

        Card card = cardService.getCardByCardNumber(cardNumber);
        Terminal terminal = terminalService.getTerminalByTerminalCode(terminalCode);
        Card companyCard = cardService.getCardByCardNumber(COMPANY_CARD_NUMBER);

        if(card == null){
            return "Card not found";
        }
        if(!card.getStatus().equals(Status.ACTIVE)){
            return "Card not active";
        }

        if(terminal == null){
            return "Terminal not found";
        }
        if(!terminal.getStatus().equals(Status.ACTIVE)){
            return "Terminal not active";
        }

        if(card.getBalance() < RIDE_FIRE){
            return "Insufficient funds";
        }

        Transactions transaction = new Transactions();
        transaction.setId(UUID.randomUUID());
        transaction.setCreatedDate(LocalDateTime.now().toString());
        transaction.setVisible(true);
        transaction.setCardNumber(cardNumber);
        transaction.setTerminalCode(terminalCode);
        transaction.setAmount(RIDE_FIRE);
        transaction.setType(TransactionType.PAYMENT);

        repository.save(List.of(transaction));

        card.setBalance(card.getBalance() - RIDE_FIRE);
        companyCard.setBalance(companyCard.getBalance() + RIDE_FIRE);

        cardService.updateThisCard(card);
        cardService.updateThisCard(companyCard);

        return "Success! Left: " + card.getBalance();
    }

    public void fillBalance(String cardNumber, double amount) {
        Transactions transactions = new Transactions();
        transactions.setId(UUID.randomUUID());
        transactions.setCreatedDate(LocalDateTime.now().toString());
        transactions.setVisible(true);
        transactions.setCardNumber(cardNumber);
        transactions.setAmount(amount);
        transactions.setType(TransactionType.REFILL);

        cardService.fillBalance(cardNumber, amount);

        repository.save(List.of(transactions));
    }

    public List<Transactions> getTransactionsByProfileId(UUID profileId) {
        List<String> profileCardNumbers = cardService.getProfileCards(profileId)
                .stream()
                .map(Card::getCardNumber)
                .toList();

        return repository.getData()
                .stream()
                .filter(tr -> profileCardNumbers.contains(tr.getCardNumber()))
                .sorted(Comparator.comparing(Transactions::getCreatedDate).reversed())
                .toList();
    }

//    public List<TransactionResponseDto> transactionList() {
//        List<Transactions> transactions = repository.getData();
//
//        transactions.sort(Comparator.comparing(BaseEntity::getCreatedDate).reversed());
//
//        List<TransactionResponseDto> response = new LinkedList<>();
//
//        for (Transactions transaction : transactions) {
//            ProfileShortDto shortDto = cardService.getProfileShortByCardNumber(transaction.getCardNumber());
//        }
//    }
}
