package dto;

import enums.Status;

import java.util.UUID;

public record ProfileDto(
        UUID id,
        String name,
        String surname,
        String phone,
        String password,
        int cardCount,
        Status status,
        String createdDate) {

    @Override
    public String toString(){
        return "Id: " + id +
                "\nName: " + name +
                "\nSurname: " + surname +
                "\nPhone: " + phone +
                "\nPassword: " + password +
                "\nCard Count: " + cardCount +
                "\nStatus: " + status +
                "\nCreated Date: " + createdDate;
    }

}
