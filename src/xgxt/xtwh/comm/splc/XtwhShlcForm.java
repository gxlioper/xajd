package xgxt.xtwh.comm.splc;

import xgxt.comm.CommForm;
import xgxt.form.User;

public class XtwhShlcForm  extends CommForm  {
	
	private static final long serialVersionUID = 1L;
	
	User user =new User();
	
	private String id; //流程ID
	
	private String mkdm;//模块类型；
	
	private String lcmc;//流程名称；
	
	private String djlx;//所属模块代码
	
	private String spgwdm;//审批岗位代码
	
	private String ms;//描述
	
	private String spgw;//审批岗位；
	
	private String page;//显示页;
	
	private String[] yhm;//用户名;
	
	private String[]spyh;//审批用户
	
	private String[]yxyhArr;//已选用户;
	
	private String[]yxyhmArr;//已选用户名;
	
	private String ssmk;//所属模块
	
	private String maxSize; // 最大流程
	
	private String spgwzdm;//审批岗位组代码
	
	private String yhszlx;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSsmk() {
		return ssmk;
	}
	
	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getSpgwdm() {
		return spgwdm;
	}

	public void setSpgwdm(String spgwdm) {
		this.spgwdm = spgwdm;
	}

	public void setSsmk(String ssmk) {
		this.ssmk = ssmk;
	}

	public String[] getYxyhmArr() {
		return yxyhmArr;
	}

	public void setYxyhmArr(String[] yxyhmArr) {
		this.yxyhmArr = yxyhmArr;
	}

	public String[] getYxyhArr() {
		return yxyhArr;
	}

	public void setYxyhArr(String[] yxyhArr) {
		this.yxyhArr = yxyhArr;
	}

	public String[] getSpyh() {
		return spyh;
	}

	public void setSpyh(String[] spyh) {
		this.spyh = spyh;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getSpgw() {
		return spgw;
	}

	public void setSpgw(String spgw) {
		this.spgw = spgw;
	}

	public String getLcmc() {
		return lcmc;
	}

	public void setLcmc(String lcmc) {
		this.lcmc = lcmc;
	}

	public String getMkdm() {
		return mkdm;
	}

	public void setMkdm(String mkdm) {
		this.mkdm = mkdm;
	}

	public String getDjlx() {
		return djlx;
	}

	public void setDjlx(String djlx) {
		this.djlx = djlx;
	}

	public String[] getYhm() {
		return yhm;
	}

	public void setYhm(String[] yhm) {
		this.yhm = yhm;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}

	public String getSpgwzdm() {
		return spgwzdm;
	}

	public void setSpgwzdm(String spgwzdm) {
		this.spgwzdm = spgwzdm;
	}

	public String getYhszlx() {
		return yhszlx;
	}

	public void setYhszlx(String yhszlx) {
		this.yhszlx = yhszlx;
	}
	
}
