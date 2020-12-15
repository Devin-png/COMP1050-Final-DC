
public class HighScore {
public int[] highScore=new int[] {0,0,0,0,0,0,0,0,0,0};

public void addHighScore(int score) {
	for(int x=0;x<highScore.length;x++) {
		if (highScore[x]>score||highScore[x]==0&&score>0) {
			int index=(highScore.length)-1;
			while(index>x) {
				highScore[index]=highScore[index-1];
				index--;
			}
			highScore[x]=score;
			printHighScore();
			break;
		}
		System.out.print(highScore[x]+"/n");
	}
}
public String getHighScore() {
	String highScores="";
	for(int x:highScore) {
		highScores=highScores+x+"\n";
	}
	return highScores;
}
public void printHighScore() {
	for(int x:highScore) {
		System.out.print(x);
	}
}
}
