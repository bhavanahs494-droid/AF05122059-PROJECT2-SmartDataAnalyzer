package analyzer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        DataService dataService = new DataService();
        AnalyticsService analyticsService = new AnalyticsService();

        int userId = -1;

        while (true) {

            System.out.println("\n==== SMART DATA ANALYZER ====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Add Data");
            System.out.println("4. View Data");
            System.out.println("5. Delete Data");
            System.out.println("6. Analytics");
            System.out.println("7. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Username: ");
                    String u1 = sc.next();
                    System.out.print("Password: ");
                    String p1 = sc.next();
                    userService.register(u1, p1);
                    break;

                case 2:
                    System.out.print("Username: ");
                    String u2 = sc.next();
                    System.out.print("Password: ");
                    String p2 = sc.next();

                    userId = userService.login(u2, p2);

                    if (userId != -1)
                        System.out.println("Login Successful!");
                    else
                        System.out.println("Invalid Credentials!");
                    break;

                case 3:
                    if (userId != -1)
                        dataService.addData(userId, sc);
                    else
                        System.out.println("Please login first!");
                    break;

                case 4:
                    dataService.viewData(userId);
                    break;

                case 5:
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt();
                    dataService.deleteData(id);
                    break;

                case 6:
                    analyticsService.showAnalytics(userId);
                    break;

                case 7:
                    System.exit(0);
            }
        }
    }
}
