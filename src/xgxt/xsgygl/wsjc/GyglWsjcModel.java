package xgxt.xsgygl.wsjc;

import java.util.HashMap;
import java.util.List;

import xgxt.utils.Pages;

public class GyglWsjcModel {
	private Pages pages;// '分页页面';
	
	private String jczq;// '检查周期';

	private String zgzc;// '总共周次';

	private String qsrq;// '起始日期';

	private String lrxs;// '录入形式';

	private String gldj;// '关联等级';

	private String glfs;// '关联分数';

	private String jcf;// '基础分';

	private List<HashMap<String, String>> wsdjList;// '卫生分等级列表';

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getGldj() {
		return gldj;
	}

	public void setGldj(String gldj) {
		this.gldj = gldj;
	}

	public String getGlfs() {
		return glfs;
	}

	public void setGlfs(String glfs) {
		this.glfs = glfs;
	}

	public String getJcf() {
		return jcf;
	}

	public void setJcf(String jcf) {
		this.jcf = jcf;
	}

	public String getJczq() {
		return jczq;
	}

	public void setJczq(String jczq) {
		this.jczq = jczq;
	}

	public String getLrxs() {
		return lrxs;
	}

	public void setLrxs(String lrxs) {
		this.lrxs = lrxs;
	}

	public String getQsrq() {
		return qsrq;
	}

	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}

	public String getZgzc() {
		return zgzc;
	}

	public void setZgzc(String zgzc) {
		this.zgzc = zgzc;
	}

	public List<HashMap<String, String>> getWsdjList() {
		return wsdjList;
	}

	public void setWsdjList(List<HashMap<String, String>> wsdjList) {
		this.wsdjList = wsdjList;
	}
}
