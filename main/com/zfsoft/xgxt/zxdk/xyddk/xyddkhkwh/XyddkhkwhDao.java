/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-22 ����08:59:36 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.xyddkhkwh;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ����-У԰�ش�����ң�-����ά��
 * @�๦������: ����ά��
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2016-7-22 ����08:59:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyddkhkwhDao extends SuperDAOImpl<XyddkhkwhForm>{
	/*
		����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XyddkhkwhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	/*
		����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	/**
	 * ��ѯ
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XyddkhkwhForm t, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.*,t2.xm,t2.xb,t2.yhkh,t2.zzmmmc,t2.mzmc,t3.yhmc,t2.sfzh,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx,t4.xqmc ");
		sql.append(" from XG_ZDGXH_XYDDK_JGB t1 left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xqdzb t4 on t1.xq = t4.xqdm ");
		sql.append(" left join dmk_yh t3 on t2.yhdm = t3.yhdm ");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
		����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	*/

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(XyddkhkwhForm.class);
		super.setKey("jgid");
		super.setTableName("XG_ZDGXH_XYDDK_JGB");		
	}
	/**
	 * �õ�ѧ������
	 * ���ߣ�MengWei
	 * TIME��2016.07.22 9:38AM
	 */
	public String getXqmc(String xqdm){
		String sql = " select xqmc from xqdzb where xqdm = ? ";
		return dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	/**
	 * @����: �ж�Ψһ��
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-7-22 ����09:47:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecord(XyddkhkwhForm form){
		String sql = " select count(1) num from XG_ZDGXH_XYDDK_JGB where xh = ? ";
		String num = dao.getOneRs(sql, new String[]{form.getXh()}, "num");
		return Integer.valueOf(num)>0;
	}
}