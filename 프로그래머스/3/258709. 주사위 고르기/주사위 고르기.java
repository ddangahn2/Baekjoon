import java.util.*;

class Solution {
    static int max = 0;
    static ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();
    public int[] solution(int[][] dice) {
        int[] answer = {};
//         1. dice combinations 만들기
        ArrayList<Integer> combi = new ArrayList<>();
        combi.add(0);
        combination(combi, dice.length, 1);
        
//         2. combination 돌면서 여집합 만들기
        for(ArrayList<Integer> combination1: combinations){
            ArrayList<Integer> combination2 = getComplement(combination1, dice.length);
//             3. combination 마다 주사위 결과 구하기
            ArrayList<Integer> result1 = new ArrayList<>();
            ArrayList<Integer> result2 = new ArrayList<>();
            
            getResult(dice, combination1, result1, 0, 0);
            getResult(dice, combination2, result2, 0, 0);
            
            Collections.sort(result1, Comparator.reverseOrder());
            Collections.sort(result2, Comparator.reverseOrder());
            
            int win = 0;
            int lose = 0;
            
            int idx = 0;
            for(int i=0; i<result1.size(); i++) {
                while(idx < result2.size() && result1.get(i) <= result2.get(idx)) {
                    idx += 1;
                }
                win += result2.size() - idx;
            }
            idx = 0;
            for(int i=0; i<result2.size(); i++) {
                while(idx < result1.size() && result2.get(i) <= result1.get(idx)) {
                    idx += 1;
                }
                lose += result1.size() - idx;
            }

            if(win > lose) {
                if(win > max) {
                    answer = combination1.stream().mapToInt(i->i+1).toArray();
                    max = win;
                }
            }
            else {
                if(lose > max) {
                    answer = combination2.stream().mapToInt(i->i+1).toArray();
                    max = lose;
                }
            }
        }
        
        
        return answer;
    }
    public static void getResult(int[][] dice, ArrayList<Integer> combi, ArrayList<Integer> result, int count, int sum) {
        if(count == combi.size()) {
            result.add(sum);
            return;
        }
        
        
        for(int i=0; i<6; i++) {
            getResult(dice, combi, result, count + 1, sum + dice[combi.get(count)][i]);
        }
    }
    
    public static void combination(ArrayList<Integer> combi, int diceLen, int start){
        if(combi.size() == diceLen/2) {
            combinations.add(new ArrayList<Integer>(combi));
            return;
        }
        for(int i=start; i<diceLen; i++){
            combi.add(i);
            combination(combi, diceLen, i + 1);
            combi.remove(Integer.valueOf(i));
        }
    }
    public static ArrayList<Integer> getComplement(ArrayList<Integer> combi, int diceLen) {
        HashSet<Integer> set = new HashSet<>(combi);
        ArrayList<Integer> complement = new ArrayList<>();
        
        for(int i=0; i<diceLen; i++) {
            if(!set.contains(i)) {
                complement.add(i);
            }
        }
        return complement;
    }
}
