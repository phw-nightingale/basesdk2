package xyz.frt.basesdk2.common.graph;

public class DijkstraResult {

	private int[] dist;
	private int[] path;
	
	public DijkstraResult() { }

	/**
	 * 根据dist和path两个辅助数组
	 * 构建最短路径结果
	 * @param dist dist辅助数组
	 * @param path path辅助数组
	 */
	public DijkstraResult(int[] dist, int[] path) {
		super();
		this.dist = dist;
		this.path = path;
	}
	
	/**
	 * 获取到第v个点的最短路径
	 * @param v 终点在表中的位置
	 * @return 最短路径上的点序列
	 */
	public int[] getLine(int v) {
		if (v < 0 || v >= path.length) {
			throw new GraphException("非法终点位置");
		}
		int[] res = new int[path.length];
		int t = path[v];
		int num = 0;
		//获取最短路径
		for (int i = 0; i < path.length; i++) {
			if (i == 0) {
				res[i] = v;
			} else {
				if (t != -1) {
					res[i] = t;
					t = path[t];
				} else {
					break;
				}
			}
			num++;
		}
		int[] result = new int[num]; int s = 0;
		//反转获得的结果
		for (int i = num - 1; i >= 0; i--) {
			result[s] = res[i];
			s++;
		}
		return result;
	}
	
	/**
	 * 获取到终点v的最短路径长度
	 * @param v 终点
	 * @return 最短路径长度
	 */
	public int getLength(int v) {
		if (v < 0 || v >= path.length) {
			throw new GraphException("非法终点位置");
		}
		return dist[v];
	}
	
	public int[] getDist() {
		return dist;
	}
	
	public void setDist(int[] dist) {
		this.dist = dist;
	}
	
	public int[] getPath() {
		return path;
	}
	
	public void setPath(int[] path) {
		this.path = path;
	}
	
}
