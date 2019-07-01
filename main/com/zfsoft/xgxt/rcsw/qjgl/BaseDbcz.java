/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-23 ����02:20:50 
 */  
package com.zfsoft.xgxt.rcsw.qjgl;

import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-23 ����02:20:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BaseDbcz{
	private ShlcDao shdao = new ShlcDao();
	private MyJobsManager manager = new MyJobsImpl();
	//�����ת·��
	private String shPath=null;
	//����������ת·��
	private String sqPath=null;
	//�����б���ʾ����
	private String xmmc="new";
	//��Ӧ����ģ��ؼ��֣�ģ���־��
	private String gnmkmc="";
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public BaseDbcz(String sqPath,String shPath) {
		this.shPath=shPath;
		this.sqPath=sqPath;
	}
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public BaseDbcz() {
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:����(���ʹ���)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-23 ����02:22:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws
	 */
	public void sqPush(String ywid,String xh,String splcid) throws Exception{
		//������칤��
		//��ȡ��һ����˸�λ
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
	 * @����:���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-23 ����02:43:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param splcId
	 * @throws Exception
	 * void �������� 
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
	 * @����:��˳��� ���촦��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-8 ����12:27:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param splcId
	 * void �������� 
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
	 * @����:ɾ�����칤��
	 * 		 (��������ô�����Ϣδͬһ������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-23 ����03:32:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid ҵ��id
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
	 * @����:ɾ�����칤��
	 * 		 (��������ô�����Ϣδͬһ������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-23 ����03:32:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid ҵ��id
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
