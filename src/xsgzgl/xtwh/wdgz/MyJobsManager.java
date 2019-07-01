package xsgzgl.xtwh.wdgz;

/**
 * <b>�ҵĹ���<b>
 * <p>
 * 		ѧ�����ҵ����룩����ʦ���ҵĹ�����<br>
 * 		
 * 		ҵ��ģ����ݹ������ӻ�ɾ�����칤����<br>
 * 		
 * 		���ӿ��ṩ���������칤����ӻ�ɾ����
 * </p>
 * @author Penghui.Qu 2013-1-7
 * @version 1.0
 */
public interface MyJobsManager {

	
	/**
	 * �����칤��������칤�����ϣ��������뱣�������
	 * @param job
	 * @throws Exception
	 */
	public void pushJob(Job job) throws Exception;
	
	
	
	/**
	 * �ٽ����칤���Ӵ��칤�������Ƴ�
	 * �ڽ�ѧ���ҵ��������˽���״̬����
	 * @param job
	 * @throws Exception
	 */
	public void updateJob(Job job) throws Exception;
	
	
	
	/**
	 * ɾ�����죨����ҵ��ģ��ȡ�����������
	 * @param ywid
	 * @param gnmk
	 * @return
	 * @throws Exception
	 */
	public boolean removeJob(String[] ywid, String gnmk) throws Exception;
	
	
	
	/**
	 * �����ҵĹ�����Ϊ�������ṩ�ӿڣ�
	 * @param userName
	 * @param gwid
	 * @return
	 * @throws Exception
	 */
	public boolean copyWdgz(String[] userName, String gwid) throws Exception;
	
	
	/**
	 * ɾ���ҵĹ�����Ϊ�������ṩ�ӿڣ�
	 * @param userName
	 * @param gwid
	 * @return
	 * @throws Exception
	 */
	public boolean delWdgz(String[] userName, String gwid) throws Exception;

	/** 
	 * @����:����������ع��ҵĴ���
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-14 ����11:10:17
	 * @param ywid
	 * @param gwid
	 * void �������� 
	 * @throws 
	 */
	public void cancelJob(String ywid, String gwid);



	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-8 ����11:37:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param job
	 * void �������� 
	 * @throws 
	 */
	public void cancelJob(Job job);
}
