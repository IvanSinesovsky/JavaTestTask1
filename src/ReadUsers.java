import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for Users reading from file
 */
public class ReadUsers {
    ArrayList<User> users;
    private File    file;

    public ReadUsers(File file1) {
        this.file = file1;
    }

    public void allUsersOutputInformation() {
        System.out.println("\nALL USERS INFORMATION OUTPUT\n");
        fileToArrayList();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).toString());
        }
    }

    /**
     * Method allows clear file without removing it
     */
    public void fileClear() throws FileNotFoundException {
        try {
            FileWriter fw = new FileWriter(file, false);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method allows remove User by name and surname
     */
    public void deleteUser(String name, String surname) {
        fileToArrayList();
        int i = searchUser(name, surname);

        if (i > -1) {
            users.remove(i);
            System.out.println("\nUser \"" + name + " " + surname + "\" successfully deleted.\n");
        } else {
            System.out.println("\nCannot found such User \"" + name + "\" and surname \"" + surname + "\"\n");
        }

        reWriteFile();
    }

    /**
     * Method allows output information about User by name and surname
     */
    public void someUserOutputInformation(String name, String surname) {
        fileToArrayList();

        int i = searchUser(name, surname);

        System.out.println("\nInformation about " + name + " " + surname + " :\n"
                           + users.get(i).toString() + "\n");
    }

    /**
     * Method allows edit User`s email address
     */
    public void editUser(String name, String surname, WriteUsers toEdit, String email) {
        try {
            User user = deleteAndReturnUser(name, surname);
            User userToEdit = new User(name, surname, email, user.getRoles(), user.getPhoneNumbers());

            userToEdit.setUserId(user.getUserId());
            toEdit.userAdd(userToEdit);

            System.out.println("\nUser`s \"" + name + "\" \"" + surname + " email has been successfully changed.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nCannot found User with such name \"" + name + "\" and surname \"" + surname + "\"\n");
        }
    }

    /**
     * Method allows edit User`s roles
     */
    public void editUser(String name, String surname, WriteUsers toEdit, String[] roles) {
        try {
            User user = deleteAndReturnUser(name, surname);
            User userToEdit = new User(name, surname, user.getEmail(), roles, user.getPhoneNumbers());

            userToEdit.setUserId(user.getUserId());
            toEdit.userAdd(userToEdit);

            System.out.println("\nUser`s \"" + name + "\" \"" + surname + " roles have been successfully changed.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nCannot found User with such name \"" + name + "\" and surname \"" + surname + "\"\n");
        }
    }

    /**
     * Method allows edit User`s phone numbers
     */
    public void editUser(String name, String surname, String[] phoneNumbers, WriteUsers toEdit) {
        try {
            User user = deleteAndReturnUser(name, surname);
            User userToEdit = new User(name, surname, user.getEmail(), user.getRoles(), phoneNumbers);

            userToEdit.setUserId(user.getUserId());
            toEdit.userAdd(userToEdit);

            System.out.println("\nUser`s \"" + name + "\" \"" + surname + " roles has been successfully changed.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nCannot found User with such name \"" + name + "\" and surname \"" + surname + "\"\n");
        }
    }


    /**
     * Converts Users from file to users field
     */
    private void fileToArrayList() {
        users = null;
        try {
            FileReader fr = new FileReader(file);
            Scanner scan = new Scanner(fr);

            users = fileParse(scan);

            scan.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse Users from method`s fileToArrayList() Scanner to ArrayList<User>
     */
    private ArrayList<User> fileParse(Scanner scan) {
        ArrayList<User> alu = new ArrayList<>(50);
        int temp = 0;
        String tempStr = null;
        while(scan.hasNext()) {
            try {
                tempStr = scan.nextLine();
                temp = Integer.parseInt(tempStr);

            } catch (NumberFormatException e) {
                String name = tempStr;
                String surname = scan.nextLine();
                String email = scan.nextLine();
                String[] roles = templateToArray(scan.nextLine());
                String[] phoneNumbers = templateToArray(scan.nextLine());

                User user = new User(name, surname, email, roles, phoneNumbers);

                user.setUserId(temp);
                alu.add(user);
            }
        }
        return alu;
    }

    /**
     * Method allows search User by name and surname
     * @return User`s index from users field
     */
    private int searchUser(String name, String surname) {
        fileToArrayList();
        User tempUser = null;
        for(int i = 0; i < users.size(); i++) {
            tempUser = users.get(i);
            if (name.equals(tempUser.getName()) && surname.equals(tempUser.getSurname())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method allows fill file by users field elements
     */
    private void reWriteFile() {
        try {
            fileClear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        WriteUsers writeUsers = new WriteUsers(file);
        for (int i = 0; i < users.size(); i++)
            writeUsers.userAdd(users.get(i));
    }

    /**
     * Method allows convert String with ',' separator to String[]
     * @param templateLine is String with ','
     * @return String[] without separators
     */
    private String[] templateToArray(String templateLine) {
        int commaNumber = 0;
        String[] templateArray;

        for(int i = 0; i < templateLine.length(); i++) {
            if (templateLine.charAt(i) == ',') {
                commaNumber++;
            }
        }

        templateArray = new String[commaNumber];

        StringBuilder tillComma = new StringBuilder(12);
        for (int i = 0, j = 0; i < commaNumber; i++, j++) {
            while (templateLine.charAt(j) != ',') {
                tillComma.append(templateLine.charAt(j));
                j++;
            }
            templateArray[i] = tillComma.toString();
            tillComma.setLength(0);
        }

        return templateArray;
    }

    /**
     * This method deletes from file and returns it
     * Then editUser() method appends this User to file with some changes
     * @return User to save it`s id
     */
    private User deleteAndReturnUser(String name, String surname) {
        fileToArrayList();
        int i = searchUser(name, surname);

        User user = users.get(i);
        users.remove(i);

        reWriteFile();

        return user;
    }
}