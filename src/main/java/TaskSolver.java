import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tsypk on 13.04.2022 21:18
 * @project testTask
 */
public class TaskSolver {
    private final TaskReader reader;
    private final TaskWriter writer;
    private final PairsMatcher matcher;
    private int n, m;

    public TaskSolver(TaskReader reader, TaskWriter writer, PairsMatcher matcher) {
        this.reader = reader;
        this.writer = writer;
        this.matcher = matcher;
    }

    public void solve() {
        List<String> words1 = new ArrayList<>();
        List<String> words2 = new ArrayList<>();
        boolean isSwapped = false;
        init(words1, words2);
        if (n > m) {
            List<String> temp = words1;
            int tempInt = n;
            words1 = words2;
            words2 = temp;
            n = m;
            m = tempInt;
            isSwapped = true;
        }

        List<Pair<String, String>> pairs = matcher.match(words1, words2);

        for (Pair<String, String> pair : pairs) {
            String first = getFirstValue(pair, isSwapped);
            String second = getSecondValue(pair, isSwapped);
            writer.writeLine(first + " : " + second);
        }
    }


    private void init(List<String> words1, List<String> words2) {
        this.n = reader.readInt();
        for (int i = 0; i < n; ++i) {
            words1.add(String.join(" ", reader.readLine().split("\\s")));
        }

        this.m = reader.readInt();
        for (int i = 0; i < m; ++i) {
            words2.add(String.join(" ", reader.readLine().split("\\s")));
        }
    }

    private String getFirstValue(Pair<String, String> pair, boolean isSwapped) {
        if (!isSwapped)
            return pair.getKey() == null ? "?" : pair.getKey();
        return pair.getValue() == null ? "?" : pair.getValue();
    }

    private String getSecondValue(Pair<String, String> pair, boolean isSwapped) {
        if (!isSwapped)
            return pair.getValue() == null ? "?" : pair.getValue();
        return pair.getKey() == null ? "?" : pair.getKey();
    }
}
