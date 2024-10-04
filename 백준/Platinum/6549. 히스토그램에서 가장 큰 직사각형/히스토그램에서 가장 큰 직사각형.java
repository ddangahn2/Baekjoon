import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        while(n != 0) {
            ArrayList<Integer> height = new ArrayList<>();
            for (int i = 0; i < n; i++) height.add(sc.nextInt());
            height.add(-1);

            Stack<Integer> stack = new Stack<>();
            stack.push(-1);

            long ans = 0;

            for (int i = 0; i < height.size(); i++) {
                while (stack.size() > 1 && height.get(i) < height.get(stack.peek())) {
                    int mid = stack.peek();
                    stack.pop();
                    int left = stack.peek();

                    ans = Math.max(ans, (long) (i - left - 1) * height.get(mid));
                }
                stack.push(i);
            }
            System.out.println(ans);

            n = sc.nextInt();
        }
    }
}