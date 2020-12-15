import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

public class Game {
	public int coins;
	public int parsnipSeeds;
	public int parsnips;
	public int yamSeeds;
	public int yams;
	public int cornSeeds;
	public int corn;
	public int tomatoSeeds;
	public int tomatoes;
	public int day;

	public void start(Stage secondaryStage, Stage primaryStage, HighScore hs) {
		coins = 5;
		parsnipSeeds = 0;
		parsnips = 0;
		yamSeeds = 0;
		yams = 0;
		cornSeeds = 0;
		corn = 0;
		tomatoSeeds = 0;
		tomatoes = 0;
		day = 0;

		Parsnip p1 = new Parsnip();
		Parsnip p2 = new Parsnip();
		Parsnip p3 = new Parsnip();
		Yam y1 = new Yam();
		Yam y2 = new Yam();
		Yam y3 = new Yam();
		Corn c1 = new Corn();
		Corn c2 = new Corn();
		Corn c3 = new Corn();
		Tomato t1 = new Tomato();
		Tomato t2 = new Tomato();
		Tomato t3 = new Tomato();

		secondaryStage.setTitle("SFS");

		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setHgap(40);
		root.setVgap(40);

		final Button shop = new Button("Shop");
		shop.setOnAction(e -> shop());
		root.add(shop, 2, 0);

		final Button items = new Button("Items");
		items.setOnAction(e -> items());

		root.add(items, 0, 0);

		final Button menu = new Button("Menu");
		menu.setCancelButton(true);
		menu.setOnAction(e -> {
			primaryStage.show();
			secondaryStage.close();
		});
		root.add(menu, 2, 6);

		// parsnip row
		final Button parsnip1 = new Button("Plant Parsnip");
		parsnip1.setOnAction(e -> parsnipCheck(p1, parsnip1));

		final Button parsnip2 = new Button("Plant Parsnip");
		parsnip2.setOnAction(e -> parsnipCheck(p2, parsnip2));

		final Button parsnip3 = new Button("Plant Parsnip");
		parsnip3.setOnAction(e -> parsnipCheck(p3, parsnip3));

		HBox parsnip = new HBox(40);
		parsnip.setAlignment(Pos.CENTER);
		parsnip.getChildren().addAll(parsnip1, parsnip2, parsnip3);

		root.add(parsnip, 1, 2);

		// yam row
		final Button yam1 = new Button("Plant Yam");
		yam1.setOnAction(e -> yamCheck(y1, yam1));

		final Button yam2 = new Button("Plant Yam");
		yam2.setOnAction(e -> yamCheck(y2, yam2));

		final Button yam3 = new Button("Plant Yam");
		yam3.setOnAction(e -> yamCheck(y3, yam3));

		HBox yam = new HBox(40);
		yam.setAlignment(Pos.CENTER);
		yam.getChildren().addAll(yam1, yam2, yam3);

		root.add(yam, 1, 3);

		// corn row
		final Button corn1 = new Button("Plant Corn");
		corn1.setOnAction(e -> cornCheck(c1, corn1));

		final Button corn2 = new Button("Plant Corn");
		corn2.setOnAction(e -> cornCheck(c2, corn2));

		final Button corn3 = new Button("Plant Corn");
		corn3.setOnAction(e -> cornCheck(c3, corn3));

		HBox corn = new HBox(40);
		corn.setAlignment(Pos.CENTER);
		corn.getChildren().addAll(corn1, corn2, corn3);

		root.add(corn, 1, 4);

		// tomato row
		final Button tomato1 = new Button("Plant Tomato");
		tomato1.setOnAction(e -> tomatoCheck(t1, tomato1));

		final Button tomato2 = new Button("Plant Tomato");
		tomato2.setOnAction(e -> tomatoCheck(t2, tomato2));

		final Button tomato3 = new Button("Plant Tomato");
		tomato3.setOnAction(e -> tomatoCheck(t3, tomato3));

		HBox tomato = new HBox(40);
		tomato.setAlignment(Pos.CENTER);
		tomato.getChildren().addAll(tomato1, tomato2, tomato3);

		root.add(tomato, 1, 5);

		// NextDay
		final Button nextDay = new Button("Next Day");
		nextDay.setDefaultButton(true);
		nextDay.setOnAction(e -> {

			// parsnips
			p1.grow(parsnip1);
			p2.grow(parsnip2);
			p3.grow(parsnip3);
			// yam
			y1.grow(yam1);
			y2.grow(yam2);
			y3.grow(yam3);
			// corn
			c1.grow(corn1);
			c2.grow(corn2);
			c3.grow(corn3);
			// tomato
			t1.grow(tomato1);
			t2.grow(tomato2);
			t3.grow(tomato3);

			if (p1.canHarvest() && p2.canHarvest() && p3.canHarvest() && y1.canHarvest() && y2.canHarvest()
					&& y3.canHarvest() && c1.canHarvest() && c2.canHarvest() && c3.canHarvest() && t1.canHarvest()
					&& t2.canHarvest() && t3.canHarvest()) {
				hs.addHighScore(day);
				primaryStage.show();
				secondaryStage.close();
			}
			day++;
		});
		root.add(nextDay, 0, 6);
		secondaryStage.setScene(new Scene(root, 960, 720, Color.DARKOLIVEGREEN)); // Place the pane in the scene
		secondaryStage.show();
	}

	public void parsnipCheck(Parsnip p, Button b) {
		if (p.isPlanted()) {
			if (p.harvest()) {
				b.setText("Plant Parsnips");
				b.setTextFill(Color.BLACK);
				parsnips++;
			}
		} else {
			if (!p.isPlanted() && parsnipSeeds > 0) {
				p.plant();
				b.setText("Parsnip Crop");
				b.setTextFill(Color.GREEN);
				parsnipSeeds--;
			}
		}
	}

