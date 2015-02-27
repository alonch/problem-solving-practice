package main;

import numberTranslation.*;

public class NumberTranslationTester implements Test {

	private Translator translator;

	public NumberTranslationTester(Translator translator) throws Exception {
		this.translator = translator;
	}

	@Override
	public void testAll() {
		TranslatorAlonso translator = new TranslatorAlonso();

		for (int i = 0; i < 1000; i++) {
			String mySolution = translator.translate(i);
			String yourSolution = translator.translate(i);
			if (!mySolution.equalsIgnoreCase(yourSolution)) {
				System.out.println(mySolution + " < not equal to > "
						+ yourSolution + ":" + i);
				return;
			}
		}
		System.out.println("Ok");
	}

}
