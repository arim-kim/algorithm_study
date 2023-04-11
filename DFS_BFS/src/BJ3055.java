import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// 최단시간 구하라 -> BFS
// 모든 경우의 수를 보면서 가장 큰거 찾기 -> DFS

public class BJ3055 {
    // 탈출

    static int R,C;
    static char[][] map;
    static boolean[][] isAvailable; // 갈수 있는지 없는지 검사
    static Queue<Point> water;
    static Queue<Point> possibleWay;
    static int[] xWay = {0,0,1,-1};
    static int[] yWay = {1,-1,0,0};
    static int nextX, nextY;
    static Point dest;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        water = new LinkedList<>();
        possibleWay = new LinkedList<>();

        map = new char[R][C];
        isAvailable = new boolean[R][C];

        // 입력받기
        for(int i = 0; i< R; i++) {
            String str = br.readLine();
            for (int j = 0; j< C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 물, 돌이 있는 곳은 갈 수 없으므로
        for(int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 'X') { // 돌이 있으면 갈 수 없으므로
                    isAvailable[i][j] = false;
                } else if (map[i][j] == '*') { // 물이 있으면 갈 수 없음 + 물의 큐에 넣어줘야함 하나씩 빼야하나까
                    water.offer(new Point(i,j));
                    isAvailable[i][j] = false;
                } else if (map[i][j] == 'S'){ // 고슴도치 현재 위치 -> 여기서 출발해야하니까 제일 먼저 큐에 넣어줌 얘도 하나씩 빼야됨
                    possibleWay.offer(new Point(i,j)); // 고슴도치가 있는 곳은 상관 없음
                } else if (map[i][j] == 'D') { // 얘에서 걸렸음 에효 -> 두더집은 물은 못들어가지만 고슴도치는 최종적으로 갈 수 있어야함
                    isAvailable[i][j] = false;
                    dest = new Point(i,j);
                } else {
                    isAvailable[i][j] = true; // 아닌 곳은 갈 수 있어~!
                }
            }
        }

        int count = BFS();
        if(count == -1) {
            System.out.println("KAKTUS");
        } else
            System.out.println(count);


//        if(BFS() == -1) {
//            System.out.println("KAKTUS");
//        }else {
//            System.out.println(BFS());
//        }

//        System.out.println(BFS() == -1 ? -1 : BFS());
    }


    static void waterMove() {

        Queue<Point> nextWater = new LinkedList<>();

        while(!water.isEmpty()) {
            Point cur = water.poll();
            for(int i =0; i < xWay.length; i++) {
                nextX = cur.x + xWay[i];
                nextY = cur.y + yWay[i];
                if ((nextX >= 0 && nextY >= 0 && nextX < R && nextY < C) && (isAvailable[nextX][nextY])) {
                    isAvailable[nextX][nextY] = false;
                    nextWater.add(new Point(nextX,nextY));
                }
            }
        }
        water = nextWater;
    }

    static int BFS() {

        int count = 0;

        while(true) {
            if(possibleWay.isEmpty()) return -1;

            waterMove(); // 물 한번 뿌리기

            Queue<Point> nextPossible = new LinkedList<>();
            
            while(!possibleWay.isEmpty()) { // 시작점 기준으로 돌면서 갈 곳 수집하기
                Point cur = possibleWay.poll();
                for(int i =0 ; i < xWay.length; i++) {
                    nextX = cur.x + xWay[i];
                    nextY = cur.y + yWay[i];
                    if(nextX == dest.x && nextY == dest.y) {
                        return count + 1;
                    }
                    if ((nextX >= 0 && nextY >= 0 && nextX < R && nextY < C) && (isAvailable[nextX][nextY])) {
                        nextPossible.add(new Point(nextX, nextY));
                        isAvailable[nextX][nextY] = false;
                    }
                }
            }
            possibleWay = nextPossible;
            count++;
        }
    }
}
