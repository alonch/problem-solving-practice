import java.util.HashSet;
import java.util.Set;

public class NumbersChecker {
	private final static int DEFAULT_INT = 0;
	private Set<Integer> line;

	public NumbersChecker() {
		line = new HashSet<Integer>();
	}

	public void add(Integer number) throws InvalidValueException {
		if (number == DEFAULT_INT) {
			return;
		}
		addOrThrowEx(number);
	}

	private void addOrThrowEx(int number) throws InvalidValueException {
		boolean inserted = line.add(number);
		if (!inserted) {
			throw new InvalidValueException();
		}
	}
}
