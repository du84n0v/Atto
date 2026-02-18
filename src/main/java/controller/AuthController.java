package controller;

import dto.LoginDto;
import dto.ProfileDto;
import entity.Profile;
import service.AuthService;

public class AuthController {

    private final AuthService authService = new AuthService();

    public String save(ProfileDto profileDto) {
        return authService.save(profileDto);
    }

    public Profile login(LoginDto loginDto) {
        return authService.login(loginDto);
    }
}
