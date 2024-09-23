import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;

        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));
        Queue<Integer> pq = new PriorityQueue<>();

//        모든 시작시간 - 모든 종료시간 / 모든 개수
        int totalCount = jobs.length;
        int totalStartTime = 0;
        for(int i=0; i<jobs.length; i++) {
            totalStartTime += jobs[i][0];
        }
        int time = 0;
        int totalFinTime = 0;

        int index = 0;
        while (index < jobs.length) {
            if(jobs[index][0] <= time) {
                pq.add(jobs[index][1]);
                index++;
            } else if (!pq.isEmpty()){
                int shortestJob = pq.remove();
                time += shortestJob;
                totalFinTime += time;
            } else {
                time = jobs[index][0];
                pq.add(jobs[index][1]);
                index++;
            }
        }
        while (!pq.isEmpty()) {
            int shortestJob = pq.remove();
            time += shortestJob;
            totalFinTime += time;
        }

        answer = (totalFinTime - totalStartTime) / totalCount;

        return answer;
    }
}