/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-24 ����09:40:22 
 */  
package com.zfsoft.xgxt.rcsw.txhd.xmjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.base.BaseDAO;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-6-24 ����09:40:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TxhdXmjgService extends SuperServiceImpl<TxhdXmjgForm, TxhdXmjgDao> {
	
	public static String _BCZSCID = "-1";
	TxhdXmjgDao dao = new TxhdXmjgDao();
	@Override
	public List<HashMap<String, String>> getPageList(TxhdXmjgForm t, User user)
			throws Exception {
		return getHdxx(super.getPageList(t, user));
	}
	public List<HashMap<String, String>> getHdxx(
			List<HashMap<String, String>> list) {
		return list;
	}
	
	/**
	 * 
	 * @����:�����޸�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-06-24 ����12:56:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean update(TxhdXmjgForm model) throws Exception {
			return super.runUpdate(model);
		}
	
	/**
	 * ɾ����Ӧ��������
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-6-26 ����04:29:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean deleteForSh(String sqid) throws Exception{
		return dao.deleteForSh(sqid)>=0?true:false;
	}
	/**
	 * 
	 * @����:��������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-06-24 ����14:21:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean save(TxhdXmjgForm model) throws Exception {
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			return super.runInsert(model);
		}
	
	/**
	 * @throws Exception
	 * 
	 * @����:ɾ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-06-24 ����15:52:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             String[] �������� String���� 0Ϊ�ɹ�ɾ������Ϊ����ɾ����
	 * @throws
	 */
	public String[] delete(String[] ids) throws Exception {
		// StringBuffer del=new StringBuffer();
		List<String> delId = new ArrayList<String>();// ��ɾ����id����
		List<String> delSqId = new ArrayList<String>();// ��Ӧ��ɾ������������id
		if (null == ids || ids.length <= 0) {
			return null;
		}
		for (String str : ids) {
				delId.add(str);// ��¼ɾ��id
				delSqId.add(getModel(str).getGuid());
			}
		
		int i = delId.size() > 0 ? runDelete(delId.toArray(new String[] {}))
				: 0;
		return new String[] { String.valueOf(i)};
	}
	
	@Override
	public TxhdXmjgForm getModel(TxhdXmjgForm t) throws Exception {
		t = super.getModel(t);
		if(t!=null){
			// ��ѧ�ڴ���ת��Ϊѧ������
			t.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(t.getXq()));
		}
		return t;
	}
	public boolean isAdd(TxhdXmjgForm myForm){
		return dao.isAdd(myForm);
	}
	
	
	public HashMap<String, String> getOneHdjgList(String id){
		return dao.getOneHdjgList(id);
	}
	
}
