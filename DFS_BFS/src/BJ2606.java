import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BJ2606 {

    static int numOfCom ; // number of vertex
    static int numOfPair;
    static ArrayList<Integer>[] nodeList; // edge list
    static boolean[] isVisited;
    static List<Integer> circle;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        numOfCom = sc.nextInt();
        numOfPair = sc.nextInt();

        nodeList = new ArrayList[numOfCom+1];
        isVisited = new boolean[numOfCom+1];
        circle = new ArrayList<>(numOfCom+1);


        for(int i = 1; i<= numOfCom; i++) {
            nodeList[i] = new ArrayList<>();
        }

        for(int i = 0; i < numOfPair; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            nodeList[from].add(to);
            nodeList[to].add(from);
        }

        for(int i = 1; i<= numOfCom; i++) {
            if(!isVisited[i]) {
                DFS(i);
//                System.out.println(circle);
                if(circle.contains(1)) {
                    System.out.println(circle.size()-1);
                    break;
                }
                circle.clear();
            }
        }

    }


    // recursion
    static void DFS(int v){

        if(isVisited[v]) return;
        circle.add(v);
        isVisited[v] = true;

        for (int i : nodeList[v]) {
            if (!isVisited[i])
                DFS(i);
        }

        DFS(v);
    }
}
