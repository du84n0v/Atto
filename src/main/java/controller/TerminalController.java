package controller;

import dto.TerminalDto;
import entity.Terminal;
import service.TerminalService;

import java.util.List;

public class TerminalController {

    private final TerminalService service = new TerminalService();

    public void save(TerminalDto terminalDto) {
        service.save(terminalDto);
    }

    public List<Terminal> getAllTerminal() {
        return service.getAllTerminal();
    }

    public void updateAddress(String num, String address) {
        service.updateAddress(num, address);
    }

    public void changeTerminalStatus(String terminalNumber) {
        service.changeTerminalStatus(terminalNumber);
    }

    public void deleteCard(String terminalNumber) {
        service.deleteTerminal(terminalNumber);
    }
}
