/**
 * @author tsypk on 13.04.2022 04:22
 * @project testTask
 */
public class Main {
    public static void main(String[] args) {
        TaskReader reader = new TaskReader("input.txt");
        TaskWriter writer = new TaskWriter("output.txt");
        LewensteinLength lewensteinLength = new LewensteinLength();
        TaskSolver solver = new TaskSolver(reader, writer, lewensteinLength);
        try {
            solver.solve();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
