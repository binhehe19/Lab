public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.add("1. Create");
        menu.add("2. Find and Sort");
        menu.add("3. Update/Delete");
        menu.add("4. Report");
        menu.add("5. Exit");

        Manager manager = new Manager();

        while (true) {
            System.out.println("WELCOME TO STUDENT MANAGEMENT");
            menu.print();
            int userChoice = menu.getUserChoices();
            switch (userChoice) {
                case 1:
                    manager.createStudent();
                    break;
                case 2:
                    manager.findAndSort();
                    break;
                case 3:
                    manager.updateOrDelete();
                    break;
                case 4:
                    manager.report();
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                    break;
            }
        }
    }
}
