package com.zfsoft.xgxt.pjpy.xzhcp.jg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.pjpy.xzhcp.sq.ZhcpDjForm;

public class ZhcpJgDao extends SuperDAOImpl<ZhcpJgForm> {

	@Override
	public List<HashMap<String, String>> getPageList(ZhcpJgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(ZhcpJgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select t.*,t1.XM,t1.XB,t1.NJ,t1.XYDM,t1.XYMC, t1.ZYDM,t1.ZYMC,t1.BJDM,t1.BJMC,t2.xqmc ");
		sql.append(" from xg_pjpy_new_zhcpjgb t left join view_xsbfxx t1 on t.xh = t1.XH ");
		sql.append(" left join xqdzb t2 on t.xq = t2.xqdm ");
		sql.append(") t where 1= 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(ZhcpJgForm.class);
		this.setKey("sqid");
		this.setTableName("xg_pjpy_new_zhcpjgb");
	}
	
	/**
	 * 
	 * @����: ��֤�ظ���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-2-7 ����03:07:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkNotRepeat(ZhcpJgForm t){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_pjpy_new_zhcpjgb where xh = ? and xn = ?  ");
		paraList.add(t.getXh());
		paraList.add(t.getXn());
		if(StringUtils.isNotNull(t.getXq())){
			sql.append(" and xq = ? ");
			paraList.add(t.getXq());
		}
		String rs = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}), "cnt");
		return "0".equals(rs) ? true : false;
	}

}
