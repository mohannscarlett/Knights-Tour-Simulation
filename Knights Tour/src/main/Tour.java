package main;
import java.util.concurrent.ThreadLocalRandom;
/**
 * 
 * @author Mo
 * @version 1/27/2023
 */
public class Tour {
	public Tour() {
		int[][] chessBoard = new int[8][8];
		/* 
		 * Array of potential knight moves
		 * All values of horizontal and vertical are intentionally paired such that;
		 * 	if an index of horizontal[1] is used, index vertical[1] is used
		 *  this allows for knight to move in an L shape*/
		final int[] horizontal = new int[]{2,1,-1,-2,-2,-1,1,2}; //Negative values move the knight left, positive values move right
		final int[] vertical = new int[]{-1,-2,-2,-1,1,2,2,1}; //Negative values move the knight down, positive values move up
		int currentRow = 0;
		int currentColumn = 0;
		int moveToThisPosition = 0; 
		int numberOfKnightMoves = 1; 
		
		//generate knight on random 8 by 8 2d array index position
		chessBoard[ThreadLocalRandom.current().nextInt(0,8)][ThreadLocalRandom.current().nextInt(0,8)] = numberOfKnightMoves;
		
		String currentPosition[] = printChessboardBeforeTour(chessBoard).split(",");
		currentRow = Integer.parseInt(currentPosition[0]);
		currentColumn = Integer.parseInt(currentPosition[1]);
		
		numberOfKnightMoves++; // knight is now in its second turn moving
		
		//knight will attempt to randomly move around the chess board for 1000 attempts
		for(int i = 0; i < 1000; i++){
			moveToThisPosition = ThreadLocalRandom.current().nextInt(0,8);
			try {
				currentRow += vertical[moveToThisPosition];
				currentColumn += horizontal[moveToThisPosition];
				
				if(knightHasNotBeenHere(chessBoard,currentRow,currentColumn)) {
					
				chessBoard[currentRow][currentColumn] = numberOfKnightMoves; 
				numberOfKnightMoves++; 
				
				}else {
					//if position being attempted to move to has been visited revert any changes made and prepare to try again
					currentRow -= vertical[moveToThisPosition];
					currentColumn -= horizontal[moveToThisPosition];
					}
			}catch(Exception e){
				//if array is out of bounds (knight tried moving off board) revert any changes made and prepare for next loop
				currentRow -= vertical[moveToThisPosition];
				currentColumn -= horizontal[moveToThisPosition];
			}
			
		}
		printChessboardAfterTour(currentRow,currentColumn,chessBoard);
	}
	//checks position to see if knight has been here
	private boolean knightHasNotBeenHere(int chessBoard[][], int currentRow, int currentColumn) {
		if(chessBoard[currentRow][currentColumn] == 0) {
			return true;
		}
		return false;
	}
	//prints initial chess board before tour
	private String printChessboardBeforeTour(int[][] chessBoard){
		int currentRow;
		int currentColumn;
		String currentPosition = "";
		
		System.out.println("Original Chess Board");
		System.out.println();
		System.out.printf(" ");
		
		//print row index for an 8 by 8 chessBoard 
		for(int i = 0; i < chessBoard[0].length; i++) {
			System.out.printf("%9d", i+1); 
		}
		System.out.println();

		//print 8 by 8 chess board with the initial position of knight
		for(int i = 0; i < chessBoard.length; i++) {
			System.out.println();
			System.out.println();
			System.out.printf("%d", i+1);//print column index for an 8 by 8 chessBoard 
			for(int j = 0; j < chessBoard.length;j++){
				
				/*check every chessBoard element to see where the knight is
				if found the current row and current column will be updated to where the knight is */
				if(chessBoard[i][j] == 1) {
					currentRow = i;
					currentColumn = j; 
					currentPosition = i + "," + j ;//two int values need to be returned therefore a String is used as a holder object
					}
				//print chessBoard element at given index
				System.out.printf("%9d",chessBoard[i][j]);
			}
		}	
		return currentPosition;
	}
	//prints chess board after tour
	private void printChessboardAfterTour(int currentRow,int currentColumn,int[][] chessBoard){
		int completedTour = 64;
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Knight's Tour ended in: " + chessBoard[currentRow][currentColumn] + " moves.");
		
		if(chessBoard[currentRow][currentColumn] < completedTour) {
			System.out.println("This was not a full tour");
		} else {
			System.out.println("Tour complete"); 
			}
		System.out.println();
		System.out.printf(" ");
		
		//print chessBoard row index
		for(int i = 0; i < chessBoard[0].length; i++) {
			System.out.printf("%9d", i+1);
		}
		
		//print chessBoard 2d array spaced out
		System.out.println("");
		for(int i = 0; i < chessBoard.length; i++) {
			System.out.println();
			System.out.println();
			System.out.printf("%d", i+1);//print chessBoard column index
			for(int j = 0; j < chessBoard.length;j++){
				System.out.printf("%9d",chessBoard[i][j]);
			}
		}
	}
	
}
