package ui;

import entity.Profile;
import utils.ScannerUtil;


public class AdminUi {

    private final CardUi cardUi = new CardUi();
    private final TerminalUi terminalUi = new TerminalUi();

    public void run(Profile profile){
        while (true){
            switch (menu()){
                case 1 -> cardUi.run();
                case 2 -> terminalUi.run();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private int menu() {
        System.out.println("1.Card menu");
        System.out.println("2.Terminal menu");
        System.out.println("0.Exit");
        System.out.print(">>>>> ");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }
}
