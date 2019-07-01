/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-28 ����09:13:15 
 */  
package com.zfsoft.xgxt.zxdk.ysjxj.ysjxj;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ����-Ժ�轱ѧ��
 * @�๦������: TODO(Ժ�轱ѧ��) 
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2016-7-28 ����09:13:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YsjxjDao extends SuperDAOImpl<YsjxjForm>{
	/*
		����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YsjxjForm t)
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
		super.setClass(YsjxjForm.class);
		super.setKey("juid");
		super.setTableName("XG_ZXDK_NEW_YSJXJ");		
	}

	/*
		����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	/**
	 * ��ѯ
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YsjxjForm t, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select a.*,b.xqmc,c.zjlymc,t2.xm,t2.xb,t2.zzmmmc,t2.mzmc,t2.sfzh,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.xz,t2.dzyx,t2.xmsj sjdh,t2.sjhm,t2.yhkh,t3.yhmc ");
		sql.append(" from xg_zxdk_new_ysjxj a ");
		sql.append(" left join xqdzb b on a.xq = b.xqdm ");
		sql.append(" left join xg_zxdk_new_ysjxjdmb c on a.zjly = c.zjlydm ");
		sql.append(" left join view_xsbfxx t2 on t2.xh = a.xh ");
		sql.append(" left join dmk_yh t3 on t3.yhdm = t2.yhdm ");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @����:TODO(ȡѧ������)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-29 ����03:05:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXqmc(String xqdm){
		String sql = " select xqmc from xqdzb where xqdm = ? ";
		return dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	
	/**
	 * @����:TODO(��ȡ�ʽ���Դ����)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-29 ����03:12:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zjly
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZjlymc(String zjly){
		String sql = " select zjlymc from xg_zxdk_new_ysjxj a left join xg_zxdk_new_ysjxjdmb b on a.zjly = b.zjlydm where zjly = ? ";
		return dao.getOneRs(sql, new String[]{zjly}, "zjlymc");
	}
	/**
	 * @����:TODO(��ȡ�ʽ���Դ�б�)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-29 ����10:41:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZjlyList() {
		String sql = " select zjlydm,zjlymc from xg_zxdk_new_ysjxjdmb order by zjlydm ";
		return dao.getList(sql, new String[] {}, new String[] { "zjlydm","zjlymc" });
	}
	
	/**
	 * @����:TODO(�����ж�Ψһ��)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-29 ����11:56:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecord(String xh,String xn,String xq,String xmmc){
		String sql = " select count(1) num from xg_zxdk_new_ysjxj where xh = ? and xn = ? and xq = ? and xmmc = ?";
		String num = dao.getOneRs(sql, new String[]{ xh,xn,xq,xmmc }, "num");
		return Integer.valueOf(num)>0;	
	}
	/**
	 * @����:TODO(�޸��ж�Ψһ��)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-8-1 ����03:06:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param xmmc
	 * @param juid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateCheck(String xh,String xn,String xq,String xmmc,String juid){
		String sql = " select count(1) num from xg_zxdk_new_ysjxj where xh = ? and xn = ? and xq = ? and xmmc = ? and juid <>?";
		String num = dao.getOneRs(sql, new String[]{ xh,xn,xq,xmmc,juid }, "num");
		return Integer.valueOf(num)>0;	
	}
}
