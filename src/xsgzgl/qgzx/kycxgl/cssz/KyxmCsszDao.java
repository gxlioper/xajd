/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����04:28:56 
 */  
package  xsgzgl.qgzx.kycxgl.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-11-30 ����04:28:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KyxmCsszDao extends SuperDAOImpl<KyxmCsszForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KyxmCsszForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KyxmCsszForm t, User user) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}


	
	public boolean deleteJcsz(KyxmCsszForm model) throws Exception{
		String sql = "delete from XG_QGZX_QZXM_CSSZ where xmlb=?";
		int num = dao.runDelete(sql, new String[]{model.getXmlb()});
		return num>0;
	}
	public HashMap<String,String> getCssz(String xmlb) throws Exception{
		String sql = "select *  from XG_QGZX_QZXM_CSSZ where xmlb=?";
		return dao.getMapNotOut(sql, new String[]{xmlb});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(KyxmCsszForm.class);
		super.setKey("xmlb");
		super.setTableName("XG_QGZX_QZXM_CSSZ");
	}
}
