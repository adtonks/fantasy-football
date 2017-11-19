package tables;

public class PlayerScores {
	// this object is used as a wrapper for 4 integers, so make them public
	public int GKpts;
	public int DFpts;
	public int MFpts;
	public int FWpts;
	
	PlayerScores(int _GKpts, int _DFpts, int _MFpts, int _FWpts) {
		this.GKpts = _GKpts;
		this.DFpts = _DFpts;
		this.MFpts = _MFpts;
		this.FWpts = _FWpts;
	}
}
