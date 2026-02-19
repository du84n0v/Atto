package service;

import dto.ProfileShortDto;
import dto.TransactionResponseDto;
import dto.TransactionShortDto;
import entity.*;
import enums.Status;
import enums.TransactionType;
import repository.TransactionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TransactionService {

    private final TerminalService terminalService = new TerminalService();
    private final CardService cardService = new CardService();
    private final TransactionRepository repository = new TransactionRepository();
    private final ProfileService profileService = new ProfileService();

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

    public List<TransactionResponseDto> transactionList() {
        Map<String, String> terminalAddress = terminalService.getTerminalAddress();
        Map<String, ProfileShortDto> profileShortInfo = profileService.getProfileShortInfo();

        List<TransactionResponseDto> response = new LinkedList<>();

        List<Transactions> transactions = repository.getData();
        transactions.sort(Comparator.comparing(Transactions::getCreatedDate).reversed());

        for (Transactions datum : transactions) {

            String cardNumber = datum.getCardNumber();
            String terminalCode = datum.getTerminalCode();
            String terAddress = (datum.getTerminalCode() != null ? terminalAddress.get(datum.getTerminalCode()) : "null");
            Double amount = datum.getAmount();
            TransactionType type = datum.getType();
            String date = datum.getCreatedDate();
            String name = profileShortInfo.get(datum.getCardNumber()).name();
            String surname = profileShortInfo.get(datum.getCardNumber()).surname();

            TransactionResponseDto dto = new TransactionResponseDto(
                    cardNumber,
                    name,
                    surname,
                    terminalCode,
                    terAddress,
                    amount,
                    datum.getCreatedDate(),
                    type
            );
            response.add(dto);
        }

        return response;
    }

    public List<TransactionShortDto> getTodayPayment() {
        List<TransactionShortDto> response = new LinkedList<>();
        List<Transactions> base = repository.getData();
        Map<String, String> terminalAddress = terminalService.getTerminalAddress();
        base.sort(Comparator.comparing(Transactions::getCreatedDate).reversed());
        for (Transactions datum : base) {
            LocalDate date =  LocalDateTime.parse(datum.getCreatedDate()).toLocalDate();
            if(date.equals(LocalDate.now())){
                response.add(new TransactionShortDto(
                        datum.getCardNumber(),
                        terminalAddress.get(datum.getTerminalCode()) != null ? terminalAddress.get(datum.getTerminalCode()) : "null",
                        datum.getAmount(),
                        datum.getCreatedDate(),
                        datum.getType()
                ));
            }
        }
        return response;
    }

    public List<TransactionShortDto> getDailyPayment(LocalDate day) {
        List<TransactionShortDto> response = new LinkedList<>();
        Map<String, String> terminalAddress = terminalService.getTerminalAddress();
        for (Transactions tr : repository.getData()) {
            LocalDate date = LocalDateTime.parse(tr.getCreatedDate()).toLocalDate();
            if(date.equals(day)){
                response.add(new TransactionShortDto(
                        tr.getCardNumber(),
                        terminalAddress.get(tr.getTerminalCode()) != null ? terminalAddress.get(tr.getTerminalCode()) : "null",
                        tr.getAmount(),
                        tr.getCreatedDate(),
                        tr.getType()
                        )
                );
            }
        }
        return response;
    }
}
