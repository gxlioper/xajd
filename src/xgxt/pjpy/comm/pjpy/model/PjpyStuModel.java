package xgxt.pjpy.comm.pjpy.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PjpyStuModel {

	private Map<String, String> jbxx = null; 			//	基本信息
	
	private List<HashMap<String, String>> cjxx = null;  // 	成绩信息
	
	private List<HashMap<String, String>> djks = null;  //  等级考试
	
	private List<HashMap<String, String>> zcxx = null; 			//	综测信息
	
	public Map<String, String> getJbxx(){
		return jbxx;
	}
	
	public List<HashMap<String, String>> getCjxx(){	
		return cjxx;
	}
	
	public void setJbxx(Map<String, String> jbxx) {
		this.jbxx = jbxx;
	}

	public void setCjxx(List<HashMap<String, String>> cjxx) {
		this.cjxx = cjxx;
	}

	public List<HashMap<String, String>> getZcxx() {
		return zcxx;
	}

	public void setZcxx(List<HashMap<String, String>> zcxx) {
		this.zcxx = zcxx;
	}

	public List<HashMap<String, String>> getDjks() {
		return djks;
	}

	public void setDjks(List<HashMap<String, String>> djks) {
		this.djks = djks;
	}
}
