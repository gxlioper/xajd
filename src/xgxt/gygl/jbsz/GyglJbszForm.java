package xgxt.gygl.jbsz;

import java.util.HashMap;

import xgxt.action.Base;
import xgxt.gygl.GyglCommForm;

public class GyglJbszForm extends GyglCommForm {

	private static final long serialVersionUID = 1L;

	// ================基本设置====================

	private String sfsz = "否";// '是否设置';

	private String czxq = "否";// '存在校区';

	private String czyq = "否";// '存在园区';

	private String csgx = "4";// '从属关系' 4（仅楼栋）;

	private String fpdx = "xy";// '分配对象';

	private String fpfs = "1";// '分配方式';

	public static GyglJbszForm gyglJbszModel;
	
	// ================基本设置 end================

	static {
		
//		gyglJbszModel = new GyglJbszForm();
//
//		GyglJbszService service = new GyglJbszService();
//		HashMap<String, String> map = service.getGyjbsz();
//
//		// 是否已经设置
//		if (!Base.isNull(map.get("fpdx"))) {
//			gyglJbszModel.setSfsz("是");
//		}
//
//		// '存在校区';
//		String czxq = map.get("czxq");
//		if (!Base.isNull(czxq)) {
//			gyglJbszModel.setCzxq(czxq);
//		}
//		// '存在园区';
//		String czyq = map.get("czyq");
//		if (!Base.isNull(czyq)) {
//			gyglJbszModel.setCzyq(czyq);
//		}
//		// '从属关系';
//		String csgx = map.get("csgx");
//		if (!Base.isNull(csgx)) {
//			gyglJbszModel.setCsgx(csgx);
//		}
//		// '分配对象';
//		String fpdx = map.get("fpdx");
//		if (!Base.isNull(fpdx)) {
//			gyglJbszModel.setFpdx(fpdx);
//		}
//		// '分配方式';
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
