package xsgzgl.xtwh.wdgz.impl;

import java.sql.SQLException;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zfsoft.utils.StringUtil;

import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsDao;
import xsgzgl.xtwh.wdgz.MyJobsManager;


/**
 * 我的工作，我的申请 实现
 * @author Penghui.Qu 2013-1-7
 */
public class MyJobsImpl implements MyJobsManager{

	
	private MyJobsDao dao = new MyJobsDao();
	Log logger = LogFactory.getLog(MyJobsImpl.class);
	
	
	/*
	private Lock lock =  new ReentrantLock();
	private MyJobs myJobs = MyJobs.getInstance();*/
	
	
	
	/*
	 * （非 Javadoc）
	 * @see xsgzgl.xtwh.wdgz.MyJobsManager#pushJob(xsgzgl.xtwh.wdgz.Job)
	 */
	public synchronized void pushJob(final Job job) throws Exception {
		
			//addJob(job);
		
	}
	/**
	 * 
	 * @描述:用于最后一级撤销
	 * 		 模仿新的插入
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-8 上午11:35:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param job
	 * @throws Exception
	 * void 返回类型 
	 */
	public synchronized void cancelJob(final Job job){
//		try {
//			if (!StringUtil.isNull(job.getShjd())) {
//				job.setShr(dao.getShrByGwid(job.getShjd()));
//				job.setJdmc(dao.getGwmc(job.getShjd()));
//			}
//			//dao.batchInsertWdgz(job);
//			}catch (Exception e) {
//			 logger.error("撤销插入待办工作失败！");
//		}
	}
	private void addJob(Job job)  {
		
//		try{
//			dao.insertWdsq(job);
//
//			if (!StringUtil.isNull(job.getShjd())) {
//				job.setShr(dao.getShrByGwid(job.getShjd()));
//				job.setJdmc(dao.getGwmc(job.getShjd()));
//			}
//
//			dao.batchInsertWdgz(job);
//		} catch (Exception e) {
//			logger.error("插入待办工作失败！");
//		}
		
	}
	
	
	/*
	 * （非 Javadoc）
	 * @see xsgzgl.xtwh.wdgz.MyJobsManager#removeJob(xsgzgl.xtwh.wdgz.Job)
	 */
	public synchronized void updateJob(final Job job) throws Exception {
		
			//modifyJob(job);
		
	}
	
	private void modifyJob(Job job) {
//		HashMap<String, String> map = dao.getWdgzInfo(job.getSqid(), job
//				.getGnmk());
//
//		try{
//			dao.deleteWdgz(job);
//	
//			if (!StringUtil.isNull(job.getShjd())) {
//	
//				job.setShr(dao.getShrByGwid(job.getShjd()));
//				job.setJdmc(dao.getGwmc(job.getShjd()));
//	
//				if (!StringUtil.isNull(map.get("gznr"))){
//					job.setGznr(map.get("gznr"));
//				}
//				
//				dao.batchInsertWdgz(job);
//			}
//	
//			if (StringUtil.isNull(job.getJdmc())) {
//				job.setJdmc("已审核");
//			}
//	
//			dao.updateSqjd(job);
//		}catch (Exception e) {
//			logger.error("更新待办工作失败！");
//		}
	}

	
	/*
	 * （非 Javadoc）
	 * @see xsgzgl.xtwh.wdgz.MyJobsManager#removeJob(java.lang.String[], java.lang.String)
	 */
	public boolean removeJob(String[] ywid, String gnmk) throws Exception {
		
		//if (null != ywid && ywid.length > 0){
			//return dao.delWdgz(ywid,gnmk);
		//}
		return true;
	}


	/*
	 * （非 Javadoc）
	 * @see xsgzgl.xtwh.wdgz.MyJobsManager#copyWdgz(java.lang.String, java.lang.String)
	 */
	public boolean copyWdgz(String[] userName, String gwid) throws Exception {

		return true;
		//return dao.copyJobs(userName, gwid);
	}


	/*
	 * （非 Javadoc）
	 * @see xsgzgl.xtwh.wdgz.MyJobsManager#delWdgz(java.lang.String[], java.lang.String)
	 */
	public boolean delWdgz(String[] userName, String gwid) throws Exception {
		return true;
		//return dao.delJobs(userName, gwid);
	}


	/** 
	 * @描述:撤销操作后回滚我的待办
	 * @作者：qilm
	 * @日期：2013-10-14 上午11:10:17
	 * @param ywid
	 * @param gwid
	 * void 返回类型 
	 * @throws 
	 */
	public void cancelJob(String ywid, String gwid) {
		//cancel(ywid ,gwid );
	}	
	
	/** 
	 * @描述:撤销操作后回滚我的待办
	 * @作者：qilm
	 * @日期：2013-10-14 上午11:10:17
	 * @param ywid
	 * @param gwid
	 * void 返回类型 
	 * @throws 
	 */
	private void cancel(String ywid, String gwid) {

		// 取得申请信息
		HashMap<String, String> wdsq = dao.selectWdsq(ywid);
		
		// 取得上一级的，后面一级若无审核信息则无法回滚
		if(wdsq == null || wdsq.size() == 0 ) return;
		Job j = Job.getJobInstance(ywid,gwid,wdsq.get("gnmkpath"),wdsq.get("gnmklx"));
		
		HashMap<String, String> map = dao.getWdgzInfo(j.getSqid(), j
				.getGnmk());

		try{
			dao.deleteWdgz(j);
	
			if (!StringUtil.isNull(j.getShjd())) {
	
				j.setShr(dao.getShrByGwid(j.getShjd()));
				j.setJdmc(dao.getGwmc(j.getShjd()));

				if (!StringUtil.isNull(map.get("gznr"))){
					j.setGznr(map.get("gznr"));
				}
				
				dao.batchInsertWdgz(j);
			}

			if (StringUtil.isNull(j.getJdmc())) {
				j.setJdmc("已审核");
			}
	
			dao.updateSqjd(j);
		}catch (Exception e) {
			logger.error("更新待办工作失败！");
		}
	}

}
