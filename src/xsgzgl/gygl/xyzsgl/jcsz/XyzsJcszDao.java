/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-25 ����05:26:17 
 */  
package xsgzgl.gygl.xyzsgl.jcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xsgzgl.qgzx.mrgzkh.jcsz.GzkhJcszForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-5-25 ����05:26:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyzsJcszDao extends SuperDAOImpl<XyzsJcszForm> {
	public List<HashMap<String, String>> getPageList(XyzsJcszForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(XyzsJcszForm t, User user) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	public XyzsJcszForm getModel() throws Exception{
		String sql = "select * from XG_GYGL_XYZS_JCSZ where rownum=1";
		return super.getModel(sql, new String[]{});
	}
	/**
	 * 
	 * @����:��ȡ������˿���״̬
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����02:52:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] getSqShKg() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select case when t.sqkg = 1 and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg, ");
		sql.append("case when t.shkg = 1 and sysdate between to_date(nvl(t.shkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.shjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end shkg ");
		sql.append(" from XG_GYGL_XYZS_JCSZ t where 1=1");
		return dao.getOneRs(sql.toString(),new String[]{},new String[]{"sqkg","shkg"});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(XyzsJcszForm.class);
		super.setKey("id");
		super.setTableName("XG_GYGL_XYZS_JCSZ");
		
		
	}

}
