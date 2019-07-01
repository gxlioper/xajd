/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-22 ����01:38:21 
 */  
package com.zfsoft.xgxt.xpjpy.cpfwh;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-2-22 ����01:38:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CpfwhDao extends SuperDAOImpl<CpfwhForm>{
	
	@Override
	public List<HashMap<String, String>> getPageList(CpfwhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(CpfwhForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.*,t2.xm,t2.xb,t2.yhkh,t2.zzmmmc,t2.mzmc,t2.yhmc,t2.sfzh,t3.xymc,t3.xydm,t3.zydm,t3.zymc,t3.bjdm,t3.bjmc,t3.nj,t4.xqmc");
		sql.append(" from XG_PJPY_NEW_CPFS t1 left join view_xsxxb t2 on t1.xh = t2.xh");
		sql.append(" left join view_njxyzybj t3 on t2.bjdm = t3.bjdm");
		sql.append(" left join xqdzb t4 on t1.xq = t4.xqdm");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);		
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(CpfwhForm.class);
		super.setKey("id");
		super.setTableName("XG_PJPY_NEW_CPFS");
	}
	
	public boolean isHaveRecord(CpfwhForm form){
		String sql = "select count(1) num from XG_PJPY_NEW_CPFS where xh = ? and xn = ? and xq = ?";
		String num = dao.getOneRs(sql, new String[]{form.getXh(),form.getXn(),form.getXq()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	public String getXqmc(String xqdm){
		String sql = "select xqmc from xqdzb where xqdm = ?";
		return dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	
	public List<HashMap<String, String>> getXqList(){
		String sql = "select xqdm,xqmc from xqdzb";
		return dao.getList(sql, new String[]{}, new String[]{"xqdm","xqmc"});
	}
	
	public List<String> getBjdmForTeacher(User user){
		String userStatus = user.getUserStatus();
		String sql = null;
		if("bzr".equalsIgnoreCase(userStatus)){
			sql = "select bjdm from bzrbbb where zgh = ?";
		}else if("fdy".equalsIgnoreCase(userStatus)){
			sql = "select bjdm from fdybjb where zgh = ?";
		}else if("jd".equalsIgnoreCase(userStatus)){
			sql = "select select bjdm from bzrbbb where zgh = ? union all select bjdm from fdybjb where zgh = ?";
		}
		try {
			return dao.getList(sql, new String[]{user.getUserName()}, "bjdm");
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			return null;
		}
	}
	
	public String getBjdmForStu(String xh){
		String sql = "select bjdm from view_xsxxb where xh = ?";
		return dao.getOneRs(sql, new String[]{xh}, "bjdm");
	}
	
}
