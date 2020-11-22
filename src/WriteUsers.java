import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/**
 * Class for Users writing to file
 */
public class WriteUsers {
    private File file;

    public WriteUsers(File file) {
        this.file = file;
    }

    /**
     * Method allows append User to file
     * @param newUser is User to append to file
     */
    public void userAdd(User newUser) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.append(newUser.appendToFile());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
