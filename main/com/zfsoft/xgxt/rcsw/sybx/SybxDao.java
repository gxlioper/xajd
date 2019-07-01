/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-9 ����08:45:05 
 */  
package com.zfsoft.xgxt.rcsw.sybx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: TODO ��ҵ���չ���
 * @���ߣ� honglin 
 * @ʱ�䣺 2013-5-8 ����05:22:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SybxDao extends SuperDAOImpl<SybxForm>{


	@Override
	public List<HashMap<String, String>> getPageList(SybxForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * ʹ�ø߼���ѯ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> getPageList(SybxForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select *");
		sql.append(" from view_xg_rcsw_sybxxxb a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_sybxxxb");
		super.setKey("guid");
		super.setClass(SybxForm.class);
	}
	
	/**
	 * 
	 * @����:���Ӳ���Ψһ���жϣ�ѧ�ţ�ѧ�꣩
	 * @���ߣ�honglin
	 * @���ڣ�2013-5-9 ����02:09:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public String checkExistForSave(SybxForm model) {
		StringBuilder sql = new StringBuilder("select count(1) num from xg_rcsw_sybxxxb where xh=? and xn=?");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXh(),model.getXn()}, "num");
		return num;
		
	}
	
	/**
	 * 
	 * @����:�޸Ĳ���Ψһ���жϣ�ѧ�ţ�ѧ�꣩
	 * @���ߣ�honglin
	 * @���ڣ�2013-5-9 ����02:09:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public String checkExistForUpdate(SybxForm model) {
		StringBuilder sql = new StringBuilder("select count(1) num from xg_rcsw_sybxxxb where xh=? and xn=? and guid<>?");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXh(),model.getXn(),model.getGuid()}, "num");
		return num;
		
	}

	/**
	  * 
	  * @����:��õ���ѧ����ҵ������Ϣ
	  * @���ߣ�HongLin
	  * @���ڣ�2013-5-9 ����02:09:02
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param xh
	  * @param request
	  * @return
	  * List<String[]> �������� 
	  * @throws
	  */
	public HashMap<String, String> getOneSybxList(String  guid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select xn,bxje,jhrxm,jhrsfzh,txdz,bxje,bz,czjmylbxje,sybxje,zjyy,czjmylbxcbqsrq,czjmylbxcbjsrq,sybxcbqsrq,sybxcbjsrq,cbrylb,jfrylb,sfzqfjg,sfzyxqxqsrq,sfzyxqxjzrq ");
		sql.append(",zjyymc,cbrylbmc,jfrylbmc ");
		sql.append("from view_xg_rcsw_sybxxxb a where guid=? ");
		return dao.getMapNotOut(sql.toString(),new String[]{guid});
	}
	
	/**
	 * @����:��ȡ����ԭ��
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-11 ����03:00:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getAllZjyyList() {
		String sql = "select dm,mc from WFXY_ZJYYB";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/**
	 * @����:��ȡ�α���Ա���
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-11 ����03:00:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getAllCbrylbList() {
		String sql = "select dm,mc from WFXY_CBRYLBB";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/**
	 * @����:��ȡ�ɷ���Ա���
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-11 ����03:00:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getAllJfrylbList() {
		String sql = "select dm,mc from WFXY_JFRYLBB";
		return dao.getListNotOut(sql, new String[]{});
	}
}
