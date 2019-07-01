/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-27 ����04:03:51 
 */  
package xsgzgl.gygl.gzwpcmdjgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������: ������Ʒ���ŵǼǹ���  
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2014-8-27 ����04:03:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzwpcmdjDao extends SuperDAOImpl<GzwpcmdjForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzwpcmdjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzwpcmdjForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "b",
		//"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select a.gzwpdjid,a.wpmc,a.cmsj,a.zbry,a.bz,b.*,c.ldmc,c.qsh" + 
				" from xg_gygl_gzwpcmdj a left join view_xsbfxx b on a.xh = b.xh" + 
				" left join view_xg_gygl_new_cwxx c on a.xh = c.xh where 1=1 ")
		.append(searchTj);
			
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����: ��ȡ������Ʒ���ŵǼ���Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����09:37:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gzwpdjid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getGzwpcmdjxx(String gzwpdjid) {
		String sql = "select * from (select a.gzwpdjid,a.wpmc,a.cmsj,a.zbry,a.bz,b.*,c.ldmc,c.qsh  "
			+ "  from xg_gygl_gzwpcmdj a " + "  left join view_xsbfxx b " + "    on a.xh = b.xh " 
			+ "    left join view_xg_gygl_new_cwxx c on a.xh = c.xh) where  gzwpdjid = ?";
		return dao.getMapNotOut(sql, new String[]{gzwpdjid});
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setTableName("XG_GYGL_GZWPCMDJ");
		setClass(GzwpcmdjForm.class);

	}
	
	/**
	 * 
	 * @����: ɾ����Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����09:48:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gzwpdjid
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int deleteGzwpcmdjxx(String gzwpdjid) throws Exception{
		String sql = "delete from XG_GYGL_GZWPCMDJ where GZWPDJID = ? ";
		return dao.runDelete(sql, new String[]{gzwpdjid});
		
	}
	
	
	/**
	 * 
	 * @����: ɾ����Ϣ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����09:56:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pks
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public int[] deleteGzwpcmdjxxPl(List<String[]> pks) throws Exception{
		String sql = "delete from XG_GYGL_GZWPCMDJ where GZWPDJID = ? ";
		return dao.runBatch(sql, pks);
		
	}
	
	/**
	 * 
	 * @����: �����������������Ʒ���ŵǼ���Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����11:23:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveGzwpcmdjxx(GzwpcmdjForm model) throws Exception{
		
		String select = "select GZWPDJID , WPMC , CMSJ , ZBRY , BZ , XH from XG_GYGL_GZWPCMDJ where GZWPDJID = ? ";
		String update = "update XG_GYGL_GZWPCMDJ set WPMC = ? , CMSJ = ? , ZBRY = ? , BZ = ? , XH = ? where GZWPDJID = ? ";
		
		/**
		 * ��ѯ
		 */
		List<HashMap<String, String>> ls = dao.getListNotOut(select, new String[]{model.getGzwpdjid()});
		
		/**
		 * ����
		 */
		if(ls != null && ls.size() >= 1) {
			return dao.runUpdate(update, new String[]{model.getWpmc() , model.getCmsj() , model.getZbry() , model.getBz() , model.getXh() , model.getGzwpdjid()});
		/**
		 * ����	
		 */			
		}else{
			
			return runInsert(model);
		}
		
	}
	
	/**
	 * 
	 * @����: ��ȡ����ѧ����Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����10:48:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXsxxOne(String xh){
		String sql = "select a.xh,b.ldmc,b.qsh,a.xm,a.zymc,a.bjmc,a.xymc,a.sfzh,a.xb " +
				"from view_xsjbxx a,view_xg_gygl_new_cwxx b where a.xh=b.xh and a.xh=?";
		return dao.getMapNotOut(sql, new String[]{xh});
	}
}
