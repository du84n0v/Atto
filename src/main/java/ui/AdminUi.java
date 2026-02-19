package ui;

import controller.ProfileController;
import dto.ProfileDto;
import entity.Profile;
import utils.ScannerUtil;

import java.util.List;


public class AdminUi {

    private final CardUi cardUi = new CardUi();
    private final TerminalUi terminalUi = new TerminalUi();
    private final ProfileController profileController = new ProfileController();
    private final TransactionUi transactionUi = new TransactionUi();
    private final StatisticsUi statisticsUi = new StatisticsUi();

    public void run(Profile profile){
        while (true){
            switch (menu()){
                case 1 -> cardUi.run();
                case 2 -> terminalUi.run();
                case 3 -> showProfileList();
                case 4 -> changeProfileStatus();
                case 5 -> transactionUi.transactionList();
                case 6 -> cardUi.companyCardBalance();
                case 7 -> statisticsUi.run();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void changeProfileStatus() {
        System.out.print("Enter phone number: ");
        String phone = ScannerUtil.SCANNER_STR.next();

        System.out.println(profileController.changeProfileStatusByPhone(phone));
    }

    private void showProfileList() {
        List<ProfileDto> profiles = profileController.getAllProfile();
        int cnt = 1;
        for (ProfileDto profile : profiles) {
            System.out.println("Profile #" + (cnt++));
            System.out.println(profile.toString());
            System.out.println();
        }
    }

    private int menu() {
        System.out.println("1.Card menu");
        System.out.println("2.Terminal menu");
        System.out.println("3.Profile list");
        System.out.println("4.Change profile status");
        System.out.println("5.Transaction list");
        System.out.println("6.Company card balance");
        System.out.println("7.Statistics");
        System.out.println("0.Exit");
        System.out.print(">>>>> ");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }
}
