/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-9 ����04:38:11 
 */  
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.rylbgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-9-9 ����04:38:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RylbglDao extends SuperDAOImpl<RylbglForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RylbglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		List<String> params = new ArrayList<String>();;
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_STGL_RYLB a where 1=1");
		if (!StringUtil.isNull(t.getRylbmc())) {
			params.add(t.getRylbmc());
			sql.append(" and a.rylbmc like '%'||?||'%'");
		}
		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RylbglForm t, User user)
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
		super.setTableName("XG_STGL_RYLB");
		super.setKey("rylbdm");
	}
	
	//��֤���ݿ����Ƿ���ͬ������Ա�������
	public boolean isExistsSameRylbmc(String rylbmc,String rylbdm){
		StringBuilder sql = new StringBuilder();
		String[] inputvalue = new String[]{rylbmc};
		sql.append(" select count(1) flag from  xg_stgl_rylb  where rylbmc = ?");
		//�޸�ʱ����rylbdm�����жϣ�����ʱ����null�ж�
		if(rylbdm != null && !rylbdm.equals("")){
			sql.append(" and rylbdm != ?");
			inputvalue = new String[]{rylbmc,rylbdm};
		}
		String flag = dao.getOneRs(sql.toString(), inputvalue, "flag");
		return (Integer.parseInt(flag) > 0) ? true:false;
	}
	
	//ɾ����Ա���ʱ�ж������ų�Ա���������ų�Ա��������Ƿ����õ�����������
	public boolean isExistsRylbmc_user(String rylbdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select max(flag) flag");
		sql.append(" from (select count(1) flag");
		sql.append(" from xg_rtgl_rtjg");
		sql.append(" where rylbdm = ?");
		sql.append(" union");
		sql.append(" select count(1) flag");
		sql.append(" from xg_rtgl_rtsq");
		sql.append(" where rylbdm = ?)");
		String flag = dao.getOneRs(sql.toString(), new String[]{rylbdm,rylbdm}, "flag");
		return (Integer.parseInt(flag) > 0) ? true:false;
	}
	
	//����������Ա���
	public boolean save(String rylbmc) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_stgl_rylb (rylbdm,rylbmc) values(SEQ_RYLB.Nextval,?)");
		return dao.runUpdate(sql.toString(), new String[]{rylbmc});
	}
	
	//��ȡ��Ա�������
	public String getRylbmc(String rylbdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select rylbmc from xg_stgl_rylb where rylbdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{rylbdm}, "rylbmc");
	}
}
