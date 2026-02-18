package entity;

import enums.Role;
import enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

public class Profile extends BaseEntity{
    private String name;
    private String surname;
    private String phone;
    private String password;
    private Integer cardCount;
    private Status status;
    private Role role;

}
