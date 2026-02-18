package controller;

import dto.ProfileDto;
import service.ProfileService;

import java.util.List;

public class ProfileController {

    private final ProfileService service = new ProfileService();

    public List<ProfileDto> getAllProfile() {
        return service.getAllProfile();
    }

    public String changeProfileStatusByPhone(String phone) {
        return service.changeProfileStatusByPhone(phone);
    }
}
