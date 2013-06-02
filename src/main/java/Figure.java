import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anefedov
 * Date: 5/17/13
 * Time: 6:20 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Figure {
    O {
        @Override
        public int getHole(GlassParser glassParser) {
            return (Integer) glassParser.getTunnel(2).toArray()[0];
        }

        @Override
        public int drop() {
            return 1;
        }

        @Override
        public List<List<Integer>> diffs() {
            List<Integer> oneSideDiff = new ArrayList<Integer>(Arrays.asList(0));
            ArrayList<List<Integer>> listArrayList = new ArrayList<List<Integer>>();
            listArrayList.add(oneSideDiff);
            return listArrayList;
        }

    },
    I {
        @Override
        public int getHole(GlassParser glassParser) {
            return (Integer) glassParser.getTunnel(1).toArray()[0];
        }

        @Override
        public List<List<Integer>> diffs() {
            ArrayList<List<Integer>> listArrayList = new ArrayList<List<Integer>>();
            listArrayList.add(Collections.EMPTY_LIST);
            listArrayList.add(new ArrayList<Integer>(Arrays.asList(0,0,0)));
            return listArrayList;
        }

        @Override
        public int drop () {
            return 1;
        }

        @Override
        public List<Integer> priorityOfRotations() {
            return new ArrayList<Integer>(Arrays.asList(1,0));
        }

        @Override
        public Move correctMove(Move move) {
            Move move1 = new Move(move.offset, move.rotate);
            if ((move.rotate==1)) {
                move1.offset = move.offset+1;
            }
            return move1;
        }

    },
    J {
        @Override
        public int getHole(GlassParser glassParser) {
            return (Integer) glassParser.getTunnel(2).toArray()[0] + 1;
            //return objects;
        }

        @Override
        public List<List<Integer>> diffs() {
            ArrayList<List<Integer>> listArrayList = new ArrayList<List<Integer>>();
            listArrayList.add(new ArrayList<Integer>(Arrays.asList(0)));
            listArrayList.add(new ArrayList<Integer>(Arrays.asList(0, 0)));
            listArrayList.add(new ArrayList<Integer>(Arrays.asList(2)));
            listArrayList.add(new ArrayList<Integer>(Arrays.asList(0, -1)));
            return listArrayList;
        }

        @Override
        public Move correctMove(Move move) {
            Move move1 = new Move(move.offset, move.rotate);
            //
            if ((move.rotate==0)|| (move.rotate==3)|| (move.rotate==1)) {
                move1.offset = move.offset+1;
            }
            return move1;
        }

        @Override
        public List<Integer> priorityOfRotations() {
            return new ArrayList<Integer>(Arrays.asList(3,2,0,1));
        }

        @Override
        public int drop () {
            return 1;
        }

    },
    L {
        @Override
        public int getHole(GlassParser glassParser) {
            return (Integer) glassParser.getTunnel(2).toArray()[0];
            //return objects;
        }

        //        @Override
//        public int drop () {
//            return 1;
//        }
        @Override
        public List<List<Integer>> diffs() {
            ArrayList<List<Integer>> listArrayList = new ArrayList<List<Integer>>();
            listArrayList.add(new ArrayList<Integer>(Arrays.asList(0)));
            listArrayList.add(new ArrayList<Integer>(Arrays.asList(1,0)));

            listArrayList.add(new ArrayList<Integer>(Arrays.asList(-2)));
            listArrayList.add(new ArrayList<Integer>(Arrays.asList(0, 0)));

            return listArrayList;
        }

        @Override
        public Move correctMove(Move move) {
            Move move1 = new Move(move.offset, move.rotate);
            if ((move.rotate==2)|| (move.rotate==3) || (move.rotate==0)) {
                move1.offset = move.offset+1;
            }
            return move1;
        }

        @Override
        public List<Integer> priorityOfRotations() {
            return new ArrayList<Integer>(Arrays.asList(1,3,2,0));
        }

    },

    S {
        @Override
        public List<List<Integer>> diffs() {
            ArrayList<List<Integer>> listArrayList = new ArrayList<List<Integer>>();
            listArrayList.add(new ArrayList<Integer>(Arrays.asList(0,1,0)));
            listArrayList.add(new ArrayList<Integer>(Arrays.asList(-1)));


            return listArrayList;
        }

        @Override
        public List<Integer> priorityOfRotations() {
            return new ArrayList<Integer>(Arrays.asList(0,1));
        }

    } ,

    Z {
        @Override
        public List<List<Integer>> diffs() {
            ArrayList<List<Integer>> listArrayList = new ArrayList<List<Integer>>();
            listArrayList.add(new ArrayList<Integer>(Arrays.asList(0,-1,0)));
            listArrayList.add(new ArrayList<Integer>(Arrays.asList(1)));


            return listArrayList;
        }

        @Override
        public List<Integer> priorityOfRotations() {
            return new ArrayList<Integer>(Arrays.asList(0,1));
        }

    } ,

    UNKNOWN {
        @Override
        public int getHole(GlassParser glassParser) {
            return 0;
        }

        @Override
        public List<List<Integer>> diffs() {
            ArrayList<List<Integer>> listArrayList = new ArrayList<List<Integer>>();
            listArrayList.add(Collections.EMPTY_LIST);
            return listArrayList;
        }

        @Override
        public List<Integer> priorityOfRotations() {
            return new ArrayList<Integer>(Arrays.asList(0));
        }
    };

    public static Figure getFigure(String figure) {
        if (figure.equals("I")) {
            return I;
        }

        if (figure.equals("O")) {
            return O;
        }
        if (figure.equals("J")) {
            return J;
        }
        if (figure.equals("L")) {
            return L;
        }
        if (figure.equals("S")) {
            return S;
        }
        if (figure.equals("Z")) {
            return Z;
        }
        return UNKNOWN;
    }

    public int getHole(GlassParser glassParser) {
        return 0;
    }

    public int drop() {
        return 0;
    }

    public Move correctMove(Move move) {
        return move;
    }

    public List<Integer> priorityOfRotations() {
        ArrayList<Integer> priorities = new ArrayList<Integer>(diffs().size());
        for (int i=0 ; i< diffs().size(); i++) {
            priorities.add(i);
        }
        return priorities;
    }

    public List<List<Integer>> diffs() {
        return Collections.EMPTY_LIST;
    }
}
