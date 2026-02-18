package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import entity.Terminal;

import java.util.List;

public class TerminalRepository extends BaseRepository<Terminal> {

    public TerminalRepository(){
        super("terminals.json", new TypeReference<List<Terminal>>(){});
    }
}
