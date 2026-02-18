package dto;

public record RegisterDto(
        String name,
        String surname,
        String phone,
        String password) {
}
