/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-29 ����09:57:55 
 */  
package com.zfsoft.xgxt.znxgl.fxbgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.znxgl.sxbgl.SxbForm;
import com.zfsoft.xgxt.znxgl.znxgl.ZnxglForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-8-29 ����09:57:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FxbDao extends SuperDAOImpl<FxbForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FxbForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FxbForm t, User user)
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
		super.setClass(FxbForm.class);
		super.setKey("xjbh");
		super.setTableName("xg_znx_new_fxb");
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
    public boolean save(FxbForm t) throws Exception{
    	StringBuilder sql = new StringBuilder();
    	sql.append(" insert into xg_znx_new_fxb (xjbh,fsrbh,fssj,fsnr,ztlb,xjzt) values(?,?,?,?,?,?)");
    	return dao.runUpdate(sql.toString(), new String[]{t.getXjbh(),t.getFsrbh(),t.getFssj(),t.getFsnr(),t.getZtlb(),t.getXjzt()});
    } 

}
