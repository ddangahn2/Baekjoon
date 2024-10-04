class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder("");
        
        for(int i=0; i<number.length(); i++){
            char ch = number.charAt(i);
            
            while(sb.length() > 0 && Character.compare(sb.charAt(sb.length()-1), ch) < 0 && k > 0){
                sb.deleteCharAt(sb.length()-1);
                k--;
            }
            sb.append(ch);
        }
        String answer = sb.toString();
        if(k > 0){
            answer = sb.substring(0, sb.length()-k);
        }
        
        return answer;
    }
}