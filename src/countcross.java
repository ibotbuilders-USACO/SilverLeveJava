import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Stack;
import java.util.StringTokenizer;

class DataPair implements Comparable<DataPair> {

	private int row, col;

	// constructor
	DataPair() {
		row = 0;
		col = 0;
	}

	DataPair(int s, int e) {
		row = s;
		col = e;
	}

	// access methods
	public void setBoth(int r, int c) {
		row = r;
		col = c;
	}

	public int getRow() {
		return (row);
	}

	public int getCol() {
		return (col);
	}

	public void setRow(int s) {
		row = s;
	}

	public void setCol(int e) {
		col = e;
	}

	public int compareTo(DataPair CompPair) {
		return (this.col - CompPair.getCol());
	}

	public static DataPair[] bubbleSort(DataPair[] intArray) {
		int n = intArray.length;
		int temp = 0, temp2 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {

				if (intArray[j - 1].getRow() > intArray[j].getRow()) {
					// swap the elements!
					temp = intArray[j - 1].getRow();
					temp2 = intArray[j - 1].getCol();
					intArray[j - 1].setRow(intArray[j].getRow());
					intArray[j - 1].setCol(intArray[j].getCol());
					intArray[j].setRow(temp);
					;
					intArray[j].setCol(temp2);
				} else if (intArray[j - 1].getRow() == intArray[j].getRow()
						&& intArray[j - 1].getCol() > intArray[j].getCol()) {
					// swap the elements!
					temp = intArray[j - 1].getRow();
					temp2 = intArray[j - 1].getCol();
					intArray[j - 1].setRow(intArray[j].getRow());
					intArray[j - 1].setCol(intArray[j].getCol());
					intArray[j].setRow(temp);
					;
					intArray[j].setCol(temp2);
				}
			}
		}
		return intArray;
	}

}

