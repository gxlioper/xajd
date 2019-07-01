/**
 * @部门:学工产品事业部
 * @日期：2014-2-17 上午09:44:17 
 */
package com.zfsoft.xgxt.xsxx.fbgl.bbgl;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 编班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-2-17 上午09:44:17
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglBbglForm extends ActionForm {
	private static final long serialVersionUID = -4479217565687401435L;
	private String bjdm;// varchar2(20) n 班级代码
	private String zydm;// varchar2(8) y 专业代码
	private String bmdm;// varchar2(10) y 部门代码
	private String bjmc;// varchar2(64) y 班级名称
	private String nj;// number n 年级
	private String bjbh;// number y 班级编号
	private String sfytj;// varchar2(1) y 是否已提交
	private String bjrssx;// number y 班级人数上限
	private String pzgzid;
	private String pycc;// varchar2(64) y 培养层次
	private String xz;// varchar2(4) y 学制
	private String isNewLsh;

	private String pk;
	private String xydm;
	private Map<String, String> rowData = new HashMap<String, String>();
	private String type;
	private String bbzt;//编班状态
	private String fbzt;//分班状态
	private String xhzt;//学号状态
	private String zymc;
	private String bmmc;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//页面增加获取下一个对应代码 偏移量（第一行 偏移量为1）
	private String skewing="0";
	public void clean() {
		this.bjdm = "";
		this.zydm = "";
		this.bmdm = "";
		this.nj = "";
		this.pycc = "";
		this.xz = "";
		this.bjrssx="";
		this.bmmc="";
		this.zymc="";
		this.isNewLsh="";
	}
	/**
	 * 
	 * @描述: 通过pk获取对应pk组成值
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-5 下午05:24:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String,String> getObject(String pk){
		Map<String,String> map=new HashMap<String, String>();
		if(pk.indexOf("-")!=-1){
			String object[]=pk.split("-");
			map.put("nj",object[0]);
			map.put("bmdm", object[1]);
			map.put("zydm", object[2]);
			map.put("pycc", object[3]);
			map.put("xz", object[4]);
		}
		return map;
	}
	/**
	 * @return the pzgzid
	 */
	public String getPzgzid() {
		return pzgzid;
	}

	/**
	 * @param pzgzid要设置的
	 *            pzgzid
	 */
	public void setPzgzid(String pzgzid) {
		this.pzgzid = pzgzid;
	}

	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}

	/**
	 * @param bjdm要设置的
	 *            bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	/**
	 * @return the zydm
	 */
	public String getZydm() {
		return zydm;
	}

	/**
	 * @param zydm要设置的
	 *            zydm
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	/**
	 * @return the bmdm
	 */
	public String getBmdm() {
		if (StringUtils.isNull(bmdm)) {
			bmdm = this.getXydm();
		}
		return bmdm;
	}

	/**
	 * @param bmdm要设置的
	 *            bmdm
	 */
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}

	/**
	 * @param bjmc要设置的
	 *            bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}

	/**
	 * @param nj要设置的
	 *            nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}

	/**
	 * @return the bjbh
	 */
	public String getBjbh() {
		return bjbh;
	}

	/**
	 * @param bjbh要设置的
	 *            bjbh
	 */
	public void setBjbh(String bjbh) {
		this.bjbh = bjbh;
	}

	/**
	 * @return the sfytj
	 */
	public String getSfytj() {
		return sfytj;
	}

	/**
	 * @param sfytj要设置的
	 *            sfytj
	 */
	public void setSfytj(String sfytj) {
		this.sfytj = sfytj;
	}

	/**
	 * @return the bjrssx
	 */
	public String getBjrssx() {
		if (StringUtils.isNull(bjrssx)) {
			bjrssx = rowData.get("rssx");
		}
		return bjrssx;
	}

	/**
	 * @param bjrssx要设置的
	 *            bjrssx
	 */
	public void setBjrssx(String bjrssx) {
		if("null".equals(bjrssx)||"无上限".equals(bjrssx)){
			bjrssx=null;
		}
		this.bjrssx = bjrssx;
	}

	/**
	 * @return the rowData
	 */
	public Map<String, String> getRowData() {
		return rowData;
	}

	/**
	 * @param rowData要设置的
	 *            rowData
	 */
	public void setRowData(Map<String, String> rowData) {
		this.rowData = rowData;
	}

	/**
	 * @return the pk
	 */
	public String getPk() {
		return pk;
	}

	/**
	 * @param pk要设置的
	 *            pk
	 */
	public void setPk(String pk) {
		if(StringUtils.isNotNull(pk)){
			Map<String,String> map=getObject(pk);
			if(map.size()>0){
				setNj(map.get("nj"));
				setBmdm(map.get("bmdm"));
				setPycc(map.get("pycc"));
				setXz(map.get("xz"));
				setZydm(map.get("zydm"));
			}
		}
		this.pk = pk;
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
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}

	/**
	 * @param xydm要设置的
	 *            xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	/**
	 * @return the bbzt
	 */
	public String getBbzt() {
		return bbzt;
	}

	/**
	 * @param bbzt要设置的
	 *            bbzt
	 */
	public void setBbzt(String bbzt) {
		this.bbzt = bbzt;
	}

	/**
	 * @return the pycc
	 */
	public String getPycc() {
		return pycc;
	}

	/**
	 * @param pycc要设置的
	 *            pycc
	 */
	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	/**
	 * @return the xz
	 */
	public String getXz() {
		return xz;
	}

	/**
	 * @param xz要设置的
	 *            xz
	 */
	public void setXz(String xz) {
		this.xz = xz;
	}

	/**
	 * @return the skewing
	 */
	public String getSkewing() {
		return skewing;
	}

	/**
	 * @param skewing要设置的 skewing
	 */
	public void setSkewing(String skewing) {
		this.skewing = skewing;
	}
	/**
	 * @return the fbzt
	 */
	public String getFbzt() {
		return fbzt;
	}
	/**
	 * @param fbzt要设置的 fbzt
	 */
	public void setFbzt(String fbzt) {
		this.fbzt = fbzt;
	}
	/**
	 * @return the xhzt
	 */
	public String getXhzt() {
		return xhzt;
	}
	/**
	 * @param xhzt要设置的 xhzt
	 */
	public void setXhzt(String xhzt) {
		this.xhzt = xhzt;
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
	 * @return the bmmc
	 */
	public String getBmmc() {
		return bmmc;
	}
	/**
	 * @param bmmc要设置的 bmmc
	 */
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public String getIsNewLsh() {
		return isNewLsh;
	}
	public void setIsNewLsh(String isNewLsh) {
		this.isNewLsh = isNewLsh;
	}
	
}
