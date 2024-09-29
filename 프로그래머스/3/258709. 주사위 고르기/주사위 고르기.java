import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[] solution(int[][] dice) {
        int[] answer = {};
        // 절반만 계산하면 나머지 나머지도 계산 가능
        int n = dice.length / 2;

        List<List<Integer>> comb = new ArrayList<List<Integer>>();
        List<List<Integer>> remain = new ArrayList<List<Integer>>();

        calcComb(comb, remain, new ArrayList<>(), n, 0, dice.length);

        int winCount =0;
        for(int i=0; i<comb.size(); i++){
            List<Integer> s = comb.get(i);
            List<Integer> r = remain.get(i);
            int newCount = winCount(dice, s, r);
            if(winCount < newCount){
                winCount = newCount;
                answer = new int[s.size()];
                for(int j=0; j<s.size(); j++){
                    answer[j] = s.get(j)+1;
                }
            }

        }

        return answer;
    }
    private void calcComb(List<List<Integer>> comb, List<List<Integer>> remain, List<Integer> current, int n, int start, int len) {
        if(current.size() == n) {
            comb.add(new ArrayList<>(current));

            List<Integer> tmp = new ArrayList<>();
            for(int i=0; i<len; i++) {
                if(!current.contains(i)) {
                    tmp.add(i);
                }
            }
            remain.add(tmp);
        } else {
            for(int i=start; i<len; i++) {
                current.add(i);
                calcComb(comb, remain, current, n, i+1, len);
                current.remove(current.size()-1);
            }
        }
    }
    private int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size();
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
    private List<Integer> calculateSums(int[][] dices, List<Integer> indices) {

        List<Integer> sums = new ArrayList<>();
        calculateSumsRecursive(dices, indices, 0, 0, sums);
        return sums;
    }

    private void calculateSumsRecursive(int[][] dices, List<Integer> indices, int index, int currentSum, List<Integer> sums) {
        if (index == indices.size()) {
            sums.add(currentSum);
            return;
        }

        int diceIndex = indices.get(index);
        for (int i = 0; i < dices[diceIndex].length; i++) {
            calculateSumsRecursive(dices, indices, index + 1, currentSum + dices[diceIndex][i], sums);
        }
    }

    private int winCount(int[][] dice, List<Integer> selected, List<Integer> remain){
        int winCount = 0;

        List<Integer> selectedSums = calculateSums(dice, selected);
        List<Integer> remainingSums = calculateSums(dice, remain);

        Collections.sort(selectedSums);
        Collections.sort(remainingSums);

        for(int selectedNum : selectedSums){
            int winIndex = binarySearch(remainingSums, selectedNum);
            winCount += winIndex;
        }


        return winCount;
    }
}
