/**
 * @部门:学工产品事业部
 * @日期：2016-3-23 下午06:50:45 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqyb;


import com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.comm.BjxqybForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 北京中医药_学情月报管理模块
 * @类功能描述: TODO(北京中医药_学情月报_班级月报) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-23 下午06:50:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class BjxqybsqForm extends BjxqybForm {
			
		/** 
		 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
		 */ 
		private static final long serialVersionUID = 1L;
		
		private String sqid;//申请id
		private String xn;//学年
		private String xq;//学期
		private String yf;//月份		
		private String bygzkzqk;//本月工作开展情况
		private String xsgzrd;//学生关注热点
		private String xssxdt;//学生思想动态
		private String xstsjgzjy;//学生诉求及工作建议	
		private String txsj;//填写时间
		private String txr;//填写人
		private String shzt;//审核状态:0:未提交,1提交
		private String splc;//审批流程
		private String bjdm;
		private String xymc;
		private String bjmc;
		private String type;
		private String xqmc;			
		private String nj;		
		private String zymc;		
		private String xydm;	
		private String bjrs;//班级人数		

		/**
		 * @return the xydm
		 */
		public String getXydm() {
			return xydm;
		}
		/**
		 * @param xydm要设置的 xydm
		 */
		public void setXydm(String xydm) {
			this.xydm = xydm;
		}
		/**
		 * @return the bjrs
		 */
		public String getBjrs() {
			return bjrs;
		}
		/**
		 * @param bjrs要设置的 bjrs
		 */
		public void setBjrs(String bjrs) {
			this.bjrs = bjrs;
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
		 * @return the sqid
		 */
		public String getSqid() {
			return sqid;
		}
		/**
		 * @param sqid要设置的 sqid
		 */
		public void setSqid(String sqid) {
			this.sqid = sqid;
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
		 * @return the yf
		 */
		public String getYf() {
			return yf;
		}
		/**
		 * @param yf要设置的 yf
		 */
		public void setYf(String yf) {
			this.yf = yf;
		}
		/**
		 * @return the bygzkzqk
		 */
		public String getBygzkzqk() {
			return bygzkzqk;
		}
		/**
		 * @param bygzkzqk要设置的 bygzkzqk
		 */
		public void setBygzkzqk(String bygzkzqk) {
			this.bygzkzqk = bygzkzqk;
		}
		/**
		 * @return the xsgzrd
		 */
		public String getXsgzrd() {
			return xsgzrd;
		}
		/**
		 * @param xsgzrd要设置的 xsgzrd
		 */
		public void setXsgzrd(String xsgzrd) {
			this.xsgzrd = xsgzrd;
		}
		/**
		 * @return the xssxdt
		 */
		public String getXssxdt() {
			return xssxdt;
		}
		/**
		 * @param xssxdt要设置的 xssxdt
		 */
		public void setXssxdt(String xssxdt) {
			this.xssxdt = xssxdt;
		}
		/**
		 * @return the xstsjgzjy
		 */
		public String getXstsjgzjy() {
			return xstsjgzjy;
		}
		/**
		 * @param xstsjgzjy要设置的 xstsjgzjy
		 */
		public void setXstsjgzjy(String xstsjgzjy) {
			this.xstsjgzjy = xstsjgzjy;
		}
		/**
		 * @return the txsj
		 */
		public String getTxsj() {
			return txsj;
		}
		/**
		 * @param txsj要设置的 txsj
		 */
		public void setTxsj(String txsj) {
			this.txsj = txsj;
		}
		/**
		 * @return the txr
		 */
		public String getTxr() {
			return txr;
		}
		/**
		 * @param txr要设置的 txr
		 */
		public void setTxr(String txr) {
			this.txr = txr;
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
		 * @return the splc
		 */
		public String getSplc() {
			return splc;
		}
		/**
		 * @param splc要设置的 splc
		 */
		public void setSplc(String splc) {
			this.splc = splc;
		}
		/**
		 * @return the bjdm
		 */
		public String getBjdm() {
			return bjdm;
		}
		/**
		 * @param bjdm要设置的 bjdm
		 */
		public void setBjdm(String bjdm) {
			this.bjdm = bjdm;
		}
		

}
