package main;

public class Test {
    public static void main(String[] args){


//        String[] directions = {
//                "Go North",
//                "Go East",
//                "Go West",
//                "Go South",
//        };
//        String[] options = {
//                "RUN!",
//                "ESCAPE!",
//                "ENCOUNTER",
//        };
//
//        String[] actions = {
//                "Pick Up Item",
//                "Use Item",
//
//        };

        String[] opt = {"forward", "backward", "right", "left"};
        int startingRow = 0;
        int startingCol =1;

        int row = startingRow;
        int col = startingCol;


        int[][] map = {

                {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
                {1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };


        displayMap(map, row, col);






    }

    public static void displayMap(int[][] map, int playerRow, int playerCol) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (row == playerRow && col == playerCol) {
                    System.out.print("P "); // Player's position
                } else {
                    System.out.print(map[row][col] + " ");
                }
            }
            System.out.println();
        }
    }

}
