package xgxt.xtwh.zpgl;

import org.apache.struts.upload.FormFile;

import xgxt.comm.CommForm;

public class XtwhZpglForm extends CommForm  {
	
	FormFile file;
	
	private String sfdrzp;//�Ƿ�����Ƭ
	
	private String sfzh;//���֤��
	
	private String ksh;//������
	
	private String photoNameType;//ѡ������
	
	private String bmlx;//��������
	
	private String sfdrxszp;//�Ƿ���������Ƭ
	
	private String zpType;//��������Ƭ����

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
