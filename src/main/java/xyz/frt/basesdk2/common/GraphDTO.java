package xyz.frt.basesdk2.common;

public class GraphDTO<T> {

    private T[] vertexes;

    private Integer[][] arcs;

    private int verNum;

    private int arcNum;

    public GraphDTO() { }

    public GraphDTO(T[] vertexes, Integer[][] arcs, int verNum, int arcNum) {
        this.vertexes = vertexes;
        this.arcs = arcs;
        this.verNum = verNum;
        this.arcNum = arcNum;
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

    public int getVerNum() {
        return verNum;
    }

    public void setVerNum(int verNum) {
        this.verNum = verNum;
    }

    public int getArcNum() {
        return arcNum;
    }

    public void setArcNum(int arcNum) {
        this.arcNum = arcNum;
    }
}
