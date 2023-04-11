import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1697 {

    static int N, K;
    static Queue<Integer> queue;
    static int[] visited;
    static int cur;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new int[100001];

        for(int a : visited)
            a = 0;
        // N까지 도달한 경로 횟수가 visited[N]에 저장되게 함
        visited[N] = 0; // 방문
        queue = new LinkedList<>();
        queue.add(N);

        System.out.println(BFS());
    }

    static int BFS() {

        int count = 0;

        while(!queue.isEmpty()) {

            // queue에서 하나 빼기
            cur = queue.poll();
            // 뺀 값이 k라면 지나온 횟수 바로 출력
            if(cur == K) return visited[cur];
            count ++; // 횟수증가
            if((cur -1)==K || (cur+1)==K || (cur*2)==K) return visited[cur] + 1 ;
            put((cur-1));
            put((cur+1));
            put((cur*2));
        }

        return visited[cur];
    }


    // 아직 방문하지 않았다면 큐에 넣어주기 + 조건
    static void put(int a) {
        if((0 <= a) && (a <= 100000) && visited[a] == 0 ) {
            queue.add(a);
            visited[a] = visited[cur] + 1; // a까지 갔을때 경로 횟수는 지난 경로횟수 + 1
        }
    }
}
