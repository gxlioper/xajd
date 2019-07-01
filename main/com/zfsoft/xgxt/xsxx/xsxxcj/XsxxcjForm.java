/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午04:02:06 
 */  
package com.zfsoft.xgxt.xsxx.xsxxcj;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(学生信息采集--天津职业大学) 
 * @作者： cmj [工号：913]
 * @时间： 2013-7-30 下午04:02:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxxcjForm extends ActionForm {
	
private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String xh;            //学号
	private String hkszd;            //户口所在地
	private String hkszdshen;            //户口所在地省
	private String hkszdshi;            //户口所在地市
	private String hkszdxian;            //户口所在地县
	private String hkszdz;            //户口所在地（县以下）
	private String jtdz;            //家庭地址
	private String jtdzshen;            //家庭地址省
	private String jtdzshi;            //家庭地址市
	private String jtdzxian;            //家庭地址县
	private String jtdzz;            //家庭地址（县以下）
	private String jtlxfs;            //家庭联系方式
	private String syd;            //生源地
	private String sydshen;            //生源地省
	private String sydshi;            //生源地市
	private String sydxian;            //生源地县
	private String jg;            //籍贯
	private String jgshen;            //籍贯省
	private String jgshi;            //籍贯市
	private String jgxian;            //籍贯县
	private String hksfjrxx;            //户口是否迁入学校
	private String sfzx;            //是否住校
	private String sfsqrd;            //是否申请入党
	private String djsqssj;            //递交申请书时间
	private String rdsj;            //入党时间
	private String zzsj;            //转正时间
	private String sfssmz;            //是否少数名族
	private String ssmz;            //少数名族
	private String sfdgsx;            //是否顶岗实习
	private String sflspx;            //是否临时培训
	private String pxmc;            //培训名称
	private String sfzjxy;            //是否宗教信仰
	private String xyzjmc;            //信仰宗教名称
	private String cjzjsj;            //参加宗教时间
	private String sfjjkn;            //是否经济困难
	private String jjknyy;            //经济困难原因
	private String stsfcj;            //身体是否疾病
	private String stcjyy;            //身体疾病原因
	private String sfxxkn;            //是否学习困难
	private String xxknyy;            //学习困难原因
	private String sfxlkr;            //是否心理困难
	private String xlkryy;            //心理困扰原因
	private String sfjtkr;            //是否家庭困扰
	private String jtkryy;            //家庭困扰原因
	private String sfyqtkr;            //是否有其他困扰
	private String qtkryy;            //其他困扰原因
	private String xm;            //姓名
	private String nj;            //年级
	private String xymc;            //学院名称
	private String zymc;            //专业名称
	private String bjmc;            //班级名称
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
	 * @param hkszdmc要设置的 hkszdmc
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
	 * @param jtdzmc要设置的 jtdzmc
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
	 * @param sydmc要设置的 sydmc
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
	 * @param jgmc要设置的 jgmc
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
	 * @param mzmc要设置的 mzmc
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
	 * @param xm要设置的 xm
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
	 * @param nj要设置的 nj
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
	 * @param xymc要设置的 xymc
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
	 * @param zymc要设置的 zymc
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
	 * @param bjmc要设置的 bjmc
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
	 * @param xh要设置的 xh
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
	 * @param hkszd要设置的 hkszd
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
	 * @param hkszdshen要设置的 hkszdshen
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
	 * @param hkszdshi要设置的 hkszdshi
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
	 * @param hkszdxian要设置的 hkszdxian
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
	 * @param hkszdz要设置的 hkszdz
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
	 * @param jtdz要设置的 jtdz
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
	 * @param jtdzshen要设置的 jtdzshen
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
	 * @param jtdzshi要设置的 jtdzshi
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
	 * @param jtdzxian要设置的 jtdzxian
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
	 * @param jtdzz要设置的 jtdzz
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
	 * @param jtlxfs要设置的 jtlxfs
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
	 * @param syd要设置的 syd
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
	 * @param sydshen要设置的 sydshen
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
	 * @param sydshi要设置的 sydshi
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
	 * @param sydxian要设置的 sydxian
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
	 * @param jg要设置的 jg
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
	 * @param jgshen要设置的 jgshen
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
	 * @param jgshi要设置的 jgshi
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
	 * @param jgxian要设置的 jgxian
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
	 * @param hksfjrxx要设置的 hksfjrxx
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
	 * @param sfzx要设置的 sfzx
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
	 * @param sfsqrd要设置的 sfsqrd
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
	 * @param djsqssj要设置的 djsqssj
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
	 * @param rdsj要设置的 rdsj
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
	 * @param zzsj要设置的 zzsj
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
	 * @param sfssmz要设置的 sfssmz
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
	 * @param ssmz要设置的 ssmz
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
	 * @param sfdgsx要设置的 sfdgsx
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
	 * @param sflspx要设置的 sflspx
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
	 * @param pxmc要设置的 pxmc
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
	 * @param sfzjxy要设置的 sfzjxy
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
	 * @param xyzjmc要设置的 xyzjmc
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
	 * @param cjzjsj要设置的 cjzjsj
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
	 * @param sfjjkn要设置的 sfjjkn
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
	 * @param jjknyy要设置的 jjknyy
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
	 * @param stsfcj要设置的 stsfcj
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
	 * @param stcjyy要设置的 stcjyy
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
	 * @param sfxxkn要设置的 sfxxkn
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
	 * @param xxknyy要设置的 xxknyy
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
	 * @param sfxlkr要设置的 sfxlkr
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
	 * @param xlkryy要设置的 xlkryy
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
	 * @param sfjtkr要设置的 sfjtkr
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
	 * @param jtkryy要设置的 jtkryy
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
	 * @param sfyqtkr要设置的 sfyqtkr
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
	 * @param qtkryy要设置的 qtkryy
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
	 * @param type要设置的 type
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
	 * @param pages要设置的 pages
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
	 * @param searchModel要设置的 searchModel
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
