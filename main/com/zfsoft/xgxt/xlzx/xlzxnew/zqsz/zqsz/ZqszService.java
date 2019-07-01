package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.zqsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class ZqszService extends SuperServiceImpl<ZqszForm, ZqszDao> {
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-16 ����01:48:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYbzqList(ZqszForm t, User user)
	throws Exception {
		return dao.getYbzqList(t, user);
	}
	

	/**
	 * 
	 * @����: ��֤���ճ������Ƿ�ʹ�ù�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-17 ����02:25:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsZqMcNotUserd(String zbid,String zbzc,String xn,String xq){
		return dao.checkIsZqMcNotUserd(zbid, zbzc, xn, xq);
	}
	
	/**
	 * 
	 * @����: ��֤���ճ��Ƿ�ʹ�ù�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-17 ����01:58:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsZqNotUserd(String[] zbids){
		return dao.checkIsZqNotUserd(zbids);
	}
	
	/**
	 * 
	 * @����: ��֤�Ƿ����������ƿ���ʹ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-17 ����04:23:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param yf
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsYzqMcNotUsed(String xn,String yf,String ybid){
		return dao.checkIsYzqMcNotUsed(xn, yf,ybid);
	}
	
	/**
	 * 
	 * @����: ��֤�������Ƿ�ʹ�ù�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-17 ����03:01:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsYzqNotUserd(String[] ybids){
		return dao.checkIsYzqNotUserd(ybids);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-22 ����11:56:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveYzqsz(ZqszForm t) throws Exception{
		return dao.saveYzqsz(t);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �޸���������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-22 ����02:34:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateYzqsz(ZqszForm t) throws Exception{
		return dao.updateYzqsz(t);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ȡ������Model
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-22 ����02:45:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ZqszForm �������� 
	 * @throws
	 */
	public ZqszForm getYzqModel(String ybid) throws Exception {
		return dao.getYzqModel(ybid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-22 ����04:08:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ybids
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delYzqSz(String[] ybids) throws Exception{
		return dao.delYzqSz(ybids);
	}
	
	/**
	 * 
	 * @����: ��ѯ�����ϱ���ϸ�����ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-22 ����05:50:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @param sbbjlx
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZqsbxxqkCx(ZqszForm t, User user, String sbbjlx)throws Exception{
		return dao.getZqsbxxqkCx(t, user, sbbjlx);
	}
	
	/**
	 * 
	 * @����: ��֤�ܴ�ʱ���Ƿ��غ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-24 ����03:40:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param kssj
	 * @param jssj
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsTimeNotRepeat(String kssj,String jssj,String zbid){
		return dao.checkIsTimeNotRepeat(kssj, jssj, zbid);
	}
}
