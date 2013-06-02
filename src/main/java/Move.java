import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 26.05.13
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */
public class Move {

    public Move(int offset, int rotate) {
        this.offset = offset;
        this.rotate = rotate;
    }
    int offset;
    int rotate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (offset != move.offset) return false;
        if (rotate != move.rotate) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = offset;
        result = 31 * result + rotate;
        return result;
    }

    @Override
    public String toString() {
        return "Move{" +
                "offset=" + offset +
                ", rotate=" + rotate +
                '}';
    }


}
