package xyz.frt.basesdk2.common.graph;

import org.junit.Before;
import org.junit.Test;

public class DijkstraResultTest {

    public int[] dist = {0, 10, 50, 30, 60};

    public int[] path = {-1, 0, 3, 0, 2};

    public DijkstraResult result;

    @Before
    public void beforeTest() {
        result = new DijkstraResult(dist, path);
    }

    @Test
    public void getLineTest() {

        int[] res = result.getLine(4);
        for (int re : res) {
            System.out.print(re + " ");
        }
    }

    @Test
    public void getLengthTest() {
        int res = result.getLength(4);
        System.out.println(res);
    }

}
