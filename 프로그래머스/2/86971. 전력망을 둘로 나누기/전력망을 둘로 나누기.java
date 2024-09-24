import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int solution(int n, int[][] wires) {
        init(wires);
        min = n;
        allTreeCount = n;

        int count = treeCount(wires[0][0]);
        if(allTreeCount != count){
            throw new RuntimeException("로직이 이상한 것");
        }

        return min;
    }

    public void init(int[][] wires){
        for (int[] wire : wires) {
            map.putIfAbsent(wire[0], new HashSet<>());
            map.putIfAbsent(wire[1], new HashSet<>());
            map.get(wire[0]).add(wire[1]);
            map.get(wire[1]).add(wire[0]);
        }
    }
    Map<Integer, Set<Integer>> map = new HashMap<>();
    Set<Integer> visit = new HashSet<>();

    int allTreeCount;
    int min;
    public int treeCount(int root){
        visit.add(root);

        int count = 1;

        for (Integer childNode : map.get(root)) {
            if(visit.contains(childNode)) continue;
            count += treeCount(childNode);
        }
        int otherTreeCount = allTreeCount - count;
        min = Math.min(min, Math.abs(count - otherTreeCount));

        return count;
    }
}