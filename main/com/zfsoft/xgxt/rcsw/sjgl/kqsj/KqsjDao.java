/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-20 ����03:32:38 
 */  
package com.zfsoft.xgxt.rcsw.sjgl.kqsj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.sjgl.twsj.TwsjForm;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-1-20 ����03:32:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqsjDao extends SuperDAOImpl<KqsjForm>{

	public List<HashMap<String, String>> getPageList(KqsjForm t)
			throws Exception {
		return null;
	}

	
	//�߼���ѯ
	public List<HashMap<String, String>> getPageList(KqsjForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select t1.*,t2.xydm,t2.xymc,t2.xm,t2.xb,t2.sfzh,t2.nj,t2.zymc,t2.zydm,t2.bjmc,t2.bjdm,t2.zzmmmc,t2.mzmc ");
		sql.append(" from xg_rcswx_sjgl_kqsj t1");
		sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh ");
		sql.append(" ) a where 1 = 1 ");	
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	
	@Override
	protected void setTableInfo() {
		this.setKey("id");
		this.setTableName("xg_rcswx_sjgl_kqsj");
		this.setClass(KqsjForm.class);
		
	}
	
	/** 
	 * @����:���ӱ�����֤
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-20 ����03:31:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String checkExistForSave(KqsjForm form) {
		String sql = "select count(1) num from xg_rcswx_sjgl_kqsj t where t.xh = ? and t.xn = ?";
		return dao.getOneRs(sql, new String[]{form.getXh(),form.getXn()}, "num");
	}
	
	/** 
	 * @����:�޸ı�����֤
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-20 ����03:31:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String checkExistForUpdate(KqsjForm form) {
		String sql = "select count(1) num from xg_rcswx_sjgl_kqsj t where t.xh = ? and t.xn = ? and t.id <> ?";
		return dao.getOneRs(sql, new String[]{form.getXh(),form.getXn(),form.getId()}, "num");
	}
	
	
}
