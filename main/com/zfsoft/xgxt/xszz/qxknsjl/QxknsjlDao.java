/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-20 ����06:25:44 
 */  
package com.zfsoft.xgxt.xszz.qxknsjl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-4-21 ����08:35:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class QxknsjlDao extends SuperDAOImplExtend<QxknsjlForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QxknsjlForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(QxknsjlForm t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());		
		StringBuffer sql = new StringBuffer("");				
		sql.append(" select rownum r,a.* from ( select t1.jgguid,t1.czr czrdm,(select xm from yhb t4 where t1.czr=t4.yhm) czrxm, ");
		sql.append(" t1.czsj,t1.qxyy,t2.xh,t2.xn,t2.xq,t2.sqsj,t2.rddc,(select dcmc from xg_xszz_new_kndcdmb t4 where t2.rddc=t4.dcdm) dcmc, ");
		sql.append(" t2.sqly,t2.sjly,t2.lylcywid,t2.sfqxrd,t3.xm,t3.xb,t3.nj,t3.xydm,  "); 		
		sql.append(" t3.xymc,t3.zydm,t3.zymc,t3.bjdm,t3.bjmc,t3.sjhm from xg_xszz_new_knsqxjl "); 
		sql.append(" t1 left join xg_xszz_new_knsjgb  t2 on t1.jgguid = t2.guid left join view_xsbfxx t3 on t2.xh = t3.xh ) "); 
		sql.append(" a where  1=1  "); 	 	
		sql.append(searchTjByUser);		
		sql.append(searchTj);		
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	/**
	 * 
	 * @����:TODO(��õ���ѧ���϶���Ϣ)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-26 ����05:37:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneKnsjgList(String  guid) {
		StringBuilder sql = new StringBuilder();			
		sql.append(" select t1.sqly,t1.sqsj,t1.xn,t2.dcmc from xg_xszz_new_knsjgb t1 ");
		sql.append(" left join xg_xszz_new_kndcdmb t2 on t1.rddc = t2.dcdm where guid=? ");
		return dao.getMapNotOut(sql.toString(),new String[]{guid});
	}   
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-27 ����08:25:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgguid
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneKnsqxjlList(String  jgguid) {
		StringBuilder sql = new StringBuilder(" select czr,(select xm from yhb t4 where t1.czr=t4.yhm)czrxm, czsj,qxyy from xg_xszz_new_knsqxjl t1 where jgguid=? ");			
		return dao.getMapNotOut(sql.toString(),new String[]{jgguid});
	} 
	
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("xg_xszz_new_knsqxjl");
		super.setKey("guid");
	}


}
