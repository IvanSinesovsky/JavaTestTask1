import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file1 = new File("users1.txt");

        User user1 = new User(new String("Ivan"), new String("Ivanov"),
                              new String("ivanivanov@bsu.com"), new String[]{"Student"},
                                         new String[]{"37500 1234567", "37500 3234567", "37500 2234567"});


        User user2 = new User(new String("Petr"), new String("Petrov"),
                              new String("petrpetrov@bsu.com"), new String[]{"Employee", "lecturer"},
                                         new String[]{"37501 2345678"});


        User user3 = new User(new String("Sergey"), new String("Sergeev"),
                              new String("sergeysergeev@bsu.com"), new String[]{"Employee", "Dean"},
                                         new String[]{"37512 3456789", "37523 4567890", "37534 5678901"});


        User user4 = new User(new String("Roman"), new String("Romanov"),
                              new String("romanromanov@bsu.com"), new String[]{"Listener"},
                                         new String[]{"37545 6789012"});


        User user5 = new User(new String("Pavel"), new String("Pavlov"),
                              new String("pavelpavlov@bsu.com"), new String[]{"None"},
                                         new String[]{"37556 7890123"});


        User user6 = new User(new String("Aleksey"), new String("Alekseev"),
                              new String("alexeyalexeev@bsu.com"), new String[]{"Etrant"},
                                         new String[]{"37567 8901234"});

        WriteUsers writeUsers = new WriteUsers(file1);
        ReadUsers readUsers = new ReadUsers(file1);
        readUsers.fileClear();

        writeUsers.userAdd(user1);
        writeUsers.userAdd(user2);
        writeUsers.userAdd(user3);
        writeUsers.userAdd(user4);
        writeUsers.userAdd(user5);
        writeUsers.userAdd(user6);

        readUsers.allUsersOutputInformation();

        readUsers.editUser("Ivan", "Petrov", writeUsers, "edited@gmail.com");
        readUsers.editUser("Petr", "Petrov", writeUsers, "editedemail@gmail.com");
        readUsers.deleteUser("Aleksey", "Alekseev");

        readUsers.allUsersOutputInformation();

        readUsers.someUserOutputInformation("Pavel", "Pavlov");


    }
}
