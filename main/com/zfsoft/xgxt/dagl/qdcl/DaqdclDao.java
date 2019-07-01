/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-10 ����05:25:11 
 */  
package com.zfsoft.xgxt.dagl.qdcl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������ģ��
 * @�๦������: (������һ�仰��������������) 
 * @���ߣ�  wanghj [���ţ�1004]
 * @ʱ�䣺 2014-2-10 ����05:25:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DaqdclDao extends SuperDAOImpl<DaqdclForm> {

	protected void setTableInfo() {
		super.setTableName("xg_xsxx_dagl_daqdclb");
		super.setKey("daqdcl_id");
		super.setClass(DaqdclForm.class);
	}

	public List<HashMap<String, String>> getPageList(DaqdclForm t)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (");
		sql.append(" select t.daqdcl_id,t.daqdcl_mc,");
		sql.append("(select count(*) from XG_XSXX_DAGL_DAMBCLBDB where daqdcl_id=t.daqdcl_id) ybdmbs," );
		sql.append("(select count(*) from XG_XSXX_DAGL_XSDACLBDB where daqdcl_id=t.daqdcl_id) ybdxss " );
		sql.append(" from XG_XSXX_DAGL_DAQDCLB t) where 1=1 ");
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}

	public List<HashMap<String, String>> getPageList(DaqdclForm t, User user) throws Exception {
		return null;
	}
	
	/**
	 * 
	 * @����:�ж��Ƿ����е����������ƴ���
	 * @���ڣ�2014-4-23 ����03:24:00
	 * @param myForm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getDaqdclByName(DaqdclForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
		HashMap<String, String> lst = new HashMap<String, String>();
		String[] inputV =  new String[1];
		sql.append(" select * from XG_XSXX_DAGL_DAQDCLB t where t.daqdcl_mc=? ");
		
		if(myForm.getDaqdcl_id() !=null && !"".equals(myForm.getDaqdcl_id())){
			sql.append(" and t.daqdcl_id != ?");
			lst = dao.getMapNotOut(sql.toString(), new String[]{myForm.getDaqdcl_mc(), myForm.getDaqdcl_id()});
		}else{
			lst = dao.getMapNotOut(sql.toString(), new String[]{myForm.getDaqdcl_mc()});
		}

		return lst;
	}

	/**
	 * 
	 * @����:�����б�
	 * @���ڣ�2014-4-24 ����09:51:32
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDaqdclAllList() throws Exception {
		String sql ="select daqdcl_id clid,daqdcl_mc clmc from XG_XSXX_DAGL_DAQDCLB ";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	public List<HashMap<String, String>> getMbwclList() throws Exception {
		
		String sql ="select * from XG_XSXX_DAGL_DAQDCLB ";
		return dao.getListNotOut(sql, new String[]{});
	}
}
	