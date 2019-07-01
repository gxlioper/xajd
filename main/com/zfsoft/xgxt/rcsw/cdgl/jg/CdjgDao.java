/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-29 ����03:32:06 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.cdgl.sq.CdsqDao;
import com.zfsoft.xgxt.rcsw.cdgl.sq.CdsqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: ���ؽ��DAO 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-4-29 ����03:32:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CdjgDao extends SuperDAOImpl<CdjgForm> {

	/**
	 * @���ԣ� KEY_NAME ������
	 */
	private static final String KEY_NAME = "jgid";
	/**
	 * @���ԣ� TABLE_NAME ����
	 */
	private static final String TABLE_NAME = "XG_XLJK_CDGL_CDSQJGB";
	/**
	 * @���ԣ� MODEL_CLAZZ class ����
	 */
	private static final Class<CdjgForm> MODEL_CLAZZ = CdjgForm.class;
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CdjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CdjgForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.cyrs,a.fzrxm,a.fzrlxfs,a.jgid,")
		.append("       a.xh,")
		.append("       a.cdid,")
		.append("       a.bmlbdm,")
		.append("       d.bmmc bmlbmc,")
		.append("       to_char(to_date(a.sqsj , 'yyyy-MM-dd HH24:mi:ss') , 'yyyy-MM-dd') sqsj,")
		.append("       a.sqsjdkssj,")
		.append("       a.sqsjdjssj,")
		.append("       a.sqly,")
		.append("       a.shzt,")
		.append("       a.splcid,")
		.append("       a.sjly,")
		.append("       a.sqid,")
		.append("       b.xm,")
		.append("       b.xb,")
		.append("       b.nj,")
		.append("       b.xydm,")
		.append("       b.xymc,")
		.append("       b.zydm,")
		.append("       b.zymc,")
		.append("       b.bjdm,")
		.append("       b.bjmc,b.sjhm,")
		.append("       c.cdmc,c.lxr,c.lxfs,c.xfgfsyxy,")
		.append("       c.ld,")
		.append("       c.fj,")
		.append("       c.rnrs,")
		.append("       c.sfbz,")
		.append("       c.yt,")
		.append("       c.jbsbjs,")
		.append("       c.dwkfsjkssj || '-' || c.dwkfsjjssj dwkfsj, ")
		.append("       a.sqsjdkssj || '��' ||  a.sqsjdjssj sqsjd")
		.append("  from XG_XLJK_CDGL_CDSQJGB a")
		.append("  left join view_xsjbxx b")
		.append("    on a.xh = b.xh")
		.append("  left join zxbz_xxbmdm d on a.bmlbdm = d.bmdm ")
		.append("  left join XG_XLJK_CDGL_CDXXB c")
		.append("    on a.cdid = c.cdid) t1 where 1=1 ")
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * ��ȡ���ؽ����Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-29 ����10:19:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getCdjgxx(String jgid){
		StringBuffer sql = new StringBuffer();
		sql.append("select b.xfgfsyxy,a.*,b.cdmc , b.ld , b.fj , b.rnrs , b.sfbz , b.dwkfsjkssj, b.dwkfsjjssj , b.yt , b.jbsbjs , b.sfkfsq , b.splcid ,")
		.append("    b.lxr,b.lxfs,b.filepath, c.xb , c.xm , c.nj , c.xymc , c.zymc , c.bjmc , d.bmmc ")
		.append("  from XG_XLJK_CDGL_CDSQJGB a ")
		.append("  left join XG_XLJK_CDGL_CDXXB b ")
		.append("    on a.cdid = b.cdid ")
		.append("  left join view_xsjbxx c ")
		.append("    on a.xh = c.xh ")
		.append("  left join zxbz_xxbmdm d on a.bmlbdm = d.bmdm where jgid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{jgid});
	}
	
	/**
	 * 
	 * @����:��������idɾ�����������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-30 ����09:11:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public boolean deleteJgBySqid(String sqid) throws Exception{
		String sql = "delete from XG_XLJK_CDGL_CDSQJGB where sqid = ? ";
		return dao.runUpdate(sql, new String[]{sqid});
	}

	
	/**
	 * 
	 * @����:��ȡ�Ѿ��������еĻ����Ѿ�����ͨ���ĳ��������б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-25 ����02:15:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getYxCdsq(String cdid){
		
		String sql = "select * from XG_XLJK_CDGL_CDSQJGB a WHERE a.cdid = ?";
		
		return dao.getListNotOut(sql, new String[]{cdid});
	}
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(CdjgDao.MODEL_CLAZZ);
		super.setKey(CdjgDao.KEY_NAME);
		super.setTableName(CdjgDao.TABLE_NAME);

	}

	public boolean updatePjxx(CdjgForm t) throws Exception {
		String sql = "update xg_xljk_cdgl_cdsqjgb set pj=?,pjbz=? where sqid=?";
		return dao.runUpdate(sql,new String[]{t.getPj(),t.getPjbz(),t.getSqid()});
	}

	public boolean isExistPj(CdjgForm model) {	
		String sql = "select count(1) num from xg_xljk_cdgl_cdsqjgb where sqid =? and pj is not null " ;
		String num = dao.getOneRs(sql, new String[]{model.getSqid()}, "num");
		return Integer.valueOf(num)>0;
	}

}
