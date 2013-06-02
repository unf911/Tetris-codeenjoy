import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 27.05.13
 * Time: 2:11
 * To change this template use File | Settings | File Templates.
 */
public class CompareByPriority implements Comparator<Move> {

    private final Figure fig;

    public CompareByPriority(Figure fig) {
        this.fig = fig;
    }

    @Override
    public int compare(Move o1, Move o2) {
        int priority1 = fig.priorityOfRotations().indexOf(o1.rotate);
        int priority2 = fig.priorityOfRotations().indexOf(o2.rotate);
        if (priority1 < priority2)
            return -1;
        if (priority1 == priority2)
            return 0;
        return 1;
    }
}

