package xgxt.xtwh.comm.splc;

import xgxt.comm.CommForm;
import xgxt.form.User;

public class XtwhShlcForm  extends CommForm  {
	
	private static final long serialVersionUID = 1L;
	
	User user =new User();
	
	private String id; //����ID
	
	private String mkdm;//ģ�����ͣ�
	
	private String lcmc;//�������ƣ�
	
	private String djlx;//����ģ�����
	
	private String spgwdm;//������λ����
	
	private String ms;//����
	
	private String spgw;//������λ��
	
	private String page;//��ʾҳ;
	
	private String[] yhm;//�û���;
	
	private String[]spyh;//�����û�
	
	private String[]yxyhArr;//��ѡ�û�;
	
	private String[]yxyhmArr;//��ѡ�û���;
	
	private String ssmk;//����ģ��
	
	private String maxSize; // �������
	
	private String spgwzdm;//������λ�����
	
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
