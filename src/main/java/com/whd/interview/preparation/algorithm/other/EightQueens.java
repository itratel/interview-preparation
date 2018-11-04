package com.whd.interview.preparation.algorithm.other;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/4 15:15
 * @apiNote EightQueens
 **/
public class EightQueens {

    /***
     * 皇后数量
     */
    private static final int QUEENS_NUM = 8;

    /***
     * 总共有多少中摆法
     */
    private static int count = 1;

    /***
     * 表示棋盘,0表示没有皇后，1表示皇后
     */
    static int[][] map = new int[QUEENS_NUM][QUEENS_NUM];

    public static void main(String[] args) {
        play(0);
//        show();
    }

    /***
     * 显示棋盘
     */
    private static void show() {
        System.out.println("第" + count + "种摆法");
        for (int i = 0; i < QUEENS_NUM; i++) {
            for (int j = 0; j < QUEENS_NUM; j++) {
                System.out.print(map[i][j] + "   ");
            }
            System.out.println("\n");
        }
        count++;
    }

    /***
     * 验证map[i][j]位置是否能够放皇后
     * @param row 行
     * @param col 列
     * @return boolean
     */
    private static boolean check(int row, int col) {
        //判断第这一列是否有皇后
        for (int i = row - 1; i >= 0; i--) {
            if (map[i][col] == 1){
                return false;
            }
        }

        //判断左斜上方是否有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (map[i][j] == 1){
                return false;
            }
        }

        //判断右斜上方是否有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < QUEENS_NUM; i--, j++) {
            if (map[i][j] == 1){
                return false;
            }
        }
        return true;
    }


    /***
     * 对某一行放皇后
     * @param row row
     */
    private static void play(int row) {
        //遍历当前行的所有单元格
        for (int i = 0; i < QUEENS_NUM; i++) {
            //能够放皇后
            if (check(row, i)) {
                //放皇后
                map[row][i] = 1;
                //如果是最后一行，表示完成8皇后完成，则展示棋盘查看
                if (row == QUEENS_NUM - 1) {
                    show();
                    //当最后一行这个皇后摆好了之后，去掉这个皇后，继续拜访
                    map[row][i] = 0;
                } else {
                    //继续便利下一行
                    play(row + 1);
                    //当调用自己结束时，去掉当前设置的皇后，继续拜访
                    map[row][i] = 0;
                }
            }
        }
    }
}
