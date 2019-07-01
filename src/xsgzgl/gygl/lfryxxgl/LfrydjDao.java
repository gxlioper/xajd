/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-19 ����05:08:08 
 */  
package xsgzgl.gygl.lfryxxgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������: ������Ա�Ǽǹ���  
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2014-8-19 ����05:08:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LfrydjDao extends SuperDAOImpl<LfrydjForm> {
	
	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	*/
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_GYGL_LFDJ");
		setKey("lfrdjid");
		setClass(LfrydjForm.class);
	
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LfrydjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LfrydjForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "b",
				//"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
				
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from (select a.lfrdjid,a.lfrxm,a.lfsydm,(select lfsymc from xg_gygl_lfsydm where lfsydm = a.lfsydm) lfsymc,");
		sql.append("a.lfrxb,a.lfsj,a.lqsj,a.lfrsfzh,a.zbry,b.*,d.ldmc,c.qsh");
		sql.append(" from xg_gygl_lfdj a left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join view_xg_gygl_new_cwxx c on a.xh = c.xh ");
		sql.append(" left join xg_gygl_new_ldxxb d on a.lddm = d.lddm) t where 1=1 ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	/**
	 * 
	 * @����: ��ȡ������Ա�Ǽ���Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-20 ����03:35:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lfrydjid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getLfrydjxx(String lfrdjid){
		String sql = "select * from (select a.lfrdjid,a.lfrxm,a.lfrxb,a.lfsj,a.lqsj,a.lfrsfzh,a.zbry,a.bz,b.*,d.ldmc,c.qsh  "
			+ ",a.lfsydm,(select lfsymc from xg_gygl_lfsydm where lfsydm = a.lfsydm) lfsymc"
			+ "  from xg_gygl_lfdj a " + "  left join view_xsbfxx b " + "    on a.xh = b.xh " 
			+ "    left join view_xg_gygl_new_cwxx c on a.xh = c.xh"
			+ " left join xg_gygl_new_ldxxb d on a.lddm = d.lddm) where  lfrdjid = ?";
		return dao.getMapNotOut(sql, new String[]{lfrdjid});
	}

	/**
	 * 
	 * @����: ɾ����Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-21 ����10:07:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lfrdjid
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int deleteLfrydjxx(String lfrdjid) throws Exception{
		String sql = "delete from XG_GYGL_LFDJ where LFRDJID = ? ";
		return dao.runDelete(sql, new String[]{lfrdjid});
			
	}
	
	
	/**
	 * 
	 * @����: ɾ����Ϣ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-21 ����10:45:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pks
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public int[] deleteLfrydjxxPl(List<String[]> pks) throws Exception{
		String sql = "delete from XG_GYGL_LFDJ where LFRDJID = ? ";
		return dao.runBatch(sql, pks);
		
	}
		
	/**
	 * 
	 * @����:��ȡ����ѧ����Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-26 ����09:27:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXsxxOne(String xh){
		String sql="select a.xh,b.ldmc,b.qsh,a.xm,a.zymc,a.bjmc,a.xymc " +
				"from view_xsjbxx a,view_xg_gygl_new_cwxx b where a.xh=b.xh and a.xh=?";
		return dao.getMapNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 
	 * @����: ��У����Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-8-2 ����02:36:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxx(LfrydjForm t, String pk,String type) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xh,t1.xm,t1.nj,t1.xymc,t1.bjmc,t1.ldmc,t1.qsh,t1.cwh from (select c.nj||'!!luojw!!'||c.xydm||'!!luojw!!'||c.zydm||'!!luojw!!'||a.bjdm pk,a.xh,a.xm,a.nj,c.xymc,c.bjmc,b.ldmc,b.qsh,b.cwh from xsxxb a left join view_xg_gygl_new_cwxx b on a.xh = b.xh left join view_njxyzybj_all c ");
		if("bzr".equals(type)){
			sql.append(" on a.zybj=c.bjdm where a.sfzx = '��У' or a.sfzx is null order by a.xh) t1 where 1 = 1");
		} else {
			sql.append(" on a.bjdm=c.bjdm where a.sfzx = '��У' or a.sfzx is null order by a.xh) t1 where 1 = 1");
		}
		sql.append(" and pk='");
		sql.append(pk);
		sql.append("'");
		return getPageList(t, sql.toString(), new String[]{});
	}

	/** 
	 * @����:��ѯ���������б�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��17�� ����9:00:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getLfsyList() {
		String sql = "SELECT lfsydm,lfsymc FROM xg_gygl_lfsydm";
		return dao.getListNotOut(sql, new String[]{});
	}
	
}
