package service;

import dto.LoginDto;
import dto.ProfileDto;
import dto.RegisterDto;
import entity.Profile;
import enums.Role;
import enums.Status;
import repository.ProfileRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProfileService {

    private final ProfileRepository repository = new ProfileRepository();
    private final CardService cardService = new CardService();

    public String save(RegisterDto profileDto) {
        if(phoneExist(profileDto.phone())){
            return "phoneExist";
        }

        Profile profile = new Profile();

        profile.setId(UUID.randomUUID());
        profile.setCreatedDate(LocalDateTime.now().toString());
        profile.setVisible(true);
        profile.setName(profileDto.name());
        profile.setSurname(profileDto.surname());
        profile.setPhone(profileDto.phone());
        profile.setPassword(profileDto.password());
        profile.setStatus(Status.ACTIVE);
        profile.setRole(Role.USER);

        repository.save(List.of(profile));
        return "done";

    }

    private boolean phoneExist(String phone) {
        return repository.getData()
                .stream()
                .anyMatch(profile -> profile.getPhone().equals(phone));
    }

    public Profile login(LoginDto loginDto) {
        return repository.getData()
                .stream()
                .filter(profile -> profile.getPhone().equals(loginDto.phone()) &&
                        profile.getPassword().equals(loginDto.password()))
                .findFirst()
                .orElse(null);
    }

    public List<ProfileDto> getAllProfile(){
        List<Profile> profiles = repository.getData();

        return profiles.stream().map(profile -> {

            int count = cardService.getProfileCards(profile.getId()).size();

            return new ProfileDto(
                    profile.getId(),
                    profile.getName(),
                    profile.getSurname(),
                    profile.getPhone(),
                    profile.getPassword(),
                    count,
                    profile.getStatus(),
                    profile.getCreatedDate()
            );})
                .toList();
    }

    public String changeProfileStatusByPhone(String phone) {
        List<Profile> profiles = repository.getData();
        for (Profile profile : profiles) {
            if(profile.getPhone().equals(phone)){
                profile.setStatus(profile.getStatus().equals(Status.ACTIVE) ? Status.BLOCKED : Status.ACTIVE);
                repository.update(profiles);
                return "Successfully changed";
            }
        }
        return "Profile not found";
    }
}
