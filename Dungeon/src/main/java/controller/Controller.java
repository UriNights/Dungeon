package controller;

import java.util.Scanner;

import services.Dungeon;
import view.UserInterface;

public class Controller {

	private final Scanner scan;
	private final DBController dbController;
	private final UserInterface userInterface;
	private Dungeon dungeon;
	
	private static final int NEW_GAME = 1, TOP_PLAYERS = 2, QUIT = 3;	// Game menu
	private static final int LOSE = -1, IN_GAME = 0, WIN = 3;	// Game states

	public Controller() {

		this.dbController = new DBController();
		this.scan = new Scanner(System.in);
		this.userInterface = new UserInterface(scan);
		
		scan.nextLine();	// Comença el joc amb un enter. Per ocultar la conexió a la base de dades
	}

	public boolean gameMenu() {

		while (true) {
			int menuOption = this.userInterface.printGameMenu();

			switch (menuOption) {
			case NEW_GAME:
				return true;
			case TOP_PLAYERS:
				this.userInterface.printTopPlayers(this.dbController.getTopPlayers());
				break;
			case QUIT:
				scan.close();
				dbController.close();
				return false; // Quit
			}
		}
	}

	public void configureGame() {

		userInterface.newGameAdvice();

		String nick = this.userInterface.askForNick();
		int width = this.userInterface.askForWidth();
		int height = this.userInterface.askForHeight();
		int difficulty = this.userInterface.askForDifficulty();

		this.dungeon = new Dungeon(nick, width, height, difficulty);
		this.userInterface.setDungeon(this.dungeon);
	}

	public boolean nextTurn() {

		userInterface.printGame();

		while (!dungeon.movePlayerAndKill(userInterface.askForMovement())) {
			userInterface.movementOutOfBoard();
		}

		dungeon.moveVampires();

		int gameState = dungeon.gameState();

		if (gameState == IN_GAME)
			return true;

		if (gameState == WIN)
			this.dbController.saveGame(dungeon.getPlayer().getNick(), dungeon.getPoints());
			userInterface.playerWin();
		if (gameState == LOSE)
			userInterface.playerLose();
		
		this.userInterface.printTopPlayers(this.dbController.getTopPlayers());
		return false;
	}
}
