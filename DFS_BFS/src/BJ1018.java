import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1018 {

    static int N, M;
    static boolean board[][];

    static int cnt;
    static int min = 64;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                if(str.charAt(j) == 'W') {
                    board[i][j] = true ;
                }else {
                    board[i][j] = false;
                }
            }
        }

        //------------------------------------------------------------------------

        for (int i = 0; i+7 < M; i ++) {
            for (int j = 0; j+7 < N; j++) {
                getMin(i,j);
            }
        }

        System.out.println(min);
    }

    static void getMin(int i, int j) {
        // 보드판 1택 완료 - count reset / 처음 시작 칸 설정
        cnt = 0;

        // 시작하는 칸 temp - 예상 색깔
        boolean temp = board[i][j];

        for (int z = i; z < i+8; z++) {
            for (int x = j; x < j+8; x++) {

                // 예상 색깔과 다르면 수정
                if(board[z][x] != temp)  {
                    cnt ++;
                }

                // 다음에 와야하는 색
                temp = !temp;
            }
            // 마지막 줄일 경우, 다음줄의 첫번째와 색깔이 똑같아야하므로, temp값을 반대로 해준다..!
            temp = !temp;
        }

        // 처음부터 까만색으로 바꿔서 생각하는 경우, 즉 두가지를 생각하애햐므로~
        cnt = Math.min(cnt, 64 - cnt);

        min = Math.min(cnt, min);
    }
}
