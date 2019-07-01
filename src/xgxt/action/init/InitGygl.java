package xgxt.action.init;

import xgxt.action.Base;
import xgxt.comm.xml.XMLReader;

/**
 * <p>
 * ��Ԣ�����ʼ��
 * </p>
 * 
 * @author qlj
 */
public class InitGygl {

	private String cs;// ����

	private String cws;// ��λ��

	private String bhgz;// �Զ��������ұ�Ź���

	public static InitGygl initGygl = new InitGygl();

	static {

		initGygl.setCs(initGygl.getFlowCs());
		initGygl.setCws(initGygl.getFlowCws());
		initGygl.setBhgz(initGygl.getFlowBhgz());
	}

	public String getCs() {
		return cs;
	}

	public String getCws() {
		return cws;
	}

	public String getFlowCs() {

		if (Base.isNull(cs)) {

			cs = XMLReader.getFlowControl("gygl", "cs");

		}

		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public String getFlowCws() {
		if (Base.isNull(cws)) {

			cws = XMLReader.getFlowControl("gygl", "cws");

		}
		return cws;
	}

	public void setCws(String cws) {
		this.cws = cws;
	}

	public String getFlowBhgz() {
		if (Base.isNull(bhgz)) {

			bhgz = XMLReader.getFlowControl("gygl", "bhgz");

		}
		return bhgz;
	}

	public String getBhgz() {
		return bhgz;
	}

	public void setBhgz(String bhgz) {
		this.bhgz = bhgz;
	}
}
