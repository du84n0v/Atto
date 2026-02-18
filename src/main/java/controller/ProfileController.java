package controller;

import dto.ProfileDto;
import service.ProfileService;

import java.util.List;

public class ProfileController {

    private final ProfileService service = new ProfileService();

    public List<ProfileDto> getAllProfile() {
        return service.getAllProfile();
    }
}
