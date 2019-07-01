/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-17 ����10:08:09 
 */  
package com.zfsoft.xgxt.rcsw.cxda;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.szdw.xsgbgl.rzkh.rzkhjgForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-6-17 ����10:08:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxdaDao extends SuperDAOImpl<CxdaForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxdaForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxdaForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t2.id,t2.xh,t2.cxfs,t2.bz,t2.xn,t2.xq,t3.xqmc xqmc,t1.xm,t1.xb, t1.zydm, t1.nj, t1.bjdm,t1.xydm,t1.xymc,t1.bjmc,t1.zymc,t1.sfzh,t1.mz mzmc,t1.zzmmmc  ");
		sql.append("from XG_RCSW_CXDA t2 , VIEW_XSJBXX t1 , xqdzb t3 where t2.xh = t1.xh and t3.xqdm = t2.xq)");
		sql.append(" t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append("  ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(CxdaForm.class);
		super.setKey("id");
		super.setTableName("XG_RCSW_CXDA");
	}
	
	public boolean delCxdaById(String id)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_RCSW_CXDA where id=? ");
		return dao.runUpdate(sql.toString(),new String[]{id});
	}
	
	//�ж��ڽ�������Ƿ��м�¼������в����䱣����Ϣ��������ʾ
	public boolean checkExistForSave(CxdaForm model) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("select t.id from XG_RCSW_CXDA t where t.xh = ? and t.xn = ? and t.xq = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXq()}, "id");
		if (num != null && ! num.equals("") ){
			flag = true;
		}
		return flag;
	}
	
	//��ȡѧ������
	public HashMap<String, String> getxqdz(String xqdm){
		StringBuilder sql = new StringBuilder();
		sql.append("   select xqmc from xqdzb where xqdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xqdm});
	}
}
