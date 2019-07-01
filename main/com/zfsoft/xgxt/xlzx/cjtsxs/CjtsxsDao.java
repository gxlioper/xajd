/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.xlzx.cjtsxs;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @className	�� CjtsxsDao
 * @description	�� ��������ѧ��dao(��������������)
 * @author 		��������1282��
 * @date		�� 2017-11-7 ����01:50:55
 * @version 	V1.0 
 */

public class CjtsxsDao extends SuperDAOImpl<CjtsxsForm>{

	/**
	 * @description	�� TODO
	 * @author 		��������1282��
	 * @date 		��2017-11-7 ����01:51:31
	 * @param t
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CjtsxsForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @description	�� ��ѯ�б�����
	 * @author 		��������1282��
	 * @date 		��2017-11-7 ����01:51:31
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CjtsxsForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.xydm,t2.xymc,t2.bjdm,t2.bjmc,t2.nj,t2.zydm,t2.zymc,t2.xm,t2.xb,t3.xqmc");
		sql.append(" from xg_xljkzx_cjtsxsb t1");
		sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh");
		sql.append(" left join xqdzb t3 on t1.xq = t3.xqdm");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @description	�� TODO
	 * @author 		��������1282��
	 * @date 		��2017-11-7 ����01:51:31
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(CjtsxsForm.class);
		super.setKey("id");
		super.setTableName("xg_xljkzx_cjtsxsb");		
	}
	
	/**
	 * @description	�� �Ƿ���ڸ�ѧ��(����ѧ�ꡢѧ���ж�)
	 * @author 		�� ������1282��
	 * @date 		��2017-11-7 ����02:55:00
	 * @return
	 */
	public boolean isExist(CjtsxsForm t){
		StringBuilder sb = new StringBuilder();
		String[] input;
		sb.append(" select count(1) as rn from xg_xljkzx_cjtsxsb where xh = ?");
		if(null != t.getId()){
			sb.append(" and id <> ?");
			input = new String[]{t.getXh(),t.getId()};
		}else{
			input = new String[]{t.getXh()};
		}
		String num = dao.getOneRs(sb.toString(), input, "rn");
		return Integer.valueOf(num) < 1;
	}

}
