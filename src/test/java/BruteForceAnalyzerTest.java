import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 26.05.13
 * Time: 10:29
 * To change this template use File | Settings | File Templates.
 */
public class BruteForceAnalyzerTest {

    private Figure figure;
    private BruteForceAnalyzer analyzer;
    private GlassParser glassParser;

    @Before
    public void setup() {
        figure = Figure.getFigure("O");
        glassParser = new GlassParser("****      ** *      * **      ");
        analyzer = new BruteForceAnalyzer(figure, glassParser);
    }
    @Test
    public void shouldGetPossibleSolutions() {
        List<Move> solutions = analyzer.getSolutions();
        assertEquals(2, solutions.get(0).offset);
        assertEquals(4, solutions.get(1).offset);

    }

    @Test
    public void shouldFit() {
        List<Integer> oneSideDiffs = new ArrayList<Integer>(Arrays.asList(0, 0, 0));


        assertEquals(false, analyzer.thisPositionFits(oneSideDiffs, 0));
        assertEquals(false, analyzer.thisPositionFits( oneSideDiffs, 1));
        assertEquals(false, analyzer.thisPositionFits(oneSideDiffs, 2));
        assertEquals(false, analyzer.thisPositionFits( oneSideDiffs, 3));
        assertEquals(true, analyzer.thisPositionFits(oneSideDiffs, 4));
        assertEquals(false, analyzer.thisPositionFits( oneSideDiffs, 9));
        assertEquals(true, analyzer.thisPositionFits(oneSideDiffs, 6));
    }

    @Test
    public void shouldFitLine() {
        List<Integer> oneSideDiffs = Collections.emptyList();
        assertEquals(true, analyzer.thisPositionFits(oneSideDiffs, 0));
    }

    @Test
    public void shouldFitL() {
        glassParser = new GlassParser("****      ** *      ");
        List<Integer> oneSideDiffs = new ArrayList<Integer>(Arrays.asList(-2));
        assertEquals(false, analyzer.thisPositionFits(oneSideDiffs, 0));
        assertEquals(true, analyzer.thisPositionFits( oneSideDiffs, 3));
        assertEquals(false, analyzer.thisPositionFits(oneSideDiffs, 4));
        assertEquals(false, analyzer.thisPositionFits( oneSideDiffs, 5));
    }

    @Test
    public void shouldGetSolution()     {
        assertEquals(new Move(2, 0), analyzer.getSolution());
    }

    @Test
    public void shouldGetDeepSolution()     {
        assertEquals(new Move(4, 0), analyzer.getDeepestSolution());
    }

    @Test
    public void shouldGetDepth()     {
        assertEquals(0, analyzer.getDepth(new Move(4, 0)));
        assertEquals(2, analyzer.getDepth(new Move(2, 0)));
    }

    @Test
    public void shouldGetDeepL() {
        figure = Figure.getFigure("L");
        analyzer.setFigure(figure);
        assertEquals(new Move(4,0),analyzer.getDeepestSolution());
        glassParser.setGlass("****      ** *      ");
        analyzer.setParser(glassParser);
        assertEquals(false, analyzer.thisPositionFits(figure.diffs().get(0),2));
        assertEquals(false, analyzer.thisPositionFits(figure.diffs().get(0),3));
        assertEquals(true, analyzer.thisPositionFits(figure.diffs().get(2),3));

        //System.out.println(analyzer.getSolutions());

        assertEquals(new Move(4,0),analyzer.getDeepestSolution());
    }

    @Test
    public void findLHole() {
        figure = Figure.getFigure("L");
        analyzer.setFigure(figure);
        glassParser.setGlass("**** ******* * *    ");
        analyzer.setParser(glassParser);
        System.out.println(glassParser.getLandscape());
        System.out.println(analyzer.getSolutions());
        assertEquals(new Move(3,2),analyzer.getDeepestSolution());
    }
    @Test
    public void Jbug() {
        figure = Figure.getFigure("J");
        analyzer.setFigure(figure);
        glassParser.setGlass("*         *         *         *                                                                                                                                                                         ");
        System.out.println(glassParser.getLandscape());
        analyzer.setParser(glassParser);
        assertEquals(new Move(2,0), figure.correctMove(analyzer.getDeepestSolution()));
    }

    @Test
    public void findPrior() {
        figure = Figure.getFigure("J");
        analyzer.setFigure(figure);
        assertEquals(new Move(4,1), figure.correctMove(analyzer.getDeepestPriorityzedSolution()));
    }

    //figure=J&x=6&y=16&glass=***** ********* ***** * **        **
}
