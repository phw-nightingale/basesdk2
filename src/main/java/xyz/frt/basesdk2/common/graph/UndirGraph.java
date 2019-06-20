package xyz.frt.basesdk2.common.graph;


/**
 * 带权无向图
 *
 * @param <T>
 */
public class UndirGraph<T> implements Graph<T> {

    private static final Object[] DEFAULT_VERTEX_DATA = {};

    private static final int INFINITY = 999;

    public UndirGraph() {
        initArcs();
        tag = new Integer[MAX_VER_NUM];
        vertexes = DEFAULT_VERTEX_DATA;
    }

    /**
     * 根据点和点总数构筑无向图
     *
     * @param ps     点的集合
     * @param verNum 点的数量
     */
    public UndirGraph(T[] ps, int verNum) {
        if (verNum > MAX_VER_NUM) {
            throw new GraphException("超出最大顶点数");
        }
        this.verNum = verNum;
        initArcs();
        tag = new Integer[MAX_VER_NUM];
        vertexes = new Object[MAX_VER_NUM];
        for (int i = 0; i < verNum; i++) {
            vertexes[i] = ps[i];
        }
    }

    /**
     * 同上，唯一不同的是可以控制最大点数
     *
     * @param ps          点集合
     * @param verNum      点数
     * @param MAX_VER_NUM 最大点数
     */
    public UndirGraph(T[] ps, int verNum, int MAX_VER_NUM) {
        if (MAX_VER_NUM < 0) {
            throw new GraphException("最大顶点数不能为负");
        }
        if (MAX_VER_NUM < verNum) {
            throw new GraphException("顶点数不能超过最大顶点数");
        }
        this.verNum = verNum;
        UndirGraph.MAX_VER_NUM = MAX_VER_NUM;
        initArcs();
        tag = new Integer[MAX_VER_NUM];
        vertexes = new Object[MAX_VER_NUM];
        for (int i = 0; i < verNum; i++) {
            vertexes[i] = ps[i];
        }
    }

    /**
     * 顶点
     */
    private Object[] vertexes = {};

    /**
     * 顶点之间的关系(边)
     */
    private Integer[][] arcs = {};

    /**
     * 遍历辅助用标记
     */
    private Integer[] tag = {};

    /**
     * 当前顶点数
     */
    private int verNum = 0;

    /**
     * 允许的最大顶点数
     */
    private static int MAX_VER_NUM = 100;

    /**
     * 当前边数
     */
    private int arcNum = 0;

    @Override
    public void insertVertex(T p) {
        if (verNum == MAX_VER_NUM) {
            throw new GraphException("顶点数不能超过最大顶点数");
        }
        //检查是否有ID冲突的顶点
        for (Object t: vertexes) {
            if (t instanceof Identify && p instanceof Identify) {
                if (((Identify) t).getId() == ((Identify) p).getId()) {
                    throw new GraphException("已有ID为 " + ((Identify) p).getId() + " 的顶点");
                }
            }
        }

        vertexes[verNum] = p;
        tag[verNum] = 0;

        // 初始化这条边的权值
        arcs[verNum][verNum] = 0;
        for (int i = 0; i < verNum; i++) {
            arcs[verNum][i] = INFINITY;
            arcs[i][verNum] = INFINITY;
        }
        verNum++;

    }

    @Override
    public void insertEdge(int v1, int v2, int weight) {
        if (v1 < 0 || v1 >= verNum) {
            throw new GraphException("点1不合法");
        }
        if (v2 < 0 || v2 >= verNum) {
            throw new GraphException("点2不合法");
        }
        if (v1 == v2) {
            throw new GraphException("两点位置不能相等");
        }
        if (weight <= 0) {
            throw new GraphException("权值必须大于0");
        }
        if (arcs[v1][v2] == INFINITY) {
            arcs[v1][v2] = weight;
            arcs[v2][v1] = weight;
            arcNum++;
        }
    }

    @Override
    public void deleteVertex(T p) {
        // 首先查找顶点是否存在于表中
        int v;
        for (v = 0; v < verNum; v++) {
            if (vertexes[v] instanceof Identify && p instanceof Identify) {
                Identify i1 = (Identify) vertexes[v];
                Identify i2 = (Identify) p;
                if (i1.getId() == i2.getId()) {
                    break;
                }
            }
            if (v == verNum - 1) {
                throw new GraphException("图中不存在要删除的点");
            }
        }
        // 删除于顶点关联的边
        for (int i = 0; i < verNum; i++) {
            if (arcs[v][i] != null) {
                arcs[v][i] = null;
                arcs[i][v] = null;
                arcNum--;
            }
        }
        verNum--;
        // 移动顶点
        if (v <= verNum) {
            vertexes[v] = vertexes[verNum]; // 把最后一个顶点的位置移动到删除的顶点的位置
            vertexes[verNum] = null;
            for (int i = 0; i <= verNum; i++) {
                arcs[v][i] = arcs[verNum][i];
                arcs[verNum][i] = null;
            }
            for (int i = 0; i <= verNum; i++) {
                arcs[i][v] = arcs[i][verNum];
                arcs[i][verNum] = null;
            }
        }
    }

