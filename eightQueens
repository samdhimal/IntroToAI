import java.util.*;

public class eightQueens {
    static int heuristic = 0;
    static int currentLocation = 0;
    static int neighbors = 8;

    public static int[][] randomizeQueens(){
        int board[][] = new int[8][8];
        Random rand = new Random( );
        while(currentLocation < 8){
            for(int i = 0; i < 8; i++){
                board[rand.nextInt(7)][i] = 1;
                currentLocation++;
            }
        }
        return board;
    }

    public static void print(int[][] chessBoard) {
        for (int row = 0; row < chessBoard.length; row++) {
            for (int col = 0; col < chessBoard.length; col++) {
                System.out.print(chessBoard[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static boolean findHeuristic(int [][] board, int row, int col) {
        boolean found = false;
        int count = 0;
        for(int i = 0; i < 8; i++){
            if(board[i][col] == 1){
                count++;
            }
        }

        if(count > 1) {
            found = true;
        }

        count = 0;
        for(int i = 0; i < 8; i++){
            if(board[row][i] == 1){
                count++;
            }
        }

        if(count > 1) {
            found = true;
        }

        for(int i = 1; i < 8; i++){
            if(found){
                break;
            }
            if((row+i < 8)&&(col-i >= 0)){
                if(board[row+i][col-i] == 1){
                    found = true;
                }
            }
            if((row+i < 8)&&(col+i < 8)){
                if(board[row+i][col+i] == 1){
                    found = true;
                }
            }
            if((row-i >= 0)&&(col+i < 8)){
                if(board[row-i][col+i] == 1){
                    found = true;
                }
            }
            if((row-i >= 0)&&(col-i >= 0)){
                if(board[row-i][col-i] == 1){
                    found = true;
                }
            }
        }

        return found;
    }

    public static int heuristic(int [][] board){
        int count = 0;
        for(int i = 0; i < 8; i++){
            for(int j= 0; j < 8; j++){
                if(board[i][j] == 1){
                    if(findHeuristic(board, i, j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static int[] findBestRowAndCol(int[][] board){
        int result[] = new int[2];
        int bestCol = 8;
        int bestRow = 8;
        int min = 8;
        int count = 0;

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board[i][j] < min){
                    min = board[i][j];
                    bestCol = j;
                }
                if(board[i][j] < heuristic){
                    count++;
                }
            }
        }
        neighbors = count;
        result[0] = bestCol;

        min = 8;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board[i][j] < min){
                    min = board[i][j];
                    bestRow = i;
                }
            }
        }
        result[1] = bestRow;

        return result;
    }

    public static void moveQueen( ){
        int board[][] = new int[8][8];
        int [][] temp = new int[8][8];
        int[][] copyBoard = new int[8][8];
        int restartCount = 0;
        int moveCount = 0;

        int num;
        int bestCol;
        int bestRow;
        int prev = 0;

        board = randomizeQueens();
        heuristic = heuristic(board);

        while(true){
            num = 0;

            for(int i = 0; i < 8; i++){
                System.arraycopy(board[i], 0, temp[i], 0, 8);
            }
            while(num < 8){
                for(int i = 0; i < 8;i++){
                    temp[i][num] = 0;
                }
                for(int i = 0; i < 8; i++){
                    if(board[i][num] == 1){
                        prev = i;
                    }
                    temp[i][num] = 1;
                    copyBoard[i][num] = heuristic(temp);
                    temp[i][num] = 0;
                }
                temp[prev][num] = 1;
                num++;
            }

            if(neighbors == 0){
                currentLocation = 0;
                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 8; j++){
                        board[i][j] = 0;
                    }
                }
                board = randomizeQueens( );
                heuristic = heuristic(board);
                System.out.println("RESTART");
                restartCount++;
            }

            int bestRowCol[] = findBestRowAndCol(copyBoard);
            bestCol = bestRowCol[0];
            bestRow = bestRowCol[1];

            for(int i = 0; i < 8; i++){
                board[i][bestCol] = 0;
            }

            board[bestRow][bestCol] = 1;
            moveCount++;
            heuristic = heuristic(board);

            if(heuristic(board) == 0){
                System.out.println();
                System.out.println("Current State");
                print(board);
                System.out.println("Solution Found!");
                System.out.println("State changes: " + moveCount);
                System.out.println("Restarts: " + restartCount);
                break;
            }
            System.out.println("\n");
            System.out.println("Current h: " + heuristic);
            System.out.println("Current State");
            print(board);
            System.out.println("Neighbors found with lower h: " + neighbors);
            System.out.println("Setting new current State");
        }
    }

    public static void main(String[] args) {
        moveQueen();
    }
}
