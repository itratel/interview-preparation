package com.whd.interview.preparation.algorithm.shortestpath;

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

    public static void main(String[] args) {

    }


    private static int[] newData(int[] array){
//        int variable
        return array;
    }

}
