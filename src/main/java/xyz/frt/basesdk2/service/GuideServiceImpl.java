package xyz.frt.basesdk2.service;

import org.springframework.stereotype.Service;
import xyz.frt.basesdk2.common.graph.DijkstraResult;
import xyz.frt.basesdk2.common.graph.Position;
import xyz.frt.basesdk2.common.graph.UndirGraph;

@Service
public class GuideServiceImpl implements GuideService {

    private UndirGraph<Position> graph = new UndirGraph<>();

    @Override
    public Position[] createGuide(Position[] ps, int pNum, int maxPNum) {
        graph = new UndirGraph<>(ps, pNum, maxPNum);
        return graph.getVertex();
    }

    @Override
    public Position[] insertPosition(Position p) {
        graph.insertVertex(p);
        return graph.getVertex();
    }

    @Override
    public Position[] deletePosition(Position p) {
        graph.deleteVertex(p);
        return graph.getVertex();
    }

    @Override
    public Integer[][] insertEdge(int v1, int v2, int weight) {
        graph.insertEdge(v1, v2, weight);
        return graph.getArcs();
    }

    @Override
    public Integer[][] deleteEdge(int v1, int v2) {
        graph.deleteEdge(v1, v2);
        return graph.getArcs();
    }

    @Override
    public Integer[][] setWeight(int v1, int v2, int weight) {
        graph.setWeight(v1, v2, weight);
        return graph.getArcs();
    }

    @Override
    public int getWeight(int v1, int v2) {
        return graph.getWeight(v1, v2);
    }

    @Override
    public Position[] getPositions() {
        return graph.getVertex();
    }

    @Override
    public Integer[][] getArcs() {
        return graph.getArcs();
    }

    @Override
    public DijkstraResult getShortestDistDij(int v0) {
        return graph.getShortestPathDij(v0);
    }
}
