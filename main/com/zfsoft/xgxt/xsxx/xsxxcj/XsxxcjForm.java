/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����04:02:06 
 */  
package com.zfsoft.xgxt.xsxx.xsxxcj;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(ѧ����Ϣ�ɼ�--���ְҵ��ѧ) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-7-30 ����04:02:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxxcjForm extends ActionForm {
	
private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String xh;            //ѧ��
	private String hkszd;            //�������ڵ�
	private String hkszdshen;            //�������ڵ�ʡ
	private String hkszdshi;            //�������ڵ���
	private String hkszdxian;            //�������ڵ���
	private String hkszdz;            //�������ڵأ������£�
	private String jtdz;            //��ͥ��ַ
	private String jtdzshen;            //��ͥ��ַʡ
	private String jtdzshi;            //��ͥ��ַ��
	private String jtdzxian;            //��ͥ��ַ��
	private String jtdzz;            //��ͥ��ַ�������£�
	private String jtlxfs;            //��ͥ��ϵ��ʽ
	private String syd;            //��Դ��
	private String sydshen;            //��Դ��ʡ
	private String sydshi;            //��Դ����
	private String sydxian;            //��Դ����
	private String jg;            //����
	private String jgshen;            //����ʡ
	private String jgshi;            //������
	private String jgxian;            //������
	private String hksfjrxx;            //�����Ƿ�Ǩ��ѧУ
	private String sfzx;            //�Ƿ�סУ
	private String sfsqrd;            //�Ƿ������뵳
	private String djsqssj;            //�ݽ�������ʱ��
	private String rdsj;            //�뵳ʱ��
	private String zzsj;            //ת��ʱ��
	private String sfssmz;            //�Ƿ���������
	private String ssmz;            //��������
	private String sfdgsx;            //�Ƿ񶥸�ʵϰ
	private String sflspx;            //�Ƿ���ʱ��ѵ
	private String pxmc;            //��ѵ����
	private String sfzjxy;            //�Ƿ��ڽ�����
	private String xyzjmc;            //�����ڽ�����
	private String cjzjsj;            //�μ��ڽ�ʱ��
	private String sfjjkn;            //�Ƿ񾭼�����
	private String jjknyy;            //��������ԭ��
	private String stsfcj;            //�����Ƿ񼲲�
	private String stcjyy;            //���弲��ԭ��
	private String sfxxkn;            //�Ƿ�ѧϰ����
	private String xxknyy;            //ѧϰ����ԭ��
	private String sfxlkr;            //�Ƿ���������
	private String xlkryy;            //��������ԭ��
	private String sfjtkr;            //�Ƿ��ͥ����
	private String jtkryy;            //��ͥ����ԭ��
	private String sfyqtkr;            //�Ƿ�����������
	private String qtkryy;            //��������ԭ��
	private String xm;            //����
	private String nj;            //�꼶
	private String xymc;            //ѧԺ����
	private String zymc;            //רҵ����
	private String bjmc;            //�༶����
	private String hkszdmc;
	private String jtdzmc;
	private String sydmc;
	private String jgmc;
	private String mzmc;
	
	
	
	/**
	 * @return the hkszdmc
	 */
	public String getHkszdmc() {
		return hkszdmc;
	}
	/**
	 * @param hkszdmcҪ���õ� hkszdmc
	 */
	public void setHkszdmc(String hkszdmc) {
		this.hkszdmc = hkszdmc;
	}
	/**
	 * @return the jtdzmc
	 */
	public String getJtdzmc() {
		return jtdzmc;
	}
	/**
	 * @param jtdzmcҪ���õ� jtdzmc
	 */
	public void setJtdzmc(String jtdzmc) {
		this.jtdzmc = jtdzmc;
	}
	/**
	 * @return the sydmc
	 */
	public String getSydmc() {
		return sydmc;
	}
	/**
	 * @param sydmcҪ���õ� sydmc
	 */
	public void setSydmc(String sydmc) {
		this.sydmc = sydmc;
	}
	/**
	 * @return the jgmc
	 */
	public String getJgmc() {
		return jgmc;
	}
	/**
	 * @param jgmcҪ���õ� jgmc
	 */
	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}
	/**
	 * @return the mzmc
	 */
	public String getMzmc() {
		return mzmc;
	}
	/**
	 * @param mzmcҪ���õ� mzmc
	 */
	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xmҪ���õ� xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param njҪ���õ� nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymcҪ���õ� xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * @param zymcҪ���õ� zymc
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmcҪ���õ� bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xhҪ���õ� xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the hkszd
	 */
	public String getHkszd() {
		return hkszd;
	}
	/**
	 * @param hkszdҪ���õ� hkszd
	 */
	public void setHkszd(String hkszd) {
		this.hkszd = hkszd;
	}
	/**
	 * @return the hkszdshen
	 */
	public String getHkszdshen() {
		return hkszdshen;
	}
	/**
	 * @param hkszdshenҪ���õ� hkszdshen
	 */
	public void setHkszdshen(String hkszdshen) {
		this.hkszdshen = hkszdshen;
	}
	/**
	 * @return the hkszdshi
	 */
	public String getHkszdshi() {
		return hkszdshi;
	}
	/**
	 * @param hkszdshiҪ���õ� hkszdshi
	 */
	public void setHkszdshi(String hkszdshi) {
		this.hkszdshi = hkszdshi;
	}
	/**
	 * @return the hkszdxian
	 */
	public String getHkszdxian() {
		return hkszdxian;
	}
	/**
	 * @param hkszdxianҪ���õ� hkszdxian
	 */
	public void setHkszdxian(String hkszdxian) {
		this.hkszdxian = hkszdxian;
	}
	/**
	 * @return the hkszdz
	 */
	public String getHkszdz() {
		return hkszdz;
	}
	/**
	 * @param hkszdzҪ���õ� hkszdz
	 */
	public void setHkszdz(String hkszdz) {
		this.hkszdz = hkszdz;
	}
	/**
	 * @return the jtdz
	 */
	public String getJtdz() {
		return jtdz;
	}
	/**
	 * @param jtdzҪ���õ� jtdz
	 */
	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}
	/**
	 * @return the jtdzshen
	 */
	public String getJtdzshen() {
		return jtdzshen;
	}
	/**
	 * @param jtdzshenҪ���õ� jtdzshen
	 */
	public void setJtdzshen(String jtdzshen) {
		this.jtdzshen = jtdzshen;
	}
	/**
	 * @return the jtdzshi
	 */
	public String getJtdzshi() {
		return jtdzshi;
	}
	/**
	 * @param jtdzshiҪ���õ� jtdzshi
	 */
	public void setJtdzshi(String jtdzshi) {
		this.jtdzshi = jtdzshi;
	}
	/**
	 * @return the jtdzxian
	 */
	public String getJtdzxian() {
		return jtdzxian;
	}
	/**
	 * @param jtdzxianҪ���õ� jtdzxian
	 */
	public void setJtdzxian(String jtdzxian) {
		this.jtdzxian = jtdzxian;
	}
	/**
	 * @return the jtdzz
	 */
	public String getJtdzz() {
		return jtdzz;
	}
	/**
	 * @param jtdzzҪ���õ� jtdzz
	 */
	public void setJtdzz(String jtdzz) {
		this.jtdzz = jtdzz;
	}
	/**
	 * @return the jtlxfs
	 */
	public String getJtlxfs() {
		return jtlxfs;
	}
	/**
	 * @param jtlxfsҪ���õ� jtlxfs
	 */
	public void setJtlxfs(String jtlxfs) {
		this.jtlxfs = jtlxfs;
	}
	/**
	 * @return the syd
	 */
	public String getSyd() {
		return syd;
	}
	/**
	 * @param sydҪ���õ� syd
	 */
	public void setSyd(String syd) {
		this.syd = syd;
	}
	/**
	 * @return the sydshen
	 */
	public String getSydshen() {
		return sydshen;
	}
	/**
	 * @param sydshenҪ���õ� sydshen
	 */
	public void setSydshen(String sydshen) {
		this.sydshen = sydshen;
	}
	/**
	 * @return the sydshi
	 */
	public String getSydshi() {
		return sydshi;
	}
	/**
	 * @param sydshiҪ���õ� sydshi
	 */
	public void setSydshi(String sydshi) {
		this.sydshi = sydshi;
	}
	/**
	 * @return the sydxian
	 */
	public String getSydxian() {
		return sydxian;
	}
	/**
	 * @param sydxianҪ���õ� sydxian
	 */
	public void setSydxian(String sydxian) {
		this.sydxian = sydxian;
	}
	/**
	 * @return the jg
	 */
	public String getJg() {
		return jg;
	}
	/**
	 * @param jgҪ���õ� jg
	 */
	public void setJg(String jg) {
		this.jg = jg;
	}
	/**
	 * @return the jgshen
	 */
	public String getJgshen() {
		return jgshen;
	}
	/**
	 * @param jgshenҪ���õ� jgshen
	 */
	public void setJgshen(String jgshen) {
		this.jgshen = jgshen;
	}
	/**
	 * @return the jgshi
	 */
	public String getJgshi() {
		return jgshi;
	}
	/**
	 * @param jgshiҪ���õ� jgshi
	 */
	public void setJgshi(String jgshi) {
		this.jgshi = jgshi;
	}
	/**
	 * @return the jgxian
	 */
	public String getJgxian() {
		return jgxian;
	}
	/**
	 * @param jgxianҪ���õ� jgxian
	 */
	public void setJgxian(String jgxian) {
		this.jgxian = jgxian;
	}
	/**
	 * @return the hksfjrxx
	 */
	public String getHksfjrxx() {
		return hksfjrxx;
	}
	/**
	 * @param hksfjrxxҪ���õ� hksfjrxx
	 */
	public void setHksfjrxx(String hksfjrxx) {
		this.hksfjrxx = hksfjrxx;
	}
	/**
	 * @return the sfzx
	 */
	public String getSfzx() {
		return sfzx;
	}
	/**
	 * @param sfzxҪ���õ� sfzx
	 */
	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}
	/**
	 * @return the sfsqrd
	 */
	public String getSfsqrd() {
		return sfsqrd;
	}
	/**
	 * @param sfsqrdҪ���õ� sfsqrd
	 */
	public void setSfsqrd(String sfsqrd) {
		this.sfsqrd = sfsqrd;
	}
	/**
	 * @return the djsqssj
	 */
	public String getDjsqssj() {
		return djsqssj;
	}
	/**
	 * @param djsqssjҪ���õ� djsqssj
	 */
	public void setDjsqssj(String djsqssj) {
		this.djsqssj = djsqssj;
	}
	/**
	 * @return the rdsj
	 */
	public String getRdsj() {
		return rdsj;
	}
	/**
	 * @param rdsjҪ���õ� rdsj
	 */
	public void setRdsj(String rdsj) {
		this.rdsj = rdsj;
	}
	/**
	 * @return the zzsj
	 */
	public String getZzsj() {
		return zzsj;
	}
	/**
	 * @param zzsjҪ���õ� zzsj
	 */
	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
	}
	/**
	 * @return the sfssmz
	 */
	public String getSfssmz() {
		return sfssmz;
	}
	/**
	 * @param sfssmzҪ���õ� sfssmz
	 */
	public void setSfssmz(String sfssmz) {
		this.sfssmz = sfssmz;
	}
	/**
	 * @return the ssmz
	 */
	public String getSsmz() {
		return ssmz;
	}
	/**
	 * @param ssmzҪ���õ� ssmz
	 */
	public void setSsmz(String ssmz) {
		this.ssmz = ssmz;
	}
	/**
	 * @return the sfdgsx
	 */
	public String getSfdgsx() {
		return sfdgsx;
	}
	/**
	 * @param sfdgsxҪ���õ� sfdgsx
	 */
	public void setSfdgsx(String sfdgsx) {
		this.sfdgsx = sfdgsx;
	}
	/**
	 * @return the sflspx
	 */
	public String getSflspx() {
		return sflspx;
	}
	/**
	 * @param sflspxҪ���õ� sflspx
	 */
	public void setSflspx(String sflspx) {
		this.sflspx = sflspx;
	}
	/**
	 * @return the pxmc
	 */
	public String getPxmc() {
		return pxmc;
	}
	/**
	 * @param pxmcҪ���õ� pxmc
	 */
	public void setPxmc(String pxmc) {
		this.pxmc = pxmc;
	}
	/**
	 * @return the sfzjxy
	 */
	public String getSfzjxy() {
		return sfzjxy;
	}
	/**
	 * @param sfzjxyҪ���õ� sfzjxy
	 */
	public void setSfzjxy(String sfzjxy) {
		this.sfzjxy = sfzjxy;
	}
	/**
	 * @return the xyzjmc
	 */
	public String getXyzjmc() {
		return xyzjmc;
	}
	/**
	 * @param xyzjmcҪ���õ� xyzjmc
	 */
	public void setXyzjmc(String xyzjmc) {
		this.xyzjmc = xyzjmc;
	}
	/**
	 * @return the cjzjsj
	 */
	public String getCjzjsj() {
		return cjzjsj;
	}
	/**
	 * @param cjzjsjҪ���õ� cjzjsj
	 */
	public void setCjzjsj(String cjzjsj) {
		this.cjzjsj = cjzjsj;
	}
	/**
	 * @return the sfjjkn
	 */
	public String getSfjjkn() {
		return sfjjkn;
	}
	/**
	 * @param sfjjknҪ���õ� sfjjkn
	 */
	public void setSfjjkn(String sfjjkn) {
		this.sfjjkn = sfjjkn;
	}
	/**
	 * @return the jjknyy
	 */
	public String getJjknyy() {
		return jjknyy;
	}
	/**
	 * @param jjknyyҪ���õ� jjknyy
	 */
	public void setJjknyy(String jjknyy) {
		this.jjknyy = jjknyy;
	}
	/**
	 * @return the stsfcj
	 */
	public String getStsfcj() {
		return stsfcj;
	}
	/**
	 * @param stsfcjҪ���õ� stsfcj
	 */
	public void setStsfcj(String stsfcj) {
		this.stsfcj = stsfcj;
	}
	/**
	 * @return the stcjyy
	 */
	public String getStcjyy() {
		return stcjyy;
	}
	/**
	 * @param stcjyyҪ���õ� stcjyy
	 */
	public void setStcjyy(String stcjyy) {
		this.stcjyy = stcjyy;
	}
	/**
	 * @return the sfxxkn
	 */
	public String getSfxxkn() {
		return sfxxkn;
	}
	/**
	 * @param sfxxknҪ���õ� sfxxkn
	 */
	public void setSfxxkn(String sfxxkn) {
		this.sfxxkn = sfxxkn;
	}
	/**
	 * @return the xxknyy
	 */
	public String getXxknyy() {
		return xxknyy;
	}
	/**
	 * @param xxknyyҪ���õ� xxknyy
	 */
	public void setXxknyy(String xxknyy) {
		this.xxknyy = xxknyy;
	}
	/**
	 * @return the sfxlkr
	 */
	public String getSfxlkr() {
		return sfxlkr;
	}
	/**
	 * @param sfxlkrҪ���õ� sfxlkr
	 */
	public void setSfxlkr(String sfxlkr) {
		this.sfxlkr = sfxlkr;
	}
	/**
	 * @return the xlkryy
	 */
	public String getXlkryy() {
		return xlkryy;
	}
	/**
	 * @param xlkryyҪ���õ� xlkryy
	 */
	public void setXlkryy(String xlkryy) {
		this.xlkryy = xlkryy;
	}
	/**
	 * @return the sfjtkr
	 */
	public String getSfjtkr() {
		return sfjtkr;
	}
	/**
	 * @param sfjtkrҪ���õ� sfjtkr
	 */
	public void setSfjtkr(String sfjtkr) {
		this.sfjtkr = sfjtkr;
	}
	/**
	 * @return the jtkryy
	 */
	public String getJtkryy() {
		return jtkryy;
	}
	/**
	 * @param jtkryyҪ���õ� jtkryy
	 */
	public void setJtkryy(String jtkryy) {
		this.jtkryy = jtkryy;
	}
	/**
	 * @return the sfyqtkr
	 */
	public String getSfyqtkr() {
		return sfyqtkr;
	}
	/**
	 * @param sfyqtkrҪ���õ� sfyqtkr
	 */
	public void setSfyqtkr(String sfyqtkr) {
		this.sfyqtkr = sfyqtkr;
	}
	/**
	 * @return the qtkryy
	 */
	public String getQtkryy() {
		return qtkryy;
	}
	/**
	 * @param qtkryyҪ���õ� qtkryy
	 */
	public void setQtkryy(String qtkryy) {
		this.qtkryy = qtkryy;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param typeҪ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pagesҪ���õ� pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModelҪ���õ� searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}

}
