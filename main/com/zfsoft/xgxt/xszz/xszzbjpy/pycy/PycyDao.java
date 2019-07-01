/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-17 ����09:36:24 
 */  
package com.zfsoft.xgxt.xszz.xszzbjpy.pycy;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������: �༶����С���Ա
 * @���ߣ� ������[���ţ�1123]
 * @ʱ�䣺 2016-5-17 ����09:36:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PycyDao extends SuperDAOImpl<PycyForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(PycyForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(PycyForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select a.xh,a.xm,a.SJHM,a.guid,a.sfxsdb,a.tjzt,a.tjztmc,a.sfxsdbmc,b.*,'' bz, ");
		sql.append(" case when a.ldmc is not null then a.ssmc else '' end ssmc from ( ");
		sql.append(" select a.xh,a.xm,a.bjdm,a.SJHM,b.tjzt,decode(b.tjzt,'1','���ύ','δ�ύ') tjztmc, ");
		sql.append(" b.guid,b.sfxsdb,decode(b.sfxsdb,'1','��','��') sfxsdbmc,c.ldmc,c.qsh,c.cwh, ");
		sql.append(" (c.ldmc || '' || c.qsh || '��' || c.cwh || '�Ŵ�') ssmc ");
		sql.append(" from xg_xszz_new_knsrd_bjpyxz b left join view_xsbfxx a on a.xh=b.xh ");
		sql.append(" left join view_xg_gygl_new_cwxx c on a.XH = c.xh) a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		
		return getPageList(t, sql.toString(), inputValue);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}
	
	
	

}
