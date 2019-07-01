/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����04:14:33 
 */
package com.zfsoft.xgxt.rcsw.xsxwkh.jcxmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ������Ŀά��
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-8-2 ����05:02:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XsxwJcxmwhDao extends SuperDAOImpl<XsxwJcxmwhForm> {

	/**
	 * 
	 * @����:������Ŀ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-8-2 ����05:02:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCfxmPageList(XsxwJcxmwhForm model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_XSXWKH_JCFDMB where lx='0'");
		if (!StringUtil.isNull(model.getMc())) {
			params.add(model.getMc());
			sql.append(" and mc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(),params.toArray(new String[] {}));

	}

	@Override
	public List<HashMap<String, String>> getPageList(XsxwJcxmwhForm model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_XSXWKH_JCFDMB where lx='1'");
		if (!StringUtil.isNull(model.getMc())) {
			params.add(model.getMc());
			sql.append(" and mc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(),params.toArray(new String[] {}));
	}


	@Override
	public List<HashMap<String, String>> getPageList(XsxwJcxmwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("XG_XSXWKH_JCFDMB");
		super.setKey("dm");
	}

}
