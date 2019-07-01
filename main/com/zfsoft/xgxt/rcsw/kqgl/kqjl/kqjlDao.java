/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-8-4 ����10:18:51 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqjl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2017-8-4 ����10:18:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class kqjlDao extends SuperDAOImpl<kqjlForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(kqjlForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(kqjlForm t, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from( ");
		sql.append("select a.*,b.lddm,b.ldmc,b.qsh,c.xm,c.xymc,c.xydm,c.zydm,c.zymc,c.bjdm,c.bjmc,c.nj,c.xb ");
		sql.append("from ( select case when substr(kqsj, 6, 2) >= 9 then substr(kqsj, 0, 4) || '-' || to_char(to_number(substr(kqsj, 0, 4)) + 1) else to_number(substr(kqsj, 0, 4)) - 1 || '-' || substr(kqsj, 0, 4) end xn,");
		sql.append("xh,dkzt,kqsj,kqlb,case when (substr(kqsj, 6, 2) >= 2 and substr(kqsj, 6, 2) < 9) then");
		sql.append(" '�ڶ�ѧ��'when substr(kqsj, 6, 2) >= 9 and  substr(kqsj, 9, 2) > 1 then  '��һѧ��' else '��һѧ��' end xq");
		sql.append(" from xg_rcsw_kqglb) a  left join view_xg_gygl_new_cwxx b on a.xh = b.xh");
		sql.append(" left join view_xsbfxx c  on a.xh = c.xh");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);	
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_kqglb");
		super.setKey("xh");
		super.setClass(kqjlForm.class);
		
	}

	/**
	 * @param kqsj  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2017-8-7 ����05:00:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Map<String,String> �������� 
	 * @throws 
	 */
	public Map<String, String> getKqjl(String xh, String kqsj) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from view_kqjljg where xh=? and kqsj=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,kqsj});
	}

}
