
import java.util.Random;
import java.util.Scanner;
public class WordGen {
	
	public static void main(String []args) {
		Scanner input = new Scanner(System.in);
		String[][]puzzle = new String [1][1];
		String[][]showPuzzle = new String[1][1];
		while(true) {
			printIntro();
			String ans = input.next();
			
			if(ans.equals("g")) {
				int num = numWords(input);
				int size;
				if(num > 5) {
					size =  num * 2;
				}
				else {
					size = 10;
				}
				puzzle = new String [size][size];
				showPuzzle = new String[size][size];
				generate(input,puzzle, num, showPuzzle);
			}
			else if(ans.equals("p")) {
				print(puzzle);
			}
			else if(ans.equals("s")) {
				show(showPuzzle);
			}
			else if(ans.equals("1")) {
				break;
			}
		}
		
	}
	
	public static void show(String[][]puzzle) {
		for(int row = 0; row < puzzle.length; row++) {
			for(int col = 0; col < puzzle.length; col++) {
				if(puzzle[row][col] == null) {
					puzzle[row][col] = "x";
				}
				
			}
		}
		
		for(int l = 0; l < puzzle.length; l++) {
			 for(int k = 0; k < puzzle.length; k++) {
				 System.out.print(puzzle[l][k] + " ");
			 }
			 System.out.println();
		}
		System.out.println();
	}
	
	public static int numWords(Scanner input) {
		System.out.println("How many words would you like to have");
		return input.nextInt();
	}
	
	public static void printIntro() {
		System.out.println("""
				Welcome to my word search generator!
				This program will allow you to generate your own word search puzzle 
				Please select an option:
				Generate a new word search (g)
				Print out your word search (p)
				Show the solution to your word search (s)
				Quit the program (1)
				""");
	}
	
	public static void generate(Scanner input, String[][] puzzle, int num, String[][]showPuzzle) {
		Random rd = new Random(); 
		
		for(int i = 0; i < num; i ++) {
			System.out.println("add word:");
			String word = input.next();
			int ran = rd.nextInt(3) + 1;
			int row = rd.nextInt(puzzle.length);
			int col = rd.nextInt(puzzle.length);
			while(!(check(ran, row, col, puzzle, word))) {
				ran = rd.nextInt(3)+ 1;
				row = rd.nextInt(puzzle.length);
				col = rd.nextInt(puzzle.length);
			}
			distribute(word, ran, row, col, puzzle, showPuzzle);
		}
		
		
	}
	
	public static boolean check(int num, int row, int col, String[][] puzzle, String word) {
		//across
		if(num == 1 && col + word.length() <= puzzle.length) {
			for(int i = 0; i < word.length(); i++) {
				if(puzzle[row][col + i] != null){
					return false;
				}
			}
			return true;
		}
		//down
		else if(num == 2 && row + word.length() <= puzzle.length) {
			for(int i = 0; i < word.length(); i++) {
				if(puzzle[row + i][col] != null) {
					return false;
				}
			}
			return true;
		}
		//diagonal
		else if(num == 3 && (col + word.length() <= puzzle.length) && (row + word.length() < puzzle.length)) {
			for(int i = 0; i < word.length(); i++) {
				if(puzzle[row + i][col + i] != null) {
					return false;
				}
			}
			return true;
		}
		
		else {
			return false;
		}
		
	}
	
	public static void print(String[][]puzzle) {
		Random rd = new Random(); 
		for(int row = 0; row < puzzle.length; row++) {
			for(int col = 0; col < puzzle.length; col++) {
				if(puzzle[row][col] == null) {
					int ran = rd.nextInt(26) + 97;
					puzzle[row][col] = (char) ran + "";
				}
				
			}
		}
		
		for(int l = 0; l < puzzle.length; l++) {
			 for(int k = 0; k < puzzle.length; k++) {
				 System.out.print(puzzle[l][k] + " ");
			 }
			 System.out.println();
		}
		System.out.println();
	}
	
	public static void distribute(String word, int num, int row, int col, String[][]puzzle, String [][]showPuzzle) {
		
		//across
		if(num == 1) {
			for(int i = 0; i < word.length(); i++) {
				puzzle[row][col + i] = word.charAt(i) + "";
				showPuzzle[row][col + i] = word.charAt(i) + "";
			}
		}
		//down
		if(num == 2) {
			for(int i = 0; i < word.length(); i++) {
				puzzle[row + i][col] = word.charAt(i) + "";
				showPuzzle[row + i][col] = word.charAt(i) + "";
			}
		}
		//diagonal
		if(num == 3) {
			for(int i = 0; i < word.length(); i++) {
				puzzle[row + i][col + i] = word.charAt(i) + "";
				showPuzzle[row + i][col + i] = word.charAt(i) + "";
			}
		}
	}
}
