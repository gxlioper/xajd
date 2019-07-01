/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-4-20 ����03:15:22 
 */  
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxdmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� lgx[����:1553]
 * @ʱ�䣺 2018-4-20 ����03:15:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JqfxDmwhDao extends SuperDAOImpl<JqfxDmwhForm> {


	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("XG_RCSW_JQFXGL_DMWHB");
		super.setKey("fxdm");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */	
	@Override
	public List<HashMap<String, String>> getPageList(JqfxDmwhForm model)
			throws Exception {
		// TODO �Զ����ɷ������
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer("select * from (select a.*,rownum r from XG_RCSW_JQFXGL_DMWHB a order by a.fxdm) where 1=1 ");
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
		
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */	
	@Override
	public List<HashMap<String, String>> getPageList(JqfxDmwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	
	/**
	 * 
	 * @����:TODO(���ӷ�У�������ά��)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ� 2018-4-20 ����03:15:22 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean addFxgldmInfo(JqfxDmwhForm model)
	throws Exception {
		boolean b = false;
		String sql;
		//����ҵ�����ӵ�ʱ�����ж��Ƿ����д���ά���������ܰ��ʾ��Ϣ
		sql = "select count(1) num from XG_RCSW_JQFXGL_DMWHB where FXDM=? or FXMC= ? ";
		String num = dao.getOneRs(sql,
				new String[] { model.getFxdm(),model.getFxmc()}, "num");
		if ("0".equals(num)) {
			sql = "insert into XG_RCSW_JQFXGL_DMWHB(FXDM,FXMC) values(?,?)";
			b = dao.runUpdate(sql, new String[] { model.getFxdm(),
					model.getFxmc()});
		} else {
			// msg="��У���������������Ѵ��ڣ�";
			throw new SystemException(MessageKey.JQFXGL_DMWH_REPEAT);
		}
	
		return b;
	}
	
	
	/**
	 * 
	 * @����:TODO(���·�У����ά��)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ� 2018-4-20 ����03:15:22 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateFxgldmInfo(JqfxDmwhForm model)
	throws Exception {
		boolean b = false;
		String sql;
		//����ҵ����µ�ʱ�����ж��Ƿ����д���ά���������ܰ��ʾ��Ϣ
		sql = "select count(1) num from XG_RCSW_JQFXGL_DMWHB where FXMC= ? ";
		String num = dao.getOneRs(sql,
				new String[] { model.getFxmc() }, "num");
		
		if ( "0".equals(num)||"1".equals(num)) {
			sql = "update XG_RCSW_JQFXGL_DMWHB set FXMC=? where FXDM=?";
			b = dao.runUpdate(sql,
					new String[] {model.getFxmc(),model.getFxdm()});
		} else {
			// msg="�ô�������Ѵ��ڣ�";
			throw new SystemException(MessageKey.JQFXGL_DMWH_REPEAT);
		}		
		return b;
	}
	
	/**
	 * 
	 * @����:TODO(ɾ����У����ά��)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ� 2018-4-20 ����03:15:22 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int deleteFxgldmInfo(String values) throws Exception {
		int num = 0;
		String sql = "delete from XG_RCSW_JQFXGL_DMWHB where fxdm in ("
				+ values + ")";
		num = dao.runDelete(sql, new String[] {});
		return num;
	}
	
	/**
	 * 
	 * @����:TODO(�ж�ֻ��������ڽ��ά�����е��Ƿ��Ѿ�Ӧ�øô���ά��)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ� 2018-4-20 ����03:15:22 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fxdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDel(String fxdm){		
		StringBuffer sql = new StringBuffer();		
		sql.append(" select * from XG_RCSW_JQFXGL_DMWHB a left join xg_rcsw_jqfxgl_jgb b ");
		sql.append(" on a.fxdm = b.fxdm  where  b.fxdm = ? ");
		Map<String,String> map= dao.getMapNotOut(sql.toString(),new String[]{fxdm});
		String fxmc = map.get("fxmc");
		//ֻ�з�У����ά���������δӦ�òſ���ɾ��
		return fxmc==null?true:false;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ� 2018-4-20 ����03:15:22 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xszbblxdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getFxmc(String fxdm){
		StringBuffer sb=new StringBuffer();
		sb.append(" select a.fxmc from XG_RCSW_JQFXGL_DMWHB a ");
		sb.append(" where  a.fxdm = ? ");
		return dao.getMapNotOut(sb.toString(),new String[]{fxdm});
	}		

}

