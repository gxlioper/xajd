/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-28 ����10:12:50 
 */  
package xsgzgl.gygl.gyjldmgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-4-28 ����10:12:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public  class ZjlyGyglDao extends SuperDAOImpl<GyjldmglForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyjldmglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyjldmglForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.GYJLLBDLDM,");
		sql.append(" t.GYJLLBDLMC,");
		sql.append(" decode(t.lb, 'jf', '�ӷ���', 'kf', '�۷���', t.lb) lb,");
		sql.append(" decode(t.lb, 'jf', '+' || t.fz, 'kf', '-' || t.fz, t.fz) fz");
		sql.append(" from xg_gygl_new_gyjllbdlb t");
		return getPageList(t, sql.toString(), new String[]{});
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setTableName("XG_GYGL_NEW_GYJLLBDLB");
		this.setClass(GyjldmglForm.class);
		this.setKey("gyjllbdldm");
	}
	
	//�����Ƿ����Ŀ��������Ŀ�����Ƿ����
	public boolean checkIsExists(GyjldmglForm t){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> parameterlist = new ArrayList<String>();
		sql.append("  select count(1) num");
		sql.append(" from XG_GYGL_NEW_GYJLLBDLB t");
		sql.append(" where (t.gyjllbdldm = ?");
		parameterlist.add(t.getGyjllbdldm());
		sql.append(" or t.gyjllbdlmc = ?)");
		parameterlist.add(t.getGyjllbdlmc());
		if(StringUtils.isNotNull(t.getGyjllbdldm())){
			sql.append(" and t.gyjllbdldm != ?");
			parameterlist.add(t.getGyjllbdldm());
		}
		
		String num = dao.getOneRs(sql.toString(),parameterlist.toArray(new String[]{}) , "num");
	    if(num.equals("0")){
	    	return false;
	    }else{
	    	return true;
	    }
	}
	
	//ɾ��ʱ��鱶ɾ������Ŀ�Ƿ���ʹ����
	public boolean checkIsUsingNow(String[]para){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from XG_GYGL_ZJLY_GYJCWHB where jcdm in");
		sql.append(" (");
		for (int i = 0; i < para.length; i++) {
			sql.append("?");
			if(i !=  para.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		String num = dao.getOneRs(sql.toString(), para, "num");
		if(num.equals("0")){
			return false;
		}else{
			return true;
		}
	}

}
