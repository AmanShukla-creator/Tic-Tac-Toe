import java.util.Scanner;

public class TicTacToe{
    public static void main(String args[]){
        char[][] Board = new char[3][3];
        for(int row = 0; row < Board.length; row++){
            for(int col = 0; col < Board[row].length; col++){
                Board[row][col] = ' ';

            }
        }
        Scanner sc = new Scanner(System.in);
        char Player = 'X';

        boolean Gameover = false;

        while (!Gameover) {
            printBoard(Board);
            System.out.println("Player " + Player + " enter: ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            if (Board[row][col] == ' ') {
                Board[row][col] = Player;
                Gameover = haveWon(Board, Player);
                if (Gameover) {
                    System.out.println("Player " + Player + " have Won");
                } else {
                    Player = (Player == 'X') ? 'O' : 'X';
                }

                //                else{
                //                    if(Player == 'X'){
                //                        Player = 'O';
                //                    }else{
                //                        Player = 'X';
                //                    }
                //
                //                }
            } else {
                System.out.println("Invalid Move. Try Again!");
            }

        }
        
        printBoard(Board);

    }
    public static boolean haveWon(char[][] Board, char Player) {
        // Check rows first
        for (int row = 0; row < Board.length; row++) {
            if (Board[row][0] == Player && Board[row][1] == Player && Board[row][2] == Player) {
                return true;
            }
        }// check columns
        for (int col = 0; col < Board[0].length; col++) {
            if(Board[0][col] == Player && Board[1][col] == Player && Board[2][col] == Player)
                return true;
        }// Diagonals
        if( Board[0][0] == Player && Board[1][1] == Player && Board[2][2] == Player ){
            return true;
        }
        if( Board[0][2] == Player && Board[1][1] == Player && Board[2][0] == Player ){
            return true;
        }
        return false;
    }

    public static void printBoard(char[][] Board) {
        for (int row = 0; row < Board.length; row++) {
            for (int col = 0; col < Board[row].length; col++) {
                System.out.print(Board[row][col] + " | ");
            }
            System.out.println();
        }
    }
}