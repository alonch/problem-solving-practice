package main;
import java.lang.reflect.Constructor;

import numberTranslation.Translator;

public class Tester {

	public static void main(String[] args) throws Exception {
		String testerName = args[0];
		String solutionName = args[1];
		
		Class solutionClass = Class.forName(solutionName);
		Constructor constructor = solutionClass.getConstructor();
		Translator solution = (Translator) constructor.newInstance();
		
		Class testerClass = Class.forName(testerName);
		constructor = testerClass.getConstructor(Translator.class);
		Test tester = (Test) constructor.newInstance(solution);
		tester.testAll();
	}
}
