/**
 * @部门:学工产品事业部
 * @日期：2013-12-19 下午05:21:26 
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: 家庭成员信息
 * @作者： ligl
 * @时间： 2013-12-19 下午05:21:26
 * @版本： V1.0
 * @修改记录:
 */

public class JtcyxxModel implements java.io.Serializable {
	private static final long serialVersionUID = -7957060390800045792L;
	private String guid;
	private String cygx ;//成员关系 
	private String cyxm ;//成员姓名 
	private String cynl ;//成员年龄 
	private String cyzzmm ;//成员政治面貌 
	private String cymz ;//成员民族 
	private String cyzy ;//成员职业 
	private String cyxxdw ;//成员学习或工作单位 
	private String cylxdh ;//成员联系电话 
	private String cynsr ;//成员年收入 
	private String cyjkzk ;//成员健康状况 
	private String dcsj ;//调查时间
	private String zyjszw;//专业技术职务
	private String zwjb;//职务级别
	private String dzyx;//电子邮箱
	private String csny;//出生年月
	private String ylzd1 ;//预留字段一 
	private String ylzd2 ;//预留字段二 
	private String ylzd3 ;//预留字段三 
	private String ylzd4 ;//预留字段四 
	private String ylzd5 ;//预留字段五
	private String jtcyzjlx;
	private String jtcyzjhm;

	public String getJtcyzjlx() {
		return jtcyzjlx;
	}

	public void setJtcyzjlx(String jtcyzjlx) {
		this.jtcyzjlx = jtcyzjlx;
	}

	public String getJtcyzjhm() {
		return jtcyzjhm;
	}

	public void setJtcyzjhm(String jtcyzjhm) {
		this.jtcyzjhm = jtcyzjhm;
	}

	/**
	 * @return the cygx
	 */
	public String getCygx() {
		return cygx;
	}
	/**
	 * @param cygx要设置的 cygx
	 */
	public void setCygx(String cygx) {
		this.cygx = cygx;
	}
	/**
	 * @return the cyxm
	 */
	public String getCyxm() {
		return cyxm;
	}
	/**
	 * @param cyxm要设置的 cyxm
	 */
	public void setCyxm(String cyxm) {
		this.cyxm = cyxm;
	}
	/**
	 * @return the cynl
	 */
	public String getCynl() {
		return cynl;
	}
	/**
	 * @param cynl要设置的 cynl
	 */
	public void setCynl(String cynl) {
		this.cynl = cynl;
	}
	/**
	 * @return the cyzzmm
	 */
	public String getCyzzmm() {
		return cyzzmm;
	}
	/**
	 * @param cyzzmm要设置的 cyzzmm
	 */
	public void setCyzzmm(String cyzzmm) {
		this.cyzzmm = cyzzmm;
	}
	/**
	 * @return the cymz
	 */
	public String getCymz() {
		return cymz;
	}
	/**
	 * @param cymz要设置的 cymz
	 */
	public void setCymz(String cymz) {
		this.cymz = cymz;
	}
	/**
	 * @return the cyzy
	 */
	public String getCyzy() {
		return cyzy;
	}
	/**
	 * @param cyzy要设置的 cyzy
	 */
	public void setCyzy(String cyzy) {
		this.cyzy = cyzy;
	}
	/**
	 * @return the cyxxdw
	 */
	public String getCyxxdw() {
		return cyxxdw;
	}
	/**
	 * @param cyxxdw要设置的 cyxxdw
	 */
	public void setCyxxdw(String cyxxdw) {
		this.cyxxdw = cyxxdw;
	}
	/**
	 * @return the cylxdh
	 */
	public String getCylxdh() {
		return cylxdh;
	}
	/**
	 * @param cylxdh要设置的 cylxdh
	 */
	public void setCylxdh(String cylxdh) {
		this.cylxdh = cylxdh;
	}
	/**
	 * @return the cynsr
	 */
	public String getCynsr() {
		return cynsr;
	}
	/**
	 * @param cynsr要设置的 cynsr
	 */
	public void setCynsr(String cynsr) {
		this.cynsr = cynsr;
	}
	/**
	 * @return the cyjkzk
	 */
	public String getCyjkzk() {
		return cyjkzk;
	}
	/**
	 * @param cyjkzk要设置的 cyjkzk
	 */
	public void setCyjkzk(String cyjkzk) {
		this.cyjkzk = cyjkzk;
	}
	/**
	 * @return the dcsj
	 */
	public String getDcsj() {
		return dcsj;
	}
	/**
	 * @param dcsj要设置的 dcsj
	 */
	public void setDcsj(String dcsj) {
		this.dcsj = dcsj;
	}
	/**
	 * @return the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * @param ylzd1要设置的 ylzd1
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	/**
	 * @return the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}
	/**
	 * @param ylzd2要设置的 ylzd2
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	/**
	 * @return the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}
	/**
	 * @param ylzd3要设置的 ylzd3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	/**
	 * @return the ylzd4
	 */
	public String getYlzd4() {
		return ylzd4;
	}
	/**
	 * @param ylzd4要设置的 ylzd4
	 */
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	/**
	 * @return the ylzd5
	 */
	public String getYlzd5() {
		return ylzd5;
	}
	/**
	 * @param ylzd5要设置的 ylzd5
	 */
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}
	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guid要设置的 guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getZyjszw() {
		return zyjszw;
	}
	public void setZyjszw(String zyjszw) {
		this.zyjszw = zyjszw;
	}
	public String getZwjb() {
		return zwjb;
	}
	public void setZwjb(String zwjb) {
		this.zwjb = zwjb;
	}
	public String getDzyx() {
		return dzyx;
	}
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	public String getCsny() {
		return csny;
	}
	public void setCsny(String csny) {
		this.csny = csny;
	}
	
	
}
