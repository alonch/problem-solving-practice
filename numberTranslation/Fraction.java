package numberTranslation;

public class Fraction {
	private int denominator;
	private int dividor;
	private final static int STEP_DIVITION = 10;

	public Fraction(int number) {
		this.denominator = number;
	}

	public int getDenominator() {
		return denominator;
	}

	public void operateNextDividor() {
		dividor /= STEP_DIVITION;
	}

	public boolean isThreeDigits() {
		return dividor == 100;
	}

	public boolean isTwoDigits() {
		return dividor == 10;
	}

	public int div() {
		return denominator / dividor;
	}

	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}

	public int getDividor() {
		return dividor;
	}

	public void setDividor(int divisor) {
		this.dividor = divisor;
	}

	public void operateModular() {
		denominator %= dividor;
	}

	public void calculateMaxDiv() {
		int current = 1;
		Integer number = getDenominator();
		for (int i = 0; i < number.toString().length(); i++) {
			current *= STEP_DIVITION;
		}
		setDividor(current);
	}

	public int getDenominatorFirstDigit() {
		String numberTxt = String.valueOf(getDenominator());
		String leterNumber = numberTxt.charAt(0) + "";
		return Integer.parseInt(leterNumber);
	}

	public int getDenominatorFirstDigitWithoutLastDecimal() {
		return getDenominator() / 10 * 10;
	}

}