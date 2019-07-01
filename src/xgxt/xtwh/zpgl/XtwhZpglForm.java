package xgxt.xtwh.zpgl;

import org.apache.struts.upload.FormFile;

import xgxt.comm.CommForm;

public class XtwhZpglForm extends CommForm  {
	
	FormFile file;
	
	private String sfdrzp;//是否导入照片
	
	private String sfzh;//身份证号
	
	private String ksh;//考生号
	
	private String photoNameType;//选择类型
	
	private String bmlx;//部门类型
	
	private String sfdrxszp;//是否导入新生照片
	
	private String zpType;//操作的照片类型

	public String getBmlx() {
		return bmlx;
	}

	public void setBmlx(String bmlx) {
		this.bmlx = bmlx;
	}

	public String getPhotoNameType() {
		return photoNameType;
	}

	public void setPhotoNameType(String photoNameType) {
		this.photoNameType = photoNameType;
	}

	public String getKsh() {
		return ksh;
	}

	public void setKsh(String ksh) {
		this.ksh = ksh;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getSfdrzp() {
		return sfdrzp;
	}

	public void setSfdrzp(String sfdrzp) {
		this.sfdrzp = sfdrzp;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public String getSfdrxszp() {
		return sfdrxszp;
	}

	public void setSfdrxszp(String sfdrxszp) {
		this.sfdrxszp = sfdrxszp;
	}

	public String getZpType() {
		return zpType;
	}

	public void setZpType(String zpType) {
		this.zpType = zpType;
	}
}
