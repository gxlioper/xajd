package xgxt.gygl.gywh;

import java.util.ArrayList;
import java.util.HashMap;

public class DelDetectModel {
	
	private String path;//·��
	
	private boolean bool;//�Ƿ��в���ɾ����
	
	private ArrayList<Object> message;//��ʾ��Ϣ
	
	private String []pkValue;//����

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
