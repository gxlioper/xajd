/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-26 ����11:49:12 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.xsbxbx;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ���չ���-ѧ�����ձ���
 * @���ߣ� ������ [����:1123]
 * @ʱ�䣺 2015-1-26 ����11:49:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsbxbxDao extends SuperDAOImpl<XsbxbxForm> {
	
	/*
             ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(XsbxbxForm.class);
		super.setKey("bxid");
		super.setTableName("xg_rcsw_bxgl_xsbxbxb");
	}

	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	*/

	@Override
	public List<HashMap<String, String>> getPageList(XsbxbxForm t)
			throws Exception {
		
		return null;
	}

	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/

	@Override
	public List<HashMap<String, String>> getPageList(XsbxbxForm t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from (select decode(t1.clzb, '01', '��ȫ', '02', 'ȱ��', t1.clzb) clzbmc, ");
		sql.append(" decode(t1.bxxz, '01', '��ҵ����', '02', '����ҽ��', t1.bxxz) bxxzmc, ");
		sql.append(" decode(t1.lx, '01', 'סԺ', '02', '����', '03', '����', t1.lx) lxmc, ");
		sql.append(" t1.*, ");
		sql.append(" t2.XM, ");
		sql.append(" t2.XYMC, ");
		sql.append(" t2.XYDM, ");
		sql.append(" t2.ZYDM, ");
		sql.append(" t2.BJDM, ");
		sql.append(" t2.SFZH, ");
		sql.append(" t2.ZYMC, ");
		sql.append(" t2.BJMC, ");
		sql.append(" t2.XB, ");
		sql.append(" t2.NJ, ");
		sql.append(" t2.SJHM ");
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			sql.append(" ,bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm ");
		}
		sql.append(" from XG_RCSW_BXGL_XSBXBXB t1 ");
		sql.append(" left join view_xsbfxx t2 ");
		sql.append(" on t1.xh = t2.xh ");
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			sql.append(" left join view_xg_bzrxx bzr on t2.bjdm = bzr.bjdm");
		}
		sql.append(" ) t where 1 = 1 ");
		
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����: �鿴������¼����
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2015-1-28 ����09:19:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bxid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXsbxbx(String bxid){
		String sql = " select t1.*,decode(t1.clzb, '01', '��ȫ', '02', 'ȱ��', t1.clzb) clzbmc," +
				"decode(t1.bxxz, '01', '��ҵ����', '02', '����ҽ��', t1.bxxz) bxxzmc," +
				"decode(t1.lx, '01', 'סԺ', '02', '����', '03', '����', t1.lx) lxmc from XG_RCSW_BXGL_XSBXBXB t1 " +
				" where  bxid = ? ";
		return dao.getMapNotOut(sql, new String[]{bxid});
	}
	
	/**
	 * 
	 * @����: ����֤����ӡ
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2015-2-3 ����01:39:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bxid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> bxbxZm(String bxid) throws Exception{
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.xh, a.zlbh, a.bxje, a.jbnr, a.jzyy, a.jzsj, a.bzqk, b.XYMC, b.ZYMC, b.BJMC, b.XM, b.XB, b.SFZH ");
		sql.append(" from XG_RCSW_BXGL_XSBXBXB a ");
		sql.append(" left join view_xsbfxx b ");
		sql.append(" on a.xh = b.xh where bxid = ? ");
		
		return dao.getMap(sql.toString(), new String[]{bxid}, new String[]{"xh","zlbh","bxje","jbnr","jzyy","jzsj","bzqk","xymc","zymc","bjmc","xm","xb","sfzh"});
	}
	/**
	 * 
	 * @����:ɾ�����ձ���������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-2 ����03:14:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bxid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delBxbxjg(String bxid) throws Exception{
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_rcsw_bxgl_xsbxbxb where bxid=?");
		return dao.runUpdate(sql.toString(), new String[]{bxid});
	}
	
	
	
}
