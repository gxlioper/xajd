/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-17 ����11:02:02 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.sq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-17 ����11:02:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsqktbDao extends SuperDAOImpl<TsqktbForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TsqktbForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TsqktbForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.*,t2.xqmc,t3.xydm,t3.bjdm,t3.xb,t3.nj,t3.bjmc,t3.xymc,t3.xm,t3.mzmc,t3.zzmmmc,t3.jtdzxx,t3.lxdh,t3.sfzh,t3.zymc,(case when t5.xqmc is not null  then t4.xqmc || '��' || t5.xqmc else t4.xqmc end) xqxx,t4.xqmc xqmc1,t5.xqmc xqmc2, ");
		sql.append(" decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc");
		sql.append(" from xg_bjzyy_tsqktb_sq t1 ");
		sql.append(" left join xqdzb t2 on t1.xq = t2.xqdm ");
		sql.append(" left join view_xsbfxx t3 on t1.xh = t3.xh ");
		sql.append(" left join xg_bjzyy_xqyb_xqfl t4 on t1.xqdm1 = t4.xqdm ");
		sql.append(" left join xg_bjzyy_xqyb_xqfl t5 on t1.xqdm2 = t5.xqdm ");
		sql.append(" ) a where 1=1 ");		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(TsqktbForm.class);
		super.setKey("sqid");
		super.setTableName("xg_bjzyy_tsqktb_sq");		
	}
	
	public String getXqmc(String xqdm){
		String sql = "select xqmc from xqdzb where xqdm = ?";
		return dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	
	public List<HashMap<String,String>> getXqflList(){
		String sql = "select * from xg_bjzyy_xqyb_xqfl";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	public boolean isHaveRecord(TsqktbForm form){
		StringBuilder sql = new StringBuilder("select count(1) num from xg_bjzyy_tsqktb_sq where xh = ? and tbsj = ? ");
		StringBuilder sql1 = new StringBuilder("select count(1)numm from xg_bjzyy_tsqktb_jg where xh = ? and tbsj = ? ");
		if(form.getSqid()!=null){
			sql.append(" and sqid <> '"+ form.getSqid() +"'");
		}
		String num = dao.getOneRs(sql.toString(), new String[]{form.getXh(),form.getTbsj()}, "num");
		String numm = dao.getOneRs(sql1.toString(), new String[]{form.getXh(),form.getTbsj()}, "numm");
		if(Integer.valueOf(num)>0 || Integer.valueOf(numm)>0){
			return true;
		}else{
			return false;
		}
	}
	
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_bjzyy_xqyb_xqfl_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	public Map<String, String> getTbxx(TsqktbForm form){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t4.xqmc xqmc1,t5.xqmc xqmc2,t2.xqmc from xg_bjzyy_tsqktb_sq t1 ");
		sql.append(" left join xqdzb t2 on t1.xq = t2.xqdm ");
		sql.append(" left join xg_bjzyy_xqyb_xqfl t4 on t1.xqdm1 = t4.xqdm ");
		sql.append(" left join xg_bjzyy_xqyb_xqfl t5 on t1.xqdm2 = t5.xqdm ");
		sql.append(" where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{form.getSqid()});
	}
	
}
