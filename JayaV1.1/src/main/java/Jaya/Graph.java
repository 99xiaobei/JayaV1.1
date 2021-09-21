package Jaya;


import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
    final int max = 10000;
    final int INF = 10000;

    /*
     * 顶点节点
     */
    public class VexNode{
        int adjvex;
        int data;
    }

    VexNode[] vexNodes;
    ArrayList thevexs = new ArrayList();
    //   int[] thevexs; //顶点集合
    double[][] edges = new double[max][max]; //边集合




    /*
     * 创建图
     */
    public void createGraph(Graph graph, double[][] A, ArrayList vexs) {
        for (int i = 0; i < vexs.size(); i++) {
            thevexs.add(vexs.get(i));
            for (int j = 0; j < vexs.size(); j++) {
                this.edges[i][j] = A[i][j];
            }
        }
    }

    public Object getValueOfThevexs(int index)
    {
        return thevexs.get(index);
    }

    public void scanfpath(ArrayList path){//输入路径
        System.out.printf("请输入路径");
        Scanner in = new Scanner(System.in);
        int i = 0;
        while(path.equals("#")){
            path.add(i,in.nextInt());
            i ++;
        }
    }

    /*
     * 输出图
     */
    public void printGraph(Graph graph) {
        for (int i = 0; i < graph.thevexs.size(); i++) {
            for (int j = 0; j < graph.thevexs.size(); j++) {
                //没有路径则输出/
                if (graph.edges[i][j]==10000) {
                    System.out.printf("%4s","/");

                }else {
                    System.out.printf("%.1f"+" ",graph.edges[i][j]);
                }

            }
            System.out.println("\n");
        }
    }
}
