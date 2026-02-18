package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import entity.Card;

import java.util.List;

public class CardRepository extends BaseRepository<Card> {

    public CardRepository(){
        super("cards.json", new TypeReference<List<Card>>(){});
    }
}
