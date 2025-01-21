import java.util.*;

class Solution {
    public static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        // 총 정답의 개수
        // 1. 전체 num의 합
        // 2. target을 달성하기 위해서 몇을 빼야하는지 (target)
        // 3. 수들을 더해서 minus를 달성할 수 있도록 bfs
        
        int totalSum = Arrays.stream(numbers).sum();
        
        int bfsTarget = totalSum - target;
        
        if(bfsTarget % 2 != 0 || bfsTarget < 0)
            return 0;
        else if(bfsTarget == 0)
            return 1;
        
        bfsTarget /= 2;
        
        bfs(bfsTarget, 0, numbers, 0);
        
        return answer;
    }
    
    public void bfs(int target, int sum, int[] numbers, int idx) {
        if(target < sum) return;
        else if(target == sum) {
            answer += 1;
            return;
        }
        
        for(int i=idx; i<numbers.length; i++) {
            sum += numbers[i];
            bfs(target, sum, numbers, i+1);
            sum -= numbers[i];
        }
    }
}