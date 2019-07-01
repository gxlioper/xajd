/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-11 ����01:54:19 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-9-11 ����01:54:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JJalDao extends SuperDAOImpl<JJalForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JJalForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		if(StringUtils.equals("1", t.getType())){
			sql.append("select t1.*,decode(t1.sffb , '1' , '�ѷ���' , '0' , 'δ����' , '') sffbmc from XSGGFW_JJFW_ANLI t1 where t1.sffb = '1' order by t1.jlrq desc ");
		}else if(StringUtils.equals("0", t.getType())){
			sql.append("select t1.*,decode(t1.sffb , '1' , '�ѷ���' , '0' , 'δ����' , '') sffbmc from XSGGFW_JJFW_ANLI t1 where t1.sffb = '0' order by t1.jlrq desc ");
		}
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JJalForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(JJalForm.class);
		super.setKey("sid");
		super.setTableName("XSGGFW_JJFW_ANLI");
	}

}
