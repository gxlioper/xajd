/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-19 ����10:14:34 
 */  
package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxdmwh;

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
 * @ģ������: ���ڷ�У����ά������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� Dulq[����:995]
 * @ʱ�䣺 2016-2-19 ����10:14:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public  class JqfxdmwhDao extends SuperDAOImpl<JqfxdmwhForm> {


	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("XG_GYGL_NEW_FXGL_DMWHB");
		super.setKey("fxdm");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */	
	@Override
	public List<HashMap<String, String>> getPageList(JqfxdmwhForm model)
			throws Exception {
		// TODO �Զ����ɷ������
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer("select * from (select a.*,rownum r from XG_GYGL_NEW_FXGL_DMWHB a order by a.fxdm) where 1=1 ");
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
		
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */	
	@Override
	public List<HashMap<String, String>> getPageList(JqfxdmwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	
	/**
	 * 
	 * @����:TODO(���ӷ�У�������ά��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-2-24 ����04:22:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean addFxgldmInfo(JqfxdmwhForm model)
	throws Exception {
		boolean b = false;
		String sql;
		//����ҵ�����ӵ�ʱ�����ж��Ƿ����д���ά���������ܰ��ʾ��Ϣ
		sql = "select count(1) num from XG_GYGL_NEW_FXGL_DMWHB where FXDM=? or FXMC= ? ";
		String num = dao.getOneRs(sql,
				new String[] { model.getFxdm(),model.getFxmc()}, "num");
		if ("0".equals(num)) {
			sql = "insert into XG_GYGL_NEW_FXGL_DMWHB(FXDM,FXMC) values(?,?)";
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
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-2-24 ����04:22:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateFxgldmInfo(JqfxdmwhForm model)
	throws Exception {
		boolean b = false;
		String sql;
		//����ҵ����µ�ʱ�����ж��Ƿ����д���ά���������ܰ��ʾ��Ϣ
		sql = "select count(1) num from XG_GYGL_NEW_FXGL_DMWHB where FXMC= ? ";
		String num = dao.getOneRs(sql,
				new String[] { model.getFxmc() }, "num");
		
		if ( "0".equals(num)||"1".equals(num)) {
			sql = "update XG_GYGL_NEW_FXGL_DMWHB set FXMC=? where FXDM=?";
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
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-2-24 ����04:20:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int deleteFxgldmInfo(String values) throws Exception {
		int num = 0;
		String sql = "delete from XG_GYGL_NEW_FXGL_DMWHB where fxdm in ("
				+ values + ")";
		num = dao.runDelete(sql, new String[] {});
		return num;
	}
	
	/**
	 * 
	 * @����:TODO(�ж�ֻ��������ڽ��ά�����е��Ƿ��Ѿ�Ӧ�øô���ά��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-16 ����10:43:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fxdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDel(String fxdm){		
		StringBuffer sql = new StringBuffer();		
		sql.append(" select * from xg_gygl_new_fxgl_dmwhb a left join xg_gygl_new_fxgl_jgb b ");
		sql.append(" on a.fxdm = b.fxdm  where  b.fxdm = ? ");
		Map<String,String> map= dao.getMapNotOut(sql.toString(),new String[]{fxdm});
		String fxmc = map.get("fxmc");
		//ֻ�з�У����ά���������δӦ�òſ���ɾ��
		return fxmc==null?true:false;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-16 ����10:54:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xszbblxdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getFxmc(String fxdm){
		StringBuffer sb=new StringBuffer();
		sb.append(" select a.fxmc from xg_gygl_new_fxgl_dmwhb a ");
		sb.append(" where  a.fxdm = ? ");
		return dao.getMapNotOut(sb.toString(),new String[]{fxdm});
	}		

}
