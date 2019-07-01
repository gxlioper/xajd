/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-26 ����06:05:47 
 */  
package com.zfsoft.xgxt.jjgl.xqsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.jjgl.jjxq.JjxqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-26 ����06:05:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XqshDao extends SuperDAOImpl<XqshForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XqshForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*, decode(t1.shzt,'0','δ���','1','ͨ��','2','��ͨ��') shztmc ,t2.xm znxm,t2.xb znxb,t2.nj znnj,");
		sql.append("t3.jjnjmc,t4.jjxkmc from XSGGFW_JJFW_JZJJXQSQB t1 ");
		sql.append("left join XSGGFW_JJFW_JZZNXXB t2 on t2.znid = t1.znid ");
		sql.append("left join XSGGFW_JJFW_JJNJDMB t3 on t1.jjnjdm = t3.jjnjdm ");
		sql.append("left join XSGGFW_JJFW_JJXKDMB   t4 on t1.jjxkdm = t4.jjxkdm ");
		
		if(StringUtils.equals("dsh", t.getType())){
			sql.append("where t1.shzt = '0' ");
		}else if(StringUtils.equals("ysh", t.getType())){
			sql.append("where t1.shzt <> '0' ");
		}
			
		if (!StringUtil.isNull(t.getSqr())){
			sql.append(" and t1.sqr like '%'||?||'%' ");
			params.add(t.getSqr());
		}
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XqshForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}

}
