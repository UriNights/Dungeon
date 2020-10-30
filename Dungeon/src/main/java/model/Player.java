package model;

public class Player {

	private String nick;
	private int position;
	private int battery;
	
	public Player(String nick, int batteryConst) {
		this.nick = nick;
		this.position = 0;
		this.battery = 4 * batteryConst;
	}
	
	public void move(int newPosition) {
		this.position = newPosition;	
		this.battery--;
	}
	
	public boolean hasBattery() {
		if (0 < this.battery) return true;
		return false;
	}
	
	
	// Getters and Setters

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public String getNick() {
		return this.nick;
	}
}
