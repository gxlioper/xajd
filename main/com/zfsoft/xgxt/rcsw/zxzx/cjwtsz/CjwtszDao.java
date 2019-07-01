package com.zfsoft.xgxt.rcsw.zxzx.cjwtsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class CjwtszDao extends SuperDAOImpl<CjwtszForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_zxzx_zxzx");
		super.setKey("zxid");
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CjwtszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CjwtszForm t, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.*,decode(t1.sfzd,'1','��','��') sfzdmc,t2.bkmc from xg_rcsw_zxzx_zxzx t1 ");
		sql.append(" left join xg_rcsw_zxzx_zxbk t2 on t1.bkid=t2.bkid ");
		sql.append(" where t2.sfqy='1' and t1.sfcjwt='1' ");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	@Override
	public CjwtszForm getModel(CjwtszForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,decode(t1.sfzd,'1','��','��') sfzdmc,t2.bkmc,t3.hfid,t3.hfnr from xg_rcsw_zxzx_zxzx t1 ");
		sql.append(" left join xg_rcsw_zxzx_zxbk t2 on t1.bkid=t2.bkid ");
		sql.append(" left join xg_rcsw_zxzx_zxhf t3 on t3.zxid=t1.zxid ");
		sql.append(" where t1.zxid = ? ");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(), new String[] { t.getZxid() });
		CjwtszForm model=new CjwtszForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}
	/**
	 * ɾ����������
	 */
	public boolean deleteCjwtsz(String values) throws Exception {
		String[] arr = values.split(",");
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < arr.length; i++) {
			params.add(new String[]{ arr[i] });
		}
		String sql = "delete from xg_rcsw_zxzx_zxzx where zxid=?";
		int[] num = dao.runBatch(sql, params);
		boolean rs = dao.checkBatchResult(num);
		if(rs){
			sql = "delete from xg_rcsw_zxzx_zxhf where zxid=?";
			num = dao.runBatch(sql, params);
			rs = dao.checkBatchResult(num);
		}
		return rs;
	}
}
