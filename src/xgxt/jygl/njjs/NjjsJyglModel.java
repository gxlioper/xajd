package xgxt.jygl.njjs;

import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class NjjsJyglModel {
	private Pages pages;
	
	private String nj;     //�꼶
	private String bynd;     //��ҵ���
	private String csrq;     //��������
	private String xz;     //ѧ��
	private String sxgw;     //ʵϰ��λ
	private String zydm;     //רҵ����
	private String pyfs;     //������ʽ
	private String sxjyfs;     //ʵϰ��ҵ��ʽ
	private String lxdh;     //��ϵ�绰
	private String xh;     //ѧ��
	private String xm;     //����
	private String zymc;     //רҵ����
	private String sfzh;     //���֤��
	private String sxdwdz;     //ʵϰ��λ��ַ
	private String yb;     //�ʱ�
	private String sjhm;     //�ֻ�����
	private String bjdm;     //�༶����
	private String sstj;     //ʳ������
	private String jtzk;     //��ͨ״��
	private String email;     //��������
	private String xl;     //ѧ��
	private String sydq;     //��Դ����
	private String sxdw;     //ʵϰ��λ
	private String sxdwxz;     //ʵϰ��λ����
	private String sxdwbdqk;     //ʵϰ��λ�䶯���
	private String sxbm;     //ʵϰ����
	private String jydw;     //��ҵ��λ
	private String lxdz;     //��ϵ��ַ
	private String xb;     //�Ա�
	private String xydm;     //ѧԺ����
	private String xymc;     //ѧԺ����
	private String bjmc;     //�༶����
	private String ldbh;     //�Ͷ�����
	private String byqx;     //��ҵȥ��
	private String sfsb;	 //�Ƿ��ϱ�
	private String shen;	 //ʡ
	private String jtszd;	 //��ͥ��ַ
	private String lxfs;	 //��ϵ��ʽ
	
	private String userType;
	private String userName;
	
	private String jndj;
	public String getJtszd() {
		return jtszd;
	}
	public void setJtszd(String jtszd) {
		this.jtszd = jtszd;
	}
	public String getLxfs() {
		return lxfs;
	}
	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}
	public String getShen() {
		return shen;
	}
	public void setShen(String shen) {
		this.shen = shen;
	}
	public String getSfsb() {
		return sfsb;
	}
	public void setSfsb(String sfsb) {
		this.sfsb = sfsb;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getBynd() {
		return bynd;
	}
	public void setBynd(String bynd) {
		this.bynd = bynd;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getSxgw() {
		return sxgw;
	}
	public void setSxgw(String sxgw) {
		this.sxgw = sxgw;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getPyfs() {
		return pyfs;
	}
	public void setPyfs(String pyfs) {
		this.pyfs = pyfs;
	}
	public String getSxjyfs() {
		return sxjyfs;
	}
	public void setSxjyfs(String sxjyfs) {
		this.sxjyfs = sxjyfs;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getSxdwdz() {
		return sxdwdz;
	}
	public void setSxdwdz(String sxdwdz) {
		this.sxdwdz = sxdwdz;
	}
	public String getYb() {
		return yb;
	}
	public void setYb(String yb) {
		this.yb = yb;
	}
	public String getSjhm() {
		return sjhm;
	}
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getSstj() {
		return sstj;
	}
	public void setSstj(String sstj) {
		this.sstj = sstj;
	}
	public String getJtzk() {
		return jtzk;
	}
	public void setJtzk(String jtzk) {
		this.jtzk = jtzk;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getXl() {
		return xl;
	}
	public void setXl(String xl) {
		this.xl = xl;
	}
	public String getSydq() {
		if(StringUtils.isNotNull(shen) && StringUtils.isNull(sydq)){
			sydq = shen;
		}
		return sydq;
	}
	public void setSydq(String sydq) {
		this.sydq = sydq;
	}
	public String getSxdw() {
		return sxdw;
	}
	public void setSxdw(String sxdw) {
		this.sxdw = sxdw;
	}
	public String getSxdwxz() {
		return sxdwxz;
	}
	public void setSxdwxz(String sxdwxz) {
		this.sxdwxz = sxdwxz;
	}
	public String getSxdwbdqk() {
		return sxdwbdqk;
	}
	public void setSxdwbdqk(String sxdwbdqk) {
		this.sxdwbdqk = sxdwbdqk;
	}
	public String getSxbm() {
		return sxbm;
	}
	public void setSxbm(String sxbm) {
		this.sxbm = sxbm;
	}
	public String getJydw() {
		return jydw;
	}
	public void setJydw(String jydw) {
		this.jydw = jydw;
	}
	public String getLxdz() {
		return lxdz;
	}
	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getLdbh() {
		return ldbh;
	}
	public void setLdbh(String ldbh) {
		this.ldbh = ldbh;
	}
	public String getByqx() {
		return byqx;
	}
	public void setByqx(String byqx) {
		this.byqx = byqx;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getJndj() {
		return jndj;
	}
	public void setJndj(String jndj) {
		this.jndj = jndj;
	}
	public String getFdyQuery(){
		String query = "";
		if("fdy".equals(userType)){
			query = " and exists (select 1 from fdybjb b where b.bjdm=a.bjdm and b.zgh='"+userName+"') ";
		}else if("bzr".equalsIgnoreCase(userType)){
			query = " and exists (select 1 from bzrbbb b where b.bjdm=a.bjdm and b.zgh='"+userName+"') ";
		}else if("jd".equalsIgnoreCase(userType)){
			query = " and (exists (select 1 from fdybjb b where b.bjdm=a.bjdm and b.zgh='"+userName+"') " + 
					"or exists (select 1 from bzrbbb b where b.bjdm=a.bjdm and b.zgh='"+userName+"'))";
		}
		return query;
	}
}
