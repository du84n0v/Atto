package dto;

import enums.TransactionType;

public record TransactionResponseDto(
        String cardNumber,
        String name,
        String surname,
        String terminalNumber,
        String terminalAddress,
        Double amount,
        String date,
        TransactionType type) {

    @Override
    public String toString(){
        return "Card Number: " + cardNumber +
                "\nName: " + name +
                "\nSurname: " + surname +
                "\nTerminal Number: " + terminalNumber +
                "\nTerminal address: " + terminalAddress +
                "\nAmount: " + amount +
                "\nDate: " + date +
                "\nType: " + type;
    }

}
