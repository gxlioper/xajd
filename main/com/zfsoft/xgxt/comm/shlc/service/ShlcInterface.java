/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-13 ����04:11:35 
 */  
package com.zfsoft.xgxt.comm.shlc.service;


import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;



/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ͨ��ģ��
 * @�๦������: ��˲���
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-6-13 ����04:11:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public interface ShlcInterface {

	
	/**
	 * 
	 * @����: ��˲���
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-13 ����04:22:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return String ��ǰ״̬
	 * @throws Exception 
	 */
	public String runAuditing(ShxxModel model) throws Exception;
	
	public String runAuditingNotCommit(ShxxModel model) throws Exception;
	
	/**
	 * 
	 * @����:�����˵ĳ���
	 * @���ߣ�945
	 * @���ڣ�2013-11-26 ����08:59:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean firstStepCancle(String ywid,String lcid) throws Exception;
	
	/**
	 * 
	 * @����: ������˲���
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-14 ����01:36:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid ҵ��ID
	 * @param shlc �������ID
	 * @param gwid ��λID
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 */
	public boolean runCancel(String shr,String ywid,String shlc,String gwid) throws Exception;

	/**
	 * ����: ������ˡ����һ�����ɳ�����
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-11 
	 * @param shr �����
	 * @param shid ���ID��ϵͳά��-���״̬��GUID��
	 * @param shlc �������ID
	 */
	public boolean runCancel(String shr, String shid,String shlc) throws Exception;
	
	/**
	 * ����: ������ˡ����ɳ�����
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-11 
	 * @param shr �����
	 * @param shid ���ID��ϵͳά��-���״̬��GUID��
	 * @param shlc �������ID
	 */
	public String runCancelNew(String shr, String shid, String shlc) throws Exception;
	
	/**
	 * 
	 * @����: �����ύ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-13 ����04:21:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param shlc
	 * @return
	 * boolean �������� 
	 * @throws Exception 
	 */
	public boolean runSubmit(String ywid,String shlc,String xh,String tzlj,String tzljsq) throws Exception;
	
	/**
	 * 
	 * @����:������ʱ��ɾ���ύ��¼
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-11-28 ����01:46:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteShjl(String ywid) throws Exception;


	/**
	 * 
	 * @����: ���˻ص�Ŀ���λ�б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����02:09:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @param gwid
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getKthSpgw(String splc,String gwid);
	
	/**
	 * 
	 * @����:ͨ��ҵ��id�͸�λid�õ������������µ�һ����¼
	 * @���ߣ�hj[945]
	 * @���ڣ�2013-12-17 ����10:31:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param gwid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getShxxByCondition(String ywid,String gwid);
	
	/**
	 * 
	 * @����:���������Ƿ����,true:����ɣ�false:δ���
	 * @���ߣ�945
	 * @���ڣ�2013-12-27 ����10:27:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean splcsfwc(String ywid);
	
	
	
	
	/**
	 * 
	 * @����: ���û�������id��ѯ����������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-5-4 ����03:47:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param gnid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getShyjList(User user,String gnid);
	
	
	/**
	 * 
	 * @����: ���泣��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-5-5 ����08:42:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param gnid
	 * @param shyj
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveCyyj(User user,String gnid,String[] shyj);
	
	 public  boolean runSubmitBatchNotCommit(String[] ywids, String shlc,String[] xhs,String tzlj,String tzljsq)throws Exception;
}
