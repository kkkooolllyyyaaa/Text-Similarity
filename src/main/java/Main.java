/**
 * @author tsypk on 13.04.2022 04:22
 * @project testTask
 */
public class Main {
    public static void main(String[] args) {
        TaskReader reader = new TaskReader("input.txt");
        TaskWriter writer = new TaskWriter("output.txt");
        EditDistance editDistance = new LewensteinDistance();
        TaskSolver solver = new TaskSolver(reader, writer, new PairsMatcher(editDistance));
        try {
            solver.solve();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
