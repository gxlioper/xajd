/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-07-31 ����02:33:17 
 */
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.stlbgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-07-31 ����02:33:17
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class StlbglDao extends SuperDAOImpl<StlbglForm> {

	@Override
	public List<HashMap<String, String>> getPageList(StlbglForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_stgl_stlb a where 1=1");
		if (!StringUtil.isNull(model.getStlbmc())) {
			params.add(model.getStlbmc());
			sql.append(" and a.stlbmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}

	/**
	 * ��ȡ��������б�
	 */
	@Override
	public List<HashMap<String, String>> getPageList(StlbglForm model, User user) throws Exception {
		return null;
	}

	/**
	 * @throws Exception
	 * 
	 * @����:�����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-07-31 ����03:52:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return boolean ��������
	 * @throws
	 */
	public boolean addStlb(StlbglForm model) throws Exception {
		boolean flag = false;
		String sql;
		sql = "select count(1) num from xg_stgl_stlb where  stlbdm=?  or  stlbmc = ?";
		String num = dao.getOneRs(sql, new String[] { model.getStlbdm(),model.getStlbmc() }, "num");
		if ("0".equals(num)) {
			flag=runInsert(model);
		} else {
			throw new SystemException(MessageKey.STGL_JCSZ_STLB_REPEAT);
		}

		return flag;

	}
	
	/**
	 *��ȡ�������
	 */
	public String getStlbmc(String stlbdm) throws Exception {
		String sql = "select stlbmc from xg_stgl_stlb where stlbdm =?";
		return dao.getOneRs(sql, new String[]{stlbdm}, "stlbmc");

	}
	
	public boolean isExistsXmData(String lbdm) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) num from(select stlbdm from xg_stgl_xmlb where stlbdm=? ");
		sql.append(" union all select stlbdm from  xg_stgl_jtsq where stlbdm=?");
		sql.append(" union all select stlbdm from  xg_stgl_jtjg where stlbdm=?)");
		return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{lbdm,lbdm,lbdm}, "num"))>0;
		
	}
	/**
	 *��ȡ��������б�
	 */
	public List<HashMap<String, String>> getStlbList(){
		String sql = "select * from xg_stgl_stlb order by stlbdm asc";
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_stgl_stlb");
		super.setKey("stlbdm");
	}

}
