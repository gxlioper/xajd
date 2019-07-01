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
 * �ҵĹ������ҵ����� ʵ��
 * @author Penghui.Qu 2013-1-7
 */
public class MyJobsImpl implements MyJobsManager{

	
	private MyJobsDao dao = new MyJobsDao();
	Log logger = LogFactory.getLog(MyJobsImpl.class);
	
	
	/*
	private Lock lock =  new ReentrantLock();
	private MyJobs myJobs = MyJobs.getInstance();*/
	
	
	
	/*
	 * ���� Javadoc��
	 * @see xsgzgl.xtwh.wdgz.MyJobsManager#pushJob(xsgzgl.xtwh.wdgz.Job)
	 */
	public synchronized void pushJob(final Job job) throws Exception {
		
			//addJob(job);
		
	}
	/**
	 * 
	 * @����:�������һ������
	 * 		 ģ���µĲ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-8 ����11:35:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param job
	 * @throws Exception
	 * void �������� 
	 */
	public synchronized void cancelJob(final Job job){
//		try {
//			if (!StringUtil.isNull(job.getShjd())) {
//				job.setShr(dao.getShrByGwid(job.getShjd()));
//				job.setJdmc(dao.getGwmc(job.getShjd()));
//			}
//			//dao.batchInsertWdgz(job);
//			}catch (Exception e) {
//			 logger.error("����������칤��ʧ�ܣ�");
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
//			logger.error("������칤��ʧ�ܣ�");
//		}
		
	}
	
	
	/*
	 * ���� Javadoc��
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
//				job.setJdmc("�����");
//			}
//	
//			dao.updateSqjd(job);
//		}catch (Exception e) {
//			logger.error("���´��칤��ʧ�ܣ�");
//		}
	}

	
	/*
	 * ���� Javadoc��
	 * @see xsgzgl.xtwh.wdgz.MyJobsManager#removeJob(java.lang.String[], java.lang.String)
	 */
	public boolean removeJob(String[] ywid, String gnmk) throws Exception {
		
		//if (null != ywid && ywid.length > 0){
			//return dao.delWdgz(ywid,gnmk);
		//}
		return true;
	}


	/*
	 * ���� Javadoc��
	 * @see xsgzgl.xtwh.wdgz.MyJobsManager#copyWdgz(java.lang.String, java.lang.String)
	 */
	public boolean copyWdgz(String[] userName, String gwid) throws Exception {

		return true;
		//return dao.copyJobs(userName, gwid);
	}


	/*
	 * ���� Javadoc��
	 * @see xsgzgl.xtwh.wdgz.MyJobsManager#delWdgz(java.lang.String[], java.lang.String)
	 */
	public boolean delWdgz(String[] userName, String gwid) throws Exception {
		return true;
		//return dao.delJobs(userName, gwid);
	}


	/** 
	 * @����:����������ع��ҵĴ���
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-14 ����11:10:17
	 * @param ywid
	 * @param gwid
	 * void �������� 
	 * @throws 
	 */
	public void cancelJob(String ywid, String gwid) {
		//cancel(ywid ,gwid );
	}	
	
	/** 
	 * @����:����������ع��ҵĴ���
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-14 ����11:10:17
	 * @param ywid
	 * @param gwid
	 * void �������� 
	 * @throws 
	 */
	private void cancel(String ywid, String gwid) {

		// ȡ��������Ϣ
		HashMap<String, String> wdsq = dao.selectWdsq(ywid);
		
		// ȡ����һ���ģ�����һ�����������Ϣ���޷��ع�
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
				j.setJdmc("�����");
			}
	
			dao.updateSqjd(j);
		}catch (Exception e) {
			logger.error("���´��칤��ʧ�ܣ�");
		}
	}

}
