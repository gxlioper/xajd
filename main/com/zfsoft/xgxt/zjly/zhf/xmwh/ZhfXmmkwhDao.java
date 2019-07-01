/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-27 ����09:37:03 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ۺϷֹ���ģ��
 * @�๦������: ��Ŀģ��ά��(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-27 ����09:37:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfXmmkwhDao extends SuperDAOImpl<ZhfXmmkwhForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfXmmkwhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfXmmkwhForm t, User user)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_zjly_zhszf_mkxmb ");
		if(!StringUtil.isNull(t.getCxzd())) {
			sql.append(" where xmmkmc like '%'||?||'%' ");
			return getPageList(t, sql.toString(), new String[]{t.getCxzd()});
		}else{
			return getPageList(t, sql.toString(), new String[]{});
		}		
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setKey("xmmkdm");
		this.setTableName("xg_zjly_zhszf_mkxmb");
		this.setClass(ZhfXmmkwhForm.class);	
	}
	
	/** 
	 * @����:ͳ�������Ƿ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-27 ����09:54:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int count(ZhfXmmkwhForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_zjly_zhszf_mkxmb where xmmkmc = ? ");
		if(null != t.getXmmkmc() && !"".equals(t.getXmmkmc()) && "" != t.getXmmkmc()){
			sql.append(" and xmmkdm <> ?");
			return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{t.getXmmkmc(),t.getXmmkdm()}, "num"));
		}else{		
			return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{t.getXmmkmc()}, "num"));
		}
	}
	
	/** 
	 * @����:ͳ����Ŀģ���µļƷ���Ŀ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-27 ����03:27:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmmkdms
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int countJfxm(String[] xmmkdms) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_zjly_zhszf_jfxmb where xmmkdm in (");
		if(xmmkdms.length>1){
			for(int i = 0;i<xmmkdms.length;i++){
				if(i == xmmkdms.length-1){
					sql.append("'"+xmmkdms[i]+"'");
				}else{
					sql.append("'"+xmmkdms[i]+"',");
				}
				
			}
		}else{
			sql.append("'"+xmmkdms[0]+"'");
		}
		sql.append(")");
		return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{}, "num"));
	}
	
}
