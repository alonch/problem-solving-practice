import java.lang.reflect.Constructor;

public class Test {

	public static void main(String[] args) throws Exception {
		Class dinamycClass = Class.forName(args[0]);
		SudokuCardTest tester = new SudokuCardTest(dinamycClass);
		tester.testAll();
	}
}
