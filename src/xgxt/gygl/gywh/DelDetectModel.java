package xgxt.gygl.gywh;

import java.util.ArrayList;
import java.util.HashMap;

public class DelDetectModel {
	
	private String path;//路径
	
	private boolean bool;//是否有不可删数据
	
	private ArrayList<Object> message;//提示信息
	
	private String []pkValue;//主键

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String[] getPkValue() {
		return pkValue;
	}

	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}

	public ArrayList<Object> getMessage() {
		return message;
	}

	public void setMessage(ArrayList<Object> message) {
		this.message = message;
	}
}
