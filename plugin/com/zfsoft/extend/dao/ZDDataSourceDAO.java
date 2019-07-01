/**
 * @部门:学工产品事业部
 * @日期：2015-6-5 下午05:01:41 
 */  
package com.zfsoft.extend.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;

import com.zfsoft.extend.service.DBQueryDescriptor;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-5 下午05:01:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

@SuppressWarnings("unchecked")
public class ZDDataSourceDAO extends SuperDAOImpl {

	public List<HashMap<String,String>> obtainDataSource(DBQueryDescriptor descriptor){
		if(descriptor == null ){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		String tableName = descriptor.getTableName();
		String[] fields = descriptor.getFields();
		if(StringUtils.isBlank(tableName)){
			return null;
		}
		if(fields == null || fields.length == 0){
			sb.append("select dm , mc from ").append(tableName).append(" order by mc");
		}else{
			sb.append("select ").
				append(fields[0]).
				append(" as dm, ").
				append(fields[1]).
				append(" as mc from ").
				append(tableName).
				append(" order by ").
				append(fields[2]);
		}
		return dao.getListNotOut(sb.toString(), new String[]{});
	}
	
	
	@Override
	public List getPageList(Object t) throws Exception {
		
		return null;
	}
	@Override
	public List getPageList(Object t, User user) throws Exception {
		
		return null;
	}
	@Override
	protected void setTableInfo() {
		
	}

}
