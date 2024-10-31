import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String game = br.readLine();

        while(!"end".equals(game)){
            if(isPossible(game)) System.out.println("valid");
            else System.out.println("invalid");

            game = br.readLine();
        }
    }
    public static boolean isPossible(String game){
        int Ocnt = 0;
        int Xcnt = 0;

        boolean Owin = false;
        boolean Xwin = false;

        for(char pick: game.toCharArray()){
            if(pick == 'O') Ocnt++;
            else if(pick == 'X') Xcnt++;
        }

        if(Xcnt < Ocnt || Ocnt + 1 < Xcnt) return false;

        for(int i=0; i<3; i++){
            char pick = game.charAt(3*i);
            if(pick == game.charAt(3*i+1) && pick == game.charAt(3*i+2)){
                if(pick == 'O') Owin = true;
                else if(pick == 'X') Xwin = true;
            }
        }

        for(int i=0; i<3; i++){
            char pick = game.charAt(i);
            if(pick == game.charAt(3+i) && pick == game.charAt(6+i)){
                if(pick == 'O') Owin = true;
                else if(pick == 'X') Xwin = true;
            }
        }

//        char pick = game.charAt(4);
//        if(pick == game.charAt(0) && pick == game.charAt(8)){
//            if(pick == 'O') Owin = true;
//            else if(pick == 'X') Xwin = true;
//        }
//        if(pick == game.charAt(2) && 2 == game.charAt(6)){
//            if(pick == 'O') Owin = true;
//            else if(pick == 'X') Xwin = true;
//        }
        if (game.charAt(0) == game.charAt(4) && game.charAt(4) == game.charAt(8)) {
            if (game.charAt(0) == 'O') Owin = true;
            else if (game.charAt(0) == 'X') Xwin = true;
        }
        if (game.charAt(2) == game.charAt(4) && game.charAt(4) == game.charAt(6)) {
            if (game.charAt(2) == 'O') Owin = true;
            else if (game.charAt(2) == 'X') Xwin = true;
        }

        if(!Owin && !Xwin && (Ocnt + Xcnt) == 9) return true;
        if(Owin && !Xwin && (Ocnt == Xcnt)) return true;
        if(Xwin && !Owin && (Ocnt + 1 == Xcnt)) return true;

        return false;
    }
}