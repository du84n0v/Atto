package service;

import entity.ProfileCard;
import repository.ProfileCardRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProfileCardService {

    private final ProfileCardRepository repository = new ProfileCardRepository();

    public String assignCardToProfile(String cardNumber, UUID profileId) {
        if(isCardOwned(cardNumber)){
            return "Card already has owner";
        }

        ProfileCard profileCard = new ProfileCard();
        profileCard.setId(UUID.randomUUID());
        profileCard.setCreatedDate(LocalDateTime.now().toString());
        profileCard.setVisible(true);
        profileCard.setCardNumber(cardNumber);
        profileCard.setProfileId(profileId);

        repository.save(List.of(profileCard));

        return "Successfully assigned";
    }

    public List<ProfileCard> getProfileCard(){
        return repository.getData();
    }

    private boolean isCardOwned(String cardNumber) {
        return repository.getData()
                .stream()
                .anyMatch(pc -> pc.getCardNumber().equals(cardNumber));
    }

    public List<String> getProfileCardNumbers(UUID profileId) {
        return repository.getData()
                .stream()
                .filter(pc -> pc.getProfileId().equals(profileId))
                .map(ProfileCard::getCardNumber)
                .toList();
    }

    public void delete(String cardNumber) {
        repository.update(
                getProfileCard()
                        .stream()
                        .filter(pc -> !pc.getCardNumber().equals(cardNumber))
                        .toList()
        );
    }
}
