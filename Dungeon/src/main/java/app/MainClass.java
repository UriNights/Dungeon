package app;

import controller.GameController;

public class MainClass {

	public static void main(String[] args) {

		GameController gameController = new GameController();
		gameController.start();
		
		System.exit(0);		
	}
}