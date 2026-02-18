package service;

import dto.LoginDto;
import dto.RegisterDto;
import entity.Profile;

public class AuthService {

    private final ProfileService profileService = new ProfileService();

    public String save(RegisterDto profileDto) {
        return profileService.save(profileDto);
    }

    public Profile login(LoginDto loginDto) {
        return profileService.login(loginDto);
    }
}
