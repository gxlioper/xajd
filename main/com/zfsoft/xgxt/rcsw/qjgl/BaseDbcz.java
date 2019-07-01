/**
 * @部门:学工产品事业部
 * @日期：2013-9-23 下午02:20:50 
 */  
package com.zfsoft.xgxt.rcsw.qjgl;

import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 代办操作
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-23 下午02:20:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BaseDbcz{
	private ShlcDao shdao = new ShlcDao();
	private MyJobsManager manager = new MyJobsImpl();
	//审核跳转路径
	private String shPath=null;
	//个人申请跳转路径
	private String sqPath=null;
	//代办列表显示名称
	private String xmmc="new";
	//对应功能模块关键字（模块标志）
	private String gnmkmc="";
	/**
	 * @描述 ：TODO描述下当前构造方法
	 */
	public BaseDbcz(String sqPath,String shPath) {
		this.shPath=shPath;
		this.sqPath=sqPath;
	}
	/**
	 * @描述 ：TODO描述下当前构造方法
	 */
	public BaseDbcz() {
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:申请(推送代办)
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-23 下午02:22:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws
	 */
	public void sqPush(String ywid,String xh,String splcid) throws Exception{
		//保存代办工作
		//获取第一级审核岗位
		String spgw =shdao.getFirstGwid(splcid);
		if (null != spgw){
			Job job = Job.getJobInstance(ywid, xh, spgw, 
					shPath, 
					sqPath,gnmkmc, xmmc);
			manager.pushJob(job);
		}
	}
	/**
	 * 
	 * @描述:审核
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-23 下午02:43:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param splcId
	 * @throws Exception
	 * void 返回类型 
	 */
	public void shPush(String ywid,String splcId) throws Exception{
		String nextShgw = shdao.getDshGwid(ywid).get("gwid");
		Job job = null;
		job = Job.getJobInstance(ywid, nextShgw, shPath,
				gnmkmc);
		manager.updateJob(job);
	}
	/**
	 * 
	 * @描述:审核撤销 代办处理
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-8 下午12:27:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param splcId
	 * void 返回类型 
	 */
	public void cancel(String ywid,String splcId){
		String spgw =shdao.getLastGwid(splcId);
		if (null != spgw){
			Job job = Job.getJobInstance(ywid, "", spgw, 
					shPath, 
					sqPath,gnmkmc, xmmc);
			manager.cancelJob(job);
		}
	}
	/**
	 * 
	 * @描述:删除代办工作
	 * 		 (必须和设置代办信息未同一操作类)
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-23 下午03:32:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid 业务id
	 * @return boolean 
	 * @throws Exception
	 */
	public boolean remove(String[] ywid) throws Exception{
		if(!StringUtils.isNull(this.getGnmkMc())){
			return manager.removeJob(ywid, this.getGnmkMc());
		}
		return false;
	}
	/**
	 * 
	 * @描述:删除代办工作
	 * 		 (必须和设置代办信息未同一操作类)
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-23 下午03:32:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid 业务id
	 * @return boolean 
	 * @throws Exception 
	 */
	public boolean remove(String ywid) throws Exception{
		return remove(new String[]{ywid});
	}
	public String getShPath() {
		return shPath;
	}
	public void setShPath(String shPath) {
		this.shPath = shPath;
	}
	public String getSqPath() {
		return sqPath;
	}
	public void setSqPath(String sqPath) {
		this.sqPath = sqPath;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getGnmkMc() {
		return gnmkmc;
	}
	public void setGnmkMc(String gnmkMc) {
		this.gnmkmc = gnmkMc;
	}
}
