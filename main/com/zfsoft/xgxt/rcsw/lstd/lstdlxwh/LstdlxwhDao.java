/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-25 ����09:36:03 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdlxwh;

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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ��ɫͨ������ά��
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-11-25 ����09:36:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LstdlxwhDao extends SuperDAOImpl<LstdlxwhForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LstdlxwhForm t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select lxdm,lxmc,decode(bs,'1','��','��')bs from xg_rcsw_lstd_lxwhb a where 1 = 1 ");
		if (!StringUtil.isNull(t.getLxmc())) {
			params.add(t.getLxmc());
			sql.append("  and lxmc like '%'||?||'%'");
		}
		return getPageList(t, sql.toString(),params.toArray(new String[] {}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LstdlxwhForm t, User user)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		
		super.setTableName("xg_rcsw_lstd_lxwhb");
		super.setKey("lxdm");

	}
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����03:36:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean addLxInfo(LstdlxwhForm model) throws Exception {
		boolean b = false;
		String sql;
		sql = " select count(1) num from xg_rcsw_lstd_lxwhb where lxmc=?  ";
		String num = dao.getOneRs(sql,new String[] {model.getLxmc()}, "num");
		if ("0".equals(num)) {
			sql = " insert into xg_rcsw_lstd_lxwhb(lxdm,lxmc) values(?,?)";
			b = dao.runUpdate(sql, new String[] {model.getLxdm(),model.getLxmc()});
		} else {
			// msg="����ɫͨ�����������Ѵ��ڣ�";
			throw new SystemException(MessageKey.RCSW_LSTD_LSTDMCCZ);
		}
	
		return b;
	}
	
	
	/**
	 * 
	 * @����:�޸�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����04:23:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateLxInfo(LstdlxwhForm model)
	throws Exception {
		boolean b = false;
		String sql1;
		String sql;
		sql1 = "select count(1) num from xg_rcsw_lstd_lxwhb where lxmc=? ";
		String num = dao.getOneRs(sql1,new String[] { model.getLxmc() }, "num");
		//����ͬ���������ƺ����ʹ���  ���������Ƿ��Ѵ���
		sql = "select lxdm  from xg_rcsw_lstd_lxwhb where lxdm = ? and lxmc=? ";
		String lxdm = dao.getOneRs(sql, new String[] { model.getLxdm(),model.getLxmc()},"lxdm");
		if ((lxdm.equals(model.getLxdm())) || "0".equals(num)) {
			sql = "update xg_rcsw_lstd_lxwhb set lxmc=? where lxdm=?";
			b = dao.runUpdate(sql, new String[] { model.getLxmc(),model.getLxdm()});
		} else {
			// msg="��ѧ��֤�������������Ѵ��ڣ�";
			throw new SystemException(MessageKey.RCSW_LSTD_LSTDMCCZ);
		}
		return b;
	}
	
	
	/**
	 * 
	 * @����:��ȡ������ʹ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����04:24:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	
	public String getMaxLxdm() {
		String sql = "select max(to_number(lxdm)) max from xg_rcsw_lstd_lxwhb ";
		return dao.getOneRs(sql, new String[]{}, "max");
	}
	
	
	/**
	 * 
	 * @����:������ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����04:27:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param lxdm
	 * @return
	 * @throws Exception
	 * LstdlxwhForm �������� 
	 * @throws
	 */
	public LstdlxwhForm getLxwhForm(LstdlxwhForm t, String lxdm)
		throws Exception {
	
		StringBuilder sql = new StringBuilder();
		sql.append("select a.* from xg_rcsw_lstd_lxwhb a  ");
		sql.append(" where lxdm = ? ");
		
		return this.getModel(t, sql.toString(), new String[] { lxdm });
		
	}
	
	

	/**
	 * 
	 * @����:ɾ�����ʹ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����04:31:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	
	
	public int deleteLxwhInfo(String values) throws Exception {
		int num = 0;
		String sql = "delete from xg_rcsw_lstd_lxwhb where lxdm in ( ? )";
		num = dao.runDelete(sql, new String[] {values});
		return num;
	}

	
	/**
	 * 
	 * @����:�ж��Ƿ��ѱ�����ͽ��Ӧ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����04:33:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] checkLxdmForsqjg(String values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.lxmc from xg_rcsw_lstd_lxwhb a left join  ");
		sql.append(" XG_RCSW_LSTD_SQB b on a.lxdm=b.lxdm left join XG_RCSW_LSTD_JGB c on a.lxdm=c.lxdm ");
		sql.append(" where a.lxdm  in(" + values + ")  and  ( b.lxdm is not null or  c.lxdm is not null ) ");
		String[] xszbblxmc = dao.getRs(sql.toString(), new String[] {},"lxmc");
		return xszbblxmc;
	}
	
	
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-20 ����12:33:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xszbblxdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDel(String lxdm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct a.lxmc from xg_rcsw_lstd_lxwhb a left join ");
		sql.append(" XG_RCSW_LSTD_SQB b on a.lxdm=b.lxdm ");
		sql.append(" left join XG_RCSW_LSTD_JGB c on a.lxdm=c.lxdm ");
		sql.append(" where a.lxdm  = ?   and  ( b.lxdm is not null or  c.lxdm is not null ) " );
		Map<String,String> map= dao.getMapNotOut(sql.toString(),new String[]{lxdm});
		String lxmc = map.get("lxmc");
		//���δ�ύ�ſ����ύ
		return lxmc==null?true:false;
	}
	
	
	public HashMap<String, String> getLxsq(String lxdm){
		StringBuffer sb=new StringBuffer();
		sb.append(" select a.lxmc from xg_rcsw_lstd_lxwhb a ");
		sb.append(" where  a.lxdm = ? ");
		return dao.getMapNotOut(sb.toString(),new String[]{lxdm});
	}

}
