package server;

/**
 * This object is just used as a wrapper for 4 integers
 * @author adtonks
 *
 */
class PlayerScores {
	// this object is just used as a wrapper for 4 integers
	public int GKpts;
	public int DFpts;
	public int MFpts;
	public int FWpts;
	
	/**
	 * Takes all of the arguments as parameters
	 * @param _GKpts, goalie points
	 * @param _DFpts, defenders points
	 * @param _MFpts, midfielder points
	 * @param _FWpts, forward points
	 */
	PlayerScores(int _GKpts, int _DFpts, int _MFpts, int _FWpts) {
		this.GKpts = _GKpts;
		this.DFpts = _DFpts;
		this.MFpts = _MFpts;
		this.FWpts = _FWpts;
	}
}
