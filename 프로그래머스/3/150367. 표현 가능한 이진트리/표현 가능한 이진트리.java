class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int i=0; i<numbers.length; i++){
            StringBuilder sb = new StringBuilder("");
            
            intToBinary(sb, numbers[i]);
            makeFullTree(sb);
            
            if(checkTree(sb.toString())){
                answer[i] = 1;
            }
        }
        return answer;
    }
    public static void intToBinary(StringBuilder sb, long number){
        while(number != 0){
            if(number % 2 == 1){
                sb.append("1");
            }
            else{
                sb.append("0");
            }
            number /= 2;
        }
        sb.reverse();
    }
    public static void makeFullTree(StringBuilder sb){
        int len = 1;
        while(sb.length() > len){
            len *= 2;
            len += 1;
        }
        
        int header = len - sb.length();
        for(int i=0; i<header; i++){
            sb.insert(0, "0");    
        }
    }
    public static boolean checkTree(String sb){
        if(sb.length() == 1){
            return true;
        }
        
        int mid = sb.length() / 2;
        
        if(sb.charAt(mid) == '0') {
            for(int i = 0; i < sb.length(); i++) {
                if(sb.charAt(i) == '1') {
                    return false;
                }
            }
        }

        boolean leftCheck = checkTree(sb.substring(0, mid));
        boolean rightCheck = checkTree(sb.substring(mid+1));
          
        return leftCheck && rightCheck;
    }
}