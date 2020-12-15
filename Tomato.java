import javafx.scene.control.Button;

public class Tomato extends Plant {
	public void grow(Button b) {
		if(planted) {
			day++;
			if(day>=6) {
				b.setText("Harvest");
			}
		}
	}
	public boolean harvest() {
		if(day>=6) {
			day=2;
			return true;
		}
		else
			return false;
	}
	public boolean canHarvest() {
		if(day>=6) {
			return true;
		}
		else
			return false;
	}
}
