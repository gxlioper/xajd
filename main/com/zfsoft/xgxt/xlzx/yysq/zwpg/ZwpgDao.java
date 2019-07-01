/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-3-6 ����05:04:29 
 */  
package com.zfsoft.xgxt.xlzx.yysq.zwpg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018-3-6 ����05:04:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZwpgDao extends SuperDAOImpl<ZwpgForm> {

	
	
	@Override
	protected void setTableInfo() {
		super.setKey("id");
		super.setTableName("XLZX_ZWPG");
		
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwpgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwpgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-3-19 ����10:16:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * ZwpgForm �������� 
	 * @throws 
	 */
	public HashMap<String,String> getInfoByXh(String xh) {
		String sql = "select * from (select t.* from XLZX_ZWPG t  where  xh=? order by sj desc) where rownum=1";
		return dao.getMapNotOut(sql, new String[]{xh});
	}


	public  HashMap<String,String> getInfoById(String id) {
		String sql = "select * from XLZX_ZWPG where  id=? ";
		return dao.getMapNotOut(sql, new String[]{id});
	}
}
