import javafx.scene.control.Button;

public class Corn extends Plant {

	public void grow(Button b) {
		if(planted) {
			day++;
			if(day>=4) {
				b.setText("Harvest");
			}
		}
	}
	public boolean harvest() {
		if(day>=4) {
			day=1;
			return true;
		}
		else
			return false;
	}
	public boolean canHarvest() {
		if(day>=4) {
			return true;
		}
		else
			return false;
	}
}
