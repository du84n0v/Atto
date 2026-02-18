package ui;

import controller.AuthController;
import dto.LoginDto;
import dto.ProfileDto;
import dto.RegisterDto;
import entity.Profile;
import enums.Role;
import utils.ScannerUtil;

public class AuthUi {

    private final AuthController authController = new AuthController();
    private final AdminUi adminUi = new AdminUi();
    private final UserUi userUi = new UserUi();
    private final TransactionUi transactionUi = new TransactionUi();

    public void run() {

        while (true) {
            switch (menu()) {
                case 1 -> login();
                case 2 -> register();
                case 3 -> makePayment();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void makePayment() {
        System.out.print("Enter card number: ");
        String cardNumber = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter terminal code: ");
        String terminalCode = ScannerUtil.SCANNER_STR.next();

        System.out.println(transactionUi.makePayment(cardNumber, terminalCode));
    }

    public void login() {
        System.out.print("Enter phone number: ");
        String phone = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter password: ");
        String password = ScannerUtil.SCANNER_STR.next();

        Profile profile = authController.login(new LoginDto(phone, password));

        if(profile == null){
            System.out.println("Phone or password is incorrect. Please check");
            return ;
        }

        if(profile.getRole().equals(Role.ADMIN)){
            adminUi.run(profile);
        }
        else {
            userUi.run(profile);
        }
    }

    public void register() {
        System.out.print("Enter name : ");
        String name = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter surname : ");
        String surname = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter phone : ");
        String phone = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter password : ");
        String password = ScannerUtil.SCANNER_STR.next();

        String answer = authController.save(new RegisterDto(name, surname, phone, password));
        if(answer.equals("phoneExist")){
            System.out.println("This number already registrated");
        }
        else if(answer.equals("done")){
            System.out.println("Successfully registered");
        }
    }

    private int menu() {
        IO.println("1.Login");
        IO.println("2.Register");
        IO.println("3.Make Payment");
        IO.println("0.Exit");
        IO.print(">>>>> ");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }
}
