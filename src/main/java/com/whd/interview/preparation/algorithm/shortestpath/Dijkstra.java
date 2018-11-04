package com.whd.interview.preparation.algorithm.shortestpath;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/1 11:39
 * @apiNote Dijkstra
 * 1)算法思想：设G=(V,E)是一个带权有向图，把图中顶点集合V分成两组，
 * 第一组为已求出最短路径的顶点集合（用S表示，初始时S中只有一个源点，以后每求得一条最短路径 , 就将加入到集合S中，直到全部顶点都加入到S中，算法就结束了），
 * 第二组为其余未确定最短路径的顶点集合（用U表示），按最短路径长度的递增次序依次把第二组的顶点加入S中。在加入的过程中，
 * 总保持从源点v到S中各顶点的最短路径长度不大于从源点v到U中任何顶点的最短路径长度。
 * 此外，每个顶点对应一个距离，S中的顶点的距离就是从v到此顶点的最短路径长度，U中的顶点的距离，是从v到此顶点只包括S中的顶点为中间顶点的当前最短路径长度。
 *
 * 2)算法步骤：
 * a.初始时，S只包含源点，即S＝{v}，v的距离为0。U包含除v外的其他顶点，即:U={其余顶点}，若v与U中顶点u有边，则<u,v>正常有权值，若u不是v的出边邻接点，则<u,v>权值为∞。
 * b.从U中选取一个距离v最小的顶点k，把k，加入S中（该选定的距离就是v到k的最短路径长度）。
 * c.以k为新考虑的中间点，修改U中各顶点的距离；若从源点v到顶点u的距离（经过顶点k）比原来距离（不经过顶点k）短，则修改顶点u的距离值，修改后的距离值的顶点k的距离加上边上的权。
 * d.重复步骤b和c直到所有顶点都包含在S中。
 *
 *
 * 3)存储结构：
 * 1.两个数组
 * 2.链式存储主要有邻接表、邻接多重表和十字链表
 */
public class Dijkstra {

     class Node{
        /**
         * 节点的标识符
         */
        private Integer identifier;
        /**
         * 该节点是否被访问过
         */
        private boolean visited = false;
        /**
         * 该节点与其他节点的映射关系
         */
        private Map<Node,Integer> mapping = new HashMap<Node,Integer>();

        public Integer getIdentifier() {
            return identifier;
        }
        public void setIdentifier(Integer identifier) {
            this.identifier = identifier;
        }
        public boolean isVisited() {
            return visited;
        }
        public void setVisited(boolean visited) {
            this.visited = visited;
        }
        public Map<Node, Integer> getMapping() {
            return mapping;
        }
    }


    /**
     * Getting optimal path by Dijkstra algorithm
     * @param src
     * @param dest
     * @return
     */
    public static LinkedList<Node> getOptimalPath(Node src, Node dest){
        return dijkstra(src, dest, 0, 0, new LinkedList<Node>());
    }

    /**
     * Dijkstra algorithm
     * @param src 起始节点
     * @param dest 目标节点
     * @param adjacentDist 起始节点与目标节点相邻时的距离
     * @param optimalDist 最短路径权值之和
     * @param optimalPath 最短路径
     * @return
     */
    private static LinkedList<Node> dijkstra(Node src, Node dest, int adjacentDist, int optimalDist, LinkedList<Node> optimalPath){
        if(optimalPath.size()==0){
            optimalPath.add(src);
        }
        //当前节点与其他节点的映射关系
        Map<Node,Integer> mapping = src.getMapping();
        //当前节点与其相邻节点的最小距离
        int partialMinDist = 0;
        //当前预选的下一最优节点
        Node partialOptimalNode = null;
        for (Map.Entry<Node, Integer> entry : mapping.entrySet()) {
            Node nextNode = entry.getKey();
            //判断该相邻节点是否被访问过
            if (nextNode.isVisited()) continue;
            int dist = entry.getValue();
            //终点与起点相邻，但未必路径最短
            if (nextNode.getIdentifier().equals(dest.getIdentifier()) && optimalPath.size() == 1) adjacentDist = dist;
            if (partialMinDist != 0) {
                boolean currentIsOptimal = partialMinDist <= dist;
                if (!currentIsOptimal) {
                    partialMinDist = dist;
                    partialOptimalNode = nextNode;
                }
            } else {
                partialMinDist = dist;
                partialOptimalNode = nextNode;
            }
        }
        //设置当前节点已被访问
        src.setVisited(true);
        //加入链表
        optimalPath.add(partialOptimalNode);
        optimalDist += partialMinDist;
        if(!partialOptimalNode.getIdentifier().equals(dest.getIdentifier())){
            //若未到终点时当前最优路径长度已大于终点与起点相邻的距离，则最短路径为起点--->终点
            if(adjacentDist != 0 && optimalDist > adjacentDist) {
                src = optimalPath.removeFirst();
                optimalPath.clear();
                optimalPath.add(src);
                optimalPath.add(dest);
                optimalDist = adjacentDist;
                return optimalPath;
            }
            //否则以当前节点递归
            return dijkstra(partialOptimalNode, dest, adjacentDist, optimalDist, optimalPath);
        }else{
            return optimalPath;
        }
    }


    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        Dijkstra.Node node_1 = dijkstra.new Node();
        Dijkstra.Node node_2 = dijkstra.new Node();
        Dijkstra.Node node_3 = dijkstra.new Node();
        Dijkstra.Node node_4 = dijkstra.new Node();
        Dijkstra.Node node_5 = dijkstra.new Node();
        Dijkstra.Node node_6 = dijkstra.new Node();

        node_1.setIdentifier(1);
        node_1.getMapping().put(node_2, 7);
        node_1.getMapping().put(node_3, 9);
        node_1.getMapping().put(node_6, 14);

        node_2.setIdentifier(2);
        node_2.getMapping().put(node_1, 7);
        node_2.getMapping().put(node_3, 10);
        node_2.getMapping().put(node_4, 15);

        node_3.setIdentifier(3);
        node_3.getMapping().put(node_1,7);
        node_3.getMapping().put(node_2,10);
        node_3.getMapping().put(node_4,11);
        node_3.getMapping().put(node_6,2);

        node_4.setIdentifier(4);
        node_4.getMapping().put(node_3, 11);
        node_4.getMapping().put(node_2, 15);
        node_4.getMapping().put(node_5, 6);

        node_5.setIdentifier(5);
        node_5.getMapping().put(node_4, 6);
        node_5.getMapping().put(node_6, 9);

        node_6.setIdentifier(6);
        node_6.getMapping().put(node_5, 9);
        node_6.getMapping().put(node_1, 14);

        LinkedList<Node> optimalPath = Dijkstra.getOptimalPath(node_1, node_5);
        System.out.println("-------The optimal path--------");
        for (Iterator<Node> iterator = optimalPath.iterator(); iterator.hasNext();) {
            Node node = iterator.next();
            if (iterator.hasNext()) {
                System.out.print(node.getIdentifier()+"-->");
            }else{
                System.out.print(node.getIdentifier());
            }
        }
    }
}
