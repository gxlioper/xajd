package xgxt.jxgl.cdty.gfjy;

import org.apache.struts.upload.FormFile;

import xgxt.comm.CommForm;
import xgxt.form.User;

public class CdtyGfjyForm extends CommForm {

	User user=new User();
	
	FormFile checkFilePath;
	
	FormFile impFilePath;
	
	private String[] pkV;//主键
	
	private String []xhArr;//学号数组
	
	private String []kcdmArr;//课程代码
	
	private String []njArr;//年级
	
	private String[]fsArr;//分数
	
	private String kcmc;//课程名称
	
	// 2013.2.20 qlj 课程分导入
	private String drnj; // 年级（导入时选择）
	
	private String nj;//年级


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
