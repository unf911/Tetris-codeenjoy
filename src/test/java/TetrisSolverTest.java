import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class TetrisSolverTest {

    private final TetrisSolver solver = new TetrisSolver();
    private GlassParser glassParser;


    @Test
    public void shouldGetBottom()  {
        GlassParser glassParser = new GlassParser();
        glassParser.setGlass("********  ********  ******    ******    ******    ******    ******    ******      ****      ****      ****      ****      ****      ****      ****      ****      ****      ****                        ");
        System.out.println(glassParser.getBottom());
        assertEquals(glassParser.getBottom(), "********  ");
        assertEquals(8, glassParser.getBottomHoleForSquare()) ;


    }

    @Test
    public void shouldParse() {
        GlassParser glassParser = new GlassParser();
        glassParser.setGlass("********  ********  ******    ******    ******    ******    ******    ******      ****      ****      ****      ****      ****      ****      ****      ****      ****      ****                        ");
        List<String> parse = glassParser.parse();
        assertEquals(parse.get(0), "********  ");
        assertEquals(parse.get(1), "********  ");
        assertEquals(parse.size(),20);



    }

    @Test
    public void shouldFindHole() {
        GlassParser glassParser = new GlassParser();
        glassParser.setGlass("*******   ********  ");
        Set<Integer> holes = glassParser.getHoles(2, 0);
        assertEquals(holes.size(), 2);
        Set<Integer> holes2 = glassParser.getHoles(2, 1);
        assertEquals( 1, holes2.size());
       // System.out.println(holes);

    }


    @Test
    public void shouldFindHole2() {
        GlassParser glassParser = new GlassParser();
        glassParser.setGlass("*******   *******   ");
        Set<Integer> holes = glassParser.getTunnel(2);
        //assertEquals(holes.size(), 2);
       System.out.println(holes);
        assertEquals( 2, holes.size());

    }

    @Test
    public void shouldFindHole3() {
        GlassParser glassParser = new GlassParser();
        glassParser.setGlass("*******     ********");
        Set<Integer> holes = glassParser.getTunnel(2);
        //assertEquals(holes.size(), 2);
        System.out.println(holes);
        assertEquals( 1, holes.size());

    }
    @Test
    public void shouldFindFilled() {
        GlassParser glassParser = new GlassParser();
        glassParser.setGlass("*******     ********");
        Set<Integer> holes = glassParser.getFilled(2,0);
        //assertEquals(holes.size(), 2);
        System.out.println(holes);
        //assertEquals( 1, holes.size());

    }

    @Test
    public void shouldGetLandscape() {
        GlassParser glassParser = new GlassParser("****      ** *      ");
        assertEquals(2,glassParser.getLandscape().get(0).intValue());
        assertEquals(2,glassParser.getLandscape().get(1).intValue());
        assertEquals(1,glassParser.getLandscape().get(2).intValue());
        assertEquals(2,glassParser.getLandscape().get(3).intValue());
        assertEquals(0,glassParser.getLandscape().get(4).intValue());
    }




}
