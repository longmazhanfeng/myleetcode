import java.io.BufferedReader;
import java.io.FileReader;

public class jy_dong {
	private int mEdgNum; // 边的数量
	private static int[][] mLinkID = new int[600][600]; // 边索引
	private static int[][] mMatrix = new int[600][600]; // 邻接矩阵
	private static int[] mVexs; // 顶点集合
	private static final int MAX = 1000; // 正无穷
	
	public static void main(String[] args) {
		// 邻接矩阵数据默认初始化为 MAX
		// 设置边索引矩阵初始数据，设为-1
		for (int i = 0; i < 600; i++) {
			for (int j = 0; j < 600; j++) {
				mLinkID[i][j] = -1;
				mMatrix[i][j] = MAX;
			}
		}
		// 记录顶点
		int vexNum = 0;
		// 图数据读入
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader("D:/eclipse_workspace/JY/src/topo.csv"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				// 转换成int数组
				int[] int_array = new int[item.length];
				for (int i = 0; i < item.length; i++) {
					int_array[i] = Integer.parseInt(item[i]);
				}
				// 构建边索引矩阵
				mLinkID[int_array[1]][int_array[2]] = int_array[0];
				// 构建图的邻接矩阵
				mMatrix[int_array[1]][int_array[2]] = int_array[3];
				// 确定顶点数量(找出最大值)
				if (vexNum < int_array[1]) {
					vexNum = int_array[1];
					if (vexNum < int_array[2]) {
						vexNum = int_array[2];
					}
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		++vexNum;
		
		boolean flag[] = new boolean[vexNum]; // 表示起始顶点到指定“i”顶点的最短路径已成功获取
		int[] prev = new int[vexNum]; // 顶点“i”的前驱顶点
		int[] dist = new int[vexNum]; // 顶点“i”的当前最短路径
		// 顶点个数为 ++vexNum，以第一个(0, 0)为起始点
		for (int i = 0; i < vexNum; i++) {
			flag[i] = false;
			prev[i] = 0;
			dist[i] = mMatrix[0][i];
		}
		
		int k = 0;
		for (int i = 0; i < vexNum; i++) {
			int min = MAX;
			for (int j = 0; j < vexNum; j++) {
				if (flag[j] == false && dist[j] < min) {
					min = dist[j];
					k = j;
				}
			}
			
			// 标记顶点“k”为已经获取到的最短路径
			
			for (int j = 0; j < vexNum; j++) {
				int tmp = (mMatrix[k][j] == MAX ? MAX : (min + mMatrix[k][j]));
				if (flag[j] == false && (tmp < dist[j])) {
					dist[j] = tmp;
					prev[j] = k;
				}
			}
		}
		
		for (int i = 0; i < vexNum; i++) {
			System.out.printf("Shortest(0, %d) = %d", i, dist[i]);
		}
		
		
		
//		try { 
//	          File csv = new File("F:/writers.csv"); // CSV数据文件
//	    
//	          BufferedWriter bw = new BufferedWriter(new FileWriter(csv， true)); // 附加
//	          // 添加新的数据行
//	          bw.write(""李四"" + "，" + ""1988"" + "，" + ""1992""); 
//	          bw.newLine(); 
//	          bw.close(); 
//	    
//	        } catch (FileNotFoundException e) { 
//	          // File对象的创建过程中的异常捕获
//	          e.printStackTrace(); 
//	        } catch (IOException e) { 
//	          // BufferedWriter在关闭对象捕捉异常
//	          e.printStackTrace(); 
//	        } 
	}
}
