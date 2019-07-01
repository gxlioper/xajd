/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����01:29:18 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.xlwjyjk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-6-3 ����01:29:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlwjyjkDao extends SuperDAOImpl<XlwjyjkForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XlwjyjkForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XlwjyjkForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.gzdj , a.rksj , a.bz , b.xh , b.xm , b.nj , b.xydm , b.xymc , b.zydm , b.zymc , b.xb , b.bjdm , b.bjmc, c.lxdm ,c.lxmc, " +
				"(select max(sj) from XG_XLJK_XLWJYJ_XLFDXXB d where d.xh = a.xh) zjfdsj " + 
				"from XG_XLJK_XLWJYJ_XLWJYJK a " +
				"left join view_xsjbxx b on a.xh = b.xh left join XG_XLJK_XLWJYJ_XLWTLX c on a.lxdm = c.lxdm) t1 where 1=1 ")
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	//PL submit
	public int batchSubmit(String[] xhs , String lxdm , String gzdj , String bz , String rksj) throws Exception{
	
		String sql = "insert into XG_XLJK_XLWJYJ_XLWJYJK (xh , lxdm , gzdj , rksj , bz) values (?,?,?,?,?)";
		
		List<String[]> param = new ArrayList<String[]>();
		
		for (int i = 0; i < xhs.length; i++) {
			String[] p = new String[]{xhs[i] , lxdm , gzdj , rksj , bz};
			param.add(p);
		}
		
		int[] r = dao.runBatch(sql, param);
		
		return r.length;
	}
	
	/**
	 * 
	 * @����:��ȡԤ����ѧ����Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-5 ����02:16:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> xlwjyjkxsxx(String xh) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.gzdj , a.rksj , a.bz , b.xh , b.xm , b.nj , b.xydm , b.xymc , b.zydm , b.zymc , b.xb , b.bjdm , b.bjmc, c.lxdm ,c.lxmc, " +
				"(select max(sj) from XG_XLJK_XLWJYJ_XLFDXXB d where d.xh = a.xh) zjfdsj " + 
				"from XG_XLJK_XLWJYJ_XLWJYJK a " +
				"left join view_xsjbxx b on a.xh = b.xh left join XG_XLJK_XLWJYJ_XLWTLX c on a.lxdm = c.lxdm) t1 where xh = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
		
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(XlwjyjkForm.class);
		setKey("xh");
		setTableName("XG_XLJK_XLWJYJ_XLWJYJK");
	}

}
