import javafx.scene.control.Button;

public class Plant {
	public int day=0;
	public boolean planted=false;
	
	public void plant() {
		day=0;
		planted=true;
	}
	
	public boolean isPlanted() {
		return planted;
	}
	
	public void grow(Button b) {
		if(planted) {
			day++;
			if(day>=3) {
				b.setText("Harvest");
			}
		}
	}
	public boolean harvest() {
		if(day>=3) {
			planted=false;
			return true;
		}
		else
			return false;
	}
	public boolean canHarvest() {
		if(day>=3) {
			return true;
		}
		else
			return false;
	}
}
