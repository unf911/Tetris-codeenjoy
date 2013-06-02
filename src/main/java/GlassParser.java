

import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: anefedov
 * Date: 5/17/13
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class GlassParser {
    public static final int WIDTH = 10;
    private String glass;
    private List<String> list;
    private int depth;

    public GlassParser(String glass) {
        this.setGlass(glass);
    }

    public GlassParser() {
    }


    void setGlass(String glass)  {
        this.glass = glass;
        this.depth = this.glass.length()/WIDTH;
    }

    public int getDepth() {
        return depth;
    }


    public String getBottom() {
       return glass.substring(0,10);
    }

    public List<String> parse () {
        List<String> stringArrayList = new ArrayList<String>();
        int length = glass.length();
        for (int i =0 ; i<depth; i++) {
            stringArrayList.add(glass.substring(i*10, i*10+10));

        }
        return stringArrayList;
    }

    public Set<Integer> getHoles(int i, int line) {
        String space = " ";
        String repeat = StringUtils.repeat(space, i);
        int i1 = 0;
        Set<Integer> integerHashSet = new HashSet<Integer>();
        while (i1 >= 0) {
            int i2 = parse().get(line).indexOf(repeat, i1);
            if (i2!=-1 ) {
                integerHashSet.add(i2);
            } else {
                break;
            }
            i1=i2+1;
        }
        return integerHashSet;
    }

    public Set<Integer> getFilled(int i, int line) {
        String space = "*";
        String repeat = StringUtils.repeat(space, i);
        int i1 = 0;
        Set<Integer> integerHashSet = new HashSet<Integer>();
        while (i1 >= 0) {
            int i2 = parse().get(line).indexOf(repeat, i1);
            if (i2!=-1 ) {
                integerHashSet.add(i2);
            } else {
                break;
            }
            i1=i2+1;
        }
        return integerHashSet;
    }


    public Set<Integer> getTunnel(int holesCount) {
        Set<Integer> intersectionsFinal = null;
        Set<Integer> intersections = null;
        for (int j=this.depth-1; j >= 0; j--) {

            Set<Integer> holes = getHoles(holesCount, j);

            if ((holes.size()>0) && (intersections== null)) {
                intersections = holes;
            } else {
                intersections.retainAll(holes);
            }
            if (intersections.size()<=0) {
                break;
            } else {
                intersectionsFinal = new HashSet(intersections);
            }

        }
        return intersectionsFinal;
    }

    public Set<Integer> getTunnelBelow(int holesCount, int howDeep) {
        Set<Integer> intersectionsFinal = null;
        Set<Integer> intersections = null;
        for (int j=howDeep-1; j >= 0; j--) {

            Set<Integer> holes = getHoles(holesCount, j);
            if ((holes.size()>0) && (intersections== null)) {
                intersections = holes;
            } else {
                intersections.retainAll(holes);
            }
            if (intersections.size()<=0) {
                break;
            } else {
                intersectionsFinal = new HashSet(intersections);
            }

        }
        return intersectionsFinal;
    }


//    public int notEmplyLine(){
//        List<String> parse = parse();
//        for (int i=parse.size()-1; i<0; i--) {
//           parse.get(i).indexOf("*");
//        }
//    }

    public int getBottomHoleForSquare() {

        return getBottom().indexOf("  ");
    }
    public int getBottomHoleForLine() {

        return getBottom().indexOf(" ");
    }


    public int getBottomHoleForJ() {
        return getBottom().indexOf("  ");
    }

    public boolean getXY(int x, int y) {
        if (parse().get(y).charAt(x) =='*') {
            return true;
        }
        return false;
    }

    public List<Integer> getLandscape() {
        //ArrayList<Integer> integers = new ArrayList<Integer>(WIDTH);
        Integer[] integers = new Integer[10];
        for (int i=0; i<WIDTH; i++) {
            integers[i] = 0;
            for (int j=depth-1; j>=0; j--) {
                if (getXY(i,j)) {
                    integers[i] = j+1;
                    break;
                }
            }
        }
        return Arrays.asList(integers);
    }

    public List<Integer> getDiff() {
        ArrayList<Integer> diffs = new ArrayList<Integer>(WIDTH-1);
        Integer prevValue = null;
        for (Integer landscapeValue : getLandscape()) {
            if (prevValue == null) {
                prevValue = landscapeValue;
            } else {
                Integer diff = landscapeValue - prevValue;
                diffs.add(diff);
                prevValue = landscapeValue;
            }
        }
        return diffs;
    }


}
