/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-21 ����03:43:13 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-����Ϣ����
 * @�๦������: ������ 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-7-21 ����03:43:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjsqDao extends SuperDAOImpl<HjsqForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjsqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (select t1.*, t2.xm, t2.xb, t2.xydm, t2.xymc, t2.nj, t2.zydm, t2.zymc, t2.bjdm, t2.bjmc, t2.zzmmmc, t2.mzmc, t2.yhmc, t2.yhkh, t2.sfzh, t3.jxlbmc, t4.jxdjmc, t5.jxmcmc, ");
        sql.append(" decode(t1.shzt, '0', 'δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', '�˻�', '5', '�����', t1.shzt) shztmc, ");
        sql.append(" (case t1.jsfs when '1' then '����' when '2' then '����' else t1.jsfs end) jsfsmc ");
        sql.append(" from xg_hjxxgl_sqb t1 left join view_xsxxb t2 on t1.xh = t2.xh left join xg_hjxxgl_jxlb t3 on t1.jxlbdm = t3.jxlbdm ");
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
		super.setClass(HjsqForm.class);
		super.setKey("sqid");
		super.setTableName("xg_hjxxgl_sqb");
	}
	
	/**
	 * 
	 * @����: ��̬ȡֵ(��������)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-22 ����01:57:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jxlbdm
	 * @param jsfs
	 * @param jxdjdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxmcList(String jxlbdm, String jsfs, String jxdjdm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.jxmcdm,t.jxmcmc from xg_hjxxgl_jxmc t ");
		
		sql.append("  where t.jxlbdm = '"+jxlbdm+"' and t.jsfs = '"+jsfs+"' and t.jxdjdm = '"+jxdjdm+"' "); 
		
		sql.append(" order by t.jxmcdm ");
		
		return dao.getArrayList(sql.toString(), new String[]{}, new String[]{"jxmcdm","jxmcmc"});
	}
	
	/**
	 * 
	 * @����: ��̬ȡֵ(���)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-22 ����04:20:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jxlbdm
	 * @param jsfs
	 * @param jxdjdm
	 * @param jxmcdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getJe(String jxlbdm, String jsfs, String jxdjdm, String jxmcdm) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select t.je from xg_hjxxgl_jxmc t ");
		
		sql.append("  where t.jxlbdm = '"+jxlbdm+"' and t.jsfs = '"+jsfs+"' and t.jxdjdm = '"+jxdjdm+"' and t.jxmcdm = '"+jxmcdm+"' "); 
		
		return dao.getArrayList(sql.toString(), new String[]{}, new String[]{"je"});
	}
	
	/**
	 * 
	 * @����: ��ȡsplc
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-22 ����04:39:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_PJPY_XWHJCSSZ ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	/**
	 * 
	 * @����: �ظ���֤�����ӣ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-26 ����11:24:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String isExistByHjsqSave(HjsqForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_hjxxgl_sqb where xh=? and jxlbdm = ? and jsfs = ? and jxdjdm = ? and jxmcdm = ? and hdsj = ? and shzt <> '2' ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(), model.getJxlbdm(), model.getJsfs(), model.getJxdjdm(), model.getJxmcdm(), model.getHdsj()}, "num");
		return num;
		
	}
	
	/**
	 * 
	 * @����: �ظ���֤���޸ģ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-26 ����11:25:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String isExistByHjsqUpdate(HjsqForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_hjxxgl_sqb where xh=? and jxlbdm = ? and jsfs = ? and jxdjdm = ? and jxmcdm = ? and hdsj = ? and shzt <> '2' and sqid <> ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(), model.getJxlbdm(), model.getJsfs(), model.getJxdjdm(), model.getJxmcdm(), model.getHdsj(), model.getSqid()}, "num");
		return num;
		
	}
	
	/**
	 * ��д
	 */
	public HjsqForm getModel(String sqid) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (select t1.*, t2.xm, t2.xydm, t2.xymc, t2.nj, t2.zydm, t2.zymc, t2.bjdm, t2.bjmc, t2.zzmmmc, t2.mzmc, t2.yhmc, t2.yhkh, t2.sfzh, t3.jxlbmc, t4.jxdjmc, t5.jxmcmc, ");
        sql.append(" decode(t1.shzt, '0', 'δ�ύ', '1', 'ͨ��', '2', '��ͨ��', '3', '�˻�', '5', '�����', t1.shzt) shztmc, ");
        sql.append(" (case t1.jsfs when '1' then '����' when '2' then '����' else t1.jsfs end) jsfsmc ");
        sql.append(" from xg_hjxxgl_sqb t1 left join view_xsxxb t2 on t1.xh = t2.xh left join xg_hjxxgl_jxlb t3 on t1.jxlbdm = t3.jxlbdm ");
        sql.append(" left join xg_hjxxgl_jxdj t4 on t1.jxdjdm = t4.jxdjdm left join xg_hjxxgl_jxmc t5 on t1.jxmcdm = t5.jxmcdm) t ");
        sql.append(" where 1 = 1 and t.sqid = ? ");
        return getModel(sql.toString(), new String[] { sqid });
	}
}
