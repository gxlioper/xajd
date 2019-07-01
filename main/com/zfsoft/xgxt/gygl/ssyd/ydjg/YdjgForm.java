/**
 * @部门:学工产品事业部
 * @日期：2013-7-25 下午4:14:57 
 */  
package com.zfsoft.xgxt.gygl.ssyd.ydjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @模块名称: 公寓管理-宿舍异动
 * @类功能描述:宿舍异动结果
 * @作者： qilm
 * @时间： 2013-9-29
 * @版本： V1.0
 */
public class YdjgForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	private String ssydid;	//宿舍异动id
	private String xh;		//学号
	private String czsj;	//记录时间
	private String xn;		//学年
	private String xq;		//学期
	private String ssydlx;	//宿舍异动类型
	private String tstzyy;	//退宿/调整原因
	private String tstzsj;	//退宿/调整时间
	private String bz;		//备注
	private String nj;		//年级
	private String xydm;	//学院代码
	private String zydm;	//专业代码
	private String bjdm;	//班级代码
	private String ydqlddm;	//异动前楼栋代码
	private String ydqldmc;	//异动前楼栋名称
	private String ydqqsh;	//异动前寝室号
	private String ydqcwh;	//异动前床位号
	private String ydqqsrzsj;	//异动前寝室入住时间
	private String ydhlddm;	//异动后楼栋代码
	private String ydhldmc;	//异动后楼栋名称
	private String ydhqsh;	//异动后寝室号
	private String ydhcwh;	//异动后床位号
	private String ydhnj;	//异动后寝室原所属年级
	private String ydhxydm;	//异动后寝室原所属学院代码
	private String ydhzydm;	//异动后寝室原所属专业代码
	private String ydhbjdm;	//异动后寝室原所属班级代码
	private String sfcwcsh;	//是否床位初始化
	private String sjly;	//数据来源
	private String ssydsqid;	//宿舍异动申請id
	private String fjxx; //附件信息
	private String gdfjxx; //更多附件信息
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;

	//调整时间和类型，用于页面传递值
	private String tzsssj;
	private String tzssyy;
	private String cwxx;
	

	//入住时间、类型、床位信息，用于页面传递值
	private String rzsssj;
	private String rzssyy;
	private String rzcwxx;
	
	private String tjsqrxm; //提交申请人姓名
	
	private String jflx;
	private String zsfje;

	public String getTjsqrxm() {
		return tjsqrxm;
	}
	public void setTjsqrxm(String tjsqrxm) {
		this.tjsqrxm = tjsqrxm;
	}
	
	public String getYdhnj() {
		return ydhnj;
	}

	public void setYdhnj(String ydhnj) {
		this.ydhnj = ydhnj;
	}

	public String getYdhxydm() {
		return ydhxydm;
	}

	public void setYdhxydm(String ydhxydm) {
		this.ydhxydm = ydhxydm;
	}

	public String getYdhzydm() {
		return ydhzydm;
	}

	public void setYdhzydm(String ydhzydm) {
		this.ydhzydm = ydhzydm;
	}

	public String getYdhbjdm() {
		return ydhbjdm;
	}

	public void setYdhbjdm(String ydhbjdm) {
		this.ydhbjdm = ydhbjdm;
	}

	public String getCwxx() {
		return cwxx;
	}

	public void setCwxx(String cwxx) {
		this.cwxx = cwxx;
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
	public String getSsydid() {
		return ssydid;
	}

	public void setSsydid(String ssydid) {
		this.ssydid = ssydid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getCzsj() {
		return czsj;
	}

	public void setCzsj(String czsj) {
		this.czsj = czsj;
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

	public String getSsydlx() {
		return ssydlx;
	}

	public void setSsydlx(String ssydlx) {
		this.ssydlx = ssydlx;
	}

	public String getTstzyy() {
		return tstzyy;
	}

	public void setTstzyy(String tstzyy) {
		this.tstzyy = tstzyy;
	}

	public String getTstzsj() {
		return tstzsj;
	}

	public void setTstzsj(String tstzsj) {
		this.tstzsj = tstzsj;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
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

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getYdqlddm() {
		return ydqlddm;
	}

	public void setYdqlddm(String ydqlddm) {
		this.ydqlddm = ydqlddm;
	}

	public String getYdqldmc() {
		return ydqldmc;
	}

	public void setYdqldmc(String ydqldmc) {
		this.ydqldmc = ydqldmc;
	}

	public String getYdqqsh() {
		return ydqqsh;
	}

	public void setYdqqsh(String ydqqsh) {
		this.ydqqsh = ydqqsh;
	}

	public String getYdqcwh() {
		return ydqcwh;
	}

	public void setYdqcwh(String ydqcwh) {
		this.ydqcwh = ydqcwh;
	}

	public String getYdhlddm() {
		return ydhlddm;
	}

	public void setYdhlddm(String ydhlddm) {
		this.ydhlddm = ydhlddm;
	}

	public String getYdhldmc() {
		return ydhldmc;
	}

	public void setYdhldmc(String ydhldmc) {
		this.ydhldmc = ydhldmc;
	}

	public String getYdhqsh() {
		return ydhqsh;
	}

	public void setYdhqsh(String ydhqsh) {
		this.ydhqsh = ydhqsh;
	}

	public String getYdhcwh() {
		return ydhcwh;
	}

	public void setYdhcwh(String ydhcwh) {
		this.ydhcwh = ydhcwh;
	}

	public String getSfcwcsh() {
		return sfcwcsh;
	}

	public void setSfcwcsh(String sfcwcsh) {
		this.sfcwcsh = sfcwcsh;
	}

	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSsydsqid() {
		return ssydsqid;
	}

	public void setSsydsqid(String ssydsqid) {
		this.ssydsqid = ssydsqid;
	}

	public String getYdqqsrzsj() {
		return ydqqsrzsj;
	}

	public void setYdqqsrzsj(String ydqqsrzsj) {
		this.ydqqsrzsj = ydqqsrzsj;
	}

	public String getTzsssj() {
		return tzsssj;
	}

	public void setTzsssj(String tzsssj) {
		this.tzsssj = tzsssj;
	}

	public String getTzssyy() {
		return tzssyy;
	}

	public void setTzssyy(String tzssyy) {
		this.tzssyy = tzssyy;
	}
	public String getFjxx() {
		return fjxx;
	}
	public void setFjxx(String fjxx) {
		this.fjxx = fjxx;
	}
	public String getGdfjxx() {
		return gdfjxx;
	}
	public void setGdfjxx(String gdfjxx) {
		this.gdfjxx = gdfjxx;
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
