/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-3-29 ����09:42:08 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.zcxmdj;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2017-3-29 ����09:42:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcxmdjDao extends SuperDAOImpl<CsszForm>{
	/*
		����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zjdx_pjpy_zcxmxxb");
		super.setKey("id");
		super.setClass(CsszForm.class);
	}

	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	*/

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		return null;
	}

	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user)
			throws Exception {
		return null;
	}
	
	public boolean zcxmDjPlbc(List<String[]> params) throws SQLException {
		String sql = " insert into xg_zjdx_pjpy_zcxmxxb(xmdm,mc,px) values(?,?,?) ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/*
	     �޸ı����������ɾ��ͬһ������������Ϣ
	 */
	public boolean delZcxmdj(String xmdm) throws Exception {
		String sql = " delete from xg_zjdx_pjpy_zcxmxxb where xmdm = ?";
		return dao.runUpdate(sql, new String[] { xmdm });
	}
}
