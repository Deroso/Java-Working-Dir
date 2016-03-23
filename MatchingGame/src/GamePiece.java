package src;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GamePiece extends ImageView{

	private int type;
	private int value;
	private String name;
	private Boolean powerUp;
	private int iIndex;
	private int jIndex;
	private String id;
	
	
	public GamePiece() {
		type = 0;
		value = 1000;
		name = "Red";	
		this.setImage(new Image("red.png"));
		powerUp = false;
		this.setOnMouseClicked(m -> {	
			System.out.println("i : " + this.getiIndex() + ", j : " + this.getjIndex());
		});
	}
													// *** - Converted String to Image
	public GamePiece(String name, int type, int value, Image image){
		this.type = type;
		this.value = value;
		this.name = name;
		this.setImage(image);
		powerUp = false;
		this.setOnMouseClicked(m -> {	//*** - This is the event listener that gets and outputs the i and j indices.
			System.out.println("i : " + this.getiIndex() + ", j : " + this.getjIndex());
			Game.delete(this.getiIndex(), this.getjIndex());
		});
	}
	
	
	public GamePiece(int type, int value, String name, Image image, Boolean powerUp){
		this.type = type;
		this.value = value;
		this.name = name;
		this.setImage(image);
		this.powerUp = powerUp;
	}
	
	//Getters and setters
	public int getType() {
		return type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}
	
	
	public Boolean getPowerUp() {
		return powerUp;
	}
	
	public void setPowerUp(Boolean powerUp) {
		this.powerUp = powerUp;
	}
	
	public void setiIndex(int iIndex){
		this.iIndex = iIndex;
	}
	
	public int getiIndex(){
		return iIndex;
	}
	
	public void setjIndex(int jIndex){
		this.jIndex = jIndex;
	}
	
	public int getjIndex(){
		return jIndex;
	}

}
