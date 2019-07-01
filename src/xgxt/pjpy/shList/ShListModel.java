
package xgxt.pjpy.shList;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class ShListModel extends Observable {
	private String xxdm;
	private List<HashMap<String, String>> shList;
	private String cond;//²éÑ¯Ìõ¼þ
	private HashMap<String, String[]> shColumns ; 
	
	public HashMap<String, String[]> getShColumns() {
		return shColumns;
	}

	public void setShColumns(HashMap<String, String[]> shColumns) {
		this.shColumns = shColumns;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public List<HashMap<String, String>> getShList() {
		return shList;
	}

	public void setShList(List<HashMap<String, String>> shList) {
		this.shList = shList;
	}

	public String getXxdm() {
		return xxdm;
	}

	public void setXxdm(String xxdm) {
		this.xxdm = xxdm;
		setChanged();
		notifyObservers();
	}
	
}