public class countcross {
	static DataPair waterStartCow = new DataPair();
	static int numDistant = 0, numDistantFinal = 0;
	static Hashtable roadPos;
	static int[][] waterFilled;
	static DataPair[] cowPos;
	static int fieldDim, numCows, numRoads;
	static Stack stack;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("countcross.in"));
		File file = new File("countcross.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		fieldDim = Integer.parseInt(st.nextToken());
		numCows = Integer.parseInt(st.nextToken());
		numRoads = Integer.parseInt(st.nextToken());
		int i;
		// cowPos = new int[fieldDim][fieldDim];
		cowPos = new DataPair[numCows];
		waterFilled = new int[fieldDim][fieldDim];
		int j, count = 0;
		roadPos = new Hashtable(numRoads);
		stack = new Stack();
		String key;
		while (count < numRoads) {
			StringTokenizer str = new StringTokenizer(f.readLine());
			key = str.nextToken() + str.nextToken() + str.nextToken() + str.nextToken();
			roadPos.put(key, 1);
			count++;
		}
		count = 0;
		for (i = 0; i < fieldDim; i++) {
			for (j = 0; j < fieldDim; j++) {
				waterFilled[i][j] = 0;
			}
		}
		for (i = 0; i < numCows; i++) {
			cowPos[i] = new DataPair();
		}
		while (count < numCows) {
			StringTokenizer fg = new StringTokenizer(f.readLine());
			int row = Integer.parseInt(fg.nextToken());
			int col = Integer.parseInt(fg.nextToken());
			cowPos[count] = new DataPair(row, col);
			count++;
		}
		cowPos = DataPair.bubbleSort(cowPos);
		check();
		numDistantFinal = numDistant / 2;
		System.out.println(numDistantFinal);
		out.println(numDistantFinal);
		out.close();
	}

	public static boolean roadBlock(DataPair current, DataPair go) {
		int i;
		boolean blocked = false;
		String currentGo, currentGo2;
		currentGo = Integer.toString(current.getRow()) + Integer.toString(current.getCol())
				+ Integer.toString(go.getRow()) + Integer.toString(go.getCol());
		currentGo2 = Integer.toString(go.getRow()) + Integer.toString(go.getCol()) + Integer.toString(current.getRow())
				+ Integer.toString(current.getCol());
		if (roadPos.containsKey(currentGo) || roadPos.containsKey(currentGo2)) {
			blocked = true;
		}
		return blocked;
	}

	public static DataPair[] adjPerCow(DataPair current) {
		DataPair[] adjacent = new DataPair[4];
		if (current.getRow() - 1 < 1) {
			adjacent[0] = new DataPair(-1, -1);
		} else {
			if (waterFilled[current.getRow() - 2][current.getCol() - 1] == 1) {
				adjacent[0] = new DataPair(-1, -1);
			} else {
				adjacent[0] = new DataPair(current.getRow() - 1, current.getCol());
			}
		}
		if (current.getCol() + 1 > fieldDim) {
			adjacent[1] = new DataPair(-1, -1);
		} else {
			if (waterFilled[current.getRow() - 1][current.getCol()] == 1) {
				adjacent[1] = new DataPair(-1, -1);
			} else {
				adjacent[1] = new DataPair(current.getRow(), current.getCol() + 1);
			}
		}
		if (current.getRow() + 1 > fieldDim) {
			adjacent[2] = new DataPair(-1, -1);
		} else {
			if (waterFilled[current.getRow()][current.getCol() - 1] == 1) {
				adjacent[2] = new DataPair(-1, -1);
			} else {
				adjacent[2] = new DataPair(current.getRow() + 1, current.getCol());
			}

		}
		if (current.getCol() - 1 < 1) {
			adjacent[3] = new DataPair(-1, -1);
		} else {
			if (waterFilled[current.getRow() - 1][current.getCol() - 2] == 1) {
				adjacent[3] = new DataPair(-1, -1);
			} else {
				adjacent[3] = new DataPair(current.getRow(), current.getCol() - 1);
			}

		}
		return adjacent;
	}

	public static void loop(int i, int j) {
		int k, n = 0, row, col, count = 0, first = 0, row1 = 0, col1 = 0;
		boolean roadBlocked;

		DataPair[] adjacent = new DataPair[4];
		
	}

	public static void check() {
		int k, n = 0, row, col, count = 0, first = 0, row1 = 0, col1 = 0;
		boolean roadBlocked;
		int i, j, p, l;
		DataPair[] adjacent = new DataPair[4];
		for (i = 0; i < numCows; i++) {

			for (j = 0; j < fieldDim; j++) {
				if (count == numCows) {
					break;
				}
				waterStartCow.setBoth(i + 1, j + 1);
				if (cowPos[count].getRow() == waterStartCow.getRow()
						&& cowPos[count].getCol() == waterStartCow.getCol()) {

					stack.push(new DataPair(0, 0));
					while (!stack.isEmpty()) {

						if (first == 0) {
							stack.pop();
							first = 1;
						} else {
							waterStartCow = (DataPair) stack.pop();
						}
						waterFilled[i][j] = 1;
						adjacent = adjPerCow(waterStartCow);
						for (k = 0; k < 4; k++) {

							if (adjacent[k].getRow() == -1) {

							} else {
								roadBlocked = roadBlock(waterStartCow, adjacent[k]);
								if (roadBlocked == false) {
									waterFilled[adjacent[k].getRow() - 1][adjacent[k].getCol() - 1] = 1;
									stack.push(adjacent[k]);
								}
							}
						}

					}
					first = 0;
					DataPair pos = new DataPair();
					// for(row=0;row<fieldDim;row++)
					// {
					for (row = 0; row < fieldDim; row++) 
					{
						for (col = 0; col < fieldDim; col++) 
						{
							if (n == numCows) 
							{
								break;
							}
							pos.setBoth(row, col);
							if (waterFilled[row][col] == 0 && cowPos[n].getRow() == pos.getRow() + 1 && cowPos[n].getCol() == pos.getCol() + 1) 
							{
								numDistant++;
							}
							if (cowPos[n].getRow() == pos.getRow() + 1 && cowPos[n].getCol() == pos.getCol() + 1) 
							{
								n++;
							}

							
							// row1++;
							// col1++;
						}
					}
					for(p=0;p<fieldDim;p++)
					{
						for(l=0;l<fieldDim;l++)
						{
							waterFilled[p][l] = 0;
						}
					}
					n = 0;
					count++;

				}

			}
		}
	}

}
