import java.util.*;

class Solution {
    static int[] p = new int[2500];
    static Map<Integer, String> map = new HashMap<>();
    
    public String[] solution(String[] commands) {
        for(int i=0; i<p.length; i++) p[i] = i;

        List<String> answerList = new ArrayList<>();
        
        for(String command: commands){
            String[] line = command.split(" ");
     
            if("UPDATE".equals(line[0])){
                if(line.length == 4){
                    int r = Integer.parseInt(line[1]) - 1;
                    int c = Integer.parseInt(line[2]) - 1;
                    String value = line[3];
                    
                    int idx = r*50 + c;
                    
                    int findIdx = find(idx);
                    map.put(findIdx, value);
                }
                else{
                    String value1 = line[1];
                    String value2 = line[2];
                    
                    changeValue(value1, value2);
                }
            }
            else if("MERGE".equals(line[0])){
                int r1 = Integer.parseInt(line[1]) - 1;
                int c1 = Integer.parseInt(line[2]) - 1;
                int r2 = Integer.parseInt(line[3]) - 1;
                int c2 = Integer.parseInt(line[4]) - 1;
                
                int idx1 = r1*50 + c1;
                int idx2 = r2*50 + c2;
                
                int findIdx1 = find(idx1);
                int findIdx2 = find(idx2);
                
                merge(findIdx1, findIdx2);
            }
            else if("UNMERGE".equals(line[0])){
                int r = Integer.parseInt(line[1]) - 1;
                int c = Integer.parseInt(line[2]) - 1;
                
                int idx = r*50 + c;
                
                int findIdx = find(idx);
                unmerge(findIdx, idx);
            }
            else if("PRINT".equals(line[0])){
                int r = Integer.parseInt(line[1]) - 1;
                int c = Integer.parseInt(line[2]) - 1;
                
                int idx = r*50 + c;
                int findIdx = find(idx);
                
                if(map.containsKey(findIdx)) answerList.add(map.get(findIdx));
                else answerList.add("EMPTY");
            }
        }
 
        String[] answer = new String[answerList.size()];
        answer = answerList.toArray(answer);
        return answer;
    }
    public static int find(int idx){
        if(idx != p[idx]){
            p[idx] = find(p[idx]);
        }
        
        return p[idx];
    }
    public static void changeValue(String v1, String v2){
        for(Map.Entry<Integer, String> entry: map.entrySet()){
            if(entry.getValue().equals(v1)){
                map.put(entry.getKey(), v2);
            }
        }
    }
    public static void merge(int idx1, int idx2){
        if(idx1 == idx2) return;
        
        if(map.containsKey(idx1)){
            p[idx2] = idx1;
            if(map.containsKey(idx2)){
                map.remove(idx2);
            }
        }
        else if(map.containsKey(idx2)){
            p[idx1] = idx2;
        }
        else{
            p[idx2] = idx1;
        }

    }
    public static void unmerge(int parent, int idx){
        List<Integer> parentIdx = new ArrayList<>();
        
        for(int i=0; i<p.length; i++){
            if(find(i) == parent) parentIdx.add(i);
        }
        for(int i: parentIdx){
            p[i] = i;
        }
        if(map.containsKey(parent)){
            String value = map.remove(parent);
            map.put(idx, value);
        }
    }
}