import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * Button commands:
 * .setText changes what text is displayed on the button
 * .setDefaultButton(boolean) 
 * .isDefaultButton()
 */
public class GUI extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("SFS");
		HighScore hs=new HighScore();
		
		final Button play = new Button("Play");
		play.setDefaultButton(true);
		play.setOnAction(e -> {
			Game SFS=new Game();
			primaryStage.hide();
			SFS.start(new Stage(), primaryStage, hs);
		});
		
		final Button exit = new Button("Exit");
		exit.setCancelButton(true);
		primaryStage.setOnCloseRequest(e -> exit(primaryStage));
		exit.setOnAction(e -> exit(primaryStage));
		
		final Button highScore = new Button("HighScores");
		
		final Button help = new Button("Help");
		
		final Button credits = new Button("Credits");
		
		final GridPane root = new GridPane(); // Forms the root of the nodes, organize vertically
		root.setAlignment(Pos.CENTER);
		root.setHgap(240);
		root.setVgap(40);
		
		Text title=new Text("Super Farming Simulator!");
		title.setFont(Font.font("Verdana",20));
		title.setFill(Color.GREEN);
		root.add(title, 0, 0);
		
		VBox btn=new VBox(10);
		btn.setAlignment(Pos.TOP_LEFT);
		btn.getChildren().addAll(play,help,highScore,credits,exit);
		btn.setSpacing(90);
		root.add(btn,0,1); // Add the button to the root
		
		//used as decoration to showcase desc
		Rectangle rect=new Rectangle(360,480,Color.rgb(204,170,130));
		rect.setArcHeight(20);
		rect.setArcWidth(20);
		root.add(rect, 1, 1);
		
		//desc will be changed depending on what button the user presses
		Text desc=new Text("Welcome!");
		VBox text=new VBox(10);
		text.setAlignment(Pos.TOP_LEFT);
		text.setMargin(desc,new Insets(20,0,0,20));
		text.getChildren().add(desc);
		root.add(text, 1, 1);
		
		//actions for buttons that change desc
		highScore.setOnAction(e -> desc.setText(highScore(hs)));
		help.setOnAction(e -> desc.setText(help()));
		credits.setOnAction(e -> desc.setText(credits()));

		primaryStage.setScene(new Scene(root, 960, 720)); // Place the pane in the scene
		primaryStage.show();
	}

	private String help() {
		return "In the game Super Farming Simulator, you can buy, sell, plant, \nand grow crops. The goal of the game is to unlock all crops \nunlocked and have them grown in each slot in the shortest \namount of days. Good luck and have fun!";
	}
	
	private String highScore(HighScore hs) {
		if(hs.highScore[0]==0) {
			return "There are no scores yet. Set the record!";
		}
		if(hs.highScore[0]!=0) {
			return hs.getHighScore();
		}
		return "Error";
	}
	
	private String credits() {
		return "A program by Devin Coles";
	}


	private void exit(Stage stage) {
		System.out.println("Program Successfully Saved");
		stage.close();
	}
}
