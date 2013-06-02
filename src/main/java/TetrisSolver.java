public class TetrisSolver {
    final static int DO_NOT_ROTATE = 0;
    final static int ROTATE_90_CLOCKWISE = 1;
    final static int ROTATE_180_CLOCKWISE = 2;
    final static int ROTATE_90_COUNTERCLOCKWISE = 3;

    public String answer(String figure, int x, int y, String glass, String next) {
        // add "drop" to response when you need to drop a figure
        // for details please check http://codenjoy.com/portal/?p=170#commands
        GlassParser glassParser = new GlassParser(glass);
        int left=0;
        int right=0;
        int offset=0;
        int drop=0;
        Figure fig = Figure.getFigure(figure);
        //int hole = fig.getHole(glassParser);
        //offset = x -hole;
        Move deepestSolution = new BruteForceAnalyzer(fig, glassParser).getDeepestPriorityzedSolution();
//        if (fig.equals(Figure.J) && (deepestSolution.rotate==0)) {
//            deepestSolution.offset=deepestSolution.offset;
//        }
        deepestSolution = fig.correctMove(deepestSolution);
//        if (fig.equals(Figure.J) && (deepestSolution.rotate==3)) {
//            deepestSolution.offset=deepestSolution.offset+1;
//        }
//        if (fig.equals(Figure.J) && (deepestSolution.rotate==2)) {
//            deepestSolution.offset=deepestSolution.offset+1;
//        }
//        if (fig.equals(Figure.L) && (deepestSolution.rotate==3)) {
//            deepestSolution.offset=deepestSolution.offset+1;
//        }
//        if (fig.equals(Figure.L) && (deepestSolution.rotate==2)) {
//            deepestSolution.offset=deepestSolution.offset+1;
//        }


        offset = x-deepestSolution.offset;
       // offset = oldBehaviourLevel4(figure, x, y, glassParser, offset);
        if (offset>0) {
            left = offset;
        } else {
            right =-offset;
        }
        if ((fig.equals(Figure.J)) && (!((y==19) && (deepestSolution.rotate!=0)))) {
            deepestSolution.rotate = 0;
        }
        if ((fig.equals(Figure.L)) && (!((y==19) && (deepestSolution.rotate!=0)))) {

            deepestSolution.rotate = 0;
        }
        if ((fig.equals(Figure.S)) && (!((y==19) && (deepestSolution.rotate!=0)))) {

            deepestSolution.rotate = 0;
        }

        String move = "left=" + Integer.toString(left) +
                ", right=" + Integer.toString(right) + ", rotate=" + Integer.toString(deepestSolution.rotate);
        if (fig.drop() ==1)  {
            move = move + ", drop";
        }
        return move;
        //return "left=0, right=0, rotate=0";
    }

    private int oldBehaviourLevel4(String figure, int x, int y, GlassParser glassParser, int offset) {
        if ((figure.equals("O" ) && (y==20)))  {
           offset= x-glassParser.getBottomHoleForSquare();


        }
        if (figure.equals("I") && (y==19))  {
            offset= x-glassParser.getBottomHoleForLine();

        }
        return offset;
    }
}
