import java.util.*;

public class BJ1260 {

    static int N, M, V; // input vertex, edge, start vertex
    static ArrayList<Integer>[] nodeList; // edge list
    static boolean[] isVisted;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // vertex
        M = sc.nextInt(); // edge
        V = sc.nextInt(); // start vertex
        nodeList = new ArrayList[N+1];


        isVisted = new boolean[N+1];  // visited vertexs

        // init
        for (int i = 1; i <= N; i++) {
            nodeList[i] = new ArrayList<>();
        }

        // insert node
        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            // twoWay
            nodeList[from].add(to);
            nodeList[to].add(from);
        }

        // ++++++++ sorting
        for (int i = 1; i <=N; i++) {
            Collections.sort(nodeList[i]);
        }

        DFS(V);
        System.out.println();
        Arrays.fill(isVisted, false);

        BFS(V);
    }

    // recursion
    static void DFS(int v) {

        if (isVisted[v]) return;

        System.out.print(v + " ");
        isVisted[v] = true;

        for (int i : nodeList[v]) {
            if (!isVisted[i])
                DFS(i);
        }
    }

    // Queue
    static void BFS(int v){

        Queue<Integer> q = new LinkedList<>();

        q.offer(v);
        isVisted[v] = true;

        while(!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");

            for(int i : nodeList[node]) {
                if(!isVisted[i]) {
                    q.offer(i);
                    isVisted[i] = true;
                }
            }
        }
    }
}
