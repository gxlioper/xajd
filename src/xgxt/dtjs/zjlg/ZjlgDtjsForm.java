package xgxt.dtjs.zjlg;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class ZjlgDtjsForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205711105806100577L;

	private String nj;

	private String nd;

	private String xn;

	private String zydm;

	private String xydm;

	private String bjdm;

	private String zymc;

	private String xymc;

	private String bjmc;

	private String xq;

	private String xh;

	private String zjxh;

	private String xm;

	private String xb;

	private String zjsj;// 'ת��ʱ��';

	private String ydw;// '��Ч��';

	private String dfjzyf;// '���ѽ����·�';

	private String jsxbh;// '�����ű��';

	private String lxdh;// '��ϵ�绰';

	private String zjlx;// 'ת������';

	private String yxq;// 'ԭ��λ';

	private String zjmm;// 'ת��������ò';

	private String bz;// '��ע';

	private String zjdz;// 'ת�ӵ�ַ';

	private String zbdm; // ֧������

	private String kssj;// ���ʱ��

	private String xsccdm;// ѧ����δ���

	private String[] checkVal;

	private String[] zbcy; // ֧����Ա

	private String pxjssj;// ��ѵ��ʼʱ��

	private String pxkssj;// ��ѵ����ʱ��

	private String pxjg;// ��ѵ���

	private String pxxmdm;// ��ѵ��Ŀ����

	private String thcs;// '̸������';

	private String jssj;// 'Ԥ����������';

	private String lxr2;// ��ϵ��2

	private String cjzzxxqk;// '�μ�����ѧϰ���';

	private String sfkyzz;// '�Ƿ����ת��';

	private String lxr1;// ��ϵ��1

	private String dfjnqk;// '���ѽ������';

	private String kcqk;// '�������';

	private String zzsj;// 'ת��ʱ��';

	private String cjhdqk;// '�μӻ���';

	private String zbshqk;// '֧���������';

	private String rdsj;// '�뵳ʱ��';

	private String ybdykssj;// Ԥ����Ա��ʼʱ��

	private String ybdyjssj;// Ԥ����Ա����ʱ��

	private String zzxxqk;// '�μ�����ѧϰ���';

	private String zzzt;// '��ְ״̬';

	private String zbmc;// '֧������';

	private String ssxx;// '����ѧԺ';

	private String rdlxr;// '�뵳��ϵ��';

	private String lxrzb;// '��ϵ��֧��';

	private String lxrbj;// '��ϵ�˰༶';

	private String lxrdh;// '��ϵ�˵绰';

	private String djsqssj;// '�ݽ��뵳������ʱ��';

	private String sxhbqk;// '˼��㱨���';

	private String sfwj;// '�Ƿ�Υ��';

	private String zzmm;// '������ò';

	private String lx;// '����';

	private String zgh;// 'ְ����';

	private String fdyxm;// '����Ա����';

	// ͨ�÷�ҳ
	Pages pages = new Pages();

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
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

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZjdz() {
		return zjdz;
	}

	public void setZjdz(String zjdz) {
		this.zjdz = zjdz;
	}

	public String getZjlx() {
		return zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}

	public String getZjsj() {
		return zjsj;
	}

	public void setZjsj(String zjsj) {
		this.zjsj = zjsj;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getZbdm() {
		return zbdm;
	}

	public void setZbdm(String zbdm) {
		this.zbdm = zbdm;
	}

	public String[] getZbcy() {
		return zbcy;
	}

	public void setZbcy(String[] zbcy) {
		this.zbcy = zbcy;
	}

	public String getPxjg() {
		return pxjg;
	}

	public void setPxjg(String pxjg) {
		this.pxjg = pxjg;
	}

	public String getPxjssj() {
		return pxjssj;
	}

	public void setPxjssj(String pxjssj) {
		this.pxjssj = pxjssj;
	}

	public String getPxkssj() {
		return pxkssj;
	}

	public void setPxkssj(String pxkssj) {
		this.pxkssj = pxkssj;
	}

	public String getPxxmdm() {
		return pxxmdm;
	}

	public void setPxxmdm(String pxxmdm) {
		this.pxxmdm = pxxmdm;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getXsccdm() {
		return xsccdm;
	}

	public void setXsccdm(String xsccdm) {
		this.xsccdm = xsccdm;
	}

	public String getCjzzxxqk() {
		return cjzzxxqk;
	}

	public void setCjzzxxqk(String cjzzxxqk) {
		this.cjzzxxqk = cjzzxxqk;
	}

	public String getDfjnqk() {
		return dfjnqk;
	}

	public void setDfjnqk(String dfjnqk) {
		this.dfjnqk = dfjnqk;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getKcqk() {
		return kcqk;
	}

	public void setKcqk(String kcqk) {
		this.kcqk = kcqk;
	}

	public String getLxr1() {
		return lxr1;
	}

	public void setLxr1(String lxr1) {
		this.lxr1 = lxr1;
	}

	public String getLxr2() {
		return lxr2;
	}

	public void setLxr2(String lxr2) {
		this.lxr2 = lxr2;
	}

	public String getSfkyzz() {
		return sfkyzz;
	}

	public void setSfkyzz(String sfkyzz) {
		this.sfkyzz = sfkyzz;
	}

	public String getThcs() {
		return thcs;
	}

	public void setThcs(String thcs) {
		this.thcs = thcs;
	}

	public String getCjhdqk() {
		return cjhdqk;
	}

	public void setCjhdqk(String cjhdqk) {
		this.cjhdqk = cjhdqk;
	}

	public String getRdsj() {
		return rdsj;
	}

	public void setRdsj(String rdsj) {
		this.rdsj = rdsj;
	}

	public String getYbdyjssj() {
		return ybdyjssj;
	}

	public void setYbdyjssj(String ybdyjssj) {
		this.ybdyjssj = ybdyjssj;
	}

	public String getYbdykssj() {
		return ybdykssj;
	}

	public void setYbdykssj(String ybdykssj) {
		this.ybdykssj = ybdykssj;
	}

	public String getZbshqk() {
		return zbshqk;
	}

	public void setZbshqk(String zbshqk) {
		this.zbshqk = zbshqk;
	}

	public String getZzsj() {
		return zzsj;
	}

	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
	}

	public String getZzxxqk() {
		return zzxxqk;
	}

	public void setZzxxqk(String zzxxqk) {
		this.zzxxqk = zzxxqk;
	}

	public String getZzzt() {
		return zzzt;
	}

	public void setZzzt(String zzzt) {
		this.zzzt = zzzt;
	}

	public String getDfjzyf() {
		return dfjzyf;
	}

	public void setDfjzyf(String dfjzyf) {
		this.dfjzyf = dfjzyf;
	}

	public String getJsxbh() {
		return jsxbh;
	}

	public void setJsxbh(String jsxbh) {
		this.jsxbh = jsxbh;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getYdw() {
		return ydw;
	}

	public void setYdw(String ydw) {
		this.ydw = ydw;
	}

	public String getYxq() {
		return yxq;
	}

	public void setYxq(String yxq) {
		this.yxq = yxq;
	}

	public String getZjmm() {
		return zjmm;
	}

	public void setZjmm(String zjmm) {
		this.zjmm = zjmm;
	}

	public String getZbmc() {
		return zbmc;
	}

	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
	}

	public String getSsxx() {
		return ssxx;
	}

	public void setSsxx(String ssxx) {
		this.ssxx = ssxx;
	}

	public String getDjsqssj() {
		return djsqssj;
	}

	public void setDjsqssj(String djsqssj) {
		this.djsqssj = djsqssj;
	}

	public String getLxrbj() {
		return lxrbj;
	}

	public void setLxrbj(String lxrbj) {
		this.lxrbj = lxrbj;
	}

	public String getLxrdh() {
		return lxrdh;
	}

	public void setLxrdh(String lxrdh) {
		this.lxrdh = lxrdh;
	}

	public String getLxrzb() {
		return lxrzb;
	}

	public void setLxrzb(String lxrzb) {
		this.lxrzb = lxrzb;
	}

	public String getRdlxr() {
		return rdlxr;
	}

	public void setRdlxr(String rdlxr) {
		this.rdlxr = rdlxr;
	}

	public String getSfwj() {
		return sfwj;
	}

	public void setSfwj(String sfwj) {
		this.sfwj = sfwj;
	}

	public String getSxhbqk() {
		return sxhbqk;
	}

	public void setSxhbqk(String sxhbqk) {
		this.sxhbqk = sxhbqk;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getZjxh() {
		return zjxh;
	}

	public void setZjxh(String zjxh) {
		this.zjxh = zjxh;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getFdyxm() {
		return fdyxm;
	}

	public void setFdyxm(String fdyxm) {
		this.fdyxm = fdyxm;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

}