    @Override
    public void deleteEdge(int v0, int v1) {
        if (v0 < 0 || v0 >= verNum) {
            throw new GraphException("点1不合法");
        }
        if (v1 < 0 || v1 >= verNum) {
            throw new GraphException("点2不合法");
        }
        if (v0 == v1) {
            throw new GraphException("v1不能等于v2");
        }
        if (arcs[v0][v1] < INFINITY) {    //如果存在边
            arcs[v0][v1] = INFINITY;
            arcs[v1][v0] = INFINITY;
            arcNum--;
        }
    }

    @Override
    public int getWeight(int v1, int v2) {
        if (v1 < 0 || v1 >= verNum) {
            throw new GraphException("v1不合法");
        }
        if (v2 < 0 || v2 >= verNum) {
            throw new GraphException("v2不合法");
        }
        if (v1 == v2) {
            return 0;
        }
        return arcs[v1][v2];
    }

    @Override
    public void setWeight(int v1, int v2, int weight) {
        if (v1 < 0 || v1 >= verNum) {
            throw new GraphException("v1不合法");
        }
        if (v2 < 0 || v2 >= verNum) {
            throw new GraphException("v2不合法");
        }
        if (v1 == v2) {
            throw new GraphException("两点位置不能相同");
        }
        if (weight <= 0) {
            throw new GraphException("权值必须大于0");
        }
        if (isEdgeExist(v1, v2)) {
            arcs[v1][v2] = weight;
            arcs[v2][v1] = weight;
        }
    }

    @Override
    public T[] travers() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getVerNum() {
        return verNum;
    }

    @Override
    public Object[] getVertexes() {
        return vertexes;
    }

    @Override
    public int getArcNum() {
        return arcNum;
    }

    @Override
    public Integer[][] getArcs() {
        return arcs;
    }

    @Override
    public boolean isEdgeExist(int v1, int v2) {
        if (v1 < 0 || v1 >= verNum) {
            throw new GraphException("v1不合法");
        }
        if (v2 < 0 || v2 >= verNum) {
            throw new GraphException("v2不合法");
        }
        if (v1 == v2) {
            throw new GraphException("两点不能相同");
        }
        for (int i = 0; i < arcNum; i++) {
            if (arcs[v1][v2] != INFINITY) { //arcs[v2][v1] != INFINITY
                return true;
            }
        }
        return false;
    }

    @Override
    public DijkstraResult getShortestPathDij(int v0) {

        if (v0 < 0 || v0 >= verNum) {
            throw new GraphException("起点不合法");
        }
        int[] s = new int[verNum]; // 标记数组
        int[] dist = new int[verNum]; // 最短路径数组
        int[] path = new int[verNum]; // 直接前驱点标记

        // 初始化辅助函数
        for (int i = 0; i < verNum; i++) {
            s[i] = 0;
            dist[i] = arcs[v0][i];
            if (i == 0) {
                path[i] = -1;
            } else {
                path[i] = 0;
            }
        }

        // 对顶点v0自身进行初始化
        s[v0] = 1;
        dist[v0] = 0;

        // 遍历verNum-1次，每次找出一个顶点的最短路径
        int u, v;
        for (int i = 0; i < verNum; i++) {
            int min = INFINITY;
            u = v0;

            for (v = 0; v < verNum; v++) {
                if (s[v] == 0 && dist[v] < min) {
                    min = dist[v];
                    u = v;
                }
            }
            s[u] = 1;

            // 修正当前最短路径和直接前驱点
            // 即，当已经"顶点k的最短路径"之后，更新"未获取最短路径的顶点的最短路径和前驱顶点"
            for (int j = 0; j < verNum; j++) {
                int temp = arcs[u][j] == INFINITY ? INFINITY : (min + arcs[u][j]);
                if (s[j] == 0 && (temp < dist[j])) {
                    dist[j] = temp;
                    path[j] = u;
                }
            }
        }

        return new DijkstraResult(dist, path);
    }

    public void printArcs() {
        for (int i = 0; i < MAX_VER_NUM; i++) {
            for (int v = 0; v < MAX_VER_NUM; v++) {
                System.out.print(arcs[i][v] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printVertexes() {
        for (int i = 0; i < MAX_VER_NUM; i++) {
            System.out.print(vertexes[i] + " ");
        }
        System.out.println();
    }

    /**
     * 初始化权值
     */
    private void initArcs() {
        arcs = new Integer[MAX_VER_NUM][MAX_VER_NUM];
        for (int i = 0; i < verNum; i++) {
            for (int j = 0; j < verNum; j++) {
                if (i == j) {
                    arcs[i][j] = 0; // 或arcs[j][i] = 0;
                } else {
                    arcs[i][j] = INFINITY;
                    arcs[j][i] = INFINITY;
                }
            }
        }
    }

}
