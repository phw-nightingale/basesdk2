package xyz.frt.basesdk2.service;


import xyz.frt.basesdk2.common.GraphDTO;
import xyz.frt.basesdk2.common.graph.DijkstraResult;
import xyz.frt.basesdk2.common.graph.Position;

public interface GuideService {

    GraphDTO createGuide(Position[] ps, int pNum, int maxPNum);

    GraphDTO insertPosition(Position p);

    GraphDTO deletePosition(Position p);

    GraphDTO insertEdge(int v1, int v2, int weight);

    GraphDTO deleteEdge(int v1, int v2);

    GraphDTO setWeight(int v1, int v2, int weight);

    DijkstraResult getShortestDistDij(int v0);

    int getWeight(int v1, int v2);

    Object[] getPositions();

    Integer[][] getArcs();

    int getVerNum();

    int getArcNum();

}
