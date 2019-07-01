/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-27 ����01:19:12 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-����Ϣ����
 * @�๦������: �񽱽��  
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-7-27 ����01:19:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjjgDao extends SuperDAOImpl<HjjgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjjgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (select t1.*, t2.xm, t2.xb, t2.xydm, t2.xymc, t2.nj, t2.zydm, t2.zymc, t2.bjdm, t2.bjmc, t2.zzmmmc, t2.mzmc, t2.yhmc, t2.yhkh, t2.sfzh, t3.jxlbmc, t4.jxdjmc, t5.jxmcmc, ");
        sql.append(" (case t1.jsfs when '1' then '����' when '2' then '����' else t1.jsfs end) jsfsmc ");
        sql.append(" from xg_hjxxgl_jgb t1 left join view_xsxxb t2 on t1.xh = t2.xh left join xg_hjxxgl_jxlb t3 on t1.jxlbdm = t3.jxlbdm ");
        sql.append(" left join xg_hjxxgl_jxdj t4 on t1.jxdjdm = t4.jxdjdm left join xg_hjxxgl_jxmc t5 on t1.jxmcdm = t5.jxmcdm) t ");
        sql.append(" where 1 = 1 ");
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
		super.setClass(HjjgForm.class);
		super.setKey("jgid");
		super.setTableName("xg_hjxxgl_jgb");
	}
	
	/**
	 * 
	 * @����: �ظ���֤�����ӣ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-28 ����11:11:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecordForjg(HjjgForm form) {
		String sql = " select count(1) num from xg_hjxxgl_jgb where xh= ? and jxlbdm = ? and jsfs = ? and jxdjdm = ? and jxmcdm = ? and hdsj = ? ";
		String num = dao.getOneRs(sql, new String[]{form.getXh(),form.getJxlbdm(),form.getJsfs(),form.getJxdjdm(),form.getJxmcdm(),form.getHdsj()}, "num");
		return Integer.valueOf(num)>0;	
	}
	
	/**
	 * 
	 * @����: �ظ���֤���޸ģ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-28 ����11:11:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecordForjgU(HjjgForm form) {
		String sql = " select count(1) num from xg_hjxxgl_jgb where xh= ? and jxlbdm = ? and jsfs = ? and jxdjdm = ? and jxmcdm = ? and hdsj = ? and jgid <> ? ";
		String num = dao.getOneRs(sql, new String[]{form.getXh(),form.getJxlbdm(),form.getJsfs(),form.getJxdjdm(),form.getJxmcdm(),form.getHdsj(),form.getJgid()}, "num");
		return Integer.valueOf(num)>0;	
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-28 ����11:12:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteForSq(HjjgForm form) {
		String sql = " delete from xg_hjxxgl_jgb where xh = ? and jxlbdm = ? and jsfs = ? and jxdjdm = ? and jxmcdm = ? and hdsj = ? ";
		try {
			return dao.runUpdate(sql, new String[]{form.getXh(),form.getJxlbdm(),form.getJsfs(),form.getJxdjdm(),form.getJxmcdm(),form.getHdsj()});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @����: ����ɾ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-28 ����11:08:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lclyywid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delByLclyywid(String lclyywid){
		String sql = "delete from xg_hjxxgl_jgb where lylcywid = ?";
		try {
			return dao.runUpdate(sql, new String[]{lclyywid});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ��д
	 */
	public HjjgForm getModel(String jgid) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (select t1.*, t2.xm, t2.xydm, t2.xymc, t2.nj, t2.zydm, t2.zymc, t2.bjdm, t2.bjmc, t2.zzmmmc, t2.mzmc, t2.yhmc, t2.yhkh, t2.sfzh, t3.jxlbmc, t4.jxdjmc, t5.jxmcmc, ");
        sql.append(" (case t1.jsfs when '1' then '����' when '2' then '����' else t1.jsfs end) jsfsmc ");
        sql.append(" from xg_hjxxgl_jgb t1 left join view_xsxxb t2 on t1.xh = t2.xh left join xg_hjxxgl_jxlb t3 on t1.jxlbdm = t3.jxlbdm ");
        sql.append(" left join xg_hjxxgl_jxdj t4 on t1.jxdjdm = t4.jxdjdm left join xg_hjxxgl_jxmc t5 on t1.jxmcdm = t5.jxmcdm) t ");
        sql.append(" where 1 = 1 and t.jgid = ? ");
        
        return getModel(sql.toString(), new String[] { jgid });
	}
	
	
}
