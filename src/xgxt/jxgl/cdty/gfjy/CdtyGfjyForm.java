package xgxt.jxgl.cdty.gfjy;

import org.apache.struts.upload.FormFile;

import xgxt.comm.CommForm;
import xgxt.form.User;

public class CdtyGfjyForm extends CommForm {

	User user=new User();
	
	FormFile checkFilePath;
	
	FormFile impFilePath;
	
	private String[] pkV;//����
	
	private String []xhArr;//ѧ������
	
	private String []kcdmArr;//�γ̴���
	
	private String []njArr;//�꼶
	
	private String[]fsArr;//����
	
	private String kcmc;//�γ�����
	
	// 2013.2.20 qlj �γ̷ֵ���
	private String drnj; // �꼶������ʱѡ��
	
	private String nj;//�꼶


	public String getKcmc() {
		return kcmc;
	}

	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String[] getPkV() {
		return pkV;
	}

	public void setPkV(String[] pkV) {
		this.pkV = pkV;
	}

	public String[] getKcdmArr() {
		return kcdmArr;
	}

	public void setKcdmArr(String[] kcdmArr) {
		this.kcdmArr = kcdmArr;
	}

	public String[] getNjArr() {
		return njArr;
	}

	public void setNjArr(String[] njArr) {
		this.njArr = njArr;
	}

	public String[] getXhArr() {
		return xhArr;
	}

	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}

	public String[] getFsArr() {
		return fsArr;
	}

	public void setFsArr(String[] fsArr) {
		this.fsArr = fsArr;
	}

	public FormFile getCheckFilePath() {
		return checkFilePath;
	}

	public void setCheckFilePath(FormFile checkFilePath) {
		this.checkFilePath = checkFilePath;
	}

	public FormFile getImpFilePath() {
		return impFilePath;
	}

	public void setImpFilePath(FormFile impFilePath) {
		this.impFilePath = impFilePath;
	}

	public String getDrnj() {
		return drnj;
	}

	public void setDrnj(String drnj) {
		this.drnj = drnj;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}
	
	
}
