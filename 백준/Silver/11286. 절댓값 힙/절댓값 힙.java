import java.util.*;

public class Main {
    static int answer = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> q = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();
        int N = sc.nextInt();
        for(int i=0; i<N; i++) {
            int x = sc.nextInt();
            if(x != 0) {
                map.put(x, map.getOrDefault(x, 0) + 1);
                if(x > 0){
                    q.add(x);
                }
                else{
                    q.add(-x);
                }
            }
            else {
                if(q.isEmpty()){
                    System.out.println(0);
                }
                else{
                    int pop = q.remove();
                    int isMinus = map.getOrDefault(-pop, 0);
                    if(isMinus == 0) {
                        System.out.println(pop);
                        map.put(pop, map.get(pop) - 1);
                    }
                    else {
                        System.out.println(-pop);
                        map.put(-pop, map.get(-pop) - 1);
                    }
                }
            }
        }
    }
}