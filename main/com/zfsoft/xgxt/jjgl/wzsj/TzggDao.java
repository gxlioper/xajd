/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-11 ����11:20:46 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-9-11 ����11:20:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TzggDao extends SuperDAOImpl<TzggForm> {
	
	/*
	 * ���� Javadoc��
	 * @see xsgzgl.comm.dao.SuperDAO#runInsert(java.lang.Object)
	 */
	@Override
	public boolean runInsert(TzggForm t) throws Exception{
		//String sql = "insert into XSGGFW_JJFW_TZGG (SID , TITLE , CONTENTS , PUBLISHDATE , PRIORITY , SFFB , ZGH) values (?,?,?,?,?,?,?)";
		return dao.runInsert(getTableName(), 
				new String[]{"SID" , "TITLE" , "CONTENTS" , "PUBLISHDATE" , "PRIORITY" , "SFFB" , "ZGH"}, 
				new String[]{t.getSid() , 
							 t.getTitle() , 
							 t.getContents() , 
							 t.getPublishdate() , 
							 t.getPriority() , 
							 t.getSffb() , 
							 t.getZgh()});
	};
	

	/*
	 * ���� Javadoc��
	 * @see xsgzgl.comm.dao.SuperDAO#runUpdate(java.lang.Object)
	 */
	@Override
	public boolean runUpdate(TzggForm t) throws Exception{
		String sql = "update XSGGFW_JJFW_TZGG set TITLE = ? , CONTENTS = ? , PUBLISHDATE = ? , PRIORITY = ? , SFFB = ? , ZGH = ? where SID = ?";
		return dao.runUpdate(sql, new String[]{ t.getTitle() , 
												 t.getContents() , 
												 t.getPublishdate() , 
												 t.getPriority() , 
												 t.getSffb() , 
												 t.getZgh() ,
												 t.getSid()});
	};
	

	@Override
	public List<HashMap<String, String>> getPageList(TzggForm t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		if(StringUtils.equals("1", t.getType())){
			sql.append("select t1.* , t2.xm yhm ,decode(t1.priority , '0' , '��ͨ' , '1' , '�ö�' , '') prioritymc , decode(t1.sffb , '1' , '��' , '0' , '��' , '') sffbmc  from XSGGFW_JJFW_TZGG t1 left join yhb t2 on t1.zgh = t2.yhm where t1.sffb = '0' order by t1.priority desc , t1.publishdate desc ");
		}else if(StringUtils.equals("0", t.getType())){
			sql.append("select t1.* , t2.xm yhm ,decode(t1.priority , '0' , '��ͨ' , '1' , '�ö�' , '') prioritymc , decode(t1.sffb , '1' , '��' , '0' , '��' , '') sffbmc  from XSGGFW_JJFW_TZGG t1 left join yhb t2 on t1.zgh = t2.yhm where t1.sffb = '1' order by t1.priority desc , t1.publishdate desc  ");
		}
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));

	}

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�zxb[���ţ�1036]
	 * @���ڣ�2014-9-12 ����02:19:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getModelMap(TzggForm t) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* , t2.xm yhm ,decode(t1.priority , '0' , '��ͨ' , '1' , '�ö�' , '') prioritymc , decode(t1.sffb , '1' , '��' , '0' , '��' , '') sffbmc  from XSGGFW_JJFW_TZGG t1  left join yhb t2 on t1.zgh = t2.yhm where t1.sid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSid()});
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TzggForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(TzggForm.class);
		super.setKey("sid");
		super.setTableName("XSGGFW_JJFW_TZGG");
	}

}
