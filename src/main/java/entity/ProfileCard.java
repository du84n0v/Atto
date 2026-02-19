package entity;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

public class ProfileCard extends BaseEntity{
    private String cardNumber;
    private UUID profileId;
}
