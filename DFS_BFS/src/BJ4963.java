import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ4963 {

    static int w,h;
    static int[] xWay = {0,0,1,-1,1,-1,1,-1}; // 상, 하, 좌, 우, 오위, 왼위, 오아, 왼아
    static int[] yWay = {1,-1,0,0,1,1,-1,-1};
    static Queue<Point> queue;
    static boolean[][] isVisited ;
    static int[][] map;

    static int count;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            count = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h ==0) {
                break;
            }

            map = new int[h][w];
            isVisited = new boolean[h][w];
            queue = new LinkedList<>();

            for(int i =0; i < h; i++) {
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(input[j]);
                }
            }


            for(int i =0; i < h; i++){
                for(int j =0; j < w; j++) {
                    if(map[i][j] == 1 && !isVisited[i][j]){
                        count ++;
                        isVisited[i][j] = true;
                        queue.add(new Point(i,j));
                        BFS();
                    }
                }
            }

            System.out.println(count);

        }
    }


    static void BFS(){

        int nextX;
        int nextY;

        while(!queue.isEmpty()){
            Point cur = queue.poll();
            for(int i =0; i < xWay.length; i++){
                    nextX = cur.x + xWay[i];
                    nextY = cur.y + yWay[i];
                if(nextX >= 0 && nextX < h && nextY >= 0 && nextY < w && !isVisited[nextX][nextY]) {
                    if(map[nextX][nextY] == 1) {
                        queue.add(new Point(nextX,nextY));
                        isVisited[nextX][nextY] = true;
                    }
                }
            }
        }
    }
}
