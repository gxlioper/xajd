/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-21 ����09:21:44 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm.bjdldm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������š�������ά��
 * @�๦������: �༶����ά�� 
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-11-21 ����09:21:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjdldmDao extends SuperDAOImpl<BjdldmForm> {

	
	/**
	 * ��ͨ��ѯ
	 */
	public List<HashMap<String, String>> getPageList(BjdldmForm t)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(" select lbdm, lbmc from xg_xtwh_bjdlb where 1=1 ");
		
		if(!StringUtil.isNull(t.getLbmc())){
			params.add(t.getLbmc());
			sql.append(" and lbmc like '%'||?||'%'");
		}
		
		return getPageList(t,sql.toString(),params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	public List<HashMap<String, String>> getPageList(BjdldmForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	protected void setTableInfo() {
		super.setTableName("xg_xtwh_bjdlb");
		super.setKey("lbdm");

	}
	
	/**
	 * 
	 * @����:����Ψһ����֤
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-11-21 ����11:04:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(BjdldmForm model) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_xtwh_bjdlb where lbmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getLbmc()}, "num");
		return num;
	}
	
	/**
	 * 
	 * @����:�жϽ���Ƿ��ѱ�ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-11-21 ����02:20:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] checkLbForBjdl( String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.lbmc from xg_xtwh_bjlbb a, xg_xtwh_bjdlb b where a.lbdm=b.lbdm and a.lbdm in (" +value +")");
		String[] dcmc=dao.getRs(sql.toString(), new String[]{}, "lbmc");
		return dcmc;
	}

}
