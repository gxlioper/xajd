/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-18 ����02:37:01 
 */  
package xsgzgl.gygl.zsxxgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ҳ�Զ��幫Ԣ��������
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-10-18 ����02:37:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZsxxglDaoForPage extends SuperDAOImpl<ZsxxglForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZsxxglForm t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZsxxglForm t, User user)
			throws Exception {
		return null;
	}
	
	public List<HashMap<String, String>> getPageListOld(ZsxxglForm zf,String sql,String[] param)
	throws Exception {
		return this.getPageList(zf, sql, param);
	}
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(ZsxxglForm.class);
	}

}
