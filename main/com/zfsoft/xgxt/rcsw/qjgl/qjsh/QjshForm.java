/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:00:24 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjsh;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 请假管理模块
 * @类功能描述:form
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:00:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QjshForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String qjsqid;
	private String qjlxid;
	private String qjts;
	private String kssj;
	private String jssj;
	private String qjzt;
	private String xh;
	private String xn;
	private String xq;
	private String xqmc;
	private String qjsy;
	private String splcid;
	private String shzt;
	private String sqsj;
	private String kcbhs;
	private String qjbh;//请假编号
	private String qjjs;
	private String sflx;
	private String sflxmc;
	private String bz;//备注
	private String jzdh;//家长电话
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	//下载相关
	private FormFile formfile;
	private String filepath;
	
	private String type;
	private String lcxx;
	//审核相关
	private String shyj;
	private String gwid;
	private String shid;
	private String shjg;
	private String thgw;
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String qjlxmc;
	
	private String jhrxm;//监护人姓名
	private String jhrlxfs;//监护人联系方式
	private String jtgj;//交通工具
	private String mdd;//目的地
	private String jtgjmc;
	//徐州医药高等专科学院
	private String xnxw;
	
	
	public String getXnxw() {
		return xnxw;
	}

	public void setXnxw(String xnxw) {
		this.xnxw = xnxw;
	}

	/**
	 * @return the qjlxmc
	 */
	public String getQjlxmc() {
		return qjlxmc;
	}

	/**
	 * @param qjlxmc the qjlxmc to set
	 */
	public void setQjlxmc(String qjlxmc) {
		this.qjlxmc = qjlxmc;
	}

	/**
	 * @return the qjsqid
	 */
	public String getQjsqid() {
		return qjsqid;
	}

	/**
	 * @param qjsqid要设置的
	 *            qjsqid
	 */
	public void setQjsqid(String qjsqid) {
		this.qjsqid = qjsqid;
	}

	/**
	 * @return the qjlxid
	 */
	public String getQjlxid() {
		return qjlxid;
	}

	/**
	 * @param qjlxid要设置的
	 *            qjlxid
	 */
	public void setQjlxid(String qjlxid) {
		this.qjlxid = qjlxid;
	}

	/**
	 * @return the qjts
	 */
	public String getQjts() {
		return qjts;
	}

	/**
	 * @param qjts要设置的
	 *            qjts
	 */
	public void setQjts(String qjts) {
		this.qjts = qjts;
	}

	/**
	 * @return the kssj
	 */
	public String getKssj() {
		return kssj;
	}

	/**
	 * @param kssj要设置的
	 *            kssj
	 */
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}

	/**
	 * @param jssj要设置的
	 *            jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	/**
	 * @return the qjzt
	 */
	public String getQjzt() {
		return qjzt;
	}

	/**
	 * @param qjzt要设置的
	 *            qjzt
	 */
	public void setQjzt(String qjzt) {
		this.qjzt = qjzt;
	}

	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}

	/**
	 * @param xh要设置的
	 *            xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}

	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}

	/**
	 * @param xn要设置的
	 *            xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}

	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}

	/**
	 * @param xq要设置的
	 *            xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}

	/**
	 * @return the qjsy
	 */
	public String getQjsy() {
		return qjsy;
	}

	/**
	 * @param qjsy要设置的
	 *            qjsy
	 */
	public void setQjsy(String qjsy) {
		this.qjsy = qjsy;
	}

	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}

	/**
	 * @param splcid要设置的
	 *            splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}


	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}

	/**
	 * @param pages要设置的
	 *            pages
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
	 * @param searchModel要设置的
	 *            searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type要设置的
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the lcxx
	 */
	public String getLcxx() {
		return lcxx;
	}

	/**
	 * @param lcxx要设置的 lcxx
	 */
	public void setLcxx(String lcxx) {
		this.lcxx = lcxx;
	}

	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}

	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}

	/**
	 * @param shyj要设置的 shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}

	/**
	 * @param gwid要设置的 gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}

	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}

	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}

	/**
	 * @param sqsj要设置的 sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}

	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}

	/**
	 * @param shid要设置的 shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}

	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}

	/**
	 * @param shjg要设置的 shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}

	/**
	 * @param thgw要设置的 thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getGwids() {
		return gwids;
	}

	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}

	public String[] getXhs() {
		return xhs;
	}

	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	/**
	 * @return the formfile
	 */
	public FormFile getFormfile() {
		return formfile;
	}

	/**
	 * @param formfile要设置的 formfile
	 */
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}

	public String getQjjs() {
		return qjjs;
	}

	public void setQjjs(String qjjs) {
		this.qjjs = qjjs;
	}

	public String getSflx() {
		return sflx;
	}

	public void setSflx(String sflx) {
		this.sflx = sflx;
	}

	public String getSflxmc() {
		return sflxmc;
	}

	public void setSflxmc(String sflxmc) {
		this.sflxmc = sflxmc;
	}

	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @param filepath要设置的 filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * @return the kcbhs
	 */
	public String getKcbhs() {
		return kcbhs;
	}

	/**
	 * @param kcbhs要设置的 kcbhs
	 */
	public void setKcbhs(String kcbhs) {
		this.kcbhs = kcbhs;
	}

	/**
	 * @return the qjbh
	 */
	public String getQjbh() {
		return qjbh;
	}

	/**
	 * @param qjbh要设置的 qjbh
	 */
	public void setQjbh(String qjbh) {
		this.qjbh = qjbh;
	}

	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return the jzdh
	 */
	public String getJzdh() {
		return jzdh;
	}

	/**
	 * @param jzdh要设置的 jzdh
	 */
	public void setJzdh(String jzdh) {
		this.jzdh = jzdh;
	}

	/**
	 * @return the jhrxm
	 */
	public String getJhrxm() {
		return jhrxm;
	}

	/**
	 * @param jhrxm要设置的 jhrxm
	 */
	public void setJhrxm(String jhrxm) {
		this.jhrxm = jhrxm;
	}

	/**
	 * @return the jhrlxfs
	 */
	public String getJhrlxfs() {
		return jhrlxfs;
	}

	/**
	 * @param jhrlxfs要设置的 jhrlxfs
	 */
	public void setJhrlxfs(String jhrlxfs) {
		this.jhrlxfs = jhrlxfs;
	}

	/**
	 * @return the jtgj
	 */
	public String getJtgj() {
		return jtgj;
	}

	/**
	 * @param jtgj要设置的 jtgj
	 */
	public void setJtgj(String jtgj) {
		this.jtgj = jtgj;
	}

	/**
	 * @return the mdd
	 */
	public String getMdd() {
		return mdd;
	}

	/**
	 * @param mdd要设置的 mdd
	 */
	public void setMdd(String mdd) {
		this.mdd = mdd;
	}

	/**
	 * @return the jtgjmc
	 */
	public String getJtgjmc() {
		return jtgjmc;
	}

	/**
	 * @param jtgjmc要设置的 jtgjmc
	 */
	public void setJtgjmc(String jtgjmc) {
		this.jtgjmc = jtgjmc;
	}
	
}
