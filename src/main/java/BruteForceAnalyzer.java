import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 26.05.13
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */
public class BruteForceAnalyzer {
    private GlassParser glassParser;
    private Figure fig;

    public BruteForceAnalyzer(Figure fig, GlassParser glassParser) {
        this.fig = fig;
        this.glassParser = glassParser;
    }

    public List<Move> getSolutions() {
        ArrayList<Move> solutions = new ArrayList<Move>();
        for (int rotate = 0 ; rotate < fig.diffs().size(); rotate++) {
            solutions.addAll(oneSideSolutions(fig.diffs().get(rotate), rotate));
        }
        return solutions;
    }

    private List<Move> oneSideSolutions(List<Integer> oneSideDiffs, int rotate) {
            ArrayList<Move> solutions = new ArrayList<Move>();

            //int startingOffset = 0;
            //for (int diffPos = startingOffset; diffPos < GlassParser.WIDTH -1; diffPos++) {
        for (int startingOffset=0; startingOffset<GlassParser.WIDTH; startingOffset ++) {
            if (thisPositionFits(oneSideDiffs, startingOffset)) {
                solutions.add(new Move(startingOffset, rotate));

            }
        }

        return solutions;
    }

    public boolean thisPositionFits(List<Integer> oneSideDiffs, int startingOffset) {
        boolean thisSideFits = true;
        for (int diffPosFigure = 0; diffPosFigure < oneSideDiffs.size(); diffPosFigure++) {
            Integer diff = oneSideDiffs.get(diffPosFigure);
            if (startingOffset + diffPosFigure >= GlassParser.WIDTH-1) {
                thisSideFits = false;
                break;
            }
            if (!(diff.equals(glassParser.getDiff().get(startingOffset + diffPosFigure)))) {
                thisSideFits = false;
            }
        }
        return thisSideFits;
    }

    public Move getSolution() {
        return getSolutions().get(0);
    }

    public Move getDeepestSolution() {
        List<Move> solutions = getSolutions();
        int deepestIndex = 0;
        int minDeep = glassParser.getDepth();
        for (int i =0; i< solutions.size(); i++) {
            Move move = solutions.get(i);
            int depth = getDepth(move);
            if (depth < minDeep) {
                minDeep = depth;
                deepestIndex = i;
            }
        }
        return solutions.get(deepestIndex);
    }

    public int getDepth(Move move) {
        List<Integer> diff = fig.diffs().get(move.rotate);
        List<Integer> glassDiff = glassParser.getDiff();
        int minDeep = glassParser.getDepth();
        for (int i=0; i< diff.size()+1; i++) {
            int howDeep = glassParser.getLandscape().get(i + move.offset);
            if (howDeep < minDeep) {
                minDeep = howDeep;
            }
        }
        return minDeep;
    }

    public void setFigure(Figure figure) {
        this.fig = figure;
    }

    public void setParser(GlassParser glassParser) {
        this.glassParser  = glassParser;
    }

    public Move getDeepestPriorityzedSolution() {
        List<Move> solutions = getSolutions();

        Map<Integer, List<Move>> depthMap = getDepthMap(solutions);
        Integer min = Collections.min(depthMap.keySet());
        List<Move> moves = depthMap.get(min);
        Collections.sort(moves, new CompareByPriority(fig));
        return moves.get(0);
//        for (int i=0; i< fig.diffs().size(); i++ ) {
//            Integer rotation = fig.priorityOfRotations().get(i);
//            for (int j=0; j<)
//        }

    }

    private Map<Integer, List<Move>> getDepthMap(List<Move> solutions) {
        Map<Integer, List<Move>> depthMap = new HashMap<Integer, List<Move>>();
        for (int i =0; i< solutions.size(); i++) {
            Move move = solutions.get(i);
            Integer depth = getDepth(move);

            List<Move> moves = depthMap.get(depth);
            if (moves == null) {
                moves = new ArrayList<Move>();
                depthMap.put(depth, moves);
            }
            moves.add(move);

            //depthMap.put(depth, moves);
        }
        return depthMap;
    }

//    public Move getDeepestSolution2() {
//        List<Move> solutions = getSolutions();
//        int deepestIndex = 0;
//        int minDeep = glassParser.getDepth();
//        for (int i =0; i< solutions.size(); i++) {
//            Move move = solutions.get(i);
//            int depth = getDepth(move);
//            if (depth < minDeep) {
//                minDeep = depth;
//                deepestIndex = i;
//            }
//        }
//        for (int i =0; i< solutions.size(); i++) {
//
//            Move move = solutions.get(i);
//            int depth = getDepth(move);
//            if (depth == minDeep) {
//                solutionsDeep.add(move);
//            }
//        }
//        return solutionsDeep;
//    }

    //J should fall as *
    //                 ***
    //if we have three holes

    //square without location should move somewhere deep

    //line should fall into walley, not wide deep space

    //if towers, switch to approx mode

    //no falling

}


