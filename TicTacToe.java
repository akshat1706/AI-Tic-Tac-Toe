package tictactoe;

import java.util.Scanner;
import java.util.*;
import java.util.Random;

/**
 *
 * @author Akshat Gupta
 */

public class TicTacToe{

    static Scanner s = new Scanner(System.in);  //Create instance of Scanner class to scan inputs
    static Random rand = new Random(); //Create instance of random class to generate random numbers
    static ArrayList<Integer> ms = new ArrayList<Integer>(Arrays.asList(2,7,6,9,5,1,4,3,8));    //Arraylist for MagicSquare numbers
    static ArrayList<Integer> random_moves = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8)); //ArrayList for random moves
    static int [] msq   = {2,7,6,9,5,1,4,3,8}; //Magic sqaure array
    static ArrayList<Integer> computer = new ArrayList<Integer>();  //ArrayList for computer moves
    static ArrayList<Integer> player = new ArrayList<Integer>();  //ArrayList for player moves
    static char [][] gameBoard = {{'i','i','i'},{'i','i','i'},{'i','i','i'}};  //2D array for game board
    static int [][] moves = {{0,1,2},{3,4,5},{6,7,8}};  //Moves array for displaying moves corresponding to poston on board
    static int move_count=0;    //Variable to maintain move counter
    static HashMap<Integer,Integer> moves_map = new HashMap<Integer,Integer>();
    static int winning_moves_array [][] = {{2,7,6}, {9,5,1}, {4,3,8}, {2,9,4}, {7,5,3}, {6,1,8}, {2,5,8}, {6,5,4}}; //Combinations of winning moves
    static int turn=0;  //Variable to decide who playes first; computer or human



    //MAGIC SQAURE FOR 3*3
    static void magicSquare(){
        int[][] magicSquare = new int[3][3];

        // Initialize position for 1
        int i = 3/2;
        int j = 3-1;

        // One by one put all values in magic square
        for (int num=1; num <= 3*3; )
        {
            if (i==-1 && j==3) //3rd condition
            {
                j = 3-2;
                i = 0;
            }
            else
            {
                //1st condition helper if next number
                // goes to out of square's right side
                if (j == 3)
                    j = 0;

                //1st condition helper if next number is
                // goes to out of square's upper side
                if (i < 0)
                    i=3-1;
            }

            //2nd condition
            if (magicSquare[i][j] != 0)
            {
                j -= 2;
                i++;
                continue;
            }
            else
                //set number
                magicSquare[i][j] = num++;

            //1st condition
            j++;  
            i--;
        }

        //PRINT MAGIC SQUARE
        System.out.println("The " + "3*3" + " Magic Square :");
        System.out.println();
        System.out.println("-------------");
        for(i=0; i<3; i++)
        {
            for(j=0; j<3; j++)
                if(j==2){
                    System.out.println(magicSquare[i][j]);
                }
                else{
                    System.out.print(magicSquare[i][j]+"  |  ");
                }

            System.out.println("-------------");
        }
        System.out.println();
    }
    
    //DISPLAYING  NUMBERS TO ENTER FOR CORRESPONDING INPUT MOVES
    static void rules(){
        System.out.println();
        System.out.println("-------------");
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
                if(j==2){
                    System.out.println(moves[i][j] + "  ");
                }
                else{
                    System.out.print(moves[i][j]+"  |  ");
                }

            System.out.println("-------------");
        }
        System.out.println();

    }

    

    //DISPLAY CURRENT BOARD
    static void currentBoard(){
        System.out.println();
        System.out.println("---------");
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
                if(j==2){
                    if(gameBoard[i][j]!= 'i'){
                        System.out.println(gameBoard[i][j]);
                    }
                    else{
                        System.out.println(" ");
                    }
                }

                else{
                    if(gameBoard[i][j]!= 'i'){
                        System.out.print(gameBoard[i][j]+" | ");
                    }
                    else{
                        System.out.print(" "+" | ");
                    }
                }

            System.out.println("---------");
        }
        System.out.println();
    }

    

    //UPDATING THE GAME BOARD WITH X's and O's
    static void update_game_board(char c, int n){
        if (c=='p'){                                    //Updating board with X's
            if (n==0){gameBoard[0][0]='X';}
            if (n==1){gameBoard[0][1]='X';}
            if (n==2){gameBoard[0][2]='X';}
            if (n==3){gameBoard[1][0]='X';}
            if (n==4){gameBoard[1][1]='X';}
            if (n==5){gameBoard[1][2]='X';}
            if (n==6){gameBoard[2][0]='X';}
            if (n==7){gameBoard[2][1]='X';}
            if (n==8){gameBoard[2][2]='X';}
        }
        else{                                           //updating board with O's
            if (n==0){gameBoard[0][0]='O';}
            if (n==1){gameBoard[0][1]='O';}
            if (n==2){gameBoard[0][2]='O';}
            if (n==3){gameBoard[1][0]='O';}
            if (n==4){gameBoard[1][1]='O';}
            if (n==5){gameBoard[1][2]='O';}
            if (n==6){gameBoard[2][0]='O';}
            if (n==7){gameBoard[2][1]='O';}
            if (n==8){gameBoard[2][2]='O';}

        }

    }


    //WHAT HAPPENS WHEN PLAYER PLAYS HIS TURN
    static void player_move(){
         if(random_moves.size()==0){
                    currentBoard();
                    System.out.println("Match Draw !");
                    System.exit(0);
                    }

        int number = s.nextInt();   //Inputting the player move
        if(player.contains(ms.get(number))){
            System.out.println("Invalid Move!");
            System.exit(0);
        }
        else{
            player.add(ms.get(number)); //Updating arraylist for player moves

        }
        update_game_board('p', number); //Updating the game board
        random_moves.remove(random_moves.indexOf(number));  //Updating arraylist for random moves
        currentBoard(); //Displaying the current board
        System.out.println("Player magic sqaure moves list : " + player);
        System.out.println("Computer magic sqaure moves list : " + computer);
        System.out.println("Your available moves : " + random_moves);
        

    }

    //TO BE CODED
    static void computer_move(){
        //Make the first move at position 4 if its not already filled. If it is, make first move at 0.
        //Check if there is a blocking move, if yes, then implement
        //If not, Check if there is a winning  move, if yes, then implement, if no then random move
        
        //If player moves first
        if(turn==0){
            
            if(random_moves.size()==0){
                    currentBoard();
                    System.out.println("Player magic sqaure moves list : " + player);
                    System.out.println("Computer magic sqaure moves list : " + computer);
                    System.out.println("Match Draw !");
                    System.exit(0);
                    }
            if(move_count==0){  //Hardcoding the 1st move 
            
                if(gameBoard[1][1]!='X'){
                    update_game_board('c', 4);
                    computer.add(ms.get(4)); //Updating arraylist for computer moves
                    random_moves.remove(random_moves.indexOf(4)); //Updating random moves playlist 
                    currentBoard();
                    System.out.println("Player magic sqaure moves list : " + player);
                    System.out.println("Computer magic sqaure moves list : " + computer);
                }
                else{
                    update_game_board('c', 0);
                    computer.add(ms.get(0)); //Updating arraylist for computer moves
                    random_moves.remove(random_moves.indexOf(0)); //Updating random moves playlist 
                    currentBoard();
                    System.out.println("Player magic sqaure moves list : " + player);
                    System.out.println("Computer magic sqaure moves list : " + computer);
                }
            }
            
            else if(move_count==1){  //Hardcoding the 2nd move 
                
                if(blocking_move()) {
                    
                    currentBoard();
                    System.out.println("Player magic sqaure moves list : " + player);
                    System.out.println("Computer magic sqaure moves list : " + computer);
                    
                }
                
                else if((gameBoard[2][2]=='X')&&(gameBoard[0][0]=='X')){ //Hardcoded for Encirclement Tactic 
                    update_game_board('c', 5);
                    computer.add(ms.get(5)); //Updating arraylist for computer moves
                    random_moves.remove(random_moves.indexOf(5)); //Updating random moves playlist 
                    currentBoard();
                    System.out.println("Player magic sqaure moves list : " + player);
                    System.out.println("Computer magic sqaure moves list : " + computer);
                }
                
                else if((gameBoard[0][2]=='X')&&(gameBoard[2][0]=='X')){    //Hardcoded for Encirclement Tactic 
                    update_game_board('c', 5);
                    computer.add(ms.get(5)); //Updating arraylist for computer moves
                    random_moves.remove(random_moves.indexOf(5)); //Updating random moves playlist 
                    currentBoard();
                    System.out.println("Player magic sqaure moves list : " + player);
                    System.out.println("Computer magic sqaure moves list : " + computer);
                }
                
                
                else if((gameBoard[2][1]=='X')&&(gameBoard[1][0]=='X')){
                    update_game_board('c', 6);
                    computer.add(ms.get(6)); //Updating arraylist for computer moves
                    random_moves.remove(random_moves.indexOf(6)); //Updating random moves playlist 
                    currentBoard();
                    System.out.println("Player magic sqaure moves list : " + player);
                    System.out.println("Computer magic sqaure moves list : " + computer);
                }
                
                
                else if(gameBoard[0][2]!='X'){
                    update_game_board('c', 2);
                    computer.add(ms.get(2)); //Updating arraylist for computer moves
                    random_moves.remove(random_moves.indexOf(2)); //Updating random moves playlist 
                    currentBoard();
                    System.out.println("Player magic sqaure moves list : " + player);
                    System.out.println("Computer magic sqaure moves list : " + computer);
                }
                
                else {
                    update_game_board('c', 6);
                    computer.add(ms.get(6)); //Updating arraylist for computer moves
                    random_moves.remove(random_moves.indexOf(6)); //Updating random moves playlist 
                    currentBoard();
                    System.out.println("Player magic sqaure moves list : " + player);
                    System.out.println("Computer magic sqaure moves list : " + computer);
                }
            }
        
            
            else{
                if(winning_move()){
                currentBoard();
                System.out.println("Player magic sqaure moves list : " + player);
                System.out.println("Computer magic sqaure moves list : " + computer);
                System.out.println("Computer Won !!");
                System.exit(0);
                }
                else if(blocking_move()) {
                    
                    currentBoard();
                    System.out.println("Player magic sqaure moves list : " + player);
                    System.out.println("Computer magic sqaure moves list : " + computer);
                    
                }
            
                else {
                    random_move();
                    currentBoard();
                    System.out.println("Player magic sqaure moves list : " + player);
                    System.out.println("Computer magic sqaure moves list : " + computer);
                }
        }    

        System.out.println("The computer has made it's move.");
        System.out.println("Your available moves : " + random_moves);
        }
        
        
        
        //If computer moves first
        if(turn==1){
            if(random_moves.size()==0){
                    currentBoard();
                    System.out.println("Player magic sqaure moves list : " + player);
                    System.out.println("Computer magic sqaure moves list : " + computer);
                    System.out.println("Match Draw !");
                    System.exit(0);
                    }
            
            if(move_count==0){  //Hardcoding the 1st move 
            
                update_game_board('c', 4);
                computer.add(ms.get(4)); //Updating arraylist for computer moves
                random_moves.remove(random_moves.indexOf(4)); //Updating random moves playlist 
                currentBoard();
                System.out.println("Player magic sqaure moves list : " + player);
                System.out.println("Computer magic sqaure moves list : " + computer);
                 
            }
            
            else if(move_count==1){  //Hardcoding the 2nd move 
            
                if(gameBoard[0][0]!='X'){
                    update_game_board('c', 0);
                    computer.add(ms.get(0)); //Updating arraylist for computer moves
                    random_moves.remove(random_moves.indexOf(0)); //Updating random moves playlist 
                    currentBoard();
                    System.out.println("Player magic sqaure moves list : " + player);
                    System.out.println("Computer magic sqaure moves list : " + computer);
                }
                else{
                    update_game_board('c', 2);
                    computer.add(ms.get(2)); //Updating arraylist for computer moves
                    random_moves.remove(random_moves.indexOf(2)); //Updating random moves playlist 
                    currentBoard();
                    System.out.println("Player magic sqaure moves list : " + player);
                     System.out.println("Computer magic sqaure moves list : " + computer);
                }
            }
            
            else{
                if(winning_move()){
                currentBoard();
                System.out.println("Player magic sqaure moves list : " + player);
                System.out.println("Computer magic sqaure moves list : " + computer);
                System.out.println("Computer Won !!");
                System.exit(0);
                }
                else if(blocking_move()) {
                                        
                    currentBoard();
                    System.out.println("Player magic sqaure moves list : " + player);
                    System.out.println("Computer magic sqaure moves list : " + computer);
                   
                }
            
            else {
                random_move();
                currentBoard();
                System.out.println("Player magic sqaure moves list : " + player);
                System.out.println("Computer magic sqaure moves list : " + computer);
            }
        }    

        System.out.println("The computer has made it's move.");
        System.out.println("Your available moves : " + random_moves);
         
        }

    }

    static void random_move(){
        
        int randomIndex = (int)(Math.random()*random_moves.size());
        int randomNum = random_moves.get(randomIndex);
        random_moves.remove(randomIndex);
        System.out.println(randomNum);
        update_game_board('c',randomNum);
        computer.add(ms.get(randomNum));

    }

    static boolean winning_move(){
        //if no winning move possible return 0
        //else make winning move and return 1

        moves_map.put(2,0);
        moves_map.put(7,1);
        moves_map.put(6,2);
        moves_map.put(9,3);
        moves_map.put(5,4);
        moves_map.put(1,5);
        moves_map.put(4,6);
        moves_map.put(3,7);
        moves_map.put(8,8);
        moves_map.put(10,-1);
        moves_map.put(11,-1);
        moves_map.put(12,-1);
        moves_map.put(13,-1);  
        moves_map.put(14,-1);
        moves_map.put(0,-1);
        moves_map.put(-1,-1);
        moves_map.put(-2,-1);
        moves_map.put(-3,-1);


        //iterate through the array list in pairs of 2
        //and then see if (15-sum) is present in the random_moves list
        //if yes then make winning move and return 1
        //if not then return -1

        int pool=0;     //variable to keep track of winning move
        for(int i=0; i<computer.size()-1; i++){
            if(pool==1){
                break;
            }

            for(int j=i+1; j<computer.size(); j++){

                int p = 15-(computer.get(i)+computer.get(j));
                int boardNum = moves_map.get(p);
                if(random_moves.contains(boardNum)){

                    update_game_board('c', boardNum); //Updating the game board
                    random_moves.remove(random_moves.indexOf(boardNum));  //Updating arraylist for random moves
                    pool=1;     //to break out of loop
                    break;

                }

            }

        }
        if(pool==1){
            return true;
        }
        else return false;


    }

    static boolean blocking_move(){
        //if no winning move possible return 0
        //else make winning move and return 1

        moves_map.put(2,0);
        moves_map.put(7,1);
        moves_map.put(6,2);
        moves_map.put(9,3);
        moves_map.put(5,4);
        moves_map.put(1,5);
        moves_map.put(4,6);
        moves_map.put(3,7);
        moves_map.put(8,8);
        moves_map.put(10,-1);
        moves_map.put(11,-1);
        moves_map.put(12,-1);
        moves_map.put(13,-1);
        moves_map.put(14,-1);
        moves_map.put(0,-1);
        moves_map.put(-1,-1);
        moves_map.put(-2,-1);
        moves_map.put(-3,-1);

        //iterate through the array list in pairs of 2
        //and then see if (15-sum) is present in the random_moves list
        //if yes then make winning move and return 1
        //if not then return -1

        int pool=0;     //variable to keep track of winning move
        for(int i=0; i<player.size()-1; i++){
            if(pool==1){
                break;
            }

            for(int j=i+1; j<player.size(); j++){

                int p = 15-(player.get(i)+player.get(j));
                int boardNum = moves_map.get(p);
                if(random_moves.contains(boardNum)){

                    update_game_board('c', boardNum); //Updating the game board
                    random_moves.remove(random_moves.indexOf(boardNum));  //Updating arraylist for random moves
                    pool=1;     //to break out of loop
                    computer.add(ms.get(boardNum));
                    break;

                }


            }

        }
        if(pool==1){
            return true;
        }
        else return false;

    }


    public static void main(String[] args) {


        magicSquare();  //Displaying magic square
        System.out.println("Hello! Welcome to TIC-TAC-TOE.");
        System.out.println("You will be playing against me today.");
        System.out.println("To place X on the desired position on the board, enter the corresponding number.");
        rules();
        System.out.println("We shall now begin. You are 'X. The computer is 'O'. ");
        System.out.println();
        System.out.println("If you want to play the first move, enter 0, else enter 1: ");
        int uno = s.nextInt(); //Inputting for first move decision
        if(uno==0){
            turn=0; //Player will move first
        }
        else{
            turn=1; //Computer will move first
        }
        
        
        
        //When player moves first
        if(turn==0){
            currentBoard();
            System.out.println("Player magic sqaure moves list : " + player);
            System.out.println("Computer magic sqaure moves list : " + computer);
            System.out.println("Your available moves : " + random_moves);
            for(int i=0; i<6; i++){
            System.out.println("Please enter your move: ");
            player_move();
            computer_move();
            move_count++;
            }
        }
        
        
        //When computer moves first
        if(turn==1){
            
            for(int i=0; i<6; i++){
            computer_move();
            System.out.println("Please enter your move: ");
            player_move();
            move_count++;
            }
             
        }

    }

}