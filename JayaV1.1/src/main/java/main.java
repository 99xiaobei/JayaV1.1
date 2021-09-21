//import Jaya.Fileread;
//import Jaya.Filereadpath;

import Jaya.Filereadpathxlsx;
import Jaya.Filereadxlsx;
import Jaya.Graph;
import Jaya.Jaya;

import java.util.ArrayList;
import java.util.Map;

public class main {
    private static Object ArrayList;

    public static void main(String[] args) {
        final int INF = 10000;
        ArrayList vexs = new ArrayList();
        //存放排序后的路径群
        ArrayList<Map.Entry<ArrayList<Integer>, Integer>> list =
                new ArrayList<>();


        String A[][] = new String[0][];
        String Path[][] = new String[0][];
        String B[][] = new String[0][];

        Graph graph = new Graph();
        Jaya jaya = new Jaya();
//        Fileread fileread = new Fileread();
//        Filereadpath filereadhpat = new Filereadpath();
        Filereadxlsx filereadxlxs = new Filereadxlsx();
        Filereadpathxlsx filereadpathxlsx = new Filereadpathxlsx();
        //       A = fileread.fileread();
        //       Path = filereadhpat.Filereadhpat();
        A = filereadxlxs.filereadxlsx();
        Path = filereadpathxlsx.filereadpathxlsx();
        double[][] ds = new double[A.length][A[0].length];
        int[][] ps = new int[Path.length][50];//25为路径中最大路过的点数
        for(int j=0;j<A.length;j++){
            for(int i=0;i<A[0].length;i++){
                ds[j][i] = Double.parseDouble(A[j][i]);
//                System.out.printf(A[j][i] + " ");
            }
//            System.out.printf("\n");
        }
        for(int j=0;j<Path.length;j++){
            for(int i=0;Path[j][i]!=null&&i < Path[j].length-1;i++){
                ps[j][i] = (int)(Double.parseDouble(Path[j][i]));
//                System.out.printf(Path[j][i] + " ");
            }
//            System.out.printf("\n");
        }
        for(int i = 0;i < ds.length;i ++){
            vexs.add(i);
//            System.out.printf(String.valueOf(vexs.get(i)));
        }
        graph.createGraph(graph, ds, vexs);
//        graph.printGraph(graph);
        ArrayList<Integer> path = new ArrayList<Integer>();
        int[] path1 = new int[]{0, 1, 2, 13, 18, 23, 36, 45, 48, 49, 57, 56, 55, 62, 69, 71, 72, 70, 81};
        int[] path2 = new int[]{0,10,26,27,28,38,37,46,45,48,47,54,61,69,70,81};
        int[] path3 = new int[]{0,1,2,14,15,17,18,22,30,31,33,39,51,52,53,59,58,77,74,73,81};

        ArrayList<ArrayList<Integer>> pathall = new ArrayList<>();
        ArrayList<Integer> bestpath = new ArrayList<Integer>();
        for(int i = 0; i < ps.length;i ++){
            ArrayList<Integer>swap = new ArrayList<Integer>();
            for(int j = 0;j < ps[i].length;j ++){
                swap.add(ps[i][j]);
            }
            pathall.add(swap);
        }
        for(int j=0;j<pathall.get(1).size();j++){
            path.add(pathall.get(1).get(j));
        }
        for(int j=0;j<pathall.get(0).size();j++){
            bestpath.add(pathall.get(1).get(j));
        }
        ArrayList<Integer> worstpath = new ArrayList<Integer>();
        for(int j=0;j<pathall.get(2).size();j++){
            worstpath.add(pathall.get(1).get(j));
        }



        long startTime=System.currentTimeMillis();
        for(int i = 0; i < 100; i ++){//迭代次数
            //           for(int j = 0; j < pathall.size(); j ++){
            list.clear();
            list = (ArrayList<Map.Entry<ArrayList<Integer>, Integer>>) jaya.interation(pathall,bestpath,worstpath,graph);
            path = list.get(1).getKey();
            bestpath = list.get(0).getKey();
            worstpath = list.get(2).getKey();
            for(int j = 0; j < pathall.size();j ++){
                pathall.set(j,list.get(j).getKey());
            }
            for(int k = 0; k < bestpath.size();k ++){
                System.out.printf("%d"+" ",bestpath.get(k));

            }

            System.out.println(i + "\n");

//            }
        }
        long endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

        //       for(int i = 0; i < path.size();i ++){
//            System.out.printf("%d"+" ",path.get(i));
//        }
//        System.out.println("\n");
        for(int i = 0; i < bestpath.size();i ++){
            System.out.printf("%d"+" ",bestpath.get(i));
        }
//        System.out.println("\n");
        //       for(int i = 0; i < worstpath.size();i ++){
        //           System.out.printf("%d"+" ",worstpath.get(i));
        //       }
        System.out.println("\n");
    }
}
