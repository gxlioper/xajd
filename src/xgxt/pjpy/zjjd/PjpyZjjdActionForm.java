
package xgxt.pjpy.zjjd;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �㽭������������ActionForm
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-05</p>
 */
public class PjpyZjjdActionForm extends ActionForm {

	/**
	 * �Զ����ɰ汾ID
	 */
	private static final long serialVersionUID = 1L;

	private String xh;//ѧ��
	private String xn;//ѧ��
	private String nd;//���
	private String nj;//�꼶
	private String xq;//ѧ��
	private String xydm;//ѧԺ����
	private String zydm;//רҵ����
	private String bjdm;//�༶����
	private String xm;//����
	private String yf;//�·�
	private String jf;//�ӷ�
	private String kf;//�۷�
	private String sx;//����
	private String[] cbv;
	private String sh;//���
	private String yj;//������
	private String drzw;//����ְ��
	private String rzsj;//��ְʱ��
	private String khdj;//���˵ȼ�
	private String bz;//��ע
	private String xybxf;//У԰���ַ�
	private String gybxf;//��Ԣ���ַ�
	private String xybxjf;//У԰�����ܼӷ�
	private String xybxkf;//У԰�����ܿ۷�
	private String gybxjf;//��Ԣ�����ܼӷ�
	private String gybxkf;//��Ԣ�����ܿ۷�
	private String dyfjf;//�������ӷ�
	private String dyzf;//�����ܷ�
	private String dyxj;//����С��
	private String pjcj;//ƽ���ɼ�
	private String pjcjmc;//ƽ���ɼ�����
	private String zyfjf;//�������ӷ�
	private String zyzf;//�����ܷ�
	private String zyxj;//����С��
	private String tycj;//�����ɼ�
	private String tyfjf;//�������ӷ�
	private String tyzf;//�����ܷ�
	private String tyxj;//����С��
	private String zhszcpzf;//�ۺ����ʲ����ܷ�
	private String zhszcpmc;//�ۺ����ʲ�������
	private String tzjkbzdj;//���ʽ�����׼
	private String bjpddj;//�༶�����ȼ�
	private String szxyj;//����ϵ���
	private String xysh;//ѧԺ���
	private String xxsh;//ѧУ���
	private String xyshyj;//ѧԺ������
	private String xxshyj;//ѧУ������
	private String fdyyj;//����Ա���
	private String jxjdm;//��ѧ�����
	private String rychdm;//�����ƺŴ���
	private String sfcf;//�Ƿ񴦷�
	private String wydj;//����ȼ�
	private String jsjdj;//������ȼ�
	private String rq;//����

	//ͨ��
	Pages pages = new Pages();
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getJxjdm() {
		return jxjdm;
	}
	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getSh() {
		return sh;
	}
	public void setSh(String sh) {
		this.sh = sh;
	}
	public String getYj() {
		return yj;
	}
	public void setYj(String yj) {
		this.yj = yj;
	}
	public String getJf() {
		return jf;
	}
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public void setJf(String jf) {
		this.jf = jf;
	}
	public String getKf() {
		return kf;
	}
	public void setKf(String kf) {
		this.kf = kf;
	}
	public String getSx() {
		return sx;
	}
	public void setSx(String sx) {
		this.sx = sx;
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
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
	}
	public String getDrzw() {
		return drzw;
	}
	public void setDrzw(String drzw) {
		this.drzw = drzw;
	}
	public String getKhdj() {
		return khdj;
	}
	public void setKhdj(String khdj) {
		this.khdj = khdj;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	public String getDyfjf() {
		return dyfjf;
	}
	public void setDyfjf(String dyfjf) {
		this.dyfjf = dyfjf;
	}
	public String getDyxj() {
		return dyxj;
	}
	public void setDyxj(String dyxj) {
		this.dyxj = dyxj;
	}
	public String getDyzf() {
		return dyzf;
	}
	public void setDyzf(String dyzf) {
		this.dyzf = dyzf;
	}
	public String getGybxf() {
		return gybxf;
	}
	public void setGybxf(String gybxf) {
		this.gybxf = gybxf;
	}
	public String getPjcj() {
		return pjcj;
	}
	public void setPjcj(String pjcj) {
		this.pjcj = pjcj;
	}
	public String getPjcjmc() {
		return pjcjmc;
	}
	public void setPjcjmc(String pjcjmc) {
		this.pjcjmc = pjcjmc;
	}
	public String getTycj() {
		return tycj;
	}
	public void setTycj(String tycj) {
		this.tycj = tycj;
	}
	public String getTyfjf() {
		return tyfjf;
	}
	public void setTyfjf(String tyfjf) {
		this.tyfjf = tyfjf;
	}
	public String getTyxj() {
		return tyxj;
	}
	public void setTyxj(String tyxj) {
		this.tyxj = tyxj;
	}
	public String getTyzf() {
		return tyzf;
	}
	public void setTyzf(String tyzf) {
		this.tyzf = tyzf;
	}
	public String getXybxf() {
		return xybxf;
	}
	public void setXybxf(String xybxf) {
		this.xybxf = xybxf;
	}
	public String getZhszcpmc() {
		return zhszcpmc;
	}
	public void setZhszcpmc(String zhszcpmc) {
		this.zhszcpmc = zhszcpmc;
	}
	public String getZhszcpzf() {
		return zhszcpzf;
	}
	public void setZhszcpzf(String zhszcpzf) {
		this.zhszcpzf = zhszcpzf;
	}
	public String getZyfjf() {
		return zyfjf;
	}
	public void setZyfjf(String zyfjf) {
		this.zyfjf = zyfjf;
	}
	public String getZyxj() {
		return zyxj;
	}
	public void setZyxj(String zyxj) {
		this.zyxj = zyxj;
	}
	public String getZyzf() {
		return zyzf;
	}
	public void setZyzf(String zyzf) {
		this.zyzf = zyzf;
	}
	public String getBjpddj() {
		return bjpddj;
	}
	public void setBjpddj(String bjpddj) {
		this.bjpddj = bjpddj;
	}
	public String getFdyyj() {
		return fdyyj;
	}
	public void setFdyyj(String fdyyj) {
		this.fdyyj = fdyyj;
	}
	public String getSzxyj() {
		return szxyj;
	}
	public void setSzxyj(String szxyj) {
		this.szxyj = szxyj;
	}
	public String getTzjkbzdj() {
		return tzjkbzdj;
	}
	public void setTzjkbzdj(String tzjkbzdj) {
		this.tzjkbzdj = tzjkbzdj;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXxshyj() {
		return xxshyj;
	}
	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	public String getXyshyj() {
		return xyshyj;
	}
	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}
	public String getJsjdj() {
		return jsjdj;
	}
	public void setJsjdj(String jsjdj) {
		this.jsjdj = jsjdj;
	}
	public String getRychdm() {
		return rychdm;
	}
	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}
	public String getSfcf() {
		return sfcf;
	}
	public void setSfcf(String sfcf) {
		this.sfcf = sfcf;
	}
	public String getWydj() {
		return wydj;
	}
	public void setWydj(String wydj) {
		this.wydj = wydj;
	}
	public String getGybxjf() {
		return gybxjf;
	}
	public void setGybxjf(String gybxjf) {
		this.gybxjf = gybxjf;
	}
	public String getGybxkf() {
		return gybxkf;
	}
	public void setGybxkf(String gybxkf) {
		this.gybxkf = gybxkf;
	}
	public String getXybxjf() {
		return xybxjf;
	}
	public void setXybxjf(String xybxjf) {
		this.xybxjf = xybxjf;
	}
	public String getXybxkf() {
		return xybxkf;
	}
	public void setXybxkf(String xybxkf) {
		this.xybxkf = xybxkf;
	}
}
