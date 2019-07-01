/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-22 ����10:29:42 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-2-22 ����10:29:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YpzlSqDao extends SuperDAOImpl<YpzlSqForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YpzlSqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YpzlSqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.xm,t2.xb,t2.yhkh,t2.zzmmmc,t2.mzmc,t4.yhmc,t2.sfzh,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,nvl(t3.xm,t6.xm) sqrmc,t8.xqmc,t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx,");
		if("10511".equals(Base.xxdm)){
			sql.append("t5.lbmc ytmc,");
		}		
		sql.append(" decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc");
		sql.append(" from xg_zdgxh_ypzl_sqb t1 left join view_xsbfxx t2 on t1.xh = t2.xh");
		sql.append(" left join yhb t6 on t1.sqr=t6.yhm left join view_xsbfxx t3 on t1.sqr=t3.xh");
		sql.append(" left join xqdzb t8 on t1.xq = t8.xqdm");
		sql.append(" left join dmk_yh t4 on t2.yhdm = t4.yhdm");
		if("10511".equals(Base.xxdm)){
			sql.append(" left join xg_zxdk_ytzjlbdmb t5 on t1.ytlb = t5.lbdm");
		}		
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(YpzlSqForm.class);
		super.setKey("sqid");
		super.setTableName("xg_zdgxh_ypzl_sqb");
		
	}
	
	public String getXqmc(String xqdm){
		String sql = "select xqmc from xqdzb where xqdm = ?";
		return dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_zdgxh_ypzl_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	public boolean isHaveRecord(YpzlSqForm form){
		String sql = "select count(1) num from xg_zdgxh_ypzl_sqb where xh = ? and xn = ?";
		String sql1 = "select count(1)numm from xg_zdgxh_ypzl_jgb where xh = ? and xn = ?";
		String num = dao.getOneRs(sql, new String[]{form.getXh(),form.getXn()}, "num");
		String numm = dao.getOneRs(sql1, new String[]{form.getXh(),form.getXn()}, "numm");
		if(Integer.valueOf(num)>0 || Integer.valueOf(numm)>0){
			return true;
		}else{
			return false;
		}
	}
	
	public List<HashMap<String, String>> getYtlbList(){
		String sql = "select * from xg_zxdk_ytzjlbdmb";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	public String getYtmc(String ytdm){
		String sql = "select lbmc from xg_zxdk_ytzjlbdmb where lbdm = ?";
		return dao.getOneRs(sql, new String[]{ytdm}, "lbmc");
	}
	

	
}
