/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:00:24 
 */
package com.zfsoft.xgxt.gygl.ssyd.ydsq;

import org.apache.struts.action.ActionForm;

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

public class YdsqForm extends ActionForm {	
	private static final long serialVersionUID = 1L;
	
	private String ssydsqid;//宿舍异动申请id
	private String xh;//学号
	private String sqsj;//申请时间
	private String splcid;//审批流程id
	private String xn;//学年
	private String xq;//学期
	private String xqmc;
	private String ssydlx;//00：退宿操作，01调整操作，02实习留宿（苏州工业职业技术学院），03入住操作
	private String ssydlxmc;
	private String tstzyy;//退宿/调整/实习留宿（苏州工业职业技术学院）/入住原因(关联原因类型)
	private String tstzyymc;//
	private String tstzsj;//退宿/调整/实习留宿（苏州工业职业技术学院）/入住时间
	private String tzqlddm;	//调整前楼栋代码
	private String tzqldmc;	//调整前楼栋名称
	private String tzqqsh;	//调整前寝室号
	private String tzqcwh;	//调整前床位号
	private String tzhlddm;	//调整后楼栋代码
	private String tzhldmc;	//调整后楼栋名称
	private String tzhqsh;	//调整后寝室号
	private String tzhcwh;	//调整后床位号
	private String tjsqrxm; //提交申请人姓名
	private String bz;//备注
	private String sfcwcsh;//是否床位初始化

	private String shzt;//审核状态
	private String shztmc;//审核状态名称
	private String cwxx; //床位信息ID
	
	private String fjxx; //附件信息
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	//调整时间和类型，用于页面传递值
	private String tzsssj;
	private String tzssyy;
	
	//入住时间、类型、床位信息，用于页面传递值
	private String rzsssj;
	private String rzssyy;
	private String rzcwxx;
	
	private String jflx;
	private String zsfje;

	private ExportModel exportModel = new ExportModel();
	
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @return the ssydsqid
	 */
	public String getSsydsqid() {
		return ssydsqid;
	}
	/**
	 * @param ssydsqid要设置的 ssydsqid
	 */
	public void setSsydsqid(String ssydsqid) {
		this.ssydsqid = ssydsqid;
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
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcid要设置的 splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
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
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the ssydlx
	 */
	public String getSsydlx() {
		return ssydlx;
	}
	/**
	 * @param ssydlx要设置的 ssydlx
	 */
	public void setSsydlx(String ssydlx) {
		this.ssydlx = ssydlx;
	}
	/**
	 * @return the tstzyy
	 */
	public String getTstzyy() {
		return tstzyy;
	}
	/**
	 * @param tstzyy要设置的 tstzyy
	 */
	public void setTstzyy(String tstzyy) {
		this.tstzyy = tstzyy;
	}
	/**
	 * @return the tstzsj
	 */
	public String getTstzsj() {
		return tstzsj;
	}
	/**
	 * @param tstzsj要设置的 tstzsj
	 */
	public void setTstzsj(String tstzsj) {
		this.tstzsj = tstzsj;
	}
	
	public String getTzhlddm() {
		return tzhlddm;
	}
	public void setTzhlddm(String tzhlddm) {
		this.tzhlddm = tzhlddm;
	}
	public String getTzhqsh() {
		return tzhqsh;
	}
	public void setTzhqsh(String tzhqsh) {
		this.tzhqsh = tzhqsh;
	}
	public String getTzhcwh() {
		return tzhcwh;
	}
	public void setTzhcwh(String tzhcwh) {
		this.tzhcwh = tzhcwh;
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
	 * @return the sfcwcsh
	 */
	public String getSfcwcsh() {
		return sfcwcsh;
	}
	/**
	 * @param sfcwcsh要设置的 sfcwcsh
	 */
	public void setSfcwcsh(String sfcwcsh) {
		this.sfcwcsh = sfcwcsh;
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
	 * @return the shztmc
	 */
	public String getShztmc() {
		return shztmc;
	}
	/**
	 * @param shztmc要设置的 shztmc
	 */
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
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
	 * @return the ssydlxmc
	 */
	public String getSsydlxmc() {
		return ssydlxmc;
	}
	/**
	 * @param ssydlxmc要设置的 ssydlxmc
	 */
	public void setSsydlxmc(String ssydlxmc) {
		this.ssydlxmc = ssydlxmc;
	}
	/**
	 * @return the tzsssj
	 */
	public String getTzsssj() {
		return tzsssj;
	}
	/**
	 * @param tzsssj要设置的 tzsssj
	 */
	public void setTzsssj(String tzsssj) {
		this.tzsssj = tzsssj;
	}
	/**
	 * @return the tzssyy
	 */
	public String getTzssyy() {
		return tzssyy;
	}
	/**
	 * @param tzssyy要设置的 tzssyy
	 */
	public void setTzssyy(String tzssyy) {
		this.tzssyy = tzssyy;
	}
	public String getRzsssj() {
		return rzsssj;
	}
	public void setRzsssj(String rzsssj) {
		this.rzsssj = rzsssj;
	}
	public String getRzssyy() {
		return rzssyy;
	}
	public void setRzssyy(String rzssyy) {
		this.rzssyy = rzssyy;
	}
	public String getRzcwxx() {
		return rzcwxx;
	}
	public void setRzcwxx(String rzcwxx) {
		this.rzcwxx = rzcwxx;
	}
	/**
	 * @return the tstzyymc
	 */
	public String getTstzyymc() {
		return tstzyymc;
	}
	/**
	 * @param tstzyymc要设置的 tstzyymc
	 */
	public void setTstzyymc(String tstzyymc) {
		this.tstzyymc = tstzyymc;
	}
	public String getTzqlddm() {
		return tzqlddm;
	}
	public void setTzqlddm(String tzqlddm) {
		this.tzqlddm = tzqlddm;
	}
	public String getTzqldmc() {
		return tzqldmc;
	}
	public void setTzqldmc(String tzqldmc) {
		this.tzqldmc = tzqldmc;
	}
	public String getTzqqsh() {
		return tzqqsh;
	}
	public void setTzqqsh(String tzqqsh) {
		this.tzqqsh = tzqqsh;
	}
	public String getTzqcwh() {
		return tzqcwh;
	}
	public void setTzqcwh(String tzqcwh) {
		this.tzqcwh = tzqcwh;
	}
	public String getTzhldmc() {
		return tzhldmc;
	}
	public void setTzhldmc(String tzhldmc) {
		this.tzhldmc = tzhldmc;
	}
	public String getCwxx() {
		return cwxx;
	}
	public void setCwxx(String cwxx) {
		this.cwxx = cwxx;
	}
	/**
	 * @return the tjsqrxm
	 */
	public String getTjsqrxm() {
		return tjsqrxm;
	}
	/**
	 * @param tjsqrxm要设置的 tjsqrxm
	 */
	public void setTjsqrxm(String tjsqrxm) {
		this.tjsqrxm = tjsqrxm;
	}
	public String getFjxx() {
		return fjxx;
	}
	public void setFjxx(String fjxx) {
		this.fjxx = fjxx;
	}
	public String getJflx() {
		return jflx;
	}
	public void setJflx(String jflx) {
		this.jflx = jflx;
	}
	public String getZsfje() {
		return zsfje;
	}
	public void setZsfje(String zsfje) {
		this.zsfje = zsfje;
	}
	
}
