package xsgzgl.xtwh.general.flow;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_�����_ͨ��_Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XtwhFlowModel {

	private String yhm;// �û���
	private String zdm;// �����
	private String gwdm;// ��λ����
	private String current;// ��ǰҳ
	private String init;// ��ʼ��
	private String sfbzr;
	private String sffdy;
	private String yhszlx;

	private String[] spyh;// �����û�

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String[] getSpyh() {
		return spyh;
	}

	public void setSpyh(String[] spyh) {
		this.spyh = spyh;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getInit() {
		return init;
	}

	public void setInit(String init) {
		this.init = init;
	}

	public String getZdm() {
		return zdm;
	}

	public String getGwdm() {
		return gwdm;
	}

	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public void setSfbzr(String sfbzr) {
		this.sfbzr = sfbzr;
	}

	public String getSfbzr() {
		return sfbzr;
	}

	public void setSffdy(String sffdy) {
		this.sffdy = sffdy;
	}

	public String getSffdy() {
		return sffdy;
	}

	public String getYhszlx() {
		return yhszlx;
	}

	public void setYhszlx(String yhszlx) {
		this.yhszlx = yhszlx;
	}

}
