{
	图的邻接矩阵存储方式是用两个数组来表示图。
	一个一维数组存储图中顶点信息，一个二维数组（邻接矩阵）存储图中的边或弧的信息。
}

import java.util.*;
public class Matrix_Graph{

	private char[] mVexs;//节点
	private int[][] mMatrix;//邻接矩阵
	private final static int INF = Integer.MAX_VALUE;

	//创建图
	public Matrix_Graph(char[] vexs, int[][] matrix){
		int vlen = vexs.length;
		//初始化节点
		mVexs = new char[vlen];
		for (int i = 0; i < vlen; i++) {
			mVexs[i] = vexs[i];
		}
		//初始化矩阵
		mMatrix = new int[vlen][vlen];
		for (int i = 0; i < vlen; i++) {
			for (int j = 0; j < vlen; j++) {
				mMatrix[i][j] = matrix[i][j];
			}
		}
	}

	//Dijkstra算法，求单起点全路径最短路径
	//
	//
	//参数：
	//	vsCh--起点字符
	//	prev--前驱顶点数组。可以用来打印最短路径的轨迹
	//		  即，prev[i]的值是"顶点vs"到"顶点i"的最短路径所经历的全部顶点中，位于"顶点i"之前的那个顶点。
	private void dijkstra(int vs, int[] dist, int[] prev){
		int vlen = mVexs.length;//节点个数
		boolean[] flag = new boolean[vlen];//标志数组 flag[i]=true表示"顶点vs"到"顶点i"的最短路径已成功获取

		//初始化
		for (int i = 0; i < vlen; i++) {
			prev[i] = vs;//初始化所有节点的前驱节点都为起点
			dist[i] = mMatrix[vs][i];//顶点i的最短路径为"顶点vs"到"顶点i"的权。
		}
		// 对"顶点vs"自身进行初始化
        flag[vs] = true;
		dist[vs] = 0;

		//循环vlen-1次，每次找出一个节点的最短路径。
		//因为要求得从起点vs到达所有其他节点的最短路径，所以需要vlen-1次 
		int k = 0;
		for (int i = 1; i < vlen; i++) {
			// 寻找当前最小的路径；
			// 即，在未获取最短路径的顶点中，找到离vs最近的顶点(k)。
			int min = INF;
			for(int j = 0; j < vlen; j++){
				if(flag[j] == false && dist[j] < min){
					min = dist[j];
					k = j;
				}
			}
			//找到离vs最近的节点k了
			//标记"顶点k"为已经获取到最短路径
			flag[k] = true;

			//更新其他未获取最短路径的节点的路径长度
			//即，当已知"顶点k的最短路径"之后，更新"未获取最短路径的顶点的最短路径和前驱顶点"。
			for (int j = 0; j < vlen; j++) {
				int tmpDist = mMatrix[k][j] == INF ? INF : mMatrix[k][j] + dist[k];
				if(flag[j] == false && tmpDist < dist[j]){
					dist[j] = tmpDist;
					prev[j] = k;
				}
			}
		}

		// 打印dijkstra最短路径的结果
        System.out.printf("dijkstra(%c): \n", mVexs[vs]);
        for (int i=0; i < mVexs.length; i++)
		System.out.printf("  shortest(%c, %c)=%d\n", mVexs[vs], mVexs[i], dist[i]);

	}

	//根据节点的char名称获取节点在节点数组中的位置
	private int getPosition(char target){
		for (int i = 0; i < mVexs.length; i++) {
			if(mVexs[i]==target) return i; 
		}
		return -1;
	}

	public static void main(String[] args) {
		char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                 /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
          /*A*/ {   0,  12, INF, INF, INF,  16,  14},
          /*B*/ {  12,   0,  10, INF, INF,   7, INF},
          /*C*/ { INF,  10,   0,   3,   5,   6, INF},
          /*D*/ { INF, INF,   3,   0,   4, INF, INF},
          /*E*/ { INF, INF,   5,   4,   0,   2,   8},
          /*F*/ {  16,   7,   6, INF,   2,   0,   9},
		  /*G*/ { 14, INF, INF, INF, 8, 9, 0}
		};

		Matrix_Graph graph = new Matrix_Graph(vexs, matrix);
		int[] prev = new int[vexs.length];//存储前驱节点
		int[] dist = new int[vexs.length];//存储每个节点与起点的最短距离
		int vs = 3;//起点为D
		graph.dijkstra(vs, dist, prev);//求以D为起点到达所有节点的最短路径

		//打印到达A的最短路径
		int focus = 0;
		StringBuilder sb = new StringBuilder();
        while(true){
        	sb.append(graph.mVexs[focus] + ">");
            if(focus == prev[focus]) break;
            focus = prev[focus];
        }
        sb.delete(sb.length() - 1, sb.length());
        System.out.print(sb.reverse());
	}
}

output:

	dijkstra(D): 
  	shortest(D, A)=22
  	shortest(D, B)=13
  	shortest(D, C)=3
  	shortest(D, D)=0
  	shortest(D, E)=4
  	shortest(D, F)=6
  	shortest(D, G)=12
	D>E>F>A