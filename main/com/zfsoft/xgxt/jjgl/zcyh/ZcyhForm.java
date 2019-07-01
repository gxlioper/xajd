/**
 * @部门:学工产品事业部
 * @日期：2014-8-28 上午11:07:20 
 */  
package com.zfsoft.xgxt.jjgl.zcyh;

import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.AutoArrayList;
import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

import java.util.ArrayList;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-29 下午01:48:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class ZcyhForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private Pages pages = new Pages();
	
	private static final long serialVersionUID = 1L;

	private String type;
	
	private String yhm;//用户名
	
	private String mm;//密码 
	
	private String xm;//姓名
	
	private String sfzh;//身份证号
	
	private String lxdh; //联系电话
	
	private String jtzz; //家庭住址
	
	private String gzdw; //工作单位
	
	private String fj; //附件;
	
	private String photo;//头像
	
	private String zt;//注册信息状态
	
	private String zcsj;//注册时间

	private String hmdsj;
	private String hmdyy;

	private String xb;
	private ArrayList<ZnxxModel> znxxModelList = new AutoArrayList(ZnxxModel.class);
	/**
	 * @return the yhm
	 */
	public String getYhm() {
		return yhm;
	}

	/**
	 * @param yhm要设置的 yhm
	 */
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	/**
	 * @return the mm
	 */
	public String getMm() {
		return mm;
	}

	/**
	 * @param mm要设置的 mm
	 */
	public void setMm(String mm) {
		this.mm = mm;
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
	 * @return the sfzh
	 */
	public String getSfzh() {
		return sfzh;
	}

	/**
	 * @param sfzh要设置的 sfzh
	 */
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}

	/**
	 * @param lxdh要设置的 lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	/**
	 * @return the jtzz
	 */
	public String getJtzz() {
		return jtzz;
	}

	/**
	 * @param jtzz要设置的 jtzz
	 */
	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}

	/**
	 * @return the gzdw
	 */
	public String getGzdw() {
		return gzdw;
	}

	/**
	 * @param gzdw要设置的 gzdw
	 */
	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}

	/**
	 * @return the fj
	 */
	public String getFj() {
		return fj;
	}

	/**
	 * @param fj要设置的 fj
	 */
	public void setFj(String fj) {
		this.fj = fj;
	}

	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * @param photo要设置的 photo
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * @return the zt
	 */
	public String getZt() {
		return zt;
	}

	/**
	 * @param zt要设置的 zt
	 */
	public void setZt(String zt) {
		this.zt = zt;
	}

	/**
	 * @return the zcsj
	 */
	public String getZcsj() {
		return zcsj;
	}

	/**
	 * @param zcsj要设置的 zcsj
	 */
	public void setZcsj(String zcsj) {
		this.zcsj = zcsj;
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
	 * @return the hmdyy
	 */
	public String getHmdyy() {
		return hmdyy;
	}

	/**
	 * @param hmdyy要设置的 hmdyy
	 */
	public void setHmdyy(String hmdyy) {
		this.hmdyy = hmdyy;
	}

	/**
	 * @return the hmdsj
	 */
	public String getHmdsj() {
		return hmdsj;
	}

	/**
	 * @param hmdsj要设置的 hmdsj
	 */
	public void setHmdsj(String hmdsj) {
		this.hmdsj = hmdsj;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public ArrayList<ZnxxModel> getZnxxModelList() {
		return znxxModelList;
	}

	public void setZnxxModelList(ArrayList<ZnxxModel> znxxModelList) {
		this.znxxModelList = znxxModelList;
	}
}
