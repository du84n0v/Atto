package ui;

import controller.TerminalController;
import dto.TerminalDto;
import entity.Terminal;
import utils.ScannerUtil;

import java.util.List;

public class TerminalUi {

    private final TerminalController controller = new TerminalController();

    public void run(){
        while (true){
            switch (menu()){
                case 1 -> createTerminal();
                case 2 -> showAllTerminal();
                case 3 -> updateTerminalAddress();
                case 4 -> changeTerminalStatus();
                case 5 -> deleteTerminal();
                case 0 -> {
                    return;
                }
            }
        }
    }

    public int menu(){
        System.out.println("1.Create terminal");
        System.out.println("2.Show all terminal");
        System.out.println("3.Change terminal address");
        System.out.println("4.Change terminal status");
        System.out.println("5.Delete terminal");
        System.out.print(">>>> ");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }

    public void createTerminal(){
        System.out.print("Enter terminal number: ");
        String terminalNumber = ScannerUtil.SCANNER_STR.next();
        ScannerUtil.SCANNER_STR.nextLine();
        System.out.print("Enter address: ");
        String address = ScannerUtil.SCANNER_STR.nextLine();

        controller.save(new TerminalDto(terminalNumber, address));
    }

    public void showAllTerminal(){
        int cnt = 1;
        for (Terminal terminal : getAllTerminal()) {
            System.out.println("=====   Terminal #" + (cnt++) + "   =====");
            System.out.println(terminal.toString());
            System.out.println();
        }
    }

    public List<Terminal> getAllTerminal() {
         return controller.getAllTerminal();
    }

    public void updateTerminalAddress() {
        showAllTerminal();
        System.out.print("Enter terminal number: ");
        String num = ScannerUtil.SCANNER_STR.next();
        ScannerUtil.SCANNER_STR.nextLine();
        System.out.print("Enter new address: ");
        String address = ScannerUtil.SCANNER_STR.nextLine();

        controller.updateAddress(num, address);
    }

    public void changeTerminalStatus() {
        showAllTerminal();
        System.out.print("Enter terminal number: ");
        String terminalNumber = ScannerUtil.SCANNER_STR.next();
        controller.changeTerminalStatus(terminalNumber);
    }

    public void deleteTerminal() {
        showAllTerminal();
        System.out.print("Enter terminal number: ");
        String terminalNumber = ScannerUtil.SCANNER_STR.next();
        controller.deleteCard(terminalNumber);
    }
}
