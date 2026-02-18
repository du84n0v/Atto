package entity;

import enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

public class Card extends BaseEntity{
    private String cardNumber;
    private Double balance;
    private Status status;
    private String expiredDate;

    public String forUser(){
        return "Card Number: " + cardNumber +
                "\nBalance: " + balance +
                "\nStatus: " + status +
                "\nExpired Date: " + expiredDate;
    }

    public String toString(){
        return "ID: " + getId() +
                "\nCreatedDate: " + getCreatedDate() +
                "\nVisible: " + getVisible() +
                "\nCard Number: " + cardNumber +
                "\nBalance: " + balance +
                "\nStatus: " + status +
                "\nExpired Date: " + expiredDate;
    }

}
