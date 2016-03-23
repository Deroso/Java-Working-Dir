package src;
import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application{
	
	public static void main(String[] args){
		Application.launch(args);
	}
	
	static public GamePiece[][] pieceArray = new GamePiece[7][7];
	public ImageView[][] gridArray = new ImageView[7][7];
	static Random r = new Random();
	public static int rand;
	//Pane declaration
	HBox hbox = new HBox();
	VBox vbox = new VBox();
	StackPane stack = new StackPane();
	ImageView back = new ImageView(new Image("background.png"));
	static GridPane gPane = new GridPane();
	StackPane sPane = new StackPane();
	static GamePiece[] array = new GamePiece[4];
	static int score = 60001;
	Label timer = new Label("Timer Timey"); 
	static Label scoreDisplay = new Label("Score : 10500");
	
	
	public void start(Stage primaryStage) throws Exception {
		
		//Pane property modifications
		gPane.setAlignment(Pos.CENTER);
		gPane.setPadding(new Insets(10, 10, 10, 10));
		hbox.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);
		gPane.setHgap(10);
		gPane.setVgap(10);
		
		//Label and image declaration
		
		
		//GamePiece object declaration
		GamePiece red = new GamePiece("red", 0, 1000, new Image("red.png"));
		GamePiece green = new GamePiece("green", 1, 2000, new Image("green.png"));
		GamePiece blue = new GamePiece("blue" , 2, 3000, new Image("blue.png"));
		GamePiece purple = new GamePiece("purple", 3, 4000, new Image("purple.png"));
		
		//GamePiece object array declaration
		
		array[0] = red;
		array[1] = green;
		array[2] = blue;
		array[3] = purple;
		
		Rectangle rect = new Rectangle(50, 50);
		
		//adding nodes to the panes
		stack.getChildren().addAll(back, gPane);
		vbox.getChildren().addAll(new Timer(), scoreDisplay);
		sPane.getChildren().add(rect);
		hbox.getChildren().addAll(vbox, stack);
		
		//START HERE
		//Create a border pane
		HBox pane = new HBox();
		pane.setSpacing(20);
	    pane.setAlignment(Pos.CENTER);
	    
		VBox mainMenu = new VBox();
		mainMenu.setSpacing(50);
		mainMenu.setAlignment(Pos.CENTER);
		
		//Create a buttons
		 Button start = new Button("Start");
		    start.setAlignment(Pos.CENTER);//Place nodes in the pane
		    start.setFont(Font.font("Gabriola", FontWeight.BOLD, 40));
		    start.setOnAction(e -> {
		    	pane.getChildren().clear();
		    	/*pane.setStyle("-fx-background-color:#00FFFF;");*/
		    	boardGen(array, gPane, pieceArray);
		    	//game();
		    }  );
		    
		 Button leader = new Button("Leader Board");
		   leader.setAlignment(Pos.CENTER);//Place nodes in the pane
		    leader.setFont(Font.font("Gabriola", FontWeight.BOLD, 40));
		    leader.setOnAction(e -> {
		    	pane.getChildren().clear();
		    	BackgroundImage myBI2= new BackgroundImage(new Image("/images/space-bckgrd.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
				pane.setBackground(new Background(myBI2));
		    	//leader();
		    }  );
		    
		 Button exit = new Button("Exit");
		 	exit.setAlignment(Pos.CENTER);//Place nodes in the pane
			exit.setFont(Font.font("Gabriola", FontWeight.BOLD, 40));
			exit.setOnAction(e -> {
				System.exit(0);
			    }  );
		
		mainMenu.getChildren().addAll(start,leader,exit);
			BackgroundImage myBI= new BackgroundImage(new Image("/images/space-bckgrd2.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
			pane.setBackground(new Background(myBI));
					
		pane.getChildren().addAll(mainMenu);
		
		//Create a scene and place it in the stage
	Scene menu = new Scene(pane);
	primaryStage.setMaximized(true);
	primaryStage.setTitle("Main Menu");//Set the stage title
	primaryStage.setScene(menu);
	start.setTooltip(curser());
	primaryStage.show();
		//END HERE
		
	/*
	 
		//Scene declaration
		Scene scene2 = new Scene(sPane, 500, 500);
		Scene scene = new Scene(hbox, 400, 400);
		
		//Stage declaration
		Stage stage2 = new Stage();
		stage2.setTitle("Start Screen");
		stage2.setScene(scene2);
		stage2.show();
		
		
		//events
		//START MENU EVETNS
		rect.setOnMouseClicked(e ->{
			boardGen(array, gPane, pieceArray);
			primaryStage.show();
		});
		
		primaryStage.setTitle("This is a title");
		primaryStage.setScene(scene);
		
		// This will clear the grid pane when the window is closed. This makes regenerating the board easier.
		primaryStage.setOnCloseRequest(w -> {
			gPane.getChildren().clear();
		});
		*/
	}

	public static void boardGen(GamePiece[] array, GridPane gPane, GamePiece[][] pieceArray){
		
		//generation array
		for(int i = 0; i < 7 ; i++){
			for(int j = 0; j < 7; j++){
				rand = r.nextInt(4);
				
				// Because GamePiece extends ImageView, we can place use the GamePiece array
				// to directly display the objects in the GridPane.
				pieceArray[i][j] = new GamePiece(array[rand].getName(), array[rand].getType(), array[rand].getValue(), array[rand].getImage());
				
				// These set the iIndex and jIndex values inside the objects when they are first created.
				pieceArray[i][j].setiIndex(i);
				pieceArray[i][j].setjIndex(j);
				
				// Adds the GamePiece to the array.
				FadeTransition fade = new FadeTransition(Duration.millis(1000), pieceArray[i][j]);
				fade.setFromValue(0.1);
				fade.setToValue(1);
				fade.setCycleCount(1);
				gPane.add(pieceArray[i][j], i, j);
				fade.play();
			}
		}
	}
	
	
	public static void delete(int i, int j){
		GamePiece temp = pieceArray[i][j];
			rand = r.nextInt(4);
			while(rand == pieceArray[i][j].getType()){
				rand = r.nextInt(4);
			}
			score += temp.getValue();
			scoreDisplay.setText("" + score);
			pieceArray[i][j] = null;
			pieceArray[i][j] = new GamePiece(array[rand].getName(), array[rand].getType(), array[rand].getValue(), array[rand].getImage());
			pieceArray[i][j].setiIndex(i);
			pieceArray[i][j].setjIndex(j);
			FadeTransition fade2 = new FadeTransition(Duration.millis(1000), pieceArray[i][j]);
			fade2.setFromValue(0.1);
			fade2.setToValue(1);
			fade2.setCycleCount(1);
			gPane.getChildren().remove(temp);						
			gPane.add(pieceArray[i][j], i, j);
			fade2.play();
	}
	
	public static void downPoints(){
		score-=1000;
		scoreDisplay.setText("" + score);
	}
	  static Tooltip curser(){
		  Tooltip tooltip = new Tooltip();
		  tooltip.setText("test");
		  return tooltip;

		  }
	
}

class Timer extends Label {
	//This is the amount of time before the game ends
	int sec = 60;
	  public Timer() {
		  
		  Timeline timeline = new Timeline(
			      new KeyFrame(Duration.seconds(0),
			        new EventHandler<ActionEvent>() {
			          @Override public void handle(ActionEvent actionEvent) {
			        	  // decrements the timer by 1
			        	  sec--;
			        	  Game.downPoints();
			        	  setText(sec + "");
			          }
			        }
			      ),
			      new KeyFrame(Duration.millis(1000))	// This is the amount of time before it reiterates
			    );
			    timeline.setCycleCount(60);		// number of cycles to repeat
			    timeline.play();
	  }

}