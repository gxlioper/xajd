/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-18 ����02:33:45 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdk.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jcsz.XyzsJcszForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-18 ����02:33:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxdkCsszDao extends SuperDAOImpl<XnwxdkCsszModel> {

	public List<HashMap<String, String>> getPageList(XnwxdkCsszModel t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(XnwxdkCsszModel t, User user) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	public XnwxdkCsszModel getModel() throws Exception{
		String sql = "select * from xg_zdgxh_wxjk_cssz where rownum=1";
		return super.getModel(sql, new String[]{});
	}
	/**
	 * 
	 * @����:��ȡ���뿪��״̬
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����02:52:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String getSqKg() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select case when t.sqkg = 1 and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg ");
		sql.append(" from xg_zdgxh_wxjk_cssz t where 1=1");
		return dao.getOneRs(sql.toString(),new String[]{},"sqkg");
	}
	@Override
	protected void setTableInfo() {
		super.setClass(XnwxdkCsszModel.class);
		super.setKey("id");
		super.setTableName("xg_zdgxh_wxjk_cssz");
		
		
	}

}
