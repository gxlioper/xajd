/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-29 ����09:56:07 
 */  
package com.zfsoft.xgxt.znxgl.sxbgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.znxgl.wdznx.WdznxForm;
import com.zfsoft.xgxt.znxgl.znxgl.ZnxglForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-8-29 ����09:56:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SxbDao extends SuperDAOImpl<SxbForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SxbForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SxbForm t, User user)
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
		super.setClass(SxbForm.class);
		super.setKey("xjbh");
		super.setTableName("XG_ZNX_NEW_SXB");
	}
	
    public boolean runUpate_sxbjl(SxbForm sx) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_ZNX_NEW_SXB set jsrydbj='1' where xjbh= ? and jsrbh = ?");
		return dao.runUpdate(sql.toString(), new String[]{sx.getXjbh(),sx.getJsrbh()});
		
	}
    
    public boolean runUpate_sxbscjl(SxbForm sx)throws Exception{
    	StringBuilder sql = new StringBuilder();
		sql.append(" update XG_ZNX_NEW_SXB set jsrscbj='1' where xjbh= ? and jsrbh = ?");
		return dao.runUpdate(sql.toString(), new String[]{sx.getXjbh(),sx.getJsrbh()});
	}
    
    /**
     * 
     * @����:����
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2015-12-7 ����03:19:04
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param sx
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
    public boolean save(SxbForm sx) throws Exception{
    	StringBuilder sql = new StringBuilder();
    	sql.append(" insert into XG_ZNX_NEW_SXB (xjbh,jsrbh,fprbh) values(?,?,?)");
    	return dao.runUpdate(sql.toString(), new String[]{sx.getXjbh(),sx.getJsrbh(),sx.getFprbh()});
    }
}
