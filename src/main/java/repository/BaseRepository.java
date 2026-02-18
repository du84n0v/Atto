package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BaseRepository<T>{
    private final File file;
    private final ObjectMapper objectMapper;
    private final TypeReference<List<T>> type;

    public BaseRepository(String fileName, TypeReference type) {
        file = new File(fileName);
        this.type = type;
        objectMapper = new ObjectMapper();
        initFile();
    }

    private void initFile() {
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Messege: " + file.getName() + ": " + e.getMessage());
            }
        }
    }

    public void save(List<T> data){
        List<T> list = getData();
        try {
            list.addAll(data);
            objectMapper.writeValue(file, list);
        } catch (IOException e) {
            throw new RuntimeException("Messege: " + file.getName() + ": " + e.getMessage());
        }
    }

    public List<T> getData(){
        try {
            List<T> list = objectMapper.readValue(file, type);
            return (list != null) ? new LinkedList<>(list) : new LinkedList<>();
        } catch (IOException e) {
            return new LinkedList<>();
        }
    }

    public void update(List<T> data){
        try {
            objectMapper.writeValue(file, data);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}


