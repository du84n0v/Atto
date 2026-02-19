package dto;

import enums.TransactionType;

public record TransactionShortDto(
        String cardNumber,
        String terminalAddress,
        Double amount,
        String transactionDate,
        TransactionType type) {

    @Override
    public String toString(){
        return "Card number: " + cardNumber +
                "\nTerminal address: " + terminalAddress +
                "\nAmount: " + amount +
                "\nDate: " + transactionDate +
                "\nType: " + type;
    }
}
