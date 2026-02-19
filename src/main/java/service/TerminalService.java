package service;

import dto.TerminalDto;
import entity.Card;
import entity.Terminal;
import enums.Status;
import repository.TerminalRepository;

import java.time.LocalDateTime;
import java.util.*;

public class TerminalService {

    private final TerminalRepository repository = new TerminalRepository();

    public void save(TerminalDto terminalDto) {
        Terminal terminal = new Terminal();

        terminal.setId(UUID.randomUUID());
        terminal.setCreatedDate(LocalDateTime.now().toString());
        terminal.setVisible(true);
        terminal.setStatus(Status.ACTIVE);
        terminal.setNumber(terminalDto.terminalNumber());
        terminal.setAddress(terminalDto.address());

        repository.save(List.of(terminal));
    }

    public List<Terminal> getAllTerminal() {
        return repository.getData();
    }

    public void updateAddress(String num, String address) {
        List<Terminal> terminals = new LinkedList<>();
        for (Terminal terminal : getAllTerminal()) {
            if(terminal.getNumber().equals(num)){
                terminal.setAddress(address);
            }
            terminals.add(terminal);
        }
        repository.update(terminals);
    }

    public void changeTerminalStatus(String terminalNumber) {
        List<Terminal> terminals = getAllTerminal();
        for (Terminal terminal : terminals) {
            if(terminal.getNumber().equals(terminalNumber)){
                terminal.setStatus(terminal.getStatus().equals(Status.ACTIVE) ? Status.BLOCKED : Status.ACTIVE);
            }
        }
        repository.update(terminals);
    }

    public void deleteTerminal(String terminalNumber) {
        repository.update(
                getAllTerminal()
                        .stream()
                        .filter(terminal -> !terminal.getNumber().equals(terminalNumber))
                        .toList()

        );
    }

    public boolean isActiveTerminal(String terminalCode) {
        return repository.getData()
                .stream()
                .anyMatch(terminal -> terminal.getNumber().equals(terminalCode) &&
                        terminal.getStatus().equals(Status.ACTIVE));
    }

    public Terminal getTerminalByTerminalCode(String terminalCode) {
        return repository.getData()
                .stream()
                .filter(terminal -> terminal.getNumber().equals(terminalCode))
                .findFirst()
                .orElse(null);
    }

    public Map<String, String> getTerminalAddress() {
        Map<String, String> response = new HashMap<>();
        for (Terminal datum : repository.getData()) {
            response.put(datum.getNumber(), datum.getAddress());
        }
        return response;
    }
}
