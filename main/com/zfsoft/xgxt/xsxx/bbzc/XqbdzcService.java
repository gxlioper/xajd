/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-16 ����10:02:13 
 */  
package com.zfsoft.xgxt.xsxx.bbzc;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ�ڱ���ע��
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-16 ����10:02:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XqbdzcService extends SuperServiceImpl<XqbdzcForm, XqbdzcDao> {

	private XqbdzcDao dao = new XqbdzcDao();

	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 * @param dao
	 */
	public XqbdzcService() {
		super.setDao(dao);
	}
	
	/**
	 * @throws Exception 
	 * ��ȡѧ��ע����Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-16 ����07:24:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXqZcXx(String xh , String xn , String xq) throws Exception{
		return dao.getXqzcxx(xh, xn, xq);
	}
	
	/**
	 * ��ȡע��״̬���鿴ҳ���ж�����Ҫ��
	 * ����
	 */
	public String getZczt(String xh , String xn , String xq) throws Exception{
		return dao.getZczt(xh, xn, xq);
	}
	/**
	 * 
	 * @����:��ȡ��������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-17 ����09:35:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getCwsjList(String xh , String xn , String xq){
		return dao.getCwsjList(xh, xn, xq);
	}
	public boolean doDtZc(XqbdzcForm model) throws Exception{
		boolean flag = true;
		flag=dao.deleteZcxx(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:����ע��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-17 ����01:09:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param plids
	 * @param xn
	 * @param xq
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean plXqzc(String[] plids, String xn , String xq , String zcr , String zcsj, XqbdzcForm t, User user) throws Exception{
		if(plids!= null && plids.length != 0){
//			wzcxsArr=plids;
			return dao.plXqzc(plids, xn, xq, zcr, zcsj, t, user, "ids");
		}
//		if(wzcxsArr == null || wzcxsArr.length == 0)
//			return false;
		
		return dao.plXqzc(new String[]{}, xn, xq, zcr, zcsj, t, user, "all");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:��������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-17 ����01:42:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param plids
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean plCxzc(String[] plIds ,String xn ,String xq, XqbdzcForm t, User user) throws Exception{
//		String[] ids = null;
//		if(cxxsList == null || cxxsList.size()==0){
//			ids=plIds;
//		}
//		else{
//			ids= new String[cxxsList.size()];
//			for (int i = 0; i < cxxsList.size(); i++) {
//				ids[i]=cxxsList.get(i).get("id");
//			}
//		}
//		dao.runDelete(ids);
		
		if(null==t.getPlIds()){
			return dao.plCxzc(xn, xq, t,  user);
		}
		else{
			String[] ids=plIds;
			dao.runDelete(ids);
			
			return true;
		}
	}
	/**
	 * 
	 * @����:δ����ԭ��ά��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-23 ����03:01:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param plids
	 * @param xn
	 * @param xq
	 * @param zcr
	 * @param zcsj
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean wbdyywh(XqbdzcForm model) throws Exception{
		return dao.wbdyywh(model);
	}
	/**
	 * 
	 * @����:��ȡ�û���������б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-23 ����03:50:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCyyyList(User user) {
		return dao.getCyyyList(user);
	}
	/**
	 * 
	 * @����:����ԭ�򱣴�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-23 ����04:16:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param gnid
	 * @param shyj
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveCyyy(User user,String[] cyyy){
		
		if (cyyy == null || cyyy.length == 0)
			return false;
		
		
		try {
			boolean b = dao.delCyyy(user);
			
			if (b){
				return dao.saveCyyy(user, cyyy);
			}
			
			return b;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:��ȡδע��ѧ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-10 ����11:21:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
    public String[] getWzcList(XqbdzcForm model,User user) throws Exception{
		return dao.getWzcArr(model,user);
	}
    
    public List<HashMap<String,String>> getBdzcList(XqbdzcForm model,User user) throws Exception{
		return dao.getBdzcList(model,user);
	}

	/**
	 * @throws Exception  
	 * @����:��ȡδ����ѧ����Ŀ
	 * @���ߣ��Ƴ���
	 * @���ڣ�2015-12-15 ����03:36:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * String[] �������� 
	 * @throws 
	 */
	public String getWzcListCount(XqbdzcForm model, User user) throws Exception {
		return dao.getBdzcListCount(model,user,"-1");
	}
	
	/**
	 * 
	 * @����:��ȡδע��ѧ����Ŀ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2016-1-12 ����07:19:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getWclListCount(XqbdzcForm model, User user) throws Exception {
		return dao.getBdzcListCount(model,user,"0");
	}

	/**
	 * @throws Exception  
	 * @����:��ȡ������
	 * @���ߣ��Ƴ���
	 * @���ڣ�2015-12-15 ����05:15:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getBdzcListCount(XqbdzcForm model, User user) throws Exception {
		return dao.getBdzcListCount(model,user,null);
	}
	public List<HashMap<String, String>> getWbdlb() {
		return dao.getWbdlb();
	}
	
	
}
