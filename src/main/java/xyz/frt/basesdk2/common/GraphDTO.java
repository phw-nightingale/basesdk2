package xyz.frt.basesdk2.common;

import xyz.frt.basesdk2.common.graph.Identify;

public class GraphDTO<T extends Identify> {

    private T[] vertexes;

    private Integer[][] arcs;

    public GraphDTO() { }

    public GraphDTO(T[] vertexes, Integer[][] arcs) {
        this.vertexes = vertexes;
        this.arcs = arcs;
    }

    public T[] getVertexes() {
        return vertexes;
    }

    public void setVertexes(T[] vertexes) {
        this.vertexes = vertexes;
    }

    public Integer[][] getArcs() {
        return arcs;
    }

    public void setArcs(Integer[][] arcs) {
        this.arcs = arcs;
    }
}
