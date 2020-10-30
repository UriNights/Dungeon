package view;

public enum MainMenu {

	NEW_GAME("New game", 1),
	TOP_PLAYERS("Top players", 2),
	QUIT("Quit", 3);

	private String menuText;
	private int position;
	
	private MainMenu(String menuText, int position) {
		this.menuText = menuText;
		this.position = position;
	}

	public String getMenuText() {
		return menuText;
	}

	public int getPosition() {
		return position;
	}
}
