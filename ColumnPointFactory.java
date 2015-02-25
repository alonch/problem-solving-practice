
public class ColumnPointFactory implements PointFactory{

	@Override
	public Point getPoint(int x, int y) {
		return new Point(x, y);
	}

}
