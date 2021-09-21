package Jaya;

import java.util.*;
import java.util.Map.Entry;

public class Jaya {
    final int max = 1000;
    final int INF = 10000;

    public void jaya(){

    }
    public void scanfpath(ArrayList a){//输入路径
        Scanner in = new Scanner(System.in);
        System.out.println("请输入路径：");
        int i = 0;
        while(!a.equals(9)){
            a.add(i,in.nextInt());
            i ++;
        }
    }
    /*
    根据约束条件生成新的路径
     */
    public void findpath(Graph graph, ArrayList<Integer> path, ArrayList<Integer> bestpath,
                         ArrayList<Integer> worstpath,ArrayList<Integer> tempath){
//        tempath.add(path.get(0));
/*
    tempath存放下一代路径，temp存放约束条件项
 */

        tempath.add(0,path.get(0));//生成的新路径

//        for(int j = 1;tempath.get(j-1) != graph.thevexs.get(graph.thevexs.size()-1); j ++){//修改终点
        for(int j = 1;tempath.get(j-1) != 10000&&tempath.get(j-1)!=bestpath.get(bestpath.size()-1); j ++){
            int t[] = {0,0,0};
            int r[] = {1,1,1,1};
            ArrayList<Integer> temp = new ArrayList<>();//临时存放r0*t0*path[0]……

            if(path.contains(tempath.get(j-1))){
                t[0] = 1;
                temp.add(path.get(path.indexOf(tempath.get(j-1))+1));
            }
            if(bestpath.contains(tempath.get(j-1))){
                t[1] = 1;
                temp.add(bestpath.get(bestpath.indexOf(tempath.get(j-1))+1));
            }
            if(worstpath.contains(tempath.get(j-1))){
                t[2] = 1;
                temp.add(worstpath.get(worstpath.indexOf(tempath.get(j-1))+1));
            }
 /*          if(t[0]*temp.get(0) != 0){
                temp.add(t[0]*tempath.get(j-1));
            }
            if(t[1]*temp.get(1) != 0){
                temp.add(t[1]*tempath.get(j-1));
            }
            if(t[2]*temp.get(2) != 0){
                temp.add(t[2]*tempath.get(j-1));
            }
*/

            temp.add(findranvex(graph,j,worstpath,bestpath,tempath));

            int k = (int)(Math.random()*(temp.size()-1-0)+0);
            tempath.add(temp.get(k));
        }
    }
    /*
    返回与路径中相邻的随机点出去worstpath
     */
    public int findranvex(Graph graph, int vex,ArrayList<Integer> worstpath,ArrayList<Integer> bestpath,ArrayList path){//path中存放路径
//        Random ran = new Random();
        ArrayList<Integer> tempath = new ArrayList();
        for(int i = 0; i < graph.edges.length; i ++){
            if((graph.edges[vex][i] != 0)&&(graph.edges[vex][i] != INF)
                    &&(!worstpath.contains(graph.thevexs.get(i)))&&(!bestpath.contains(graph.thevexs.get(i)))){
                tempath.add(i);
            }
        }
        if(tempath.size() < 1){
            return bestpath.get(vex);
        }
        int k = (int)(Math.random()*((tempath.size()-1)-0)+0);//取随机相邻的点
        return tempath.get(k);
//        return ran.nextInt(tempath.size());
    }
   /*
   迭代产生新的路径
    */

    public List interation(ArrayList<ArrayList<Integer>> path,
                           ArrayList<Integer> bestpath, ArrayList<Integer> worstpath, Graph graph){
        ArrayList<Integer> bestpath_1 = new ArrayList();
        ArrayList<Integer> worstpath_1 = new ArrayList();
        ArrayList<ArrayList<Integer>> pathtemp = new ArrayList<>();//存放迭代中产生的路径

        IdentityHashMap<ArrayList<Integer>, Integer> Sites = new IdentityHashMap<>();
        pathtemp.clear();
        Sites.clear();
        for(int i = 0;i < path.size();i ++){
            ArrayList<Integer> temp = new ArrayList();
            findpath(graph,path.get(i),bestpath,worstpath,temp);
            pathtemp.add(temp);

        }
        for(int i = 0;i < path.size();i ++){
            pathtemp.add(i+path.size(),path.get(i));
        }
        for(int i = 0;i < path.size()*2;i ++){

            Sites.put(pathtemp.get(i),sum(pathtemp.get(i),graph));
        }

        /*
        findpath(graph,path,bestpath,worstpath,path_1);
        findpath(graph,bestpath,bestpath,worstpath,bestpath_1);
        findpath(graph,worstpath,bestpath,worstpath,worstpath_1);
        */
        /*
        Sites.put(path,sum(path,graph));
        Sites.put(bestpath,sum(bestpath,graph));
        Sites.put(worstpath,sum(worstpath,graph));
        Sites.put(path_1,sum(path_1,graph));
        Sites.put(bestpath_1,sum(bestpath_1,graph));
        Sites.put(worstpath_1,sum(worstpath_1,graph));
        */
//        List<Map.Entry<ArrayList<Integer>, Integer>> list = new ArrayList<>();
        List<Map.Entry<ArrayList<Integer>, Integer>> list = new ArrayList<>(Sites.entrySet());
//        list = (List<Entry<ArrayList<Integer>, Integer>>) Sites;
        Sorted(Sites,list,3);
//        for(int i = 0; i < list.size();i ++){
//            path.set(i, (ArrayList<Integer>) list.get(i));
        //       }

        return list;
    }
    /*
    路径求和
     */
    private int sum(ArrayList<Integer> path,Graph graph){
        int sum = 0;
        for(int i = 0;i < path.size()-1&&graph.edges[path.get(i)][path.get(i+1)]!= 10000; i ++){
            sum += graph.edges[path.get(i)][path.get(i+1)];
        }
        return sum;
    }
    /*
    给种群根据权值从小到大排序并返回初代种群数目相同的新种群
     */
    public void Sorted(Map<ArrayList<Integer>,Integer> Sort, List<Entry<ArrayList<Integer>, Integer>> list,int populsize){

        list.sort(new Comparator<Map.Entry<ArrayList<Integer>, Integer>>() {
            @Override
            public int compare(Map.Entry<ArrayList<Integer>, Integer> o1, Map.Entry<ArrayList<Integer>, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        /*
        for(int i = 0; i < populsize; i ++){
            Sort.put(list.get(i).getKey(),list.get(i).getValue());
        }
        */
    }

}
