package entity;

import enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

public class Transactions extends BaseEntity{
    private String cardNumber;
    private String terminalCode;
    private Double amount;
    private TransactionType type;

    public String toStringForUser(){
        return "Date: " + getCreatedDate() +
                "\nCardNumber: " + cardNumber +
                "\nTerminal code: " + terminalCode +
                "\nAmount: " + amount +
                "\nType: " + type;
    }
}
