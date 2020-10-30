package helpers;

public class IOFilter {

	public static int toIntIfIsBetween(String input, int minNumber, int maxNumber) {
		
		if (input.matches("[0-9]+")) {
			
			int value = Integer.parseInt(input);
			if (minNumber <= value && value <= maxNumber) return value;
		}
		
		System.out.print("   Please, enter a valid input: ");
		return -1;
	}

	public static boolean isMovementInput(String input) {
		
		if (input.matches("[wasd]+")) {
			return true;
		}
		
		System.out.print("   Please, enter a valid input: ");
		return false;
	}

	public static boolean isYesOrNot(String input) {
		if (input.matches("[yn]")) {
			return true;
		}
		
		System.out.print("   Please, enter a valid input: ");
		return false;
	}
}
