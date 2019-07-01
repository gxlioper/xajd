package xgxt.xszz.whtl.ybbx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;

public class XszzYbbxSaveModel {
	
	public XszzYbbxSaveModel(){}
	
	public  XszzYbbxSaveModel(HttpServletRequest request,User user,BasicModel basicModel){
		
		XszzYbbxService service = new XszzYbbxService();

		// �����ֶ�
		String []save={"xn","xh"};
		// �����ֶ�
		String []pk={"xn","xh"};
		// ���������ֶ�
		String []oneZd={"xn","xh","czr"};
		// ���������ֶ�
		String []arrayZd={"bxje","mzyy","wzsj","ylyt"};
		
		basicModel.setPk(pk);
	
		service.getModelValue(this, request);
		
		// ---------------------�̶��ı���ֵ begin------------
		this.setCzr(user.getUserName());
	
		this.setXn(Base.currXn);
		
		// ---------------------�̶��ı���ֵ end--------------
		basicModel.setPkValue(this.getPkValue());
		
		basicModel.setOneZd(oneZd);
		
		basicModel.setArrayZd(arrayZd);
		
		// --------------����------------
		basicModel.setTableName("xg_xszz_ybbxxxb");
		// --------------��Ҫ�����ֵ--------------------
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
		
	}
	
	private String xh;// ѧ��
	
	private String xn;// ѧ��
	
	private String czr;// ������
	
	private String czsj;// ����ʱ��
	
	private String[]pkValue;// ����

	private String[]jlid;// ��¼���
	
	private String[]bxje;// ���ս��
	
	private String[]mzyy;// ����ҽԺ
	
	private String[]wzsj;// ����ʱ��
	
	private String[]ylyt;// ҽ����;
	
	private String[]shje;// ��˽��

	public String[] getBxje() {
		return bxje;
	}

	public void setBxje(String[] bxje) {
		this.bxje = bxje;
	}

	public String getCzr() {
		return czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}

	public String getCzsj() {
		return czsj;
	}

	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}

	public String[] getJlid() {
		return jlid;
	}

	public void setJlid(String[] jlid) {
		this.jlid = jlid;
	}

	public String[] getMzyy() {
		return mzyy;
	}

	public void setMzyy(String[] mzyy) {
		this.mzyy = mzyy;
	}

	public String[] getShje() {
		return shje;
	}

	public void setShje(String[] shje) {
		this.shje = shje;
	}

	public String[] getWzsj() {
		return wzsj;
	}

	public void setWzsj(String[] wzsj) {
		this.wzsj = wzsj;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String[] getPkValue() {
		return pkValue;
	}

	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}

	public String[] getYlyt() {
		return ylyt;
	}

	public void setYlyt(String[] ylyt) {
		this.ylyt = ylyt;
	}
	
	
}
