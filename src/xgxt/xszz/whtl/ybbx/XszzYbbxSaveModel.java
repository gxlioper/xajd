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

		// 主键字段
		String []save={"xn","xh"};
		// 主键字段
		String []pk={"xn","xh"};
		// 单个保存字段
		String []oneZd={"xn","xh","czr"};
		// 批量保存字段
		String []arrayZd={"bxje","mzyy","wzsj","ylyt"};
		
		basicModel.setPk(pk);
	
		service.getModelValue(this, request);
		
		// ---------------------固定的保存值 begin------------
		this.setCzr(user.getUserName());
	
		this.setXn(Base.currXn);
		
		// ---------------------固定的保存值 end--------------
		basicModel.setPkValue(this.getPkValue());
		
		basicModel.setOneZd(oneZd);
		
		basicModel.setArrayZd(arrayZd);
		
		// --------------表名------------
		basicModel.setTableName("xg_xszz_ybbxxxb");
		// --------------需要保存的值--------------------
		
		HashMap<String,String>valueMap=service.getValueMap(request, save);
		
		basicModel.setValueMap(valueMap);
		
	}
	
	private String xh;// 学号
	
	private String xn;// 学年
	
	private String czr;// 操作人
	
	private String czsj;// 操作时间
	
	private String[]pkValue;// 主键

	private String[]jlid;// 记录编号
	
	private String[]bxje;// 保险金额
	
	private String[]mzyy;// 门诊医院
	
	private String[]wzsj;// 问诊时间
	
	private String[]ylyt;// 医疗用途
	
	private String[]shje;// 审核金额

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
