package entity;

import enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

public class Terminal extends BaseEntity{
    private String number;
    private String address;
    private Status status;

    @Override
    public String toString(){
        return "ID: " + getId() +
                "\nCreated date: " + getCreatedDate() +
                "\nVisible: " + getVisible() +
                "\nNumber: " + number +
                "\nAddress: " + address +
                "\nStatus: " + status;
    }

}
