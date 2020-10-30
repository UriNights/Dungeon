package view;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import helpers.IOFilter;
import services.Dungeon;

public class UserInterface {

	private final Scanner scan;
	private Dungeon dungeon;
	public Object printTopPlayers;

	public UserInterface(Scanner scan) {

		this.scan = scan;
	}
	
	public int printGameMenu() {
		
		System.out.println("\n\t\t*** Dungeon Keeper ***");
		
		for (MainMenu menuText : MainMenu.values()) {
			System.out.println(menuText.getPosition() + ") " + menuText.getMenuText());
		}

		int input;
		do {
			input = IOFilter.toIntIfIsBetween(this.scan.nextLine(), 1, 3);		// return -1 if it's not between, or even it's a number.
		} while (input == -1);
		
		return input;
	}

	public void newGameAdvice() {
		System.out.println("\nGame config:"
				+ "\n");
	}
	
	public String askForNick() {
		System.out.print("   > What's your nick? ");
		return scan.nextLine();
	}

	public int askForWidth() {
		System.out.print("\n   > Give a width for the map (4 - 50): ");
		
		int input;
		do {
			input = IOFilter.toIntIfIsBetween(this.scan.nextLine(), 4, 50);		// return -1 if it's not between, or even it's a number.
		} while (input == -1);
		
		return input;
	}

	public int askForHeight() {
		System.out.print("   > Give a height for the map (4 - 10): ");
		
		int input;
		do {
			input = IOFilter.toIntIfIsBetween(this.scan.nextLine(), 4, 10);		// return -1 if it's not between, or even it's a number.
		} while (input == -1);
		
		return input;
	}

	public int askForDifficulty() {
		System.out.print("   > Give a difficult level, where 1 is the easiest and 10 the hardest (1 - 5): ");
		
		int input;
		do {
			input = IOFilter.toIntIfIsBetween(this.scan.nextLine(), 1, 5);		// return -1 if it's not between, or even it's a number.
		} while (input == -1);
		
		return input;
	}

	public void printGame() {
		System.out.println("\n"
				+ "   - You have " + this.dungeon.getPlayer().getBattery() + " turns before you get out of battery.");
		
		System.out.println(this.dungeon);
	}
	
	public String askForMovement() {
		
		System.out.print("   Next movement > ");
		
		String input;
		do {
			input = scan.nextLine();
		} while (!IOFilter.isMovementInput(input));
			
		return input;
	}
	
	public void movementOutOfBoard() {
		System.out.println("   Careful! You get out of the map!");
	}
	
	public void playerWin() {
		System.out.println(this.dungeon 
				+ "\n\t\t¡¡¡ YOU WIN !!!  :DDD");
	}

	public void playerLose() {
		System.out.println(this.dungeon
				+ "\n\t\t¡ You lose !  :(");
		
	}
	
	public void printTopPlayers(MongoCollection<Document> collectionTopPlayers) {

		System.out.println("\n[TOP PLAYERS]");
		int i = 1;
		for (Document topPlayer : collectionTopPlayers.find()) {
			System.out.println("   " + i + " > " + topPlayer.getString("nick") + " >>> " + topPlayer.getInteger("points") + " points");
			i++;
		}
	}
	
	
	// Getters and Setters

	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
}
