package service;

import entity.Card;
import entity.Terminal;
import entity.Transactions;
import enums.Status;
import enums.TransactionType;
import repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TransactionService {

    private final TerminalService terminalService = new TerminalService();
    private final CardService cardService = new CardService();
    private final TransactionRepository repository = new TransactionRepository();

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
}
