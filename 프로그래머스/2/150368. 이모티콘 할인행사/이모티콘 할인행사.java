class Solution {
    static int[] emoSale;
    static int[] sales = {10, 20, 30, 40};
    static int maxPlus = 0;
    static int maxPrice = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {        
        emoSale = new int[emoticons.length];
        dfs(0, emoSale, emoticons, users);
        
        int[] answer = {maxPlus, maxPrice};
        return answer;
    }
    public static void dfs(int saleCount, int[] emoSale, int[] emoticons, int[][] users) {
        if (saleCount == emoSale.length) {
            check(emoSale, emoticons, users);
            return;
        }
        for(int i=0; i<4; i++) {
            emoSale[saleCount] = sales[i];
            dfs(saleCount+1, emoSale, emoticons, users);
        }
    }
    public static void check(int[] emoSale, int[] emoticons, int[][] users) {
        int localPlus = 0;
        int localPrice = 0;
        
        for(int i=0; i<users.length; i++) {
            int ratio = users[i][0];
            int price = users[i][1];
            int buyPrice = 0;
            
            for(int j=0; j<emoSale.length; j++) {
                if(emoSale[j] >= ratio) {
                    buyPrice += emoticons[j] * (100 - emoSale[j]) / 100;
                }
            }

            if(buyPrice >= price) {
                localPlus += 1;
            }
            else { 
                localPrice += buyPrice;
            }
        }
        if (maxPlus < localPlus) {
            maxPlus = localPlus;
            maxPrice = localPrice;
        }
        else if (maxPlus == localPlus) {
            if(maxPrice < localPrice) {
                maxPrice = localPrice;
            }
        }
    }
}