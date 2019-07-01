/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-26 ����05:33:42 
 */  
package com.zfsoft.xgxt.xpjpy.bbwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-11-26 ����05:33:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BbwhDao extends SuperDAOImpl<BbwhForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BbwhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BbwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-11-27 ����10:36:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBbxxList(){
		
		String[] input = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bblj from XG_PJPY_NEW_XMSZBBDMB t1 ");
		sql.append("left join XG_PJPY_NEW_BBDYTPB t2 on t1.bbdm=t2.bbdm and t2.dyym='1' ");
		
		return dao.getListNotOut(sql.toString(), input);
		
	}
	
	/**
	 * @��������ȡ������Ϣ�б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��12�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bbdm
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getBbxxList(String bblx){
		
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bblj from XG_PJPY_NEW_XMSZBBDMB t1 ");
		sql.append("left join XG_PJPY_NEW_BBDYTPB t2 on t1.bbdm=t2.bbdm and t2.dyym='1' where ");
		if("2".equals(bblx)){
			sql.append(" t1.bblx='2' ");
		}else{
			sql.append(" t1.bblx is null or t1.bblx = '1' ");
		}
		sql.append("order by t1.bbmc ");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
		
	}
	
	/**
	 * @��������ȡ������Ϣ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��12�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bbdm
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getBbxx(String bbdm){
		
		String[] input = new String[]{bbdm};
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bblj from XG_PJPY_NEW_XMSZBBDMB t1 ");
		sql.append("left join XG_PJPY_NEW_BBDYTPB t2 on t1.bbdm=t2.bbdm where t1.bbdm = ? order by t2.dyym ");
		
		return dao.getListNotOut(sql.toString(), input);
		
	}
	
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-11-28 ����09:46:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bbdm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getDataById(String bbdm) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from  XG_PJPY_NEW_XMSZBBDMB ");
		sb.append(" where bbdm=?");
		String[] inputValue = { bbdm };
		return dao.getMapNotOut(sb.toString(), inputValue);
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_PJY_NEW_XMSZBBDMB");
		super.setKey("bbdm");
		super.setClass(BbwhForm.class);		
	}

}
