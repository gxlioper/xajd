package xgxt.action.zgkd.sxjy;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class XssyForm extends ActionForm{
	
	/**
	* <p>Title: ѧ������ϵͳ</p>
	* <p>Description: ѧ����Ϣ����˼�����-�й����-ѧ������Form��</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author ³��
	* @version 1.0
	*/
	
	private static final long serialVersionUID = -5206612735394106449L;
	private String xydm;
	private String zydm;
	private String bjdm;
	private String nj;
	private String xn;
	private String xh;
	private String xq;
	private String nd;
	private String xm;
	private String xsdjqsrq;
	private String xsdjjsrq;
	private String xgmbqsrq;
	private String xgmbjsrq;
	private String lydq;
	private String zwfx;
	private String zwfzgh;
	private String ndmbonexn;
	private String ndmbtwoxn;
	private String ndmbthreexn;
	private String ndmbfourxn;
	private String zdyj;
	private String lrrq;
	private String yjhfrq;
	private String mbtztwoxn;
	private String mbtzthreexn;
	private String mbtzfourxn;
	private String mbtzrqtwoxn;
	private String mbtzrqthreexn;
	private String mbtzrqfourxn;
	private String mbwcqkonexn;
	private String mbwcqktwoxn;
	private String mbwcqkthreexn;
	private String zdyjonexn;
	private String zdyjtwoxn;
	private String zdyjthreexn;
	private String yjhfrqonexn;
	private String yjhfrqtwoxn;
	private String yjhfrqthreexn;
	private String bzronexn;
	private String bzrtwoxn;
	private String bzrthreexn;
	private String bzrfourxn;
	private String grjl; //���˼���
	private String filter = "";//��ѯ����
	private String xzzt= "";
	private String rdsqsj;
	private String qdwjjfzsj;
	private String fzdxsj;
	private String rdsj;
	private String zzsj;
	private String sfaqzz;
	private String yjysjcsj;
	private String dyzzgxzrsj;
	private String txsj;
	private String bz;
	// ��֯��ϵ���ڵ�֧��
	private String zzgxszdzb;
	// ���뵳֧������
	private String jrdzblx;
	// ת�����
	private String zzqk;
	// ���⵳�ѽ������
	private String tsdfjn;
//	 ��ǰ״̬
	private String dqzt;
//	 ͨ��
	Pages pages = new Pages();
	
	private String zyfx;
	
	private String zymb;
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getDyzzgxzrsj() {
		return dyzzgxzrsj;
	}
	public void setDyzzgxzrsj(String dyzzgxzrsj) {
		this.dyzzgxzrsj = dyzzgxzrsj;
	}
	public String getFzdxsj() {
		return fzdxsj;
	}
	public void setFzdxsj(String fzdxsj) {
		this.fzdxsj = fzdxsj;
	}
	public String getQdwjjfzsj() {
		return qdwjjfzsj;
	}
	public void setQdwjjfzsj(String qdwjjfzsj) {
		this.qdwjjfzsj = qdwjjfzsj;
	}
	public String getRdsj() {
		return rdsj;
	}
	public void setRdsj(String rdsj) {
		this.rdsj = rdsj;
	}
	public String getRdsqsj() {
		return rdsqsj;
	}
	public void setRdsqsj(String rdsqsj) {
		this.rdsqsj = rdsqsj;
	}
	public String getSfaqzz() {
		return sfaqzz;
	}
	public void setSfaqzz(String sfaqzz) {
		this.sfaqzz = sfaqzz;
	}
	public String getTxsj() {
		return txsj;
	}
	public void setTxsj(String txsj) {
		this.txsj = txsj;
	}
	public String getYjysjcsj() {
		return yjysjcsj;
	}
	public void setYjysjcsj(String yjysjcsj) {
		this.yjysjcsj = yjysjcsj;
	}
	public String getZzsj() {
		return zzsj;
	}
	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getGrjl() {
		return grjl;
	}
	public void setGrjl(String grjl) {
		this.grjl = grjl;
	}

	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getXsdjjsrq() {
		return xsdjjsrq;
	}
	public void setXsdjjsrq(String xsdjjsrq) {
		this.xsdjjsrq = xsdjjsrq;
	}
	public String getXsdjqsrq() {
		return xsdjqsrq;
	}
	public void setXsdjqsrq(String xsdjqsrq) {
		this.xsdjqsrq = xsdjqsrq;
	}
	public String getXgmbjsrq() {
		return xgmbjsrq;
	}
	public void setXgmbjsrq(String xgmbjsrq) {
		this.xgmbjsrq = xgmbjsrq;
	}
	public String getXgmbqsrq() {
		return xgmbqsrq;
	}
	public void setXgmbqsrq(String xgmbqsrq) {
		this.xgmbqsrq = xgmbqsrq;
	}
	public String getBzrfourxn() {
		return bzrfourxn;
	}
	public void setBzrfourxn(String bzrfourxn) {
		this.bzrfourxn = bzrfourxn;
	}
	public String getBzronexn() {
		return bzronexn;
	}
	public void setBzronexn(String bzronexn) {
		this.bzronexn = bzronexn;
	}
	public String getBzrthreexn() {
		return bzrthreexn;
	}
	public void setBzrthreexn(String bzrthreexn) {
		this.bzrthreexn = bzrthreexn;
	}
	public String getBzrtwoxn() {
		return bzrtwoxn;
	}
	public void setBzrtwoxn(String bzrtwoxn) {
		this.bzrtwoxn = bzrtwoxn;
	}
	public String getLrrq() {
		return lrrq;
	}
	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}
	public String getLydq() {
		return lydq;
	}
	public void setLydq(String lydq) {
		this.lydq = lydq;
	}
	public String getMbtzfourxn() {
		return mbtzfourxn;
	}
	public void setMbtzfourxn(String mbtzfourxn) {
		this.mbtzfourxn = mbtzfourxn;
	}
	public String getMbtzrqfourxn() {
		return mbtzrqfourxn;
	}
	public void setMbtzrqfourxn(String mbtzrqfourxn) {
		this.mbtzrqfourxn = mbtzrqfourxn;
	}
	public String getMbtzrqthreexn() {
		return mbtzrqthreexn;
	}
	public void setMbtzrqthreexn(String mbtzrqthreexn) {
		this.mbtzrqthreexn = mbtzrqthreexn;
	}
	public String getMbtzrqtwoxn() {
		return mbtzrqtwoxn;
	}
	public void setMbtzrqtwoxn(String mbtzrqtwoxn) {
		this.mbtzrqtwoxn = mbtzrqtwoxn;
	}
	public String getMbtzthreexn() {
		return mbtzthreexn;
	}
	public void setMbtzthreexn(String mbtzthreexn) {
		this.mbtzthreexn = mbtzthreexn;
	}
	public String getMbtztwoxn() {
		return mbtztwoxn;
	}
	public void setMbtztwoxn(String mbtztwoxn) {
		this.mbtztwoxn = mbtztwoxn;
	}
	public String getMbwcqkonexn() {
		return mbwcqkonexn;
	}
	public void setMbwcqkonexn(String mbwcqkonexn) {
		this.mbwcqkonexn = mbwcqkonexn;
	}
	public String getMbwcqkthreexn() {
		return mbwcqkthreexn;
	}
	public void setMbwcqkthreexn(String mbwcqkthreexn) {
		this.mbwcqkthreexn = mbwcqkthreexn;
	}
	public String getMbwcqktwoxn() {
		return mbwcqktwoxn;
	}
	public void setMbwcqktwoxn(String mbwcqktwoxn) {
		this.mbwcqktwoxn = mbwcqktwoxn;
	}
	public String getNdmbfourxn() {
		return ndmbfourxn;
	}
	public void setNdmbfourxn(String ndmbfourxn) {
		this.ndmbfourxn = ndmbfourxn;
	}
	public String getNdmbonexn() {
		return ndmbonexn;
	}
	public void setNdmbonexn(String ndmbonexn) {
		this.ndmbonexn = ndmbonexn;
	}
	public String getNdmbthreexn() {
		return ndmbthreexn;
	}
	public void setNdmbthreexn(String ndmbthreexn) {
		this.ndmbthreexn = ndmbthreexn;
	}
	public String getNdmbtwoxn() {
		return ndmbtwoxn;
	}
	public void setNdmbtwoxn(String ndmbtwoxn) {
		this.ndmbtwoxn = ndmbtwoxn;
	}
	public String getYjhfrq() {
		return yjhfrq;
	}
	public void setYjhfrq(String yjhfrq) {
		this.yjhfrq = yjhfrq;
	}
	public String getYjhfrqonexn() {
		return yjhfrqonexn;
	}
	public void setYjhfrqonexn(String yjhfrqonexn) {
		this.yjhfrqonexn = yjhfrqonexn;
	}
	public String getYjhfrqthreexn() {
		return yjhfrqthreexn;
	}
	public void setYjhfrqthreexn(String yjhfrqthreexn) {
		this.yjhfrqthreexn = yjhfrqthreexn;
	}
	public String getYjhfrqtwoxn() {
		return yjhfrqtwoxn;
	}
	public void setYjhfrqtwoxn(String yjhfrqtwoxn) {
		this.yjhfrqtwoxn = yjhfrqtwoxn;
	}
	public String getZdyj() {
		return zdyj;
	}
	public void setZdyj(String zdyj) {
		this.zdyj = zdyj;
	}
	public String getZdyjonexn() {
		return zdyjonexn;
	}
	public void setZdyjonexn(String zdyjonexn) {
		this.zdyjonexn = zdyjonexn;
	}
	public String getZdyjthreexn() {
		return zdyjthreexn;
	}
	public void setZdyjthreexn(String zdyjthreexn) {
		this.zdyjthreexn = zdyjthreexn;
	}
	public String getZdyjtwoxn() {
		return zdyjtwoxn;
	}
	public void setZdyjtwoxn(String zdyjtwoxn) {
		this.zdyjtwoxn = zdyjtwoxn;
	}
	public String getZwfx() {
		return zwfx;
	}
	public void setZwfx(String zwfx) {
		this.zwfx = zwfx;
	}
	public String getZwfzgh() {
		return zwfzgh;
	}
	public void setZwfzgh(String zwfzgh) {
		this.zwfzgh = zwfzgh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXzzt() {
		return xzzt;
	}
	public void setXzzt(String xzzt) {
		this.xzzt = xzzt;
	}
	public String getJrdzblx() {
		return jrdzblx;
	}
	public void setJrdzblx(String jrdzblx) {
		this.jrdzblx = jrdzblx;
	}
	public String getTsdfjn() {
		return tsdfjn;
	}
	public void setTsdfjn(String tsdfjn) {
		this.tsdfjn = tsdfjn;
	}
	public String getZzgxszdzb() {
		return zzgxszdzb;
	}
	public void setZzgxszdzb(String zzgxszdzb) {
		this.zzgxszdzb = zzgxszdzb;
	}
	public String getZzqk() {
		return zzqk;
	}
	public void setZzqk(String zzqk) {
		this.zzqk = zzqk;
	}
	public String getDqzt() {
		return dqzt;
	}
	public void setDqzt(String dqzt) {
		this.dqzt = dqzt;
	}
	public String getZyfx() {
		return zyfx;
	}
	public void setZyfx(String zyfx) {
		this.zyfx = zyfx;
	}
	public String getZymb() {
		return zymb;
	}
	public void setZymb(String zymb) {
		this.zymb = zymb;
	}
	

}
