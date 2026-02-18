package entity;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

public class ProfileCard extends BaseEntity{
    private UUID cardId;
    private UUID profileId;
}
