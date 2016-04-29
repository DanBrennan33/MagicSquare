package brennan4114;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author dtbrennan1 - 020 194 114
 * Assignment 2 - part A
 * MagicSquare creates files with 5 matrix's to validate if the 
 * integers received is a perfect square and then if each line 
 * adds up to be a magic square.
 *  
 */

public class MagicSquare {

	/**
	 * GenerateFile method creates 5 files with
	 * 5 different matrix's 2. Using the algorithms
	 * form the lab and ensuring that 2 of the files 
	 * the validation.
	 */
	public void GenerateFile() throws IOException {
		for (int i = 0; i < 5; i++) {
			FileWriter out = new FileWriter("file" + i + ".txt");
			int[][] squareMatrix = new int[3][3];
			
			Random rand = new Random();
			
			int a, b, c;
			a = b = c = 0;
			
			a = rand.nextInt((25 - 1) + 1) + 1;
			b = rand.nextInt((25 - 1) + 1) + 1;
			c = rand.nextInt((25 - 1) + 1) + 1;

			squareMatrix[0][0] = c - b;
			squareMatrix[0][1] = c + (a + b);
			squareMatrix[0][2] = c - a;

			squareMatrix[1][0] = c - (a - b);
			squareMatrix[1][1] = c;
			squareMatrix[1][2] = c + (a - b);

			squareMatrix[2][0] = c + a;
			squareMatrix[2][1] = c - (a + b);
			squareMatrix[2][2] = c + b;

			for (int e = 0; e < 3; e++) {
				for (int f = 0; f < 3; f++) {
					if((i == 1 || i == 3) && (e == 1 && f == 1)) {
						squareMatrix[e][f] = 13;
					}
					out.write(squareMatrix[e][f] + " ");
				}
				out.write('\n');
			}
			out.close();
		}
	}

	/**
	 * Inserts the file array of integers in to a List
	 * that can then be used to validate the square.
	 */
	public ArrayList<Integer> Valid(Scanner in) {
		ArrayList<Integer> square = new ArrayList<Integer>();
		while (in.hasNext()) {
			if (in.hasNextInt()) {
				square.add(in.nextInt());
			} else {
				in.next();
			}
		}
		return square;
	}

	/**
	 * Adds each line of integers to ensure that
	 * the matrix is indeed a magic square.
	 * Returns boolean based on validation.
	 */
	public void Sum(ArrayList<Integer> magic) {
		int row, col;
		row = col = -1;
		int n = (int) (Math.sqrt(magic.size()) + 0.5);
		int[] rowSum = new int[n];
		int[] colSum = new int[n];
		int diagSumBack = 0;
		int diagSumForw = 0;
		boolean isMagic = true;

		for (int i = 0; i < magic.size(); i++) {
			col++;
			if (col % n == 0) {
				row++;
				col = 0;
			}

			rowSum[row] += magic.get(i);
			colSum[col] += magic.get(i);

			if (row == col) {
				diagSumBack += magic.get(i);
			}
			if (row + col == n - 1) {
				diagSumForw += magic.get(i);
			}
		}
		for (int i = 0; i < n && isMagic; i++) {
			isMagic = diagSumBack == rowSum[i] && diagSumBack == colSum[i];
		}
		isMagic = isMagic && diagSumBack == diagSumForw;
		if(isMagic)
			System.out.println("This is a magic square.");
		else
			System.out.println("This is not a magic square.");
	}

	public static void main(String[] args) throws Exception {
		MagicSquare ms = new MagicSquare();
		ms.GenerateFile();
		
		for (int i = 0; i < 5; i++) {
			File file = new File("file" + i + ".txt");

			Scanner in = new Scanner(file);
			ArrayList<Integer> square = ms.Valid(in);
			in.close();
			
			for(int e = 0; e < square.size(); e++) {
				System.out.print(square.get(e) + " ");
			}
			System.out.println();

			ms.Sum(square);
		}
	}
}
