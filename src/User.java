import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for User description
 */
public class User implements Serializable {
    private String name = null;
    private String surname = null;
    private String email = null;
    private String[] roles = null;
    private String[] phoneNumbers = null;
    private static int id = 100_000;
    private int userId;

    {this.userId = id++;}

    public User(String name, String surname, String email, String[] roles, String[] phoneNumbers) {
        this.name = stringValidate(name);

        this.surname = stringValidate(surname);

        this.email = emailValidate(email);

        this.roles = rolesValidate(roles);

        this.phoneNumbers = phoneNumbersValidate(phoneNumbers);
    }

    @Override
    public String toString() {
        return "Id: " + userId + '\n' + "Name: " + name + '\n' + "Surname: " + surname + '\n' + "Email: " + email + '\n'
                + "Roles: " + arrayToString(roles) + '\n' + "Phone numbers: " + arrayToString(phoneNumbers);
    }

    /**
     * Method for append User fields to file
     * @return
     */
    public String appendToFile() {
        return userId + "\n" + name + '\n' + surname + '\n' + email + '\n' +
                arrayToString(roles) + '\n' + arrayToString(phoneNumbers) + '\n';
    }

    /**
     * Checking name and surname for valid
     */
    private String stringValidate(String string) {
        try {
            assert string != null;
            return string;
        } catch (AssertionError e) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Incorrect value! Please, try again: ");
            return stringValidate(scan.nextLine());
        }
    }

    /**
     * Method allows check email address for valid (checks for '@' and '.')
     */
    private String emailValidate(String email) {
        boolean firstSymbol = false;
        boolean secondSymbol = false;
        for (int i = 0; i < email.length(); i++)
            if (email.charAt(i) == '@') {
                firstSymbol = true;
                break;
            }
        for (int i = 0; i < email.length(); i++)
            if (email.charAt(i) == '.') {
                secondSymbol = true;
                break;
            }
        if (firstSymbol & secondSymbol)
            return email;
        else {
            Scanner scan = new Scanner(System.in);
            System.out.println("Incorrect email! Please, try again: ");
            String newEmail = scan.next();
            return emailValidate(newEmail);
        }
    }

    /**
     * Checking roles for valid
     */
    private String[] rolesValidate(String[] roles) {
        try {
            assert roles[0] != null && roles.length < 4 && roles.length > 0;
        } catch (AssertionError e) {
            System.out.println("Incorrect roles values! Please, entry roles number: ");
            int rolesNumber = numberFromConsole();
            while (rolesNumber > 3 || rolesNumber < 1) {
                System.out.println("Incorrect roles values! Please, entry roles number: ");
                rolesNumber = numberFromConsole();
            }
            String[] newRoles = new String[rolesNumber];
            for (int i = 0; i < rolesNumber; i++)
                newRoles[i] = stringFromConsole();
            return rolesValidate(newRoles);
        }
        return roles;
    }

    /**
     * Method allows check phone number for valid (for example, 37500 1234567)
     */
    private String[] phoneNumbersValidate(String[] phoneNumbers) {
        for (int i = 0; i < phoneNumbers.length; i++) {
            if (!checkOnePhoneNumber(phoneNumbers[i])) {
                System.out.println("Incorrect value! Please, input phone numbers number: ");
                int numbersNumber = numberFromConsole();
                while(numbersNumber > 3 || numbersNumber < 1) {
                    System.out.println("Incorrect phone number(s) input. Please, input phone numbers number: ");
                    numbersNumber = numberFromConsole();
                }

                String[] newPhoneNumbers = new String[numbersNumber];
                for (int j = 0; j < numbersNumber; j++)
                    newPhoneNumbers[j] = stringFromConsole();
                return phoneNumbersValidate(newPhoneNumbers);
            }
        }
        return phoneNumbers;
    }

    /**
     * Using regEx to check one phone number for valid
     */
    private boolean checkOnePhoneNumber(String number) {
        Pattern pattern = Pattern.compile("^((375)([0-9]{2})(\\s)([0-9]{7}))$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    /**
     * Method allows scan integer line in console and return it
     */
    private int numberFromConsole() {
        Scanner scan = new Scanner(System.in);
        int numbersNumber = scan.nextInt();
        return numbersNumber;
    }

    /**
     * Method allows scan String line in console and return it
     */
    private String stringFromConsole() {
        System.out.println("Please, entry some value: ");
        Scanner scan = new Scanner(System.in);
        String number = scan.nextLine();
        return number;
    }

    /**
     * @param elements is array to convert
     * @return String of separated with ',' n String[n] elements
     */
    private String arrayToString(String[] elements) {
        StringBuilder stringBuilder = new StringBuilder(36);
        for (String element : elements)
            stringBuilder.append(element).append(',');
        return stringBuilder.toString();
    }

    /**
     * Getters and setters
     */
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String[] getRoles() {
        return roles;
    }
    public void setRoles(String[] roles) {
        this.roles = roles;
    }
    public String[] getPhoneNumbers() {
        return phoneNumbers;
    }
    public void setPhoneNumbers(String[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}