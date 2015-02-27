package sudoku;
public class RowPointFactory implements PointFactory {
	@Override
	public Point getPoint(int x, int y) {
		return new Point(y, x);// Inverted
	}
}
