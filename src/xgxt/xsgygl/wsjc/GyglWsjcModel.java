package xgxt.xsgygl.wsjc;

import java.util.HashMap;
import java.util.List;

import xgxt.utils.Pages;

public class GyglWsjcModel {
	private Pages pages;// '��ҳҳ��';
	
	private String jczq;// '�������';

	private String zgzc;// '�ܹ��ܴ�';

	private String qsrq;// '��ʼ����';

	private String lrxs;// '¼����ʽ';

	private String gldj;// '�����ȼ�';

	private String glfs;// '��������';

	private String jcf;// '������';

	private List<HashMap<String, String>> wsdjList;// '�����ֵȼ��б�';

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
