class Solution {
    public int solution(int[][] sizes) {
        int longSide = 0;
        int shortSide = 0;
        
        for(int[] size: sizes){
            int a = size[0];
            int b = size[1];
            
            if (a > b) {
                if (a > longSide) {
                    longSide = a;
                }
                if (b > shortSide) {
                    shortSide = b;
                }
            } else {
                if (b > longSide) {
                    longSide = b;
                }
                if (a > shortSide) {
                    shortSide = a;
                }
            }
        }
        
        int answer = longSide * shortSide;
        return answer;
    }
}