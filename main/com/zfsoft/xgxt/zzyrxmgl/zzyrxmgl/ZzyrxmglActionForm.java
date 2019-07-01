
package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl;

import com.zfsoft.xgxt.jyglnew.jygl.comm.JyglForm;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 资助育人项目管理模块
 * @类功能描述: 资助育人项目管理实体
 * @作者： Lu.Yao[工号:1271]
 * @时间： 2017-10-16 下午03:08:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class ZzyrxmglActionForm extends JyglForm {

	private static final long serialVersionUID = 156208654779020468L;
	
	//辅导信息表
	private String id;
	private String fdxxid;//辅导信息id
	private String fdfbid;//辅导发布id
	private String shzt;//审核状态 0:不同意   1:同意
	private String fdrxh;//辅导人学号
	private String fdrxm;//辅导人姓名
	private String fdrxymc;//辅导人学院名称
	private String fdrlxdh;//辅导人联系电话
	private String bfdrxh;//被辅导人学号
	private String bfdrxm;//被辅导人姓名
	private String bfdrxymc;//被辅导人学院名称
	private String bfdrlxdh;//被辅导人联系电话
	private String xspjjg;//学生评价结果
	private String lspjjg;//老师评价结果
	private String tysj;//同意时间
	
	private String fdrfdy;//辅导人辅导员工号
	private String bfdrfdy;//被辅导人辅导员工号
	private String xscshr;//学生处审核人工号
	private String fdyshzt;//辅导人辅导员审核状态
	private String bfdyshzt;//被辅导人辅导员审核状态
	private String xscshzt;//学生处审核状态
	private String shrxz;//审核人性质
	
	//辅导发布表
	private String fbrxh;//发布人学号
	private String fbrxm;//发布人姓名
	private String fbrxydm;//学院代码
	private String fbrxymc;//学院名称
	private String fdkm;//辅导科目
	private String fdsj;//辅导时间
	private String xdrs;//限定人数
	private String fblx;//类型  0:发布辅导  1:需要辅导
	private String fbbz;//备注
	private String sfsq;//是否辅导
	private String sffd;//是否申请
	
	//辅导发布学院关联表
	private String kfxydm;//学院代码
	private String kfxymc;//学院名称
	
	//辅导记录表
	private String fdrq;//辅导日期
	private String fdnr;//辅导内容
	private String fdbz;//辅导备注
	private String fdlx;//类型  0:辅导人填写   1:被辅导人填写
	
	private String xydm;//
	private String zydm;//
	private String bjdm;//
	private String bj;//
	
	private String fdjssj;
	private String gs;
	private String fddd;
	private String fdpj;
	
	public String getFdrlxdh() {
		return fdrlxdh;
	}
	public void setFdrlxdh(String fdrlxdh) {
		this.fdrlxdh = fdrlxdh;
	}
	public String getBfdrlxdh() {
		return bfdrlxdh;
	}
	public void setBfdrlxdh(String bfdrlxdh) {
		this.bfdrlxdh = bfdrlxdh;
	}
	public String getFdxxid() {
		return fdxxid;
	}
	public void setFdxxid(String fdxxid) {
		this.fdxxid = fdxxid;
	}
	public String getFdfbid() {
		return fdfbid;
	}
	public void setFdfbid(String fdfbid) {
		this.fdfbid = fdfbid;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getFdrxh() {
		return fdrxh;
	}
	public void setFdrxh(String fdrxh) {
		this.fdrxh = fdrxh;
	}
	public String getBfdrxh() {
		return bfdrxh;
	}
	public void setBfdrxh(String bfdrxh) {
		this.bfdrxh = bfdrxh;
	}
	public String getXspjjg() {
		return xspjjg;
	}
	public void setXspjjg(String xspjjg) {
		this.xspjjg = xspjjg;
	}
	public String getLspjjg() {
		return lspjjg;
	}
	public void setLspjjg(String lspjjg) {
		this.lspjjg = lspjjg;
	}
	public String getTysj() {
		return tysj;
	}
	public void setTysj(String tysj) {
		this.tysj = tysj;
	}
	public String getFbrxh() {
		return fbrxh;
	}
	public void setFbrxh(String fbrxh) {
		this.fbrxh = fbrxh;
	}
	public String getFdkm() {
		return fdkm;
	}
	public void setFdkm(String fdkm) {
		this.fdkm = fdkm;
	}
	public String getFdsj() {
		return fdsj;
	}
	public void setFdsj(String fdsj) {
		this.fdsj = fdsj;
	}
	public String getXdrs() {
		return xdrs;
	}
	public void setXdrs(String xdrs) {
		this.xdrs = xdrs;
	}
	public String getFblx() {
		return fblx;
	}
	public void setFblx(String fblx) {
		this.fblx = fblx;
	}
	public String getFbbz() {
		return fbbz;
	}
	public void setFbbz(String fbbz) {
		this.fbbz = fbbz;
	}
	
	public String getFbrxm() {
		return fbrxm;
	}
	public void setFbrxm(String fbrxm) {
		this.fbrxm = fbrxm;
	}
	public String getFbrxydm() {
		return fbrxydm;
	}
	public void setFbrxydm(String fbrxydm) {
		this.fbrxydm = fbrxydm;
	}
	public String getFbrxymc() {
		return fbrxymc;
	}
	public void setFbrxymc(String fbrxymc) {
		this.fbrxymc = fbrxymc;
	}
	public String getKfxydm() {
		return kfxydm;
	}
	public void setKfxydm(String kfxydm) {
		this.kfxydm = kfxydm;
	}
	public String getKfxymc() {
		return kfxymc;
	}
	public void setKfxymc(String kfxymc) {
		this.kfxymc = kfxymc;
	}
	public String getFdrq() {
		return fdrq;
	}
	public void setFdrq(String fdrq) {
		this.fdrq = fdrq;
	}
	public String getFdnr() {
		return fdnr;
	}
	public void setFdnr(String fdnr) {
		this.fdnr = fdnr;
	}
	public String getFdbz() {
		return fdbz;
	}
	public void setFdbz(String fdbz) {
		this.fdbz = fdbz;
	}
	public String getFdlx() {
		return fdlx;
	}
	public void setFdlx(String fdlx) {
		this.fdlx = fdlx;
	}
	public String getFdrxm() {
		return fdrxm;
	}
	public void setFdrxm(String fdrxm) {
		this.fdrxm = fdrxm;
	}
	public String getFdrxymc() {
		return fdrxymc;
	}
	public void setFdrxymc(String fdrxymc) {
		this.fdrxymc = fdrxymc;
	}
	public String getBfdrxm() {
		return bfdrxm;
	}
	public void setBfdrxm(String bfdrxm) {
		this.bfdrxm = bfdrxm;
	}
	public String getBfdrxymc() {
		return bfdrxymc;
	}
	public void setBfdrxymc(String bfdrxymc) {
		this.bfdrxymc = bfdrxymc;
	}
	public String getSfsq() {
		return sfsq;
	}
	public void setSfsq(String sfsq) {
		this.sfsq = sfsq;
	}
	public String getSffd() {
		return sffd;
	}
	public void setSffd(String sffd) {
		this.sffd = sffd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-27 上午09:27:58 
	 * @return		: the fdjssj
	 */
	public String getFdjssj() {
		return fdjssj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-27 上午09:27:58 
	 * @param 		：fdjssj the fdjssj to set
	 */
	public void setFdjssj(String fdjssj) {
		this.fdjssj = fdjssj;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-27 上午09:27:58 
	 * @return		: the gs
	 */
	public String getGs() {
		return gs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-27 上午09:27:58 
	 * @param 		：gs the gs to set
	 */
	public void setGs(String gs) {
		this.gs = gs;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-27 上午09:27:58 
	 * @return		: the fddd
	 */
	public String getFddd() {
		return fddd;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-27 上午09:27:58 
	 * @param 		：fddd the fddd to set
	 */
	public void setFddd(String fddd) {
		this.fddd = fddd;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-27 上午09:27:58 
	 * @return		: the fdpj
	 */
	public String getFdpj() {
		return fdpj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-27 上午09:27:58 
	 * @param 		：fdpj the fdpj to set
	 */
	public void setFdpj(String fdpj) {
		this.fdpj = fdpj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-19 下午02:29:16 
	 * @return		: the fdrfdy
	 */
	public String getFdrfdy() {
		return fdrfdy;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-19 下午02:29:16 
	 * @param 		：fdrfdy the fdrfdy to set
	 */
	public void setFdrfdy(String fdrfdy) {
		this.fdrfdy = fdrfdy;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-19 下午02:29:16 
	 * @return		: the bfdrfdy
	 */
	public String getBfdrfdy() {
		return bfdrfdy;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-19 下午02:29:16 
	 * @param 		：bfdrfdy the bfdrfdy to set
	 */
	public void setBfdrfdy(String bfdrfdy) {
		this.bfdrfdy = bfdrfdy;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-19 下午02:29:16 
	 * @return		: the xscshr
	 */
	public String getXscshr() {
		return xscshr;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-19 下午02:29:16 
	 * @param 		：xscshr the xscshr to set
	 */
	public void setXscshr(String xscshr) {
		this.xscshr = xscshr;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-19 下午02:29:16 
	 * @return		: the fdyshzt
	 */
	public String getFdyshzt() {
		return fdyshzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-19 下午02:29:16 
	 * @param 		：fdyshzt the fdyshzt to set
	 */
	public void setFdyshzt(String fdyshzt) {
		this.fdyshzt = fdyshzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-19 下午02:29:16 
	 * @return		: the bfdyshzt
	 */
	public String getBfdyshzt() {
		return bfdyshzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-19 下午02:29:16 
	 * @param 		：bfdyshzt the bfdyshzt to set
	 */
	public void setBfdyshzt(String bfdyshzt) {
		this.bfdyshzt = bfdyshzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-19 下午02:29:16 
	 * @return		: the xscshzt
	 */
	public String getXscshzt() {
		return xscshzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-19 下午02:29:16 
	 * @param 		：xscshzt the xscshzt to set
	 */
	public void setXscshzt(String xscshzt) {
		this.xscshzt = xscshzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-21 下午04:32:22 
	 * @return		: the shrxz
	 */
	public String getShrxz() {
		return shrxz;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-21 下午04:32:22 
	 * @param 		：shrxz the shrxz to set
	 */
	public void setShrxz(String shrxz) {
		this.shrxz = shrxz;
	}
	
	
}
