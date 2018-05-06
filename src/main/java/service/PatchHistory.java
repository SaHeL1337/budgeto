package service;

import java.util.ArrayList;
import java.util.List;

public class PatchHistory {

	private static PatchHistory INSTANCE;
	private List<Patch> history;

	public List<Patch> getHistory() {
		return history;
	}

	public void addPatchToHistory(Patch p_patch) {
		history.add(p_patch);
	}
	
	public PatchHistory() {
		history = new ArrayList<Patch>();
	}
	
	public Patch getPatchById(int p_id) {
		for(Patch patch : history) {
			if(patch.getID() == p_id) return patch;
		}
		return null;
	}
	
	public static PatchHistory getInstance(){
		if(INSTANCE == null) {
			return new PatchHistory();
		}else {
			return INSTANCE;
		}
	}
}
