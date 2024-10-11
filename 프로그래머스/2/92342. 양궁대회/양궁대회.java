import java.util.*;

class Solution {
    static int opp;
    static int arrow;
    static int[] inf;
    static int[] ans;
    static int max;

    public int[] solution(int n, int[] info) {
        ans = new int[info.length];
        inf = info;
        arrow = n;
        for(int i=0; i<info.length; i++){
            if(info[i] > 0) opp += (10-i);
        }
        
        
        // 점수를 최대로 내는 방법
        // 10 ~ 1 콤비네이션
        for(int i=n; i>0; i--){
            List<Integer> arr = new ArrayList<>();
            combination(i, 1, arr, 0);    
        }
        
        
        if(max > 0){
            return ans;
        }
        int[] answer = {-1};
        return answer;
    }
    public static void combination(int fin, int st, List<Integer> arr, int sum){
        if(arr.size() == fin) {
            check(arr);
            return;
        }
        for(int i=st; i<=10; i++){
            arr.add(i);
            combination(fin, i+1, arr, sum+i);
            arr.remove(Integer.valueOf(i));
        }
    }
    public static void check(List<Integer> arr){
        int ap = opp;
        int li = 0;
        int ar = arrow;
        
        int[] temp = new int[inf.length];
        
        for(int i=0; i<arr.size(); i++){
            int target = arr.get(i);
            int idx = 10-target;
            
            ar -= (inf[idx]+1);
            
            if(inf[idx] != 0){
                ap -= target;    
            }
            li += target;
            
            temp[idx] = inf[idx]+1;
            if(ar < 0) return;
        }
        if(li <= ap) return;
        int diff = li - ap;
        
        temp[10] = ar;
        
        if(diff > max){
            max = diff;
            ans = temp;
        }
        else if(diff == max){
            for(int i=10; i>=0; i--){
                if(ans[i] < temp[i]) {
                    ans = temp;
                    break;
                }
                else if(ans[i] > temp[i]) break;
            }
        }
    }
}