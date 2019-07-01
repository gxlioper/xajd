package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.jcsz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XlzxSbDao extends SuperDAOImpl<XlzxSbJcszForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XlzxSbJcszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XlzxSbJcszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_xlzxnew_csszb");
		return dao.getListNotOut(sql.toString(),new String[]{});
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setTableName("xg_xlzxnew_csszb");
		this.setKey("id");
		this.setClass(XlzxSbJcszForm.class);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �������ñ���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-28 ����05:25:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJcsz(List<String[]> paraList) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_xlzxnew_csszb(splc,lx)values(?,?)");
		return dao.runBatchBoolean(sql.toString(), paraList);
	}
	
	public boolean delJcsz() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xlzxnew_csszb");
		return dao.runUpdate(sql.toString(),new String[]{});
	}
	
	/**
	 * getModel��д
	 * @throws Exception 
	 */
	public XlzxSbJcszForm getModel(String lx) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_xlzxnew_csszb where lx = ?");
		return getModel(sql.toString(),new String[]{lx});
	}
}
