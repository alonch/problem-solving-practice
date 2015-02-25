public class SudokuCardAlonso extends SudokuCard {
	private static final int SUDOKU_LENGTH = 9;
	private static final int SUDOKU_INNERBOX_LENGTH = SUDOKU_LENGTH / 3;

	@Override
	public boolean isValid() {
		try {
			checkConstrains();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	private void checkConstrains() throws InvalidValueException {
		checkColumns();
		checkRows();
		checkBoxes();
	}

	private void checkColumns() throws InvalidValueException {
		for (int x = 0; x < SUDOKU_LENGTH; x++) {
			checkColumn(x);
		}
	}

	private void checkRows() throws InvalidValueException {
		for (int y = 0; y < SUDOKU_LENGTH; y++) {
			checkRow(y);
		}
	}

	private void checkColumn(int x) throws InvalidValueException {
		RowPointFactory pointFactory = new RowPointFactory();
		checkLine(x, pointFactory);
	}

	private void checkRow(int y) throws InvalidValueException {
		ColumnPointFactory pointFactory = new ColumnPointFactory();
		checkLine(y, pointFactory);
	}

	private void checkLine(int x, PointFactory pointFactory)
			throws InvalidValueException {
		NumbersChecker numbersChecker = new NumbersChecker();
		for (int y = 0; y < SUDOKU_LENGTH; y++) {
			Point point = pointFactory.getPoint(x, y);
			int number = getNumber(point);
			numbersChecker.add(number);
		}
	}

	private void checkBoxes() throws InvalidValueException {
		for (int outerX = 0; outerX < SUDOKU_INNERBOX_LENGTH; outerX++) {
			for (int outerY = 0; outerY < SUDOKU_INNERBOX_LENGTH; outerY++) {
				Point outerPoint = new Point(outerX, outerY);
				checkBox(outerPoint);
			}
		}
	}

	private void checkBox(Point outerPoint) throws InvalidValueException {
		NumbersChecker numbersChecker = new NumbersChecker();
		for (int innerX = 0; innerX < SUDOKU_INNERBOX_LENGTH; innerX++) {
			for (int innerY = 0; innerY < SUDOKU_INNERBOX_LENGTH; innerY++) {
				Point innerPoint = new Point(innerX, innerY);
				Point point = calculateBoxNumberPoint(outerPoint, innerPoint);
				int number = getNumber(point);
				numbersChecker.add(number);
			}
		}
	}

	private Point calculateBoxNumberPoint(Point outerPoint, Point innerPoint) {
		int x = outerPoint.getX() * SUDOKU_INNERBOX_LENGTH + innerPoint.getX();
		int y = outerPoint.getY() * SUDOKU_INNERBOX_LENGTH + innerPoint.getY();
		return new Point(x, y);
	}

}
