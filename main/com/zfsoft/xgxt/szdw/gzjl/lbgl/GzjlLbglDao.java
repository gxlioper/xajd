/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-25 ����03:52:37 
 */  
package com.zfsoft.xgxt.szdw.gzjl.lbgl;

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
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-6-25 ����03:52:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzjlLbglDao extends SuperDAOImpl<GzjlLbglForm> {

	@Override
	public List<HashMap<String, String>> getPageList(GzjlLbglForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_gzjlgl_gzlbb a where 1=1");
		if (!StringUtil.isNull(model.getLbmc())) {
			params.add(model.getLbmc());
			sql.append(" and a.lbmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}

	/**
	 * ��ȡ������¼����б�
	 */
	@Override
	public List<HashMap<String, String>> getPageList(GzjlLbglForm model, User user) throws Exception {
		return null;
	}

	/**
	 * @throws Exception
	 * 
	 * @����:���ӹ�����¼���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-2 ����03:52:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return boolean ��������
	 * @throws
	 */
	public boolean addGzjllb(GzjlLbglForm model) throws Exception {
		boolean flag = false;
		String sql;
		sql = "select count(1) num from xg_gzjlgl_gzlbb where lbdm=? ";
		String num = dao.getOneRs(sql, new String[] { model.getLbdm() }, "num");
		if ("0".equals(num)) {
			sql = "insert into xg_gzjlgl_gzlbb(LBDM,LBMC,LBSM,XSSX) values(?,?,?,?)";
			flag = dao.runUpdate(sql, new String[] { model.getLbdm(), model.getLbmc(), model.getLbsm(),model.getXssx() });
		} else {
			throw new SystemException(MessageKey.GZJL_LBGL_LBYCZ);
		}

		return flag;

	}

	/**
	 *ɾ��
	 */
	public int deleteGzjllb(String values) throws Exception {
		String sql = "delete from xg_gzjlgl_gzlbb where lbdm =?";
		return dao.runDelete(sql, new String[] {values});

	}
	

	/**
	 *��ȡ������¼���
	 */
	public String getGzjllbmc(String lbdm) throws Exception {
		String sql = "select lbmc from xg_gzjlgl_gzlbb where lbdm =?";
		return dao.getOneRs(sql, new String[]{lbdm}, "lbmc");

	}
	
	public boolean isExistsXmData(String lbdm) throws Exception{
		String sql ="select count(*) num from (select lbdm from xg_gzjlgl_gzjlsqb where lbdm=? union all";
		sql+=" select lbdm from xg_gzjlgl_gzjljgb where lbdm=?)";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{lbdm,lbdm}, "num"))>0;
		
	}
	
	public List<HashMap<String,String>> getGzjllbList() throws Exception {
		String sql ="select lbdm gzlbdm,lbmc gzlbmc from xg_gzjlgl_gzlbb order by xssx ";
		return dao.getListNotOut(sql, new String[]{});

	}

	@Override
	protected void setTableInfo() {
		super.setClass(GzjlLbglForm.class);
		super.setTableName("xg_gzjlgl_gzlbb");
		super.setKey("lbdm");
	}
}
