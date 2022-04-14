import java.io.FileWriter;
import java.io.IOException;

/**
 * @author tsypk on 13.04.2022 21:15
 * @project testTask
 */
public class TaskWriter {
    private final FileWriter writer;

    public TaskWriter(String filename) {
        try {
            this.writer = new FileWriter(filename);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void writeLine(String line) {
        try {
            writer.write(line + "\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
