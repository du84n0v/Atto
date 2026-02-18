package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import entity.Card;
import entity.ProfileCard;

import java.util.List;

public class ProfileCardRepository extends BaseRepository<ProfileCard> {

    public ProfileCardRepository(){
        super("profile_card.json", new TypeReference<List<ProfileCard>>(){});
    }
}
