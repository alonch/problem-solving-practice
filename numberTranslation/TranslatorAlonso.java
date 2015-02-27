package numberTranslation;

import java.util.HashMap;

public class TranslatorAlonso implements Translator {
	private static final HashMap<Integer, String> LABELS = new HashMap<Integer, String>() {
		{
			put(0, "");
			put(1, "one");
			put(2, "two");
			put(3, "three");
			put(4, "four");
			put(5, "five");
			put(6, "six");
			put(7, "seven");
			put(8, "eight");
			put(9, "nine");
			put(10, "ten");
			put(11, "eleven");
			put(12, "twelve");
			put(13, "thirteen");
			put(14, "fourteen");
			put(15, "fifteen");
			put(16, "sixteen");
			put(17, "seventeen");
			put(18, "eighteen");
			put(19, "nineteen");
			put(20, "twenty");
			put(30, "thirty");
			put(40, "forty");
			put(50, "fifty");
			put(60, "sixty");
			put(70, "seventy");
			put(80, "eighty");
			put(90, "ninety");
			put(100, "hundred");
		}
	};

	String translation;

	@Override
	public String translate(int number) {
		if (number == 0) {
			return "zero";
		}
		translation = "";
		Fraction fraction = new Fraction(number);
		fraction.calculateMaxDiv();
		return iterateDiv(fraction);
	}

	private String iterateDiv(Fraction fraction) {

		if (isOneWordAnswer(fraction.getDenominator())) {
			translation += getTranslation(fraction.getDenominator());
			return translation.trim();
		}
		fraction.operateNextDividor();
		translation += generateString(fraction);
		fraction.operateModular();
		return iterateDiv(fraction);
	}

	private boolean isOneWordAnswer(int number) {
		return number < 20;
	}

	private String getTranslation(int number) {
		return LABELS.get(number) + " ";
	}

	private String generateString(Fraction fraction) {
		if (fraction.isThreeDigits()) {
			return generateThreeDigits(fraction);
		} else if (fraction.isTwoDigits()) {
			return generateTwoDigits(fraction);
		} else {
			return generateOneDigits(fraction);
		}
	}

	private String generateThreeDigits(Fraction fraction) {
		String numberPart = LABELS.get(fraction.div());
		String hundredPart = LABELS.get(fraction.getDividor());
		return String.format("%s %s ", numberPart, hundredPart);
	}

	private String generateTwoDigits(Fraction fraction) {
		int number = fraction.getDenominatorFirstDigitWithoutLastDecimal();
		return LABELS.get(number) + " ";
	}

	private String generateOneDigits(Fraction fraction) {
		return LABELS.get(fraction.getDenominator()) + " ";
	}

}
