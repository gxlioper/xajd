package xgxt.pjpy.comm.pjpy.jbsz;

import xgxt.pjpy.comm.pjpy.PjpyCommForm;

public class PjpyJbszForm extends PjpyCommForm {

	private static final long serialVersionUID = 1L;

	private String rsszfs;// �������÷�ʽ

	private String[] tjdm;// ��������

	public String[] getTjdm() {
		return tjdm;
	}

	public void setTjdm(String[] tjdm) {
		this.tjdm = tjdm;
	}

	public String getRsszfs() {
		return rsszfs;
	}

	public void setRsszfs(String rsszfs) {
		this.rsszfs = rsszfs;
	}
}
