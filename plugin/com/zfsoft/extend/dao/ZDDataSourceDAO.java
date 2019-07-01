/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-5 ����05:01:41 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-5 ����05:01:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
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
