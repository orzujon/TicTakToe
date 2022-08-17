import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTakToe {
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	
	static char[][] gameBoard =    {{' ', '|', ' ', '|', ' '},
									{'-', '+', '-', '+', '-'}, 
									{' ', '|', ' ', '|', ' '}, 
									{'-', '+', '-', '+', '-'},
									{' ', '|', ' ', '|', ' '}};
	
	public static void boardPrint(char[][] gameBoard) {
		for (char [] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	public static void placePiece(char[][] gameBoard, int pos, String user){
		
		char symbol = ' ';
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		} else if (user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		switch (pos) {
		 	case 1: 
		 		gameBoard[0][0] = symbol;
		 		break;
		 	case 2: 
		 		gameBoard[0][2] = symbol;
		 		break;
		 	case 3: 
		 		gameBoard[0][4] = symbol;
		 		break;
		 	case 4: 
		 		gameBoard[2][0] = symbol;
		 		break;
		 	case 5: 
		 		gameBoard[2][2] = symbol;
		 		break;
		 	case 6: 
		 		gameBoard[2][4] = symbol;
		 		break;
		 	case 7: 
		 		gameBoard[4][0] = symbol;
		 		break;
		 	case 8: 
		 		gameBoard[4][2] = symbol;
		 		break;
		 	case 9: 
		 		gameBoard[4][4] = symbol;
		 		break; 
		 	default:
		 		break;
		}
	}
	
	public static String winningConditions(){
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(3, 5, 7);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		
		for (List l : winning) {
			if (playerPositions.containsAll(l)) {
				boardPrint(gameBoard);
				return "Congratulations you won!";
			} else if (cpuPositions.containsAll(l)) {
				boardPrint(gameBoard);
				return "Sorry CPU won :( !";
			} else if(playerPositions.size() + cpuPositions.size() == 9) {
				boardPrint(gameBoard);
				return "CAT!";
			}
		}
		return "";
	}
	
	
	public static void main(String[] args) {
		
		
		
		boardPrint(gameBoard);
		while(true) {
		Scanner scanner = new Scanner(System.in);
		Random rand = new Random();
		
		System.out.print("Please enter your position value (1-9): ");
		int plyPos = scanner.nextInt();
		
		while (playerPositions.contains(plyPos) || cpuPositions.contains(plyPos)) {
			System.out.println("Position is taken! Please choose a correct placement:");
			plyPos = scanner.nextInt();
		}
		
		placePiece(gameBoard, plyPos, "player");
		String result = winningConditions();
		if(result.length() > 0) {
			System.out.println(result);
			break;
			}
		
		int cpuPos = rand.nextInt(9) + 1; 
		while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
			cpuPos = rand.nextInt(9) + 1;
		} 
		placePiece(gameBoard, cpuPos, "cpu");
		boardPrint(gameBoard);
		
		result = winningConditions();
		System.out.println(result);
		if(result.length() > 0) {
			boardPrint(gameBoard);
			System.out.println(result);
			break;
			}
		}
	}
	
	
}

