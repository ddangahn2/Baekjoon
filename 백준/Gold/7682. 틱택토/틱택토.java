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
            if (game.charAt(3 * i) == game.charAt(3 * i + 1) && game.charAt(3 * i + 1) == game.charAt(3 * i + 2)) {
                if (game.charAt(3 * i) == 'O') Owin = true;
                else if (game.charAt(3 * i) == 'X') Xwin = true;
            }
        }

        for(int i=0; i<3; i++){
            if (game.charAt(i) == game.charAt(i + 3) && game.charAt(i + 3) == game.charAt(i + 6)) {
                if (game.charAt(i) == 'O') Owin = true;
                else if (game.charAt(i) == 'X') Xwin = true;
            }
        }

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
