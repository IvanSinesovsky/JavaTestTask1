import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for User description
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int id = 100_000;
    private int        userId;
    private String     name;
    private String     surname;
    private String     email;
    private String[]   roles;
    private String[]   phoneNumbers;

    {   this.userId = id++; }

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
     * @return united User`s fields
     */
    public String appendToFile() {
        return userId + "\n" + name + '\n' + surname + '\n' + email + '\n'
                + arrayToString(roles) + '\n' + arrayToString(phoneNumbers) + '\n';
    }


    /**
     * @param string is User`s name or surname to check on valid
     * @return string if string != null
     * @return stringValidate(value from console) if string == null
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
     * Method allows check email address on valid
     * @param email is String to check
     * @return email if String contains '@' and '.'
     * @return emailValidate(email from console) if String value is wrong
     */
    private String emailValidate(String email) {
        boolean firstSymbol = false;
        boolean secondSymbol = false;
        Scanner scan = new Scanner(System.in);
        String newEmail;

        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                firstSymbol = true;
            }
            if (email.charAt(i) == '.') {
                secondSymbol = true;
            }
            if (firstSymbol & secondSymbol) {
                return email;
            }
        }

        System.out.println("Incorrect email! Please, try again: ");
        newEmail = scan.next();

        return emailValidate(newEmail);
    }

    /**
     * Method allows check roles on valid
     * @param roles is Strings to check
     * @return roles if (1 <= roles.length <= 3)
     * @return rolesValidate(roles from console) if roles.length is wrong
     */
    private String[] rolesValidate(String[] roles) {
        try {
            assert roles[0] != null && roles.length < 4 && roles.length > 0;
            return roles;
        } catch (AssertionError e) {
            int rolesNumber;
            String[] newRoles;

            do {
                System.out.println("Incorrect roles number value! Please, entry new roles number: ");
                rolesNumber = numberFromConsole();
            } while (rolesNumber > 3 || rolesNumber < 1);

            newRoles = new String[rolesNumber];

            for (int i = 0; i < rolesNumber; i++) {
                newRoles[i] = stringFromConsole();
            }

            return rolesValidate(newRoles);
        }
    }

    /**
     * Method allows check phone numbers on valid
     * @param phoneNumbers is Strings to check
     * @return phoneNumbers if (1 <= phoneNumbers.length <= 3) and all numbers are correct (for example, 37500 1234567)
     * @return phoneNumbersValidate(phone numbers from console) if phoneNumbers.length is wrong or
     */
    private String[] phoneNumbersValidate(String[] phoneNumbers) {
        for (int i = 0; i < phoneNumbers.length; i++) {
            if ((phoneNumbers.length > 3 || phoneNumbers.length < 1)
                    || !checkOnePhoneNumber(phoneNumbers[i])) {
                int numbersNumber;
                String[] newPhoneNumbers;

                do {
                    System.out.println("Incorrect phone number input. Please, entry new phone numbers number: ");
                    numbersNumber = numberFromConsole();
                } while (numbersNumber > 3 || numbersNumber < 1);

                newPhoneNumbers = new String[numbersNumber];

                for (int j = 0; j < numbersNumber; j++) {
                    newPhoneNumbers[j] = stringFromConsole();
                }

                return phoneNumbersValidate(newPhoneNumbers);
            }
        }
        return phoneNumbers;
    }

    /**
     * Method allows check one phone number(@param number) on valid
     * @return true if number is correct
     */
    private boolean checkOnePhoneNumber(String number) {
        Pattern pattern = Pattern.compile("^((375)([0-9]{2})(\\s)([0-9]{7}))$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    /**
     * Method allows scan integer line in console and return it
     * @return value from console
     */
    private int numberFromConsole() {
        Scanner scan = new Scanner(System.in);
        int numbersNumber = scan.nextInt();
        return numbersNumber;
    }

    /**
     * Method allows scan String line in console and return it
     * @return String from console
     */
    private String stringFromConsole() {
        System.out.println("Please, entry new value: ");
        Scanner scan = new Scanner(System.in);
        String number = scan.nextLine();
        return number;
    }


    /**
     * Method allows convert String[] to String adding separators
     * @param elements is array to convert
     * @return String of separated with ',' n String[n] elements
     */
    private String arrayToString(String[] elements) {
        StringBuilder stringBuilder = new StringBuilder(36);
        for (String element : elements) {
            stringBuilder.append(element).append(',');
        }
        return stringBuilder.toString();
    }

    /**
     * Getters and setters
     */
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public String[] getRoles() {
        return roles;
    }
    public String[] getPhoneNumbers() {
        return phoneNumbers;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}