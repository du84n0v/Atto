package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import entity.Profile;
import entity.Transactions;

import java.util.List;

public class TransactionRepository extends BaseRepository<Transactions> {

    public TransactionRepository(){
        super("transactions.json", new TypeReference<List<Transactions>>(){});
    }
}
