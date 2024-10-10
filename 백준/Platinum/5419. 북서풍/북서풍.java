import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] indexTree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        while(T > 0){
            long result = 0;
            T--;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            ArrayList<int[]> arr = new ArrayList<>();
            while(n>0){
                n--;
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr.add(new int[]{x, y});
            }
//            x로 정렬, x 같으면 y로 정렬
            Collections.sort(arr, (a,b)->{
                if(a[0] == b[0]) return Integer.compare(a[1], b[1]);
                else return Integer.compare(a[0], b[0]);
            });
            int idx = -1;
            int temp = -1000000001;

            for(int i=0; i<arr.size(); i++){
                int[] arrEle = arr.get(i);
                if(temp < arrEle[0]) {
                    temp = arrEle[0];
                    idx++;
                }
                arrEle[0] = idx;
                arr.set(i, arrEle);
            }
//            y로 정렬 후 x정렬
            Collections.sort(arr, (a,b)->{
                if(a[1] == b[1]) return Integer.compare(a[0], b[0]);
                else return Integer.compare(b[1], a[1]);
            });

//            현재 x가 압축되어있는 상태 + y로 정렬되어있는 상태.
//            (idx+1)개의 x좌표가 있으니 이걸로 세그먼트 트리 만들어야함.
            int k = 1;
            while(k < (idx+1)){
                k *= 2;
            }

            int[] segmentTree = new int[2*k];

            for(int i=0; i<arr.size(); i++){
                int[] arrEle = arr.get(i);
                int segIdx = k + arrEle[0];

                int add = segmentTreeSum(k, segIdx, segmentTree);
                while(segIdx >= 1){
                    segmentTree[segIdx]++;
                    segIdx /= 2;
                }

                result += add;
            }

            System.out.println(result);
        }
    }

    public static int segmentTreeSum(int st, int ed, int[] segmentTree) {
        int num = 0;

        while(st <= ed){
            if(st % 2 == 1){
                num += segmentTree[st];
                st += 1;
            }
            if(ed % 2 == 0){
                num += segmentTree[ed];
                ed -= 1;
            }
            st /= 2;
            ed /= 2;
        }

        return num;
    }
}
