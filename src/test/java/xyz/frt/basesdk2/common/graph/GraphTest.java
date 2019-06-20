package xyz.frt.basesdk2.common.graph;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {

    UndirGraph<Position> graph;

    @Before
    public void before() {
        Position[] vertexes = {
                new Position(0, "一食堂", 0, 0),
                new Position(1, "二食堂", 1, 1),
                new Position(2, "图书馆", 2, 4),
                new Position(3, "6号公寓", 3, 4),
                new Position(4, "行政楼", 4, 4)
        };
        graph = new UndirGraph<>(vertexes, 5, 6);
        graph.insertEdge(0, 1, 10);
        graph.insertEdge(1, 2, 50);
        graph.insertEdge(2, 3, 20);
        graph.insertEdge(3, 4, 60);
        graph.insertEdge(4, 0, 100);
        graph.insertEdge(0, 3, 30);
        graph.insertEdge(2, 4, 10);
        printTest();
    }


    @Test
    public void printTest() {
        graph.printArcs();
        graph.printVertexes();
        System.out.println("----------------------------------");
    }

    @Test
    public void insertVertexTest() {
        graph.insertVertex(new Position(5, "文体中心", 6, 7));
        printTest();
    }

    @Test
    public void insertEdgeTest() {
        graph.insertEdge(0, 1, 10);
        graph.insertEdge(3, 4, 10);
        printTest();
    }

    @Test
    public void deleteVertexTest() {
        graph.deleteVertex(new Position(4));
        printTest();
    }

    @Test
    public void deleteEdgeTest() {
        graph.deleteEdge(0, 4);
        printTest();
    }



    @Test
    public void getShortestPathDijTest() {
        DijkstraResult result = graph.getShortestPathDij(0);
        int[] path = result.getPath();
        int[] dist = result.getDist();
        for (int i : path) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("---------------------");
        for (int i : dist) {
            System.out.print(i + " ");
        }
    }

    @Test
    public void getVertexesTest() {
        graph.getVertexes();
    }

}
