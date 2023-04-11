import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2468 {
    static int  N ;
    static int map[][];
    static int safeZone;
    static boolean visited[][];
    static Queue<Point> queue;
    static int[] nextX = {0,0,1,-1};
    static int[] nextY = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        queue = new LinkedList<>();
        map = new int[N][N];
        visited = new boolean[N][N];
        safeZone = 0;

        for(int i =0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for(int j =0; j < N; j++){
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        queue.add(new Point(0,0));;

        BFS();

        for(int i =0; i< N; i++) {
            for(int j =0; j<N; j++){
                if(!visited[i][j]) {
                    System.out.println(i + ", " + j);
                    safeZone++;
                }
            }
        }

        System.out.println(safeZone);
    }

    static void BFS(){

        while(!queue.isEmpty()){
            Point cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int now = map[x][y];
            for(int i =0; i< nextX.length; i++){
                    int Xway = x + nextX[i];
                    int Yway = y + nextY[i];
                    if(Xway >= 0 && Xway < N && Yway >=0 && Yway < N){
                        if(map[Xway][Yway] <= now) {
                            queue.add(new Point(Xway, Yway));
                            visited[Xway][Yway] = true;
                        }
                }
            }
        }
    }

    void DFS(int x, int y){


    }

    void check(int x, int y){
        int now =
    }
}
