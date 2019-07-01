/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-25 ����02:11:01 
 */  
package com.zfsoft.xgxt.dtjs.dtxxgl.zjsysxhb;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ChenQ[����:856]
 * @ʱ�䣺 2015-6-25 ����02:11:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZjsySxhbDAO extends SuperDAOImpl<ZjsySxhbForm> {

	@Override
	public List<HashMap<String, String>> getPageList(ZjsySxhbForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(ZjsySxhbForm t, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "m",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( select a.*,b.jdmc,c.xydm,c.zydm,c.nj,c.bjdm, ");
		sql.append("c.bjmc,c.xymc,c.xm,(select sum(sjfs)jdzs from xg_dtjs_zjsy_sxhbjg where  ");
		sql.append(" a.jddm=jddm and a.xh=xh group by xh,jddm ) jdzs from ");
		sql.append(" xg_dtjs_zjsy_sxhbjg a left join xg_dtjs_jbszb  b on a.jddm=b.jddm  ");
		sql.append("left join view_xsbfxx c on a.xh=c.xh order by c.nj,c.xydm,c.bjdm ) m where 1=1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);	
	}

	
	@Override
	protected void setTableInfo() {
		super.setKey("sxhbid");
		super.setTableName("XG_DTJS_ZJSY_SXHBJG");
		super.setClass(ZjsySxhbForm.class);
		
	}
   
	public HashMap<String,String> getXsdtxxMore(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*,b.jdmc,b.jddm,b.kssj from view_xsbfxx a ");
		sql.append("left join view_new_dc_dtxx_jg b on a.xh=b.xh) where xh=?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	
	public boolean isHasExists(ZjsySxhbForm model){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) zs from xg_dtjs_zjsy_sxhbjg b where xh=? and jddm=? and sjsj=?");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getXh(),
			model.getJddm(),model.getSjsj()}, "zs");
		return Integer.parseInt(num)>0?true:false;
	}
	
}