	public void yamCheck(Yam y, Button b) {
		Random rand = new Random();
		if (y.isPlanted()) {
			if (y.harvest()) {
				b.setText("Plant Yam");
				b.setTextFill(Color.BLACK);
				yams = yams + (rand.nextInt((3 - 1) + 1) + 1);
			}
		} else {
			if (!y.isPlanted() && yamSeeds > 0) {
				y.plant();
				b.setText("Yam Crop");
				b.setTextFill(Color.GREEN);
				yamSeeds--;
			}
		}
	}

	public void cornCheck(Corn c, Button b) {
		if (c.isPlanted()) {
			if (c.harvest()) {
				b.setText("Corn Field");
				corn++;
			}
		} else {
			if (!c.isPlanted() && cornSeeds > 0) {
				c.plant();
				b.setText("Corn Field");
				b.setTextFill(Color.GREEN);
				cornSeeds--;
			}
		}
	}

	public void tomatoCheck(Tomato t, Button b) {
		Random rand = new Random();
		if (t.isPlanted()) {
			if (t.harvest()) {
				b.setText("Tomato Crop");
				tomatoes = tomatoes + (rand.nextInt((3 - 2) + 1) + 1);
			}
		} else {
			if (!t.isPlanted() && tomatoSeeds > 0) {
				t.plant();
				b.setText("Tomato Crop");
				b.setTextFill(Color.GREEN);
				tomatoSeeds--;
			}
		}
	}

	public void items() {
		Stage itemStage = new Stage();

		Text coin = new Text("Coins: " + coins);
		Text days = new Text("Day: " + day);
		Text itemP = new Text("Parsnips: " + parsnips);
		Text itemPS = new Text("Parsnip Seeds: " + parsnipSeeds);
		Text itemY = new Text("Yams: " + yams);
		Text itemYS = new Text("Yam Seeds: " + yamSeeds);
		Text itemC = new Text("Corn: " + corn);
		Text itemCS = new Text("Corn Seeds: " + cornSeeds);
		Text itemT = new Text("Tomatoes: " + tomatoes);
		Text itemTS = new Text("Tomato Seeds: " + tomatoSeeds);

		GridPane itemGrid = new GridPane();
		itemGrid.setAlignment(Pos.CENTER);
		itemGrid.setHgap(10);
		itemGrid.setVgap(10);
		itemGrid.add(days, 0, 0);
		itemGrid.add(coin, 1, 0);
		itemGrid.add(itemPS, 0, 1);
		itemGrid.add(itemP, 1, 1);
		itemGrid.add(itemYS, 0, 2);
		itemGrid.add(itemY, 1, 2);
		itemGrid.add(itemCS, 0, 3);
		itemGrid.add(itemC, 1, 3);
		itemGrid.add(itemTS, 0, 4);
		itemGrid.add(itemT, 1, 4);

		itemStage.setScene(new Scene(itemGrid, 200, 200));
		itemStage.show();
	}

	public void shop() {
		Stage shopStage = new Stage();

		Text coin = new Text("coins: " + coins);

		Text buy = new Text("Buy:");

		Button buyP = new Button("Parsnip Seeds");
		buyP.setOnAction(e -> {
			if (coins >= 5) {
				coins -= 5;
				parsnipSeeds++;
				coin.setText("coins: " + coins);
			}
		});

		Button buyY = new Button("Yam Seeds");
		buyY.setOnAction(e -> {
			if (coins >= 10) {
				coins -= 10;
				yamSeeds++;
				coin.setText("coins: " + coins);
			}
		});

		Button buyC = new Button("Corn Seeds");
		buyC.setOnAction(e -> {
			if (coins >= 15) {
				coins -= 15;
				cornSeeds++;
				coin.setText("coins: " + coins);
			}
		});

		Button buyT = new Button("Tomato Seeds");
		buyT.setOnAction(e -> {
			if (coins >= 20) {
				coins -= 20;
				tomatoSeeds++;
				coin.setText("coins: " + coins);
			}
		});

		Text sell = new Text("Sell:");

		Button sellP = new Button("Sell Parsnips");
		sellP.setOnAction(e -> {
			if (parsnips > 0) {
				coins += 8;
				parsnips--;
				coin.setText("coins: " + coins);
			}
		});

		Button sellY = new Button("Sell Yams");
		sellY.setOnAction(e -> {
			if (yams > 0) {
				coins += 6;
				yams--;
				coin.setText("coins: " + coins);
			}
		});

		Button sellC = new Button("Sell Corn");
		sellC.setOnAction(e -> {
			if (corn > 0) {
				coins += 11;
				corn--;
				coin.setText("coins: " + coins);
			}
		});

		Button sellT = new Button("Sell Tomatoes");
		sellT.setOnAction(e -> {
			if (tomatoes > 0) {
				coins += 11;
				tomatoes--;
				coin.setText("coins: " + coins);
			}
		});

		GridPane shopGrid = new GridPane();
		shopGrid.setAlignment(Pos.CENTER);
		shopGrid.setHgap(10);
		shopGrid.setVgap(10);
		shopGrid.add(buy, 0, 1);
		shopGrid.add(coin, 1, 0);
		shopGrid.add(buyP, 0, 2);
		shopGrid.add(buyY, 1, 2);
		shopGrid.add(buyC, 0, 3);
		shopGrid.add(buyT, 1, 3);
		shopGrid.add(sell, 0, 4);
		shopGrid.add(sellP, 0, 5);
		shopGrid.add(sellY, 1, 5);
		shopGrid.add(sellC, 0, 6);
		shopGrid.add(sellT, 1, 6);

		shopStage.setScene(new Scene(shopGrid, 200, 300));
		shopStage.show();
	}
}
