
package xgxt.pjpy.ahjg;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ս�����ҵѧԺ��������ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-30</p>
 */
public class PjpyAhjgActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xydm;//ѧԺ����
	private String zydm;//רҵ����
	private String bjdm;//�༶����
	private String nj;//�꼶
	private String xn;//ѧ��
	private String nd;//���
	private String xh;//ѧ��
	private String xm;//����
	private String xq;//ѧ��
	private String gnmk;//����ģ��
	private String bzxm;//�೤����
	private String bzr;//������
	private String xsrs;//ѧ������
	private String zysj;//��Ҫ�¼�
	private String bj;//�༶
	private String xmdm;
	private String zdm;
	private String bjbkl;//�༶������
	private String[] cbv;//����ɾ��
	private String zccql;//��ٳ�����
	private String rychdm;//�����ƺŴ���
	private String shxm;//�����Ŀ
	private String sh;//���
	private String shyj;//������
	private String jg;//���
	private String pkValue;
	private String rychmc;//�����ƺ�����
	private String yesNo;
	private String xxjsdm;//ѧ����������
	private String xxjsmc;//ѧ������
	private String hjsj;//��ʱ��
	private String bz;//��ע
	private String cjlx;//�ɼ�����
	private String djksdm;//�ȼ����Դ���
	private String cslx;//��������
	private String zdcz;//�ֶβ���
	private String zdbj;//�ֶαȽ�
	private String zdval;//�ֶ�ֵ
	private String jxjdm;//��ѧ�����
	private String tjdm;//�����ֶ���
	private String tjzdlyb;//�����ֶ���Դ��
	private String tj;//����
	private String czmc;
	private String[] kcxz;// �γ�����
	private String djksmc;
	
	Pages pages = new Pages();

	public Pages getPages() {
		return pages;
	}
	
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getCjlx() {
		return cjlx;
	}
	public void setCjlx(String cjlx) {
		this.cjlx = cjlx;
	}
	public String getDjksdm() {
		return djksdm;
	}
	public void setDjksdm(String djksdm) {
		this.djksdm = djksdm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getHjsj() {
		return hjsj;
	}
	public void setHjsj(String hjsj) {
		this.hjsj = hjsj;
	}
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}
	public String getRychmc() {
		return rychmc;
	}
	public void setRychmc(String rychmc) {
		this.rychmc = rychmc;
	}
	public String getJg() {
		return jg;
	}
	public void setJg(String jg) {
		this.jg = jg;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getSh() {
		return sh;
	}
	public void setSh(String sh) {
		this.sh = sh;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getShxm() {
		return shxm;
	}
	public void setShxm(String shxm) {
		this.shxm = shxm;
	}
	public String getRychdm() {
		return rychdm;
	}
	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}
	public String getZccql() {
		return zccql;
	}
	public void setZccql(String zccql) {
		this.zccql = zccql;
	}
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public String getBjbkl() {
		return bjbkl;
	}
	public void setBjbkl(String bjbkl) {
		this.bjbkl = bjbkl;
	}
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String getZdm() {
		return zdm;
	}
	public void setZdm(String zdm) {
		this.zdm = zdm;
	}
	public String getBzr() {
		return bzr;
	}
	public void setBzr(String bzr) {
		this.bzr = bzr;
	}
	public String getBzxm() {
		return bzxm;
	}
	public void setBzxm(String bzxm) {
		this.bzxm = bzxm;
	}
	public String getXsrs() {
		return xsrs;
	}
	public void setXsrs(String xsrs) {
		this.xsrs = xsrs;
	}
	public String getZysj() {
		return zysj;
	}
	public void setZysj(String zysj) {
		this.zysj = zysj;
	}
	public String getGnmk() {
		return gnmk;
	}
	public void setGnmk(String gnmk) {
		this.gnmk = gnmk;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	public String getXxjsdm() {
		return xxjsdm;
	}
	public void setXxjsdm(String xxjsdm) {
		this.xxjsdm = xxjsdm;
	}
	public String getXxjsmc() {
		return xxjsmc;
	}
	public void setXxjsmc(String xxjsmc) {
		this.xxjsmc = xxjsmc;
	}
	public String getCslx() {
		return cslx;
	}
	public void setCslx(String cslx) {
		this.cslx = cslx;
	}
	public String getTjdm() {
		return tjdm;
	}
	public void setTjdm(String tjdm) {
		this.tjdm = tjdm;
	}
	public String getZdbj() {
		return zdbj;
	}
	public void setZdbj(String zdbj) {
		this.zdbj = zdbj;
	}
	public String getZdcz() {
		return zdcz;
	}
	public void setZdcz(String zdcz) {
		this.zdcz = zdcz;
	}
	public String getZdval() {
		return zdval;
	}
	public void setZdval(String zdval) {
		this.zdval = zdval;
	}
	public String getJxjdm() {
		return jxjdm;
	}
	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}
	public String getTj() {
		return tj;
	}
	public void setTj(String tj) {
		this.tj = tj;
	}
	public String getTjzdlyb() {
		return tjzdlyb;
	}
	public void setTjzdlyb(String tjzdlyb) {
		this.tjzdlyb = tjzdlyb;
	}

	public String getCzmc() {
		return czmc;
	}

	public void setCzmc(String czmc) {
		this.czmc = czmc;
	}

	public String[] getKcxz() {
		return kcxz;
	}

	public void setKcxz(String[] kcxz) {
		this.kcxz = kcxz;
	}

	public String getDjksmc() {
		return djksmc;
	}

	public void setDjksmc(String djksmc) {
		this.djksmc = djksmc;
	}
	
}
