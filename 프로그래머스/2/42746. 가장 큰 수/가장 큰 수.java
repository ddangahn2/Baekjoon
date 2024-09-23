import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        ArrayList<String> numList = new ArrayList<>();
        
        for(int number: numbers) {
            numList.add(Integer.toString(number));
        }
        Collections.sort(numList, new Comparator<String>() {
            @Override
            public int compare(String num1, String num2) {
                String order1 = num1 + num2;
                String order2 = num2 + num1;
                return order2.compareTo(order1);
            }
        });
        
        String answer = "";
        for(String str: numList) {
            if(answer.equals("0") && str.equals("0")) {
                continue;
            }
            answer += str;
        }
        
        return answer;
    }
}