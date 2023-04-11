import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2667 {

    static int N; // size of map
    static int[][] map; // 지도
    static boolean[][] isVisited;
    static List<Integer> group; // 단지 그룹 -> 각 단지마다 몇채의 집이 포함되는지를 원소로함
    static int size; // 단지 사이즈 (한 단지내 집이 몇 채인지 표현)
    static int[] Xway, Yway; // 방향키
    public static void main(String[] args) throws Exception{


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        Xway = new int[]{0, 0, -1, 1};
        Yway = new int[]{1, -1, 0, 0};

        // 지도 크기
        map = new int[N+1][N+1];
        isVisited = new boolean[N+1][N+1];

        // initialize
        group = new ArrayList<>();

        // 입력받기
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i+1][j+1] = Character.getNumericValue(line.charAt(j));
            }
        }

        // -- -------------
        for(int i = 1; i <= N; i++) {
            for (int j = 1 ; j <= N; j++) {
                if((map[i][j] == 1) && (!isVisited[i][j])) {
                    //일단 본인이 포함되므로 1
                    size = 1;
                    size = DFS(i, j);
                    group.add(size);
                    System.out.println("-------------------");
                }
            }
        }


        System.out.println("단지 수 : "+ group.size());
//        System.out.println(group.size());

        Collections.sort(group);
        for(int i : group) {
            System.out.println(i);
        }
    }

    // recursion
    static int DFS(int v1, int v2) {

        if (isVisited[v1][v2]) return 0;

        int nextV1 = 0;
        int nextV2 = 0;

        isVisited[v1][v2] = true;

        for (int i = 0; i < 4; i++) {
            nextV1 = v1 + Xway[i];
            nextV2 = v2 + Yway[i];

            if (nextV1 > 0 && nextV1 <= N && nextV2 > 0 && nextV2 <= N) {
                if(map[nextV1][nextV2] == 1 && !isVisited[nextV1][nextV2]) {
                    size ++;
                    System.out.println("x : " + nextV1+ ", y : " + nextV2 );
                    DFS(nextV1, nextV2);
                }
            }
        }

        return size;
    }
}
