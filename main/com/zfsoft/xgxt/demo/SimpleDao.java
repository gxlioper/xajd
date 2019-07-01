package com.zfsoft.xgxt.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * ����ɾ���顢��ʾ��DAO
 * @author qph
 * 213-4-8
 */
public class SimpleDao extends SuperDAOImpl<SimpleForm>{



	/**
	 * ��ͨ��ѯ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getPageList(SimpleForm model) throws Exception{
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,bjmc,");
		sql.append("xydm,zydm,bjdm from view_xsjbxx where 1=1 ");
		
		if (!StringUtil.isNull(model.getXh())){
			params.add(model.getXh());
			sql.append(" and xh like '%'||?||'%'");
		}
		
		if (!StringUtil.isNull(model.getXm())){
			params.add(model.getXm());
			sql.append(" and xm like '%'||?||'%'");
		}
		
		if (!StringUtil.isNull(model.getXydm())){
			params.add(model.getXydm());
			sql.append(" and xydm like '%'||?||'%'");
		}
		
		
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}
	
	
	/**
	 * ʹ�ø߼���ѯ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getPageList(SimpleForm model,User user) throws Exception{
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,bjdm from view_xsjbxx a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}


	
	
	
	protected void setTableInfo() {
		super.setTableName("xsxxb");
		super.setKey("xh");
		super.setClass(SimpleForm.class);
	}



}
