package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import entity.Profile;

import java.util.List;

public class ProfileRepository extends BaseRepository<Profile> {

    public ProfileRepository(){
        super("profiles.json", new TypeReference<List<Profile>>(){});
    }
}
