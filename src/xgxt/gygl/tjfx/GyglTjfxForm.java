package xgxt.gygl.tjfx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.gygl.GyglCommForm;
import xgxt.utils.Pages;

public class GyglTjfxForm extends GyglCommForm {
	
	private static final long serialVersionUID = 1L;
	
	private String[] checkVal;
	
	private String mklx;		//ģ������
	
	private String czxq;//����У��
	
	private String czyq;//����԰��

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

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getMklx() {
		return mklx;
	}

	public void setMklx(String mklx) {
		this.mklx = mklx;
	}
	
}
