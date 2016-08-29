//This class is Player.Firstly,initializes the specific features of player.
public class Player {
	//Five variables are defined.
	private String name;
	private String winPoint;
	private String failPoint;
	private String time;
	private String date;
	private boolean isSaved; //whether player score save or not
	
	//Player constructor is occurred with five parameters.
 	public Player(String name,String winPoint, String failPoint,String time,String date){
		this.name=name;
		this.winPoint=winPoint;
		this.failPoint=failPoint;
		this.time=time;
		this.date=date;
	}
	//Creates getter and setter methods.
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWinPoint() {
		return winPoint;
	}
	public void setWinPoint(String winPoint) {
		this.winPoint = winPoint;
	}
	public String getFailPoint() {
		return failPoint;
	}
	public void setFailPoint(String failPoint) {
		this.failPoint = failPoint;
	}
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	public boolean getIsSaved() {
		return isSaved;
	}
	
	public void setIsSaved(boolean saved) {
		isSaved=saved;
	}	
	
}
