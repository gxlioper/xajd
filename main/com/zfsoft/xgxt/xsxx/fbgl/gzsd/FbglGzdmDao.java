/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����10:24:45 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-27 ����10:24:45
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglGzdmDao extends SuperDAOImpl<FbglGzdmForm> {

	@Override
	public List<HashMap<String, String>> getPageList(FbglGzdmForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(FbglGzdmForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	protected void setTableInfo() {
		this.setKey("gzdm");
		this.setTableName("xg_xsxx_fbbxhgl_gzdmb");
		this.setClass(FbglGzdmForm.class);
	}

	public List<HashMap<String, String>> getGzList() {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from xg_xsxx_fbbxhgl_gzdmb");
		return dao.getList(sb.toString(), new String[] {}, new String[] {
				"gzdm", "gzmc" });
	}
	/**
	 * 
	 * @����: ��ȡ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-18 ����01:58:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gzdm
	 * @return
	 * String �������� 
	 */
	public String getGzmc(String gzdm){
		StringBuffer sb = new StringBuffer();
		sb.append("select gzmc from xg_xsxx_fbbxhgl_gzdmb where gzdm=?");
		return dao.getOneRs(sb.toString(), new String[]{gzdm}, "gzmc");
	}
}
