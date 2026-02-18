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
}
