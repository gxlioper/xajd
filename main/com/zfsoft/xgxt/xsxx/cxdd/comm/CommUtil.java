/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-6 ����11:23:36 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zxdk.xnwxdk.util.UtilForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-4-6 ����11:23:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CommUtil extends SuperDAOImpl<CommForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CommForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CommForm t, User user)
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
	
	public  boolean isNotExists(CommForm utilform){
		StringBuilder sql = new StringBuilder();
		String type = utilform.getType();
		ArrayList<String> parameter = new ArrayList<String>();
		boolean flag = true;
		//���������ͽ������ظ���֤,qb(ȫ��)
		if(type.equalsIgnoreCase("qb")){
			sql.append(" select sum(flag) flag from");
			sql.append(" (select count(1) flag from ");
			sql.append(" xg_xsxx_cxpy_pysb_xs");
			sql.append(" where xn = ?");
			sql.append(" and xq = ?");
			sql.append(" and xh = ?");
			sql.append(" union all");
			sql.append(" select count(1) flag from ");
			sql.append(" xg_xsxx_cxpy_pysb_jg");
			sql.append(" where xn = ?");
			sql.append(" and xq = ?");
			sql.append(" and xh = ?)");
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXq());
			parameter.add(utilform.getXh());
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXq());
			parameter.add(utilform.getXh());
		}else if(type.equalsIgnoreCase("sq")){
			sql.append(" select count(1) flag from ");
			sql.append(" xg_xsxx_cxpy_pysb_xs");
			sql.append(" where xn = ?");
			sql.append(" and xq = ?");
			sql.append(" and xh = ?");
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXq());
			parameter.add(utilform.getXh());
		}else if(type.equalsIgnoreCase("jg")){
			sql.append(" select count(1) flag from ");
			sql.append(" xg_xsxx_cxpy_pysb_jg");
			sql.append(" where xn = ?");
			sql.append(" and xq = ?");
			sql.append(" and xh = ?");
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXq());
			parameter.add(utilform.getXh());
		}else if(type.equalsIgnoreCase("jgedit")){
			sql.append(" select count(1) flag from ");
			sql.append(" xg_xsxx_cxpy_pysb_jg");
			sql.append(" where xn = ?");
			sql.append(" and xq = ?");
			sql.append(" and xh = ?");
			sql.append(" and xsid != ?");
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXq());
			parameter.add(utilform.getXh());
			parameter.add(utilform.getId());
		}
		String num = dao.getOneRs(sql.toString(), parameter.toArray(new String[]{}),"flag");
		if (!num.equals("0")){
			flag = false;
		}
		return flag;
	}
	
    public boolean isNotExistBj(CommForm utilform){
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> parameter = new ArrayList<String>();
    	boolean flag = true;
    	sql.append(" select count(1) flag from xg_xsxx_cxpy_pysb_bj where xn = ? and xq = ? and bjdm = ?");
    	parameter.add(utilform.getXn());
    	parameter.add(utilform.getXq());
    	parameter.add(utilform.getBjdm());
    	String num = dao.getOneRs(sql.toString(), parameter.toArray(new String[]{}),"flag");
		if (!num.equals("0")){
			flag = false;
		}
		return flag;
	}
//����ѧ����֤������Ϣ���и�ѧ�������Ƿ����
	public boolean isNotExistsDa(CommForm utilform) {
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> parameter = new ArrayList<String>();
    	boolean flag = true;
    	sql.append(" select count(1) flag from xg_xsxx_cqsxdaxxb where xh = ? ");
    	parameter.add(utilform.getXh());
    	String num = dao.getOneRs(sql.toString(), parameter.toArray(new String[]{}),"flag");
		if (!num.equals("0")){
			flag = false;
		}
		return flag;
	}
	//����ѧ����֤������Ϣ���и�ѧ�������Ƿ���ڣ�������Ϣ��
	public boolean isNotExistsCqxx(CommForm utilform) {
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> parameter = new ArrayList<String>();
    	boolean flag = true;
    	sql.append(" select count(1) flag from xg_xsxx_cqxxdaxxb where xh = ? ");
    	parameter.add(utilform.getXh());
    	String num = dao.getOneRs(sql.toString(), parameter.toArray(new String[]{}),"flag");
		if (!num.equals("0")){
			flag = false;
		}
		return flag;
	}
}
