import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
class Solution {
    public int[] solution(String[] arguments) {
        MidV q = new MidV();

        for(int i = 0; i < arguments.length; i++){
            String[] commend = arguments[i].split(" ");

            int v = Integer.parseInt(commend[1]);
            if(commend[0].equals("I")){
                q.push(v);
            }else{
                switch (v){
                    case 1 : q.removeMax();
                    break;
                    case -1: q.removeMin();
                    break;
                }
            }
        }


        int[] aw = new int[]{q.getMaxValue(),q.getMinValue()};

        return aw;
    }
}

class MidV{
    private PriorityQueue<Integer> leftHeap;
    private PriorityQueue<Integer> rightHeap;

    public MidV(){
        leftHeap = new PriorityQueue<>(10,Collections.reverseOrder());//최대값;
        rightHeap = new PriorityQueue<>();//최소값
    }


    public void push(int v){
        leftHeap.add(v);
    }

    public void removeMax(){

        while(!rightHeap.isEmpty()){
            leftHeap.add(rightHeap.poll());
        }

        leftHeap.poll();
    }

    public void removeMin(){

        while(!leftHeap.isEmpty()){
            rightHeap.add(leftHeap.poll());
        }

        rightHeap.poll();
    }

    public int getMaxValue(){

        if(leftHeap.size() == 0 && rightHeap.size() == 0)
            return 0;

        while(!rightHeap.isEmpty()){
            leftHeap.add(rightHeap.poll());
        }

        return leftHeap.peek();
    }

    public int getMinValue(){

        if(leftHeap.size() == 0 && rightHeap.size() == 0)
            return 0;

        while(!leftHeap.isEmpty()){
            rightHeap.add(leftHeap.poll());
        }

        return rightHeap.peek();
    }

}
