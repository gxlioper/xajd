package xgxt.gygl.jbsz;

import java.util.HashMap;

import xgxt.action.Base;
import xgxt.gygl.GyglCommForm;

public class GyglJbszForm extends GyglCommForm {

	private static final long serialVersionUID = 1L;

	// ================��������====================

	private String sfsz = "��";// '�Ƿ�����';

	private String czxq = "��";// '����У��';

	private String czyq = "��";// '����԰��';

	private String csgx = "4";// '������ϵ' 4����¥����;

	private String fpdx = "xy";// '�������';

	private String fpfs = "1";// '���䷽ʽ';

	public static GyglJbszForm gyglJbszModel;
	
	// ================�������� end================

	static {
		
//		gyglJbszModel = new GyglJbszForm();
//
//		GyglJbszService service = new GyglJbszService();
//		HashMap<String, String> map = service.getGyjbsz();
//
//		// �Ƿ��Ѿ�����
//		if (!Base.isNull(map.get("fpdx"))) {
//			gyglJbszModel.setSfsz("��");
//		}
//
//		// '����У��';
//		String czxq = map.get("czxq");
//		if (!Base.isNull(czxq)) {
//			gyglJbszModel.setCzxq(czxq);
//		}
//		// '����԰��';
//		String czyq = map.get("czyq");
//		if (!Base.isNull(czyq)) {
//			gyglJbszModel.setCzyq(czyq);
//		}
//		// '������ϵ';
//		String csgx = map.get("csgx");
//		if (!Base.isNull(csgx)) {
//			gyglJbszModel.setCsgx(csgx);
//		}
//		// '�������';
//		String fpdx = map.get("fpdx");
//		if (!Base.isNull(fpdx)) {
//			gyglJbszModel.setFpdx(fpdx);
//		}
//		// '���䷽ʽ';
//		String fpfs = map.get("fpfs");
//		if (!Base.isNull(fpfs)) {
//			gyglJbszModel.setFpfs(fpfs);
//		}
	}
	
	public String getCsgx() {
		return csgx;
	}

	public void setCsgx(String csgx) {
		this.csgx = csgx;
	}

	public String getCzxq() {
		return czxq;
	}

	public void setCzxq(String czxq) {
		this.czxq = czxq;
	}

	public String getCzyq() {
		return czyq;
	}

	public void setCzyq(String czyq) {
		this.czyq = czyq;
	}

	public String getFpdx() {
		return fpdx;
	}

	public void setFpdx(String fpdx) {
		this.fpdx = fpdx;
	}

	public String getSfsz() {
		return sfsz;
	}

	public void setSfsz(String sfsz) {
		this.sfsz = sfsz;
	}

	public String getFpfs() {
		return fpfs;
	}

	public void setFpfs(String fpfs) {
		this.fpfs = fpfs;
	}
}
