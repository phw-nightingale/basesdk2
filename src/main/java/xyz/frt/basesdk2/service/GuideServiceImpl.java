package xyz.frt.basesdk2.service;

import org.springframework.stereotype.Service;
import xyz.frt.basesdk2.common.GraphDTO;
import xyz.frt.basesdk2.common.graph.DijkstraResult;
import xyz.frt.basesdk2.common.graph.Position;
import xyz.frt.basesdk2.common.graph.UndirGraph;

@Service
public class GuideServiceImpl implements GuideService {

    private UndirGraph<Position> graph = new UndirGraph<>();

    @Override
    public GraphDTO createGuide(Position[] ps, int pNum, int maxPNum) {
        graph = new UndirGraph<>(ps, pNum, maxPNum);
        return new GraphDTO(graph.getVertexes(), graph.getArcs(), graph.getVerNum(), graph.getArcNum());
    }

    @Override
    public GraphDTO insertPosition(Position p) {
        graph.insertVertex(p);
        return new GraphDTO(graph.getVertexes(), graph.getArcs(), graph.getVerNum(), graph.getArcNum());
    }

    @Override
    public GraphDTO deletePosition(Position p) {
        graph.deleteVertex(p);
        return new GraphDTO(graph.getVertexes(), graph.getArcs(), graph.getVerNum(), graph.getArcNum());
    }

    @Override
    public GraphDTO insertEdge(int v1, int v2, int weight) {
        graph.insertEdge(v1, v2, weight);
        return new GraphDTO(graph.getVertexes(), graph.getArcs(), graph.getVerNum(), graph.getArcNum());
    }

    @Override
    public GraphDTO deleteEdge(int v1, int v2) {
        graph.deleteEdge(v1, v2);
        return new GraphDTO(graph.getVertexes(), graph.getArcs(), graph.getVerNum(), graph.getArcNum());
    }

    @Override
    public GraphDTO setWeight(int v1, int v2, int weight) {
        graph.setWeight(v1, v2, weight);
        return new GraphDTO(graph.getVertexes(), graph.getArcs(), graph.getVerNum(), graph.getArcNum());
    }

    @Override
    public int getArcNum() {
        return graph.getArcNum();
    }

    @Override
    public int getVerNum() {
        return graph.getVerNum();
    }

    @Override
    public int getWeight(int v1, int v2) {
        return graph.getWeight(v1, v2);
    }

    @Override
    public Object[] getPositions() {
        return graph.getVertexes();
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
