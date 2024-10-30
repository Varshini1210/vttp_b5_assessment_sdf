package vttp.batch5.sdf.task02;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Methods {
    public static String[][] printBoard(File f) throws IOException{

        String board[][] = new String[3][3];
        Reader reader = new FileReader(f);
        BufferedReader br = new BufferedReader(reader);

        String line;
        int count = 0;
        while((line= br.readLine())!=null){
            System.out.println(line);
            String[] movesArray = line.split("");
            //System.out.println(Arrays.toString(movesArray));
            board[count]=movesArray;
			count+=1;
        }
        br.close();
        reader.close();
        System.out.println(board[0][0]);
        //System.out.println(Arrays.toString(board[1][1]));
        System.out.println(Arrays.toString(board[2]));
        return board;
    }

    public static List<String> getLegalPositions(String[][] board){
        List<String> legalPositions = new ArrayList<>();
        int utility=0;
        for(int y = 0; y<3; y++){
            for(int x = 0; x<3; x++){
                
                if(board[y][x].equals(".")){
                    //int utility = calculateUtility(y,x,board);
                    System.out.println("y"+ y +"x"+x);
                    String[][] boardCopy = board;
                    boardCopy = makeMove(y,x,boardCopy,"X");
                    utility = evaluateGame(boardCopy,"X");
                    boardCopy = undoMove(y,x,boardCopy);
                    if (utility == 1){
                        String result = String.format("y=%d, x=%d, utility =%d",y,x,utility);
                        legalPositions.add(result);
                    }
                    else {
                        utility = makeOpponentMove(x,y,boardCopy);
                        String result = String.format("y=%d, x=%d, utility =%d",y,x,utility);
                        legalPositions.add(result);
                    }
                    
                    // if (utility != 1){
                    //     
                    //         }
                    //     }
                    // }
                    // String result = String.format("y=%d, x=%d, utility =%d",y,x,utility);
                    // legalPositions.add(result);
                    
                }
                // String result = String.format("y=%d, x=%d, utility =%d",y,x,utility);
                // legalPositions.add(result);
            }
        }
        System.out.println(legalPositions);
        return legalPositions;
    }

    public static String[][] makeMove(int y, int x, String[][]board, String move){
        
        board[y][x]=move;
        return board;
        
    }
    public static String[][] undoMove(int y, int x, String[][]board){
        
        board[y][x]=".";
        return board;

    }

    public static int makeOpponentMove(int y, int x, String[][]board){
        int utility = 0;
        board[y][x] = "X";
        for(int oy =0; oy<3; oy++){
            for(int ox = 0; ox<3; ox++){
                if (board[oy][ox].equals(".")){
                    String[][] boardCopy = board;
                    boardCopy = makeMove(oy,ox,boardCopy,"O");
                    utility = evaluateGame(boardCopy,"O");
                    boardCopy = undoMove(oy,ox,boardCopy);
                    System.out.println("O:" + utility);
                   
                    
                    
                }
            }
        }
        
        return utility;

    }

    public static int evaluateGame(String[][] board, String move){
        int utility = 0;
        //check rows
        for(int y = 0; y<3; y++){
            //System.out.println(board[y][0].equals(board[y][1]));
            //System.out.println(board[y][1].equals(board[y][2]));
            if ((board[y][0].equals(board[y][1]))&&(board[y][1].equals(board[y][2])))
                
                {
                if (move.equals("X")){
                    utility =1; 
                    System.out.println("Entered loop 1.1");
                }
                else if (move.equals("O")){
                    utility = -1;
                }
            }
        }
        // check columns
        for(int x = 0; x<3; x++){
            if ((board[0][x].equals(board[1][x]))&&(board[1][x].equals(board[2][x]))){
                if (move.equals("X")){
                    utility = 1;
                    System.out.println("Entered loop 2.1");
                }
                else if (move.equals("O")){
                    utility = -1;
                }
            }
        }

        // check diagonals
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])){
            if(move.equals("X")){
                utility = 1;
                System.out.println("Entered loop 3.1");
            }
            else if (move.equals("O")){
                utility = -1;
            }
        }

        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])){
            if (move.equals("X")){
                utility = 1;
                System.out.println("Entered loop 4.1");
            }
            else if (move.equals("O")){
                utility = -1;
            }
        }
        
        return utility;
    }
        
    
        


        
}

