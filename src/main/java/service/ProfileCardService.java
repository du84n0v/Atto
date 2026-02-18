package service;

import entity.Card;
import entity.ProfileCard;
import repository.ProfileCardRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProfileCardService {

    private final ProfileCardRepository repository = new ProfileCardRepository();

    public String assignCardToProfile(UUID cardId, UUID profileId) {
        if(isCardOwned(cardId)){
            return "Card already has owner";
        }

        ProfileCard profileCard = new ProfileCard();
        profileCard.setId(UUID.randomUUID());
        profileCard.setCreatedDate(LocalDateTime.now().toString());
        profileCard.setVisible(true);
        profileCard.setCardId(cardId);
        profileCard.setProfileId(profileId);

        repository.save(List.of(profileCard));

        return "Successfully assigned";
    }

    private List<ProfileCard> getProfileCard(){
        return repository.getData();
    }

    private boolean isCardOwned(UUID cardId) {
        return repository.getData()
                .stream()
                .anyMatch(pc -> pc.getCardId().equals(cardId));
    }

    public List<UUID> getProfileCards(UUID profileId) {
        return repository.getData()
                .stream()
                .filter(pc -> pc.getProfileId().equals(profileId))
                .map(ProfileCard::getCardId)
                .toList();
    }

    public void delete(UUID cardId) {
        repository.update(
                getProfileCard()
                        .stream()
                        .filter(pc -> !pc.getCardId().equals(cardId))
                        .toList()
        );
    }
}
