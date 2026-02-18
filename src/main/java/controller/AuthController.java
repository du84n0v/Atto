package controller;

import dto.LoginDto;
import dto.RegisterDto;
import entity.Profile;
import service.AuthService;

public class AuthController {

    private final AuthService authService = new AuthService();

    public String save(RegisterDto profileDto) {
        return authService.save(profileDto);
    }

    public Profile login(LoginDto loginDto) {
        return authService.login(loginDto);
    }
}
