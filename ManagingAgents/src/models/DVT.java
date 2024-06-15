package models;

public class DVT {
	public int SL_DVT;

	public DVT() {
		super();
	}

	public DVT(int sL_DVT) {
		super();
		SL_DVT = sL_DVT;
	}

	public int getSL_DVT() {
		return SL_DVT;
	}

	public void setSL_DVT(int sL_DVT) {
		SL_DVT = sL_DVT;
	}

	@Override
	public String toString() {
		return "DVT [SL_DVT=" + SL_DVT + "]";
	}
	
}
