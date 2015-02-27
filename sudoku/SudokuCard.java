package sudoku;
public abstract class SudokuCard {

	protected final int DEFAULT_INT = 0;
	protected int[][] card;

	public SudokuCard() {
		this.card = new int[9][9];
	}

	public void setNumber(Point point, int value) throws InvalidValueException {
		card[point.getX()][point.getY()] = value;
		if (!isValid()) {
			rollback(point);
		}
	}

	private void rollback(Point point) throws InvalidValueException {
		card[point.getX()][point.getY()] = DEFAULT_INT;
		throw new InvalidValueException();
	}

	public int getNumber(Point point) {
		return card[point.getX()][point.getY()];
	}

	public abstract boolean isValid();
}
