package com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhklx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/**
 * ���Żݿ�����
 */
public class HcyhklxDao extends SuperDAOImpl<HcyhklxForm> {

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_hcyhk_hcyhklx");
		super.setKey("lxdm");
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(HcyhklxForm t, User user)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.* from xg_rcsw_hcyhk_hcyhklx a ");
		sql.append(" where 1 = 1");
		
		if (!StringUtil.isNull(t.getLxmc())) {
			params.add(t.getLxmc());
			sql.append(" and a.lxmc like '%'||?||'%' ");
		}
		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HcyhklxForm t)
			throws Exception {
		return null;
	}
	
	/**
	 * ���Żݿ������Ƿ����
	 */
	public boolean isExist(HcyhklxForm model, String type)
			throws Exception {
		String[] inputs = new String[] {model.getLxmc()};
		String sql = "select count(1) num from xg_rcsw_hcyhk_hcyhklx where lxmc=?  ";
		if("update".equals(type)){
			sql += " and lxdm <> ? ";
			inputs = new String[] {model.getLxmc(), model.getLxdm()};
		}
		String num = dao.getOneRs(sql, inputs, "num");
		return Integer.valueOf(num) > 0;
	}
	
	/**
	 * ���ӻ��Żݿ�����
	 */
	public boolean addInfo(HcyhklxForm model)
		throws Exception {
		String sql = "insert into xg_rcsw_hcyhk_hcyhklx(lxdm,lxmc) values(?,?)";
		return dao.runUpdate(sql, new String[] { model.getLxdm(), model.getLxmc() });
	}
	
	/**
	 * �޸Ļ��Żݿ�����
	 */
	public boolean updateInfo(HcyhklxForm model)
		throws Exception {
		String sql = "update xg_rcsw_hcyhk_hcyhklx set lxmc=? where lxdm=?";
		return dao.runUpdate(sql, new String[] { model.getLxmc(),model.getLxdm() });
	}
	
	/**
	 * ��ȡ���Żݿ����ʹ���������ֵ
	 */
	public String getMaxHcyhkgllxdm() {
		String sql = "select max(to_number(lxdm)) max from xg_rcsw_hcyhk_hcyhklx ";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	
	/**
	 * ���Żݿ����� ������ѯ
	 */
	public HcyhklxForm getHcyhklxForm(HcyhklxForm t)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from xg_rcsw_hcyhk_hcyhklx a  ");
		sql.append(" where lxdm = ? ");

		return this.getModel(t, sql.toString(), new String[] { t.getLxdm() });
	}

	/**
	 * �Ƿ�ʹ��
	 */
	public boolean isCanDel(String lxdm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(a.lxmc) num from xg_rcsw_hcyhk_hcyhklx a left join ");
		sql.append(" xg_rcsw_hcyhk_hcccqjtxb b on a.lxdm = b.hcyhklx ");
		sql.append(" left join xg_rcsw_hcyhk_hcccqjjgb c on a.lxdm = c.hcyhklx ");
		sql.append(" where a.lxdm  = ? and ( b.hcyhklx is not null or  c.hcyhklx is not null ) " );
		String num = dao.getOneRs(sql.toString(),new String[]{lxdm},"num");
		return Integer.parseInt(num) == 0;
	}
	/**
	 * ��ȡ���л��Żݿ�����
	 */
	public List<HashMap<String, String>> getHcyhklxList()
		throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from xg_rcsw_hcyhk_hcyhklx a order by a.lxdm ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
}
