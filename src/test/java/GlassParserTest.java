import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 26.05.13
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */
public class GlassParserTest {
    private GlassParser glassParser;

    @Before
    public void setupGlassParser() {
        glassParser = new GlassParser();
        glassParser.setGlass("****      ****      ");
        // return glassParser;
    }

    @Test
    public void shouldGetXY() {
//        GlassParser glassParser = setupGlassParser();
        assertEquals(glassParser.getXY(0, 0), true);
        assertEquals(glassParser.getXY(9,0),false);

    }

    @Test
    public void shouldGetX0Y1() {
        //GlassParser glassParser = setupGlassParser();
        assertEquals(glassParser.getXY(0,1),true);
        assertEquals(glassParser.getXY(9,1),false);

    }

    @Test
    public void shouldGetLandscape() {
        assertEquals(1, glassParser.getLandscape().get(0).intValue());
        assertEquals(1, glassParser.getLandscape().get(1).intValue());
        assertEquals(0, glassParser.getLandscape().get(4).intValue());
        assertEquals(0, glassParser.getLandscape().get(9).intValue());
        glassParser.setGlass("****      ** *      * **      ");
        assertEquals(2, glassParser.getLandscape().get(0).intValue());
        assertEquals(1, glassParser.getLandscape().get(1).intValue());
        assertEquals(2, glassParser.getLandscape().get(2).intValue());

    }

    @Test
    public void shouldGetDiff() {
        //glassParser.setGlass("****      ** *      * **      ");
        assertEquals(0, glassParser.getDiff().get(0).intValue());
        assertEquals(0, glassParser.getDiff().get(1).intValue());
        assertEquals(0, glassParser.getDiff().get(2).intValue());
        assertEquals(-1, glassParser.getDiff().get(3).intValue());
        assertEquals(0, glassParser.getDiff().get(4).intValue());
        glassParser.setGlass("****      ** *      * **      ");
        assertEquals(0, glassParser.getDiff().get(8).intValue());
    }
}
