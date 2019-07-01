/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-23 ����08:37:37 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.jcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-23 ����08:37:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcszDao extends SuperDAOImpl<JcszForm> {

	/**
	 * 
	 * @����:��ѯ����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-23 ����08:41:08
	 */
	public HashMap<String , String> getJcsz(){
		
		String sql = "select a.* from XG_XLJK_XLWYGL_JCSZ a ";
		
		return dao.getMapNotOut(sql, new String[]{});
	}
	
	/**
	 * @����:��������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-23 ����08:47:44
	 */
	public boolean saveJcsz(JcszForm model){
		boolean val = true;
		String del = "delete from XG_XLJK_XLWYGL_JCSZ ";
		String inst = "insert into XG_XLJK_XLWYGL_JCSZ (bjzbrc_splcid , gyzbrc_splcid , psxxsb_splcid) values (?,?,?)";
		try {
			dao.runDelete(del, new String[]{});
			dao.runUpdate(inst, new String[]{model.getBjzbrcSplcid() , 
					model.getGyzbrcSplcid() , 
					model.getPsxxsbSplcid()});
			val = true;
		} catch (Exception e) {
			e.printStackTrace();
			val = false;
		}
		
		return val;
	}
	
	//==========================================================//
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������

	}

}
