package service;

import dto.LoginDto;
import dto.ProfileDto;
import entity.Profile;
import enums.Role;
import repository.ProfileRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProfileService {

    private final ProfileRepository profileRepository = new ProfileRepository();

    public String save(ProfileDto profileDto) {
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
        profile.setRole(Role.USER);

        profileRepository.save(List.of(profile));
        return "done";

    }

    private boolean phoneExist(String phone) {
        return profileRepository.getData()
                .stream()
                .anyMatch(profile -> profile.getPhone().equals(phone));
    }

    public Profile login(LoginDto loginDto) {
        return profileRepository.getData()
                .stream()
                .filter(profile -> profile.getPhone().equals(loginDto.phone()) &&
                        profile.getPassword().equals(loginDto.password()))
                .findFirst()
                .orElse(null);
    }
}
