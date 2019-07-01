package com.zfsoft.xgxt.szdw.thjl.zdgzxsk;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xtwh.yhsjfw.YhsjfwService;

/** 
 * �ص��עѧ����ά��
 */
public class SzdwZdgzxskDao extends SuperDAOImpl<SzdwZdgzxskForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(SzdwZdgzxskForm t)
			throws Exception {
		//  �Զ����ɷ������
		return null;
	}

	/**
	 * ��ѯ
	 */
	public List<HashMap<String, String>> getPageList(SzdwZdgzxskForm t, User user)
			throws Exception {
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		// ��ע�ȼ�����
		String searchTjByUserZdgzxsk = getSearchTjByUserZdgzxsk(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_view_szdw_thjl t where 1=1 ");
		sql.append(searchTjByUserZdgzxsk);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * ��ò�ѯ����(�û����)
	 */
	public static String getSearchTjByUserZdgzxsk(User user, String tableBm,
			String xydm, String bjdm) { 

		// �û���
		String userName = user.getUserName();
		// �û�����
		String userType = user.getUserType();
		// �û����ڲ���
		String userDep = user.getUserDep();
		// �û����
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		///String yhsjfwSql = new YhsjfwService().getYhsjfw(user, tableBm, xydm, bjdm);
		//if(yhsjfwSql != null && !yhsjfwSql.equals("")){
		//	query.append(yhsjfwSql);
		//	query.append(" and ");
		//}
		
		if ("xy".equalsIgnoreCase(userStatus)) {// �����û�ΪѧԺ
			// ����û���ѧԺ�����ҡ�ְ���ǡ���ǡ���ѧԺ��ǡ�������ʾѧԺ�ڵġ����ǡ�ѧ����
		//	if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
				query.append(xydm + " = '" + userDep + "' ");
				query.append(" and ");
		//	}
		
			query.append(" ( ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("gzdj= '����' ");
			query.append(" and ");
			query.append(" exists (select 1 from fdyxxb where zgh='" + userName + "' and zw in (select zwdm from zwb where (zwmc='���' or zwmc='ѧԺ���'))) ");
			query.append(" ) ");
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			// ����û��ǰ����λ򸨵�Ա������Σ�����ʾ����ġ����ǡ����ǡ����ǡ�ѧ����
			query.append(" exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "' ");
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("gzdj in ('����','����','����') ");
			query.append(" ) ");

		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			// ����û��Ǹ���Ա������ʾ����ġ����ǡ����ǡ�ѧ��
			query.append(" exists (select 1 from fdybjb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "' ");
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("gzdj in ('����','����') ");
			query.append(" ) ");

		} else if ("jd".equalsIgnoreCase(userStatus)) {// �����û�Ϊ����Ա�������
			// ����û��ǰ����λ򸨵�Ա������Σ�����ʾ����ġ����ǡ����ǡ����ǡ�ѧ����
			query.append(" (exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "' ");
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("gzdj in ('����','����','����') ");
			query.append(" ) ");
			
			query.append(" or exists (select 1 from fdybjb z where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  z.bjdm ");
			query.append(" and z.zgh = '" + userName + "' ");
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("gzdj in ('����','����') ");
			query.append(" ) ");
			query.append(" ) ");

		}else{// �����û�ΪѧУ������Ա��
			// ��ʾȫ����¼
			query.append(" ( ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("gzdj in ('����','����','����') ");
			query.append(" ) ");
		}

		query.append(" )");
		return query.toString();
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		
	}

}
