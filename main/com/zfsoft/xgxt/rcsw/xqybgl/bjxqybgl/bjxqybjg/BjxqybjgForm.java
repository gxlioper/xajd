/**
 * @部门:学工产品事业部
 * @日期：2016-3-31 下午04:37:17 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybjg;

import com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.comm.BjxqybForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 北京中医药_学情月报管理模块
 * @类功能描述: TODO(北京中医药_学情月报_班级月报结果) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-31 下午04:37:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjxqybjgForm extends BjxqybForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;	
	
		private String jgid;
		private String  xn;  
		private String  xq;
		private String  yf;//月份
		private String  bygzkzqk;//本月工作开展工作情况
		private String  xsgzrd;//学生工作热点
		private String  xssxdt;//学生思想动态
		private String  xstsjgzjy;//学生诉求及工作建议
		private String  txsj;//填写时间
		private String  txr;//填写人
		private String  sjly;//数据来源
		private String  lylcywid;//来源流程业务id
		private String  bjdm;
		private String type;
		
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
		 * @return the jgid
		 */
		public String getJgid() {
			return jgid;
		}
		/**
		 * @param jgid要设置的 jgid
		 */
		public void setJgid(String jgid) {
			this.jgid = jgid;
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
		 * @return the sjly
		 */
		public String getSjly() {
			return sjly;
		}
		/**
		 * @param sjly要设置的 sjly
		 */
		public void setSjly(String sjly) {
			this.sjly = sjly;
		}
		/**
		 * @return the lylcywid
		 */
		public String getLylcywid() {
			return lylcywid;
		}
		/**
		 * @param lylcywid要设置的 lylcywid
		 */
		public void setLylcywid(String lylcywid) {
			this.lylcywid = lylcywid;
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
