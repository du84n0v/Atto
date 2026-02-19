package dto;

import enums.TransactionType;

public record TransactionResponseDto(
        String cardNumber,
        String name,
        String surname,
        String terminalNumber,
        String terminalAddress,
        Double amount,
        TransactionType type) {
}
