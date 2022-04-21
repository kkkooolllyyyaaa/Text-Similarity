import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tsypk on 21.04.2022 19:29
 * @project testTask
 */
public class PairsMatcher {
    private final EditDistance editDistance;

    public PairsMatcher(EditDistance editDistance) {
        this.editDistance = editDistance;
    }

    List<Pair<String, String>> match(List<String> words1, List<String> words2) {
        if (words1.size() > words2.size()) {
            throw new RuntimeException("Invalid arguments order");
        }
        List<Pair<String, String>> pairs = new ArrayList<>();

        int m = words2.size();
        boolean[] used = new boolean[m];

        for (String s : words1) {
            int mi = Integer.MAX_VALUE, index = -1;
            for (int j = 0; j < m; ++j) {
                if (used[j])
                    continue;
                int cnt = editDistance.calculateLength(s, words2.get(j));
                if (cnt < mi) {
                    mi = cnt;
                    index = j;
                }
            }
            pairs.add(new Pair<>(s, words2.get(index)));
            used[index] = true;
        }
        for (int i = 0; i < m; ++i) {
            if (!used[i])
                pairs.add(new Pair<>(null, words2.get(i)));
        }
        return pairs;
    }
}
