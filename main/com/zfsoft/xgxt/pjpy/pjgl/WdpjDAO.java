/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-24 ����02:41:16 
 */  
package com.zfsoft.xgxt.pjpy.pjgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������֮�ҵ����� 
 * @���ߣ� zhangjw 
 * @ʱ�䣺 2013-5-24 ����02:24:52 
 * @�汾�� V5.8.16
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WdpjDAO extends SuperDAOImpl<WdpjForm>{

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 * @param t
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WdpjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WdpjForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo() 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo() 
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}
	/**
	 * @����:��ȡ����֤����Ϣ 
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-24 ����04:52:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getRyzsdyXX(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select b.xm,a.pjxn xn,c.xmmc  from xg_pjpy_pjxmsqb a left join xg_view_pjpy_pjryk b on a.xh = b.xh left join xg_pjpy_pjxmwhb c on a.xmdm = c.xmdm where (a.sqjg = 'tg' or a.sqjg ='wxsh') and a.xh = ? ");
		return dao.getMap(sql.toString(), new String[]{xh}, new String[]{"xm","xn","xmmc"});
	}

}
