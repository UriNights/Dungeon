package controller;

public class GameController {

	private final Controller controller;

	public GameController() {
		this.controller = new Controller();
	}

	public void start() {

		boolean newGame;
		
		do {
			newGame = this.controller.gameMenu();
			
			if (newGame) {
				this.newGame();
				while (this.controller.nextTurn());
			}
			
		} while (newGame);
	}

	private void newGame() {
		this.controller.configureGame();
	}
}