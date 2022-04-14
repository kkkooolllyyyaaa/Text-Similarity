import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tsypk on 13.04.2022 21:18
 * @project testTask
 */
public class TaskSolver {
    private final TaskReader reader;
    private final TaskWriter writer;
    private final LewensteinLength lewenstein;
    private int n, m;

    public TaskSolver(TaskReader reader, TaskWriter writer, LewensteinLength lewenstein) {
        this.reader = reader;
        this.writer = writer;
        this.lewenstein = lewenstein;
    }

    public void solve() {
        List<List<String>> words1 = new ArrayList<>();
        List<List<String>> words2 = new ArrayList<>();
        init(words1, words2);
        if (n > m) {
            List<List<String>> temp = words1;
            int tempInt = n;
            words1 = words2;
            words2 = temp;
            n = m;
            m = tempInt;
        }

        int[] answer = findPairs(words1, words2);
        boolean[] used = new boolean[m];
        for (int i = 0; i < n; ++i) {
            String first = String.join(" ", words1.get(i));
            String second = String.join(" ", words2.get(answer[i]));
            writer.writeLine(first + " : " + second);
            used[answer[i]] = true;
        }

        for (int i = 0; i < m; ++i) {
            if (used[i])
                continue;
            String str = String.join(" ", words2.get(i));
            writer.writeLine(str + " : ?");
        }
    }


    private int[] findPairs(List<List<String>> words1, List<List<String>> words2) {
        int n = words1.size(), m = words2.size();

        int[] answer = new int[n];
        String[] sortedWords2 = new String[m];
        boolean[] used = new boolean[m];

        for (int j = 0; j < m; ++j) {
            sortedWords2[j] = (
                    words2.get(j).stream().sorted().collect(Collectors.joining(" "))
            );
        }

        for (int i = 0; i < n; ++i) {
            int mi = Integer.MAX_VALUE, index = -1;
            String sortedWords1 = words1.get(i).stream().sorted().collect(Collectors.joining(" "));
            for (int j = 0; j < m; ++j) {
                if (used[j])
                    continue;
                int cnt = lewenstein.calculateLength(sortedWords1, sortedWords2[j]);
                if (cnt < mi) {
                    mi = cnt;
                    index = j;
                }
            }
            answer[i] = index;
            used[index] = true;
        }
        return answer;
    }

    private void init(List<List<String>> words1, List<List<String>> words2) {
        this.n = reader.readInt();
        for (int i = 0; i < n; ++i) {
            words1.add(Arrays.stream(reader.readLine().split("\\s")).collect(Collectors.toList()));
        }
        this.m = reader.readInt();
        for (int i = 0; i < m; ++i) {
            words2.add(Arrays.stream(reader.readLine().split("\\s")).collect(Collectors.toList()));
        }
    }
}
