import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1012 {

    static int T; // num of test case
    static int M, N, K; // 행, 열, 개수
    static int[][] map; // 배추 지도
    static boolean[][] isVisited;
    static int[] Xway, Yway; // 방향키
    static int[] neededWarm;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());



        Xway = new int[]{0, 0, -1, 1};
        Yway = new int[]{1, -1, 0, 0};
        neededWarm = new int[T];


        for(int cnt = 0; cnt < T; cnt ++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // 지도 크기
            map = new int[N+1][M+1];
            isVisited = new boolean[N+1][M+1];

            for(int i = 0; i < K; i++) {
                String[] input = br.readLine().split(" ");
                map[Integer.parseInt(input[0]) + 1 ][Integer.parseInt(input[1]) + 1] = 1 ;
            }

            for(int x = 1; x <= N; x++) {
                for (int y = 1 ; y <= M; y++) {
                    if((map[x][y] == 1) && (!isVisited[x][y])) {
                        neededWarm[cnt]++;
                        DFS(x,y);
                        ;
                    }
                }
            }

        }

        for(int i : neededWarm) {
            System.out.println(i);
        }
    }

    static void DFS(int v1, int v2) {

        if (isVisited[v1][v2])
            return ;

        int nextV1 = 0;
        int nextV2 = 0;

        isVisited[v1][v2] = true;

        for (int i = 0; i < 4; i++) {
            nextV1 = v1 + Xway[i];
            nextV2 = v2 + Yway[i];

            if (nextV1 > 0 && nextV1 <= N && nextV2 > 0 && nextV2 <= M) {
                if(map[nextV1][nextV2] == 1 && !isVisited[nextV1][nextV2]) {
                    DFS(nextV1, nextV2);
                }
            }
        }

        return;
    }

}
