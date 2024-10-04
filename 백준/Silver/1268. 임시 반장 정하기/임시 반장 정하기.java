import java.util.*;

public class Main {
    static int[][] studentClass;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        Map<Integer, Map<Integer, Set<Integer>>> map = new HashMap<>();

        for(int i=0; i<5; i++){
            map.put(i, new HashMap<Integer, Set<Integer>>());
        }
        studentClass = new int[N][5];

        for(int i=0; i<N; i++){
            for(int j=0; j<5; j++){
                int num = sc.nextInt();
                studentClass[i][j] = num;

                Map<Integer, Set<Integer>> stu = map.get(j);
                stu.computeIfAbsent(num, k-> new HashSet<>());
                stu.get(num).add(i);
            }
        }

        int max = 0;
        int idx = 0;

        for(int i=0; i<N; i++){
            Set<Integer> friend = new HashSet<>();
            for(int j=0; j<5; j++){
                friend.addAll(map.get(j).get(studentClass[i][j]));
            }
            if(max < friend.size()){
                max = friend.size();
                idx = i;
            }
        }

        System.out.println(idx + 1);
    }
}