package com.zfsoft.xgxt.rcsw.zxzx.zxbksz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class ZxbkszDao extends SuperDAOImpl<ZxbkszForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_zxzx_zxbk");
		super.setKey("bkid");
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZxbkszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZxbkszForm t, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.*,decode(t1.sfqy,'1','����','ͣ��') sfqymc from xg_rcsw_zxzx_zxbk t1 ");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * �Ƿ��Ѵ���
	 */
	public boolean checkExistSave(ZxbkszForm model, User user) {
		String bkid = "-1";
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_rcsw_zxzx_zxbk where bkmc = ? and bkid <> ? ");
		if(model.getBkid() != null){
			bkid = model.getBkid();
		}
		String num = dao.getOneRs(sql.toString(), new String[] { model.getBkmc().trim(),bkid }, "num");
		return "0".equals(num);
	}
	@Override
	public ZxbkszForm getModel(ZxbkszForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,decode(t1.sfqy,'1','����','ͣ��') sfqymc from xg_rcsw_zxzx_zxbk t1 ");
		sql.append(" where t1.bkid = ? ");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(), new String[] { t.getBkid() });
		ZxbkszForm model=new ZxbkszForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}
	/**
	 * �ж��Ƿ���
	 */
	public String checkZxbkszDel(String values)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select replace(wm_concat(a.bkmc),';','��') bkmc from ( ");
		sql.append(" select distinct a.bkmc from ( ");
		sql.append(" select a.bkmc from XG_RCSW_ZXZX_ZXBK a ");
		sql.append(" left join XG_RCSW_ZXZX_ZXZX b on a.bkid = b.bkid ");
		sql.append(" where b.bkid is not null and a.bkid in ('" + values.replace(",", "','") + "') ");
		sql.append(" ) a ");
		sql.append(" ) a ");
		return dao.getOneRs(sql.toString(), new String[] {}, "bkmc");
	}
	/**
	 * ��ȡʹ���еļ�¼
	 */
	public List<HashMap<String, String>> getZxbkszListSyz(String values)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.bkid,a.bkmc from ( ");
		sql.append(" select a.bkid from XG_RCSW_ZXZX_ZXBK a ");
		sql.append(" left join XG_RCSW_ZXZX_ZXZX b on a.bkid = b.bkid ");
		sql.append(" where b.bkid is not null and a.bkid in (" + values + ") ");
		sql.append(" ) a ");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	/**
	 * ��ȡ�������õ���ѯ���
	 */
	public List<HashMap<String, String>> getAllOkZxbkszList()throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from XG_RCSW_ZXZX_ZXBK where sfqy='1' order by cast(xssx as decimal(10,2)) asc ");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
}
