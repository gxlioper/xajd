/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-30 ����01:35:52 
 */  
package xsgzgl.jxgl.general.jxxxwh.qxjxzg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.jxgl.general.jxqjgl.JxqjjgForm;
import xsgzgl.jxgl.general.jxxxwh.JxglJxxxwhForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѵ����
 * @�๦������: ȡ����ѵ�ʸ� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-8-30 ����01:35:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QxjxzgDao extends SuperDAOImpl<QxjxzgForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QxjxzgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QxjxzgForm t, User user)
			throws Exception {
		
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.* from (select a.*, b.jxmc, c.xm, c.xb, c.sfzh, c.nj, c.xydm, c.zydm, c.bjdm, c.xymc, c.zymc, c.bjmc, c.zzmmmc, c.sjhm, ");
        sql.append(" case when d.cwh is not null then d.ldmc||'��'||d.qsh||'��'||d.cwh||'�Ŵ�' else '' end zsqsmc,d.qsdh qslxdh from xg_jxgl_qxjxzgb a left join xg_jxgl_jxxxwhb b on a.jxid = b.jxid ");
        sql.append(" left join view_xsjbxx c on a.xh = c.xh left join view_xg_gygl_new_cwxx d on a.xh = d.xh) t where 1 = 1 ");
        sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setTableName("xg_jxgl_qxjxzgb");
		this.setClass(QxjxzgForm.class);
	}
	
	
	@Override
	public QxjxzgForm getModel(QxjxzgForm myForm) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_jxgl_qxjxzgb where jxid = ? and xh = ? ");
		return getModel(sql.toString(), new String[]{myForm.getJxid(),myForm.getXh()});

	}

}
