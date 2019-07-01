package com.zfsoft.xgxt.jskp.dmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class DmwhDao extends SuperDAOImpl<DmwhForm> {

	@Override
	public List<HashMap<String, String>> getPageList(DmwhForm dmwh)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select * from xg_jskp_xmlbb");
		sql.append(" where 1=1");
		if(StringUtils.isNotNull(dmwh.getXmlbmc())){
			sql.append(" and xmlbmc like ?");
			paraList.add("%"+dmwh.getXmlbmc()+"%");
		}
		return getPageList(dmwh, sql.toString(),paraList.toArray(new String[]{}));
	}

	@Override
	public List<HashMap<String, String>> getPageList(DmwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(DmwhForm.class);
		this.setKey("xmlbdm");
		this.setTableName("xg_jskp_xmlbb");
	}
	
	/**
	 * 
	 * @����: ��Ŀ���List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-6 ����03:46:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jskp_xmlbb where xmlbmc like '%��������%' order by xmlbmc asc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: ��֤�����Ƿ��ѱ�ʹ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-21 ����03:15:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsDmIsNotUserd(String[] xmlbdms){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from (");
		sql.append(" select xmlb from xg_jskp_xmsqb");
		sql.append(" union");
		sql.append(" select xmlb from xg_jskp_xmjgb)");
		sql.append(" where xmlb in(");
		for (int i = 0; i < xmlbdms.length; i++) {
			sql.append("?");
			if(i != xmlbdms.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		String rs = dao.getOneRs(sql.toString(), xmlbdms, "cnt");
		return "0".equals(rs)?true:false;
	}
	
	/**
	 * 
	 * @����: ��֤��Ŀ��������Ƿ�ʹ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-21 ����04:14:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmlbmc
	 * @param xmlbdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkMcIsNotUserd(String xmlbmc,String xmlbdm){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_jskp_xmlbb where xmlbmc = ? ");
		paraList.add(xmlbmc);
		if(StringUtils.isNotNull(xmlbdm)){
			sql.append(" and xmlbdm != ?");
			paraList.add(xmlbdm);
		}
		String rs = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}), "cnt");
		return "0".equals(rs) ? true :false;
	}
	
	/**
	 * @����: ��Ŀ�������-����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-29 ����04:00:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbListByAll(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jskp_xmlbb order by xmlbmc asc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: ��Ŀ�������-��������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-29 ����04:00:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbListByNlsy(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jskp_xmlbb where xmlbmc like '%��������%' order by xmlbmc asc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: ��Ŀ�������-˼����������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-5-29 ����04:00:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbListBySzsz(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jskp_xmlbb where xmlbmc not like '%��������%' order by xmlbmc asc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
}
