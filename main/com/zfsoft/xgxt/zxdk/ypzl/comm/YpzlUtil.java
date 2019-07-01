/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-16 ����09:17:12 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.comm;

import java.util.ArrayList;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-6-16 ����09:17:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YpzlUtil extends SuperDAOImpl {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List getPageList(Object t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List getPageList(Object t, User user) throws Exception {
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
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-16 ����09:18:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public  String getAutoGnmkmc(String path){
		StringBuilder sql = new StringBuilder();
		sql.append(" select gnmkmc from gnmkdmb where dyym = ? ");
		return dao.getOneRs(sql.toString(), new String[]{path}, "gnmkmc");
	}
	
	/**
	 * 
	 * @����:��ʦ����Ի��жϣ��Ƿ���ͬһ���ظ������¼���Ƿ���true,�񷵻�false
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-17 ����10:34:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsExistsSameDate(String sqrq,String xh,String type){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		//����ͽ��ȫ����֤
		if("qb".equals(type)){
			sql.append(" select sum(num) num from");
			sql.append(" (select count(1) num");
			sql.append(" from xg_zdgxh_ypzl_sqb");
			sql.append("  where xh = ?");
			sql.append("  and to_char(to_date(sqsj, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd') = ?");
			sql.append(" union all");
			sql.append(" select count(1) num");
			sql.append(" from xg_zdgxh_ypzl_jgb");
			sql.append(" where xh = ?");
			sql.append(" and to_char(to_date(sqsj, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd') = ?) t");
			paralist.add(xh);
			paralist.add(sqrq);
			paralist.add(xh);
			paralist.add(sqrq);
			
		}else{//������֤
			sql.append(" select count(1) num");
			sql.append(" from xg_zdgxh_ypzl_jgb");
			sql.append(" where xh = ?");
			sql.append(" and to_char(to_date(sqsj, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd') = ?");
			paralist.add(xh);
			paralist.add(sqrq);
		}
		return !("0").equals(dao.getOneRs(sql.toString(), paralist.toArray(new String[]{}), "num")) ? true :false;
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:��ʦ�����ͨ��������ǰɾ��������Ѵ��ڵ���������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-17 ����03:13:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delYpjgHsd(String sqrq,String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zdgxh_ypzl_jgb ");
		sql.append(" where xh = ?");
		sql.append(" and to_char(to_date(sqsj, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd')=?");
		sql.append(" ");
		return dao.runUpdate(sql.toString(), new String[]{xh,sqrq});
	}
	
}
