package utilities;

import helpers.RandomOutput;

public class Mover {

	public static int newPositionWithChar(int position, String commands, int width, int height) {
		
		boolean outOfBoard = false;
		int newPosition = position;
		for (char keyInput : commands.toCharArray()) {

			switch (keyInput) {

			case 'w':
				newPosition -= width;
				if (newPosition < 0) outOfBoard = true;
				break;

			case 's':
				newPosition += width;
				if ((width * height) - 1 < newPosition) outOfBoard = true;
				break;

			case 'a':
				if (newPosition == 0 || width - 1 < newPosition && newPosition % width == 0) outOfBoard = true;
				newPosition--;
				break;

			case 'd':
				if (newPosition == width - 1 || width - 1 < newPosition && newPosition % width == width - 1) outOfBoard = true;
				newPosition++;
				break;

			default:
				break;
			}
		}
		
		if (outOfBoard) return -1;
		return newPosition;
	}

	public static int newRandomPosition(int position, int width, int height) {

		int movement = RandomOutput.randomIntFromZeroTo(5);		// 0,1,2,3 means movement. Higher numbers than those, means no movement.
		if (3 < movement) return -1;
		
		int newPosition = position;
		boolean outOfBoard = false;
		switch (movement) {

		case 0:
			newPosition -= width;
			if (newPosition < 0) outOfBoard = true;
			break;

		case 1:
			newPosition += width;
			if ((width * height) - 1 < newPosition) outOfBoard = true;
			break;

		case 2:
			if (newPosition == 0 || width - 1 < newPosition && newPosition % width == 0) outOfBoard = true;
			newPosition--;
			break;

		case 3:
			if (newPosition == width - 1 || width - 1 < newPosition && newPosition % width == width - 1) outOfBoard = true;
			newPosition++;
			break;

		default:
			break;
		}
		
		if (outOfBoard) return -1;
		return newPosition;
	}
}
