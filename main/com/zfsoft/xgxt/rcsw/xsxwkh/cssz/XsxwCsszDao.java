/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����04:28:56 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ����Ϊ���˲�������
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-8-2 ����02:36:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XsxwCsszDao extends SuperDAOImpl<XsxwCsszForm> {


	@Override
	public List<HashMap<String, String>> getPageList(XsxwCsszForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XsxwCsszForm t, User user) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	public XsxwCsszForm getModel() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,case when sysdate between to_date(nvl(a.kssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(jssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then 'true' else 'false' end kgzt from xg_xsxwkh_csszb a");
		return super.getModel(sql.toString(), new String[]{});
	}

	
	public boolean deleteJcsz() throws Exception{
		String sql = "delete from xg_xsxwkh_csszb";
		return dao.runUpdate(sql, new String[]{});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(XsxwCsszForm.class);
		super.setKey("id");
		super.setTableName("xg_xsxwkh_csszb");
	}
}
