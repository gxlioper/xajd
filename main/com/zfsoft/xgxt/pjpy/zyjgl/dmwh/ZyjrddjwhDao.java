/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-12-21 ����01:27:18 
 */  
package com.zfsoft.xgxt.pjpy.zyjgl.dmwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-רҵ������-����ά��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:982]
 * @ʱ�䣺 2015-12-21 ����01:27:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyjrddjwhDao extends SuperDAOImpl<ZyjrddjwhForm>{
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyjrddjwhForm t)
			throws Exception {
		StringBuilder sql = new StringBuilder("select * from xg_pjpy_zyjrddjdmb where 1=1");
		if(!StringUtil.isNull(t.getRddjmc())) {
			sql.append(" and rddjmc like '%'||?||'%' ");
			return getPageList(t, sql.toString(), new String[]{t.getRddjmc()});
		}
		return getPageList(t, sql.toString(), new String[]{});
	}

	@Override
	public List<HashMap<String, String>> getPageList(ZyjrddjwhForm t, User user)
			throws Exception {
		return null;
	}
	
	
	
	public boolean checkIsExist(ZyjrddjwhForm form) {
		String sql="select rddjmc from xg_pjpy_zyjrddjdmb where rddjmc=?";
		String mc = dao.getOneRs(sql, new String[]{form.getRddjmc()}, "rddjmc");
		return mc!=null && !mc.equals("")?true:false;
	}
	
	public boolean checkDjdmExist(String[] ids) {
		Boolean flag = false;
		String sql = "select count(1) num from xg_pjpy_grzyjxx where rddjdm = ? ";
		for(int i = 0;i<ids.length;i++) {
			String num = dao.getOneRs(sql, new String []{ids[i]}, "num");
			if(num != null && !num.equals("0")) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	protected void setTableInfo() {
		this.setKey("rddjdm");
		this.setTableName("xg_pjpy_zyjrddjdmb");
		this.setClass(ZyjrddjwhForm.class);
		
	}
	
}
