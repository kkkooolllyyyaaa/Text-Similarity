import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author tsypk on 13.04.2022 21:11
 * @project testTask
 */
public class TaskReader {
    private final Scanner in;

    public TaskReader(String filename) {
        try {
            this.in = new Scanner(new FileInputStream(filename));
            in.useDelimiter("\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String readLine() {
        return in.nextLine();
    }

    public int readInt() {
        int res = in.nextInt();
        in.nextLine();
        return res;
    }
}
