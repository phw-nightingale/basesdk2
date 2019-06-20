package xyz.frt.basesdk2.common.graph;

/**
 * 图
 * 
 * @param <T>
 */
public interface Graph<T> {

	/**
	 * 插入一个顶点
	 * 
	 * @param p
	 *            顶点
	 */
	void insertVertex(T p);

	/**
	 * 插入一条边
	 * 
	 * @param v0
	 *            起点位置，从0开始
	 * @param v1
	 *            终点位置，从0开始
	 * @param weight
	 *            权值
	 */
	void insertEdge(int v0, int v1, int weight);

	/**
	 * 删除一个顶点
	 * 
	 * @param p
	 *            顶点
	 */
	void deleteVertex(T p);

	/**
	 * 删除一条边
	 * 
	 * @param v0
	 *            起点位置，从0开始
	 * @param v1
	 *            终点位置，从0开始
	 */
	void deleteEdge(int v0, int v1);

	/**
	 * 获得某一条边的权值
	 * 
	 * @param v0
	 *            起点位置，从0开始
	 * @param v1
	 *            终点位置，从0开始
	 */
	int getWeight(int v0, int v1);

	/**
	 * 设置某条边的权值
	 * 
	 * @param v0
	 *            起点位置，从0开始
	 * @param v1
	 *            终点位置，从0开始
	 * @param weight
	 *            权值
	 */
	void setWeight(int v0, int v1, int weight);

	/**
	 * 遍历图
	 * 
	 * @return 结果集
	 */
	T[] travers();

	/**
	 * 图是否为空
	 * 
	 * @return 是否为空
	 */
	boolean isEmpty();

	/**
	 * 判断这条边是否存在
	 * 
	 * @param v1
	 *            起点位置
	 * @param v2
	 *            终点位置
	 * @return 是否存在
	 */
	boolean isEdgeExist(int v1, int v2);

	/**
	 * 获取顶点数
	 * 
	 * @return 顶点数
	 */
	int getVerNum();

	/**
	 * 获取所有顶点
	 * 
	 * @return
	 */
	Object[] getVertexes();

	/**
	 * 获取边数
	 * 
	 * @return 边数
	 */
	int getArcNum();

	/**
	 * 获取所有边
	 * 
	 * @return
	 */
	Integer[][] getArcs();

	/**
	 * 迪杰斯特拉算法求最短路径
	 * 
	 * @return 最短路径经过的点的序列
	 * @param v0 起点
	 */
	DijkstraResult getShortestPathDij(int v0);

	/**
	 * 清空图
	 */
	void clear();
}
