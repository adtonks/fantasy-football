package clientObjects;

/**
 * A class to represent Users and their Points
 * @author adamtonks
 *
 */
public class UserPoints {
	// this object is just a wrapper
	public String username;
	public int points;
	
	public UserPoints(String _username, int _points) {
		this.username = _username;
		this.points = _points;
	}
}
