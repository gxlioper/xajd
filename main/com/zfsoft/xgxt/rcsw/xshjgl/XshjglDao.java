/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-31 ����04:49:53 
 */  
package com.zfsoft.xgxt.rcsw.xshjgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.fyff.ffjg.FyffjgForm;
import com.zfsoft.xgxt.rcsw.xshjgl.XshjglForm;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshForm;


/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1186]
 * @ʱ�䣺 2015-9-14 ����09:34:02 
 * @�汾�� V5.17
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XshjglDao extends SuperDAOImpl<XshjglForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XshjglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	/**
	 * ʹ�ø߼���ѯ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XshjglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from (select t.*, ");
		sql.append(" t1.XB, ");
		sql.append(" t1.BJDM, ");
		sql.append(" t1.BJMC, ");
		sql.append(" t1.NJ, ");
		sql.append(" t1.XYDM, ");
		sql.append(" t1.XYMC, ");
		sql.append(" t1.XM, ");
		sql.append(" decode(t.qyzt, '0', 'Ǩ��', '1', 'Ǩ��') qyztmc ");
		sql.append(" from xg_rcsw_hjgl t ");
		sql.append(" left join view_xsbfxx t1 ");
		sql.append(" on t.xh = t1.XH) a ");
		sql.append(" where 1 = 1 ");
	    sql.append(searchTjByUser);
	    sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_hjgl");
		super.setKey("hjglid");
		super.setClass(XshjglForm.class);
	}
	
	//������ѯ
	public HashMap<String, String> getOneInfo(XshjglForm t) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(" from (select t.*,t1.XM,t1.XB,t1.LXDH,t1.NJ,t1.XYMC,t1.ZYMC,t1.BJMC,t1.SFZH,t1.JG, ");
		sql.append(" decode(t.qyzt, '0', 'Ǩ��', '1', 'Ǩ��') qyztmc ");
		sql.append(" from xg_rcsw_hjgl t ");
		sql.append(" left join view_xsbfxx t1 ");
		sql.append(" on t.xh = t1.XH) a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
public String checkExistForSave(XshjglForm form){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		String hjglid =  form.getHjglid();
		sql.append(" select count(1) num from xg_rcsw_hjgl ");
		sql.append(" where xh = ? and qyzt = ?  and qysj = ? ");
		params.add(form.getXh());
		params.add(form.getQyzt());
		params.add(form.getQysj());
		if(!StringUtils.isEmpty(hjglid)){
			sql.append(" and hjglid <> ? ");
			params.add(hjglid);
		}
		
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
		return num;
	}


/**
 * @����: ��������
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2015-10-14 ����08:30:10
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param t
 * @param user
 * @return
 * @throws Exception
 * List<HashMap<String,String>> �������� 
 * @throws
 */
public List<HashMap<String, String>> getXshjgldcList(XshjglForm t, User user)
throws Exception {
String searchTj = SearchService.getSearchTj(t.getSearchModel());
String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
String[] inputV = SearchService.getTjInput(t.getSearchModel());

StringBuilder sql = new StringBuilder();
sql.append(" select * ");
sql.append(" from (select t.*, ");
sql.append(" t1.XB, ");
sql.append(" t1.BJDM, ");
sql.append(" t1.BJMC, ");
sql.append(" t1.NJ, ");
sql.append(" t1.XYDM, ");
sql.append(" t1.XYMC, ");
sql.append(" t1.XM, ");
sql.append(" t1.zymc, ");
sql.append(" (select t2.qxmc from dmk_qx t2 where t1.jg=t2.qxdm) jgmc, ");
sql.append(" t1.lxdh, ");
sql.append(" decode(t.qyzt, '0', 'Ǩ��', '1', 'Ǩ��') qyztmc ");
sql.append(" from xg_rcsw_hjgl t ");
sql.append(" left join view_xsbfxx t1 ");
sql.append(" on t.xh = t1.XH) a ");
sql.append(" where 1 = 1 ");
sql.append(searchTjByUser);
sql.append(searchTj);

return dao.getListNotOut(sql.toString(), inputV);
}

}
