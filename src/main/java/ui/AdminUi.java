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

    public void run(Profile profile){
        while (true){
            switch (menu()){
                case 1 -> cardUi.run();
                case 2 -> terminalUi.run();
                case 3 -> showProfileList();
                case 4 -> changeProfileStatus();
                case 5 -> transactionUi.transactionList();
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
            System.out.println("ID: " + profile.id());
            System.out.println("Name: " + profile.name());
            System.out.println("Surname: " + profile.surname());
            System.out.println("Phone: " + profile.phone());
            System.out.println("Password: " + profile.password());
            System.out.println("Card count: " + profile.cardCount());
            System.out.println("Status: " + profile.status());
            System.out.println("Created date: " + profile.createdDate());
            System.out.println();
        }
    }

    private int menu() {
        System.out.println("1.Card menu");
        System.out.println("2.Terminal menu");
        System.out.println("3.Profile list");
        System.out.println("4.Change profile status");
        System.out.println("5.Transaction list");
        System.out.println("0.Exit");
        System.out.print(">>>>> ");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }
}
