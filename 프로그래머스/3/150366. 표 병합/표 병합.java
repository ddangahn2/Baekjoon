import java.util.*;

class Solution {
    static int[] p = new int[2500];
    static Map<Integer, String> map = new HashMap<>();
    
    public String[] solution(String[] commands) {
        ArrayList<String> arr = new ArrayList<>();
        for(int i=0; i<2500; i++){
            p[i] = i;
        }
        
        for(int t=0; t<commands.length; t++){
            String[] command = commands[t].split(" ");    
            
            if(command[0].equals("UPDATE")){
                if(command.length == 4){
                    int r = Integer.parseInt(command[1]) - 1;
                    int c = Integer.parseInt(command[2]) - 1;
                    String value = command[3];
                    
                    int parent = findParent(50*r + c);
                    map.put(parent, value);
                }
                else{
                    String value1 = command[1];
                    String value2 = command[2];
                    
                    for(Map.Entry<Integer, String> mapEntry: map.entrySet()){
                        int key = mapEntry.getKey();
                        String value = mapEntry.getValue();
                        
                        if(value.equals(value1)) {
                            map.put(key, value2);
                        }
                    }
                }
            }
            else if(command[0].equals("MERGE")){
                int r1 = Integer.parseInt(command[1]) - 1;
                int c1 = Integer.parseInt(command[2]) - 1;
                int r2 = Integer.parseInt(command[3]) - 1;
                int c2 = Integer.parseInt(command[4]) - 1;
                
                int parent1 = findParent(r1*50 + c1);
                int parent2 = findParent(r2*50 + c2);
                
                if(parent1 == parent2) {
                    continue;
                }
                if (map.containsKey(parent1)) {
                    p[parent2] = parent1;
                    if(map.containsKey(parent2)){
                        map.remove(parent2);
                    }
                } else {
                    p[parent1] = parent2;
                    if(map.containsKey(parent1)){
                        map.remove(parent1);
                    }
                }
            }
            else if(command[0].equals("UNMERGE")){
                int r = Integer.parseInt(command[1]) - 1;
                int c = Integer.parseInt(command[2]) - 1;
                
                int parent1 = findParent(r*50 + c);
                
                if(map.containsKey(parent1)) {
                    String value = map.get(parent1);
                    map.remove(parent1);
                    map.put(r*50 + c, value);
                }
                ArrayList<Integer> unmerges = new ArrayList<>();
                
                for(int i=0; i<50; i++) {
                    for(int j=0; j<50; j++) {
                        if(findParent(i*50+j) == parent1) {
                            unmerges.add(i*50 + j);
                        }
                    }
                }
                for(int unmerge: unmerges) {
                    p[unmerge] = unmerge;
                }
            }
            else{
                int r = Integer.parseInt(command[1]) - 1;
                int c = Integer.parseInt(command[2]) - 1;
                
                int parent = findParent(r*50 + c);
                if(map.containsKey(parent)) {
                    arr.add(map.get(parent));
                } else {
                    arr.add("EMPTY");
                }
            }
        }
       
        String[] answer = arr.toArray(new String[0]);
        return answer;
    }
    public static int findParent(int child) {
        int parent = p[child];
        while(parent != p[parent]) {
            parent = p[parent];
        }
        return parent;
    }
}