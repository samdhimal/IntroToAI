
import java.util.*;

public class AStarSearch {
    //Maintains visited nodes
    static HashSet<Node> closedList = new HashSet<Node>();
    //Maintains nodes that are discovered but no yet visited
    static PriorityQueue<Node> openList = new PriorityQueue<Node>();
    static int startRow;
    static int startCol;
    static int endRow;
    static int endCol;


    public static void main(String[] args) {
        //Clearing openlist and closedlist before using.
        openList.clear();
        closedList.clear();

        Node[][] tiles = new Node[15][15];

        printTiles(tiles);

        Scanner keyboard = new Scanner(System.in);

        String title = "Shortest path through a 15*15 grid";
        System.out.println(title);
        System.out.println();
        System.out.println("Enter a value from 0 to 14");


        System.out.println("Enter starting row: ");//Starting Row
        startRow = keyboard.nextInt();

        System.out.println("Enter starting col: ");//Starting Column
        startCol = keyboard.nextInt();

        while ((startRow < 0) || (startRow > 14) || (startCol < 0) || (startCol > 14) || (tiles[startRow][startCol].getVisit() == 1)) {

            System.out.println("Invalid Coordinates. Try again!");

            System.out.println("Enter starting row: ");//Starting Row
            startRow = keyboard.nextInt();
            System.out.println("Enter starting col: ");//Starting Column
            startCol = keyboard.nextInt();
        }

        System.out.println("Enter end row: ");//End Row
        endRow = keyboard.nextInt();
        System.out.println("Enter end col: ");//End Col
        endCol = keyboard.nextInt();

        while ((endRow < 0) || (endRow > 14) || (endCol < 0) || (endCol > 14) || (tiles[endRow][endCol].getVisit() == 1)) {

            System.out.println("Invalid Coordinates. Try again!");

            System.out.println("Enter end row: ");//End Row
            endRow = keyboard.nextInt();
            System.out.println("Enter end col: ");//End Column
            endCol = keyboard.nextInt();
        }

        heuristic(tiles, endRow, endCol);
        tiles[startRow][startCol].setF(0);
        openList.add(tiles[startRow][startCol]);

        int again = 1;
        while(again == 1) {

            if(openList.isEmpty()) {
                System.out.println("OpenList is EMPTY");
                break;
            }
            Node current = openList.remove();

            if(closedList.contains(current)) {
                continue;
            }

            if (current.getRow() == endRow && current.getCol() == endCol) {
                int[][] path = new int[15][15];
                int counter = 1;
                while (counter == 1 ) {
                    path[current.getRow()][current.getCol()] = 1;
                    current = current.getParent();
                    if(current.getCol() == startCol && current.getRow() == startRow) {
                        counter = 0;
                    }
                }
                path[startRow][startCol] = 1;
                finalPath(tiles,path);
                break;
            }
            find(tiles, current);
            closedList.add(current);
        }
    }

    /**
     *
     * @param tiles
     * Randomly places 10% unpathable tiles
     */
    public static void randomizeTiles(Node[][] tiles) {
        for (int row = 0; row < tiles.length; row++) {
            for(int col = 0; col< tiles[row].length; col++) {
                Node newTiles;
                if(Math.random() <= .10) {
                    newTiles = new Node(row, col, 1);
                }
                else {
                    newTiles = new Node(row, col, 0);
                }
                tiles[row][col] = newTiles;
            }
        }
    }

    /**
     *
     * @param tiles
     * Prints the starting grid
     */
    public static void printTiles(Node[][] tiles) {
        randomizeTiles(tiles);
        int i = 0;
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                if (tiles[row][col].getVisit() == 1) {
                    System.out.print(" X "); //Unpathable
                }
                else {
                    System.out.print(" _ "); //Pathable
                }
            }
            System.out.println();
        }
    }

    /**
     *
     * @param tiles
     * @param endRow
     * @param endCol
     * Calculates Manhattan distance heuristics
     */
    public static void heuristic(Node[][] tiles, int endRow, int endCol) {
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                int rowX = Math.abs(endRow-row);
                int colY = Math.abs(endCol - col);
                int heuristic = (10) * (rowX + colY);
                tiles[row][col].setH(heuristic);
                tiles[row][col].setF(100);
            }
        }
    }

    /**
     *
     * @param tiles
     * @param currentNode
     * @param cost
     * @param row
     * @param col
     */
    public static void neighbours(Node[][] tiles, Node currentNode, int cost, int row, int col) {
        int totalCost = cost + currentNode.getF();
        if (((row >= 0) && (row <= 14) && (col >= 0) && (col <= 14)) && (tiles[row][col].getVisit() == 0) && !closedList.contains(tiles[row][col])) {
            openList.add(tiles[row][col]);
            tiles[row][col].setParent(currentNode);
        }
    }

    /**
     *
     * @param tiles
     * @param currentNode
     * Finds the neighbors
     */
    public static void find(Node[][] tiles, Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();

        //Vertical and Horizontal neighbors
        neighbours(tiles, currentNode, 10, row+1, col);
        neighbours(tiles, currentNode, 10, row-1, col);
        neighbours(tiles, currentNode, 10, row, col+1);
        neighbours(tiles, currentNode, 10, row, col-1);

        //Diagonal neighbors
        neighbours(tiles, currentNode, 14, row+1, col-1);
        neighbours(tiles, currentNode, 14, row-1, col+1);
        neighbours(tiles, currentNode, 14, row+1, col+1);
        neighbours(tiles, currentNode, 14, row-1, col-1);
    }

    /**
     *
     * @param tiles
     * @param path
     * Prints the final path in the grid
     */
    public static void finalPath(Node[][] tiles, int[][] path) {
        for(int row = 0; row < tiles.length; row++) {
            for(int col = 0; col < tiles[row].length; col++) {
                if(path[row][col] == 1) {
                        System.out.print(" * ");//Path travel
                }
                else if(tiles[row][col].getVisit() == 1) {
                    System.out.print(" X ");
                }
                else {
                    System.out.print(" _ ");
                }
            }
            System.out.println();
        }
    }

}
