package xyz.frt.basesdk2.service;


import xyz.frt.basesdk2.common.graph.DijkstraResult;
import xyz.frt.basesdk2.common.graph.Position;

public interface GuideService {

    Position[] createGuide(Position[] ps, int pNum, int maxPNum);

    Position[] insertPosition(Position p);

    Position[] deletePosition(Position p);

    Integer[][] insertEdge(int v1, int v2, int weight);

    Integer[][] deleteEdge(int v1, int v2);

    Integer[][] setWeight(int v1, int v2, int weight);

    DijkstraResult getShortestDistDij(int v0);

    int getWeight(int v1, int v2);

    Position[] getPositions();

    Integer[][] getArcs();

}
