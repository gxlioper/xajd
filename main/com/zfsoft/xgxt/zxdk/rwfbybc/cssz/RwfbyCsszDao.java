/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-7 ����04:04:59 
 */  
package com.zfsoft.xgxt.zxdk.rwfbybc.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ChenQ[����:856]
 * @ʱ�䣺 2015-9-7 ����04:04:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RwfbyCsszDao extends SuperDAOImpl<RwfbyCssz> {

	@Override
	public List<HashMap<String, String>> getPageList(RwfbyCssz t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(RwfbyCssz t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("XG_ZXDK_RWFBYCSSZB");
		super.setKey("id");
		super.setClass(RwfbyCssz.class);
	}

	
	public RwfbyCssz getModel() throws Exception {
		String sql = "select * from XG_ZXDK_RWFBYCSSZB where rownum=1";
		return super.getModel(sql, new String[]{});
	}
    
	
}
