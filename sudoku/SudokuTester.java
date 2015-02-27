package sudoku;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.Stack;

import main.Test;

public class SudokuTester implements Test{
	private Class<SudokuCard> cardClass;
	SudokuCard card;

	public SudokuTester(String className) throws Exception {
		this.cardClass = (Class<SudokuCard>) Class.forName(className);
	}

	public void testAll() {
		try {
			testColumnError();
			System.out.println("Column with error: fail");
			return;
		} catch (Exception e) {
			System.out.println("Column with error: pass");
		}

		try {
			testRowError();
			System.out.println("Row with error: fail");
			return;
		} catch (Exception e) {
			System.out.println("Row with error: pass");
		}

		try {
			testColumns();
			System.out.println("Columns: pass");
		} catch (Exception e) {
			System.out.println("Columns: fail");
			return;
		}

		try {
			testRows();
			System.out.println("Rows: pass");
		} catch (Exception e) {
			System.out.println("Rows: fail");
			return;
		}

		try {
			testInnerboxes();
			System.out.println("Boxes: pass");
		} catch (Exception e) {
			System.out.println("Boxes: fail");
			return;
		}
		try {
			testInnerboxesError();
			System.out.println("Full card with error: fail");
			return;
		} catch (Exception e) {
			System.out.println("Full card with error: pass");
		}
		try {
			testFullCard();
			System.out.println("Full card: pass");
		} catch (Exception e) {
			System.out.println("Full card: fail");
			return;
		}

	}

	private void createNewObject() throws Exception {
		Constructor<SudokuCard> constructor = cardClass.getConstructor(null);
		card = (SudokuCard) constructor.newInstance(null);
	}

	private Stack<Integer> inflateShuffleNumbers() {
		Stack<Integer> numbers = inflateValidNumbers();
		Collections.shuffle(numbers);
		return numbers;
	}

	private Stack<Integer> inflateValidNumbers() {
		Stack<Integer> numbers = new Stack<Integer>();
		for (int i = 1; i < 10; i++) {
			numbers.add(i);
		}
		return numbers;
	}

	public void testColumns() throws Exception {
		Point point = new Point(0, 0);
		for (int i = 0; i < 9; i++) {
			Stack<Integer> numbers = inflateShuffleNumbers();
			createNewObject();
			point.setX(i);
			for (int j = 0; j < 9; j++) {
				point.setY(j);
				card.setNumber(point, numbers.pop());
			}
		}
	}

	public void testRows() throws Exception {
		Point point = new Point(0, 0);
		for (int i = 0; i < 9; i++) {
			Stack<Integer> numbers = inflateShuffleNumbers();
			createNewObject();
			point.setY(i);
			for (int j = 0; j < numbers.size(); j++) {
				point.setX(j);
				card.setNumber(point, numbers.pop());
			}
		}
	}

	public void testInnerboxes() throws Exception {
		Point point = new Point(0, 0);
		for (int outerX = 0; outerX < 3; outerX++) {
			for (int outerY = 0; outerY < 3; outerY++) {
				Stack<Integer> numbers = inflateShuffleNumbers();
				createNewObject();
				for (int innerX = 0; innerX < 3; innerX++) {
					point.setX(outerX * 3 + innerX);
					for (int innerY = 0; innerY < 3; innerY++) {
						point.setY(outerY * 3 + innerY);
						card.setNumber(point, numbers.pop());
					}
				}
			}
		}
	}

	public void testFullCard() throws Exception {
		int[][] solvedCard = new int[][] { { 5, 3, 4, 6, 7, 8, 9, 1, 2 },
				{ 6, 7, 2, 1, 9, 5, 3, 4, 8 }, { 1, 9, 8, 3, 4, 2, 5, 6, 7 },
				{ 8, 5, 9, 7, 6, 1, 4, 2, 3 }, { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
				{ 7, 1, 3, 9, 2, 4, 8, 5, 6 }, { 9, 6, 1, 5, 3, 7, 2, 8, 4 },
				{ 2, 8, 7, 4, 1, 9, 6, 3, 5 }, { 3, 4, 5, 2, 8, 6, 1, 7, 9 } };
		Point point = new Point(0, 0);
		createNewObject();
		for (int i = 0; i < solvedCard.length; i++) {
			point.setY(i);
			for (int j = 0; j < solvedCard[i].length; j++) {
				point.setX(j);
				card.setNumber(point, solvedCard[i][j]);
			}
		}
	}

	public void testInnerboxesError() throws Exception {
		int[][] solvedCard = new int[][] { { 5, 3, 4, 6, 7, 8, 9, 1, 2 },
				{ 6, 7, 2, 1, 9, 5, 3, 0, 8 }, { 1, 9, 8, 3, 4, 2, 5, 6, 7 },
				{ 8, 5, 9, 7, 6, 1, 4, 2, 3 }, { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
				{ 7, 1, 3, 9, 2, 4, 8, 5, 6 }, { 9, 6, 1, 5, 3, 7, 2, 4, 0 },
				{ 2, 8, 7, 4, 1, 9, 6, 3, 5 }, { 3, 0, 5, 2, 8, 6, 1, 7, 4 } };
		Point point = new Point(0, 0);
		createNewObject();
		for (int i = 0; i < solvedCard.length; i++) {
			point.setY(i);
			for (int j = 0; j < solvedCard[i].length; j++) {
				point.setX(j);
				card.setNumber(point, solvedCard[i][j]);
			}
		}
	}

	public void testColumnError() throws Exception {
		createNewObject();
		Point point = new Point(0, 0);
		card.setNumber(point, 1);
		point.setY(8);
		card.setNumber(point, 1);
	}

	public void testRowError() throws Exception {
		createNewObject();
		Point point = new Point(0, 0);
		card.setNumber(point, 1);
		point.setX(8);
		card.setNumber(point, 1);
	}

}
