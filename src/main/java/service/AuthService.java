package service;

import dto.LoginDto;
import dto.ProfileDto;
import entity.Profile;

import java.time.LocalDateTime;
import java.util.UUID;

public class AuthService {

    private final ProfileService profileService = new ProfileService();

    public String save(ProfileDto profileDto) {
        return profileService.save(profileDto);
    }

    public Profile login(LoginDto loginDto) {
        return profileService.login(loginDto);
    }
}
